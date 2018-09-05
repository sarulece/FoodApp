package com.stackroute.foodieapp.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.foodieapp.controller.RestaurantController;
import com.stackroute.foodieapp.helper.RestaurantData;
import com.stackroute.foodieapp.model.Restaurant;
import com.stackroute.foodieapp.service.RestaurantService;
@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {

	@MockBean
	private RestaurantService restaurantService;
	
	private Restaurant restaurant;
	
	@Autowired
	private MockMvc mvc;
	
	private List<Restaurant> restaurants;
	
	private RestaurantData restaurantData;
	
	private List<RestaurantData> restaurantDataList;
	
	
	@Before
	public void setup()
	{
		restaurants = new ArrayList<>();
		restaurant = new Restaurant("1003", "Sangeetha", "Velachery, Chennai", "chennai", "5",
				"chinese, south indian", 300L, "Rs.", "4.5", "390", "comment", "www.abc.com", true);
		restaurants.add(restaurant);
		restaurant = new Restaurant("1004", "Sangeetha", "Velachery, Chennai", "chennai", "5",
				"chinese, south indian", 300L, "Rs.", "4.5", "390", "comment", "www.abc.com", true);
		restaurants.add(restaurant);	
		
		restaurantDataList = new ArrayList<>();
		restaurantData = new RestaurantData(null,"1003", "Sangeetha", "Velachery, Chennai", "chennai", "5",
				"chinese, south indian", 300L, "Rs.", "4.5", "390", "comment", "www.abc.com", true);
		restaurantDataList.add(restaurantData);
		restaurantData = new RestaurantData(null,"1004", "Sangeetha", "Velachery, Chennai", "chennai", "5",
				"chinese, south indian", 300L, "Rs.", "4.5", "390", "comment", "www.abc.com", true);
		restaurantDataList.add(restaurantData);
	}
	
	@Test
	public void testSaveNewRestaurant() throws Exception
	{
		when(restaurantService.saveNewRestaurant(restaurant)).thenReturn(restaurant);
		mvc.perform(post("/api/restaurants/favourite_restaurants").contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(restaurant))).andExpect(status().isCreated());
		verify(restaurantService, times(1)).saveNewRestaurant(Mockito.any(Restaurant.class));
		verifyNoMoreInteractions(restaurantService);
		
	}
	
	@Test
	public void testUpdateRestaurant() throws Exception
	{
		restaurant.setComments("new comments");
		when(restaurantService.updateRestaurant(restaurant)).thenReturn(restaurant);
		mvc.perform(put("/api/restaurants/favourite_restaurants/{id}", 2).contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(restaurant))).andExpect(status().isOk());
		verify(restaurantService, times(1)).updateRestaurant(Mockito.any(Restaurant.class));
		verifyNoMoreInteractions(restaurantService);
		
	} 
	
	@Test
	public void testDeleteRestaurant() throws Exception
	{
		when(restaurantService.deleteRestaurant(restaurant.getId())).thenReturn(true);
		mvc.perform(delete("/api/restaurants/favourite_restaurants/{id}", 2L).contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(restaurant))).andExpect(status().isOk());
		verify(restaurantService, times(1)).deleteRestaurant(2L);
		verifyNoMoreInteractions(restaurantService);
		
	}
	
	
	@Test
	public void testGetFavouriteRestaurants() throws Exception
	{
		when(restaurantService.getFavouriteRestaurants()).thenReturn(restaurants);
		mvc.perform(get("/api/restaurants/favourite_restaurants")
				.content(jsonToString(restaurant))).andExpect(status().isOk());
		verify(restaurantService, times(1)).getFavouriteRestaurants();
		verifyNoMoreInteractions(restaurantService);
		
	}
	
	@Test
	public void testGetRestaurants() throws Exception
	{
		when(restaurantService.getRestaurants()).thenReturn(restaurantDataList);
		mvc.perform(get("/api/restaurants")).andExpect(status().isOk());
		verify(restaurantService, times(1)).getRestaurants();
		verifyNoMoreInteractions(restaurantService);
		
	}
	
	@Test
	public void testGetRestaurantsByLocationAndCuisine() throws Exception
	{
		when(restaurantService.getRestaurantsByLocationAndCuisine("Bangalore", "chinese")).thenReturn(restaurantDataList);
		mvc.perform(get("/api/restaurants/city/{city}/cuisine/{cuisine}", "Bangalore", "chinese")).andExpect(status().isOk());
		verify(restaurantService, times(1)).getRestaurantsByLocationAndCuisine("Bangalore","chinese");
		verifyNoMoreInteractions(restaurantService);
		
	}
	
	private static String jsonToString(final Object obj)
	{
		String jsonStr = null;
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			jsonStr = mapper.writeValueAsString(obj);
		}catch(JsonProcessingException e)
		{
			jsonStr = "json processing error";
		}
		return jsonStr;
	}
}
