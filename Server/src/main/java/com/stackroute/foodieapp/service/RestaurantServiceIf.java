package com.stackroute.foodieapp.service;

import java.util.List;

import com.stackroute.foodieapp.exceptions.RestaurantAlreadyExistsException;
import com.stackroute.foodieapp.exceptions.RestaurantNotFoundException;
import com.stackroute.foodieapp.helper.RestaurantData;
import com.stackroute.foodieapp.model.Restaurant;

public interface RestaurantServiceIf {

	public List<RestaurantData> getRestaurants() throws Exception;
	
	public List<RestaurantData> getRestaurantsByLocationAndCuisine(String locationName, String cuisine) throws Exception;
	
	public Restaurant saveNewRestaurant(Restaurant restaurant) throws RestaurantAlreadyExistsException;
	
	public Restaurant updateRestaurant(Restaurant restaurant) throws RestaurantNotFoundException;
	
	public boolean deleteRestaurant(Long id) throws RestaurantNotFoundException;
	
	public List<Restaurant> getFavouriteRestaurants();
	
	
}
