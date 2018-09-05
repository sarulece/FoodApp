package com.stackroute.foodieapp.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.foodieapp.exceptions.InvalidCuisineException;
import com.stackroute.foodieapp.exceptions.InvalidLocationException;
import com.stackroute.foodieapp.exceptions.RestaurantAlreadyExistsException;
import com.stackroute.foodieapp.exceptions.RestaurantNotFoundException;
import com.stackroute.foodieapp.helper.Constants;
import com.stackroute.foodieapp.helper.Cuisine;
import com.stackroute.foodieapp.helper.CuisineResponse;
import com.stackroute.foodieapp.helper.HeaderRequestInterceptor;
import com.stackroute.foodieapp.helper.LocationResponse;
import com.stackroute.foodieapp.helper.LocationSuggestion;
import com.stackroute.foodieapp.helper.RestaurantData;
import com.stackroute.foodieapp.model.Restaurant;
import com.stackroute.foodieapp.repository.RestaurantRepository;

@Service
public class RestaurantService implements RestaurantServiceIf{
	private static Logger log = LoggerFactory.getLogger(RestaurantService.class);
	
	private RestTemplate restTemplate;
	
	@Autowired
	private RestaurantRepository repo;
	
	@Override
	public List<RestaurantData> getRestaurants() throws Exception
	{		
		return this.getRestaurantsByLocationAndCuisine(null, null);
	}
	
	@Override
	public List<RestaurantData> getRestaurantsByLocationAndCuisine(String locationName, String cuisine) throws Exception
	{
		log.info("getRestaurants - Start");
		String cityId = "";
		String cuisineId = "";
		LocationSuggestion location = null;
		String searchUrl = Constants.SEARCH_URL;
		if(locationName != null && !locationName.isEmpty())
		{
			location = getLocation(locationName);
			cityId = location.getCityId().toString();
		}
		
		if(location != null && cuisine != null && !cuisine.isEmpty())
		{
			cuisineId = getCuisineId(cuisine, cityId, location.getLatitude().toString()
					, location.getLongitude().toString());
			
		}
		log.info("getRestaurants- City: " + cityId + " Cuisine " + cuisineId);
		Map<String, String> uriVars = new HashMap<>();
		uriVars.put("entity_id", cityId);
		uriVars.put("entity_type", "city");
		uriVars.put("cuisines", cuisineId);
		//ResponseEntity<String> response = restTemplate.getForEntity("https://developers.zomato.com/api/v2.1/search?entity_id=4&entity_type=city&cuisines=25", String.class);
		ResponseEntity<String> response = restTemplate.getForEntity(searchUrl, String.class, uriVars);
		log.info("Response: " + response.getBody());
		ObjectMapper objMapper = new ObjectMapper();
		//RetaurantsResultData result = objMapper.readValue(res.getBody(), RetaurantsResultData.class);
		JsonNode node = objMapper.readTree(response.getBody());
		JsonNode restaurants = node.get("restaurants");
		List<RestaurantData> resList = new ArrayList<RestaurantData>();
		Iterator<JsonNode> array = restaurants.elements();
		while(array.hasNext())
		{
			JsonNode restaurant = array.next().get("restaurant");
			RestaurantData res = objMapper.treeToValue(restaurant, RestaurantData.class);
			resList.add(res);
		}
		this.setFavourite(resList);
		
		return resList;
	}
	
	@PostConstruct
	public void init()
	{
		restTemplate = new RestTemplate();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("user-key", Constants.USER_KEY);
		headers.put("Accept", MediaType.APPLICATION_JSON_VALUE);
		HeaderRequestInterceptor obj = new HeaderRequestInterceptor(headers);
		List<ClientHttpRequestInterceptor> interceptorList = Arrays.asList(obj);
		restTemplate.setInterceptors(interceptorList);
	}
	
