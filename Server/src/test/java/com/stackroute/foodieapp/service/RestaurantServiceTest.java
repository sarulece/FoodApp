package com.stackroute.foodieapp.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.stackroute.foodieapp.exceptions.RestaurantAlreadyExistsException;
import com.stackroute.foodieapp.exceptions.RestaurantNotFoundException;
import com.stackroute.foodieapp.helper.Constants;
import com.stackroute.foodieapp.helper.Cuisine;
import com.stackroute.foodieapp.helper.CuisineData;
import com.stackroute.foodieapp.helper.CuisineResponse;
import com.stackroute.foodieapp.helper.LocationResponse;
import com.stackroute.foodieapp.helper.LocationSuggestion;
import com.stackroute.foodieapp.helper.RestaurantData;
import com.stackroute.foodieapp.model.Restaurant;
import com.stackroute.foodieapp.repository.RestaurantRepository;
import com.stackroute.foodieapp.service.RestaurantService;

public class RestaurantServiceTest {

	@Mock
	private RestaurantRepository restaurantRepo;
	
	@InjectMocks
	private RestaurantService restaurantService;
	
	private Restaurant restaurant;
	
	private Optional<Restaurant> options;
	
	private RestaurantData restaurantData;
	@Mock
	private RestTemplate restTemplate;
	
	@Before
	public void setupMock()
	{
		MockitoAnnotations.initMocks(this);
		restaurant = new Restaurant("1003", "Sangeetha", "Velachery, Chennai", "chennai", "5",
				"chinese, south indian", 300L, "Rs.", "4.5", "390", "comment", "www.abc.com", true);
		restaurant.setId(1L);
		options = Optional.of(restaurant);
		restaurantData = new RestaurantData(null,"1002", "Sangeetha", "Velachery, Chennai", "chennai", "5",
				"chinese, south indian", 300L, "Rs.", "4.5", "390", "comment", "www.abc.com", false);
	}
	
	@Test
	public void testMockCreations()
	{
		assertNotNull("RestaurantRepo creation fails", restaurantRepo);
	}
	
	@Test
	public void testSaveRestaurantSuccess() throws RestaurantAlreadyExistsException
	{
		when(restaurantRepo.save(restaurant)).thenReturn(restaurant);
		when(restaurantRepo.findByResId(restaurant.getResId())).thenReturn(null);
		final Restaurant rest = restaurantService.saveNewRestaurant(restaurant);
		assertNotNull("saveRestaurant failed", rest);
		verify(restaurantRepo, times(1)).save(restaurant);
		verify(restaurantRepo, times(1)).findByResId(restaurant.getResId());
	}
	
	@Test(expected=RestaurantAlreadyExistsException.class)
	public void testSaveRestaurantFailure() throws RestaurantAlreadyExistsException
	{
		when(restaurantRepo.findByResId(restaurant.getResId())).thenReturn(restaurant);
		final Restaurant rest = restaurantService.saveNewRestaurant(restaurant);
		assertNull("saveRestaurant succeded", rest);
		verify(restaurantRepo, times(1)).findByResId(restaurant.getResId());
	}
	
	@Test 
	public void testUpdateRestaurant() throws RestaurantNotFoundException
	{
		when(restaurantRepo.findById(1L)).thenReturn(options);
		when(restaurantRepo.save(restaurant)).thenReturn(restaurant);
		restaurant.setComments("good restaurant");
		final Restaurant restaurant1 = restaurantService.updateRestaurant(restaurant);
		assertEquals("update failed", "good restaurant", restaurant1.getComments());
		verify(restaurantRepo, times(1)).save(restaurant);
		verify(restaurantRepo, times(1)).findById(restaurant.getId());
	}
	
	@Test
	public void testDeleteRestaurant() throws RestaurantNotFoundException
	{
		when(restaurantRepo.findById(1L)).thenReturn(options);
		doNothing().when(restaurantRepo).delete(restaurant);
		final boolean flag = restaurantService.deleteRestaurant(1L);
		assertTrue("delete restaurant failed", flag);
		verify(restaurantRepo, times(1)).delete(restaurant);
		verify(restaurantRepo, times(1)).findById(1L);
	}
	
	
	@Test
	public void testGetFavouriteRestaurants()
	{
		final List<Restaurant> list = new ArrayList<Restaurant>(1);
		when(restaurantRepo.findAll()).thenReturn(list);
		final List<Restaurant> list1 = restaurantService.getFavouriteRestaurants();
		assertEquals(list, list1);
		verify(restaurantRepo, times(1)).findAll();
		
	}
	
	@Test
	public void testGetRestaurants() throws Exception
	{
		LocationResponse locationRes = new LocationResponse();
		LocationSuggestion location = new LocationSuggestion();
		location.setCityId(4);
		location.setCityName("Bangalore");
		location.setLatitude(1.5);
		location.setLongitude(2.5);
		locationRes.setLocationSuggestions(new ArrayList<LocationSuggestion>());
		locationRes.getLocationSuggestions().add(location);
		locationRes.setStatus("success");
		
		CuisineResponse cuisineRes = new CuisineResponse();
		cuisineRes.setCuisines(new ArrayList<Cuisine>());
		Cuisine cuisine = new Cuisine();
		CuisineData cuisineData = new CuisineData();
		cuisineData.setCuisineId(25);
		cuisineData.setCuisineName("chinese");
		cuisine.setCuisine(cuisineData);
		cuisineRes.getCuisines().add(cuisine);
		Map<String, String> uriVars = new HashMap<String, String>();
		uriVars.put("query", "Bangalore");
		when(restTemplate.getForObject(Constants.LOCATION_URL, LocationResponse.class, uriVars)).thenReturn(locationRes);
		uriVars = new HashMap<String, String>();
		uriVars.put("city_id", "4");
		uriVars.put("lat", "1.5");
		uriVars.put("lon", "2.5");
		when(restTemplate.getForObject(Constants.CUISINE_URL, CuisineResponse.class, uriVars)).thenReturn(cuisineRes);
		when(restaurantRepo.findAll()).thenReturn(null);
		uriVars = new HashMap<>();
		uriVars.put("entity_id", "4");
		uriVars.put("entity_type", "city");
		uriVars.put("cuisines", "25");
		BufferedReader br = new BufferedReader(new FileReader("src/test/java/city_search_bangalore.txt"));
		StringBuilder sb = new StringBuilder();
		String str;
		while((str = br.readLine()) != null)
		{
			sb.append(str);
		}
		br.close();
		ResponseEntity<String> resEntity = new ResponseEntity<String>(sb.toString(), HttpStatus.OK);
		when(restTemplate.getForEntity(Constants.SEARCH_URL, String.class, uriVars)).thenReturn(resEntity);
		
		
		List<RestaurantData> list = restaurantService.getRestaurantsByLocationAndCuisine("Bangalore", "chinese");
		assertEquals("getRestaurantByLocationAndCusine failed", 9, list.size());
	}
}