	private LocationSuggestion getLocation(String locationName) throws Exception
	{
		log.info("getLocation - locationName: " + locationName);
		LocationSuggestion location = null;
		String locationUrl = Constants.LOCATION_URL;
		Map<String, String> uriVars = new HashMap<String, String>();
		uriVars.put("query", locationName);
		LocationResponse locationRes = restTemplate.getForObject(locationUrl, LocationResponse.class, uriVars);
		if(locationRes.getStatus().equalsIgnoreCase("success"))
		{
			if(locationRes.getLocationSuggestions() != null)
			{
				location = locationRes.getLocationSuggestions().get(0);
				log.info("getLocation - Location Found: " + location.getCityName());
			}
		}else
		{
			throw new InvalidLocationException("Invalid Location Name.");
		}
		return location;
	}
	
	private String getCuisineId(String cuisineName, String cityId, String latitude, String longitude) throws InvalidCuisineException
	{
		log.info("getCuisineId - cuisine: " + cuisineName);
		log.info("getCuisineId - cityId: " + cityId);
		log.info("getCuisineId - latitude: " + latitude);
		log.info("getCuisineId - longitude: " + longitude);
		String cuisineId = null;
		String cuisinenUrl = Constants.CUISINE_URL;
		Map<String, String> uriVars = new HashMap<String, String>();
		uriVars.put("city_id", cityId);
		uriVars.put("lat", latitude);
		uriVars.put("lon", longitude);
		//CuisineResponse cuisineRes = restTemplate.getForObject("https://developers.zomato.com/api/v2.1/cuisines?city_id=4&lat=12.971606&lon=77.594376", CuisineResponse.class);
		CuisineResponse cuisineRes = restTemplate.getForObject(cuisinenUrl, CuisineResponse.class, uriVars);
		if(cuisineRes.getCuisines() != null)
		{
			for(Cuisine cuisine : cuisineRes.getCuisines())
			{
				if(cuisineName.equalsIgnoreCase(cuisine.getCuisine().getCuisineName()))
				{
					cuisineId = cuisine.getCuisine().getCuisineId().toString();
					log.info("getCuisineId - Cuisine Matched");
				}
			}
		}
		/*if(cuisineId == null)
		{
			throw new InvalidCuisineException("Invalid Cuisine Name");
		}*/
		return cuisineId;
	}

	@Override
	public Restaurant saveNewRestaurant(Restaurant restaurant) throws RestaurantAlreadyExistsException {
		Restaurant obj = repo.findByResId(restaurant.getResId());
		if(obj != null)
		{
			throw new RestaurantAlreadyExistsException("Restaurant Already Exists in Favourite List.");
		}
		obj = repo.save(restaurant);
		return obj;
	}

	@Override
	public Restaurant updateRestaurant(Restaurant updateRestaurant) throws RestaurantNotFoundException {
		Optional<Restaurant> options = repo.findById(updateRestaurant.getId());
		if(!options.isPresent())
		{
			throw new RestaurantNotFoundException("Restaurant Not Found");
		}
		Restaurant obj = options.get();
		obj.setComments(updateRestaurant.getComments());
		repo.save(obj);	 
		return obj;
	}

	@Override
	public boolean deleteRestaurant(Long id) throws RestaurantNotFoundException {
		Optional<Restaurant> options = repo.findById(id);
		if(!options.isPresent())
		{
			throw new RestaurantNotFoundException("Restaurant Not Found");
		}
		repo.delete(options.get());
		return true;
	}

	@Override
	public List<Restaurant> getFavouriteRestaurants() {
		return repo.findAll();
	}
	
	private void setFavourite(List<RestaurantData> restaurants)
	{
		Map<String, Restaurant> map = new HashMap<>();
		List<Restaurant> list = repo.findAll();
		if(list != null)
		{
			for(Restaurant r: list)
			{
				map.put(r.getResId(), r);
			}
		}
		
		if(restaurants != null)
		{
			for(RestaurantData restaurant : restaurants)
			{
				if(map.containsKey(restaurant.getResId())){
					Restaurant favouriteRestaurant = map.get(restaurant.getResId());
					restaurant.setFavourite(true);
					restaurant.setId(favouriteRestaurant.getId());
					restaurant.setComments(favouriteRestaurant.getComments());
				}			
			}
		}
	}

}
