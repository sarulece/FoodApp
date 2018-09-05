package com.stackroute.foodieapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.foodieapp.helper.RestaurantData;
import com.stackroute.foodieapp.model.Restaurant;
import com.stackroute.foodieapp.service.RestaurantService;

@CrossOrigin
@RestController
@RequestMapping(path="/api/restaurants")
public class RestaurantController {
	
	@Autowired
	private RestaurantService service;
	
	@GetMapping
	public ResponseEntity<?> getRestaurants() throws Exception
	{
		List<RestaurantData> list =  null;
		ResponseEntity<?> response = null;
		try
		{
			list =  service.getRestaurants();
			response = new ResponseEntity<List<RestaurantData>>(list, HttpStatus.OK);
			 
		}catch(Exception e)
		{
			response = new ResponseEntity<String>("{\"message\": \"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
		}
		return response;
	}
	
	@GetMapping(path="/city/{city}/cuisine/{cuisine}")
	public ResponseEntity<?> getRestaurantsByLocationAndCuisine(
			@PathVariable("city") String city, @PathVariable("cuisine") String cuisine) throws Exception
	{
		List<RestaurantData> list =  null;
		ResponseEntity<?> response = null;
		try
		{
			list =  service.getRestaurantsByLocationAndCuisine(city, cuisine);
			response = new ResponseEntity<List<RestaurantData>>(list, HttpStatus.OK);
			 
		}catch(Exception e)
		{
			e.printStackTrace();
			response = new ResponseEntity<String>("{\"message\": \"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
		}
		return response;
	}
	
	@GetMapping(path="/favourite_restaurants")
	public ResponseEntity<?> getFavouriteRestaurants()
	{
		List<Restaurant> list =  null;
		ResponseEntity<?> response = null;
		try
		{
			list =  service.getFavouriteRestaurants();
			response = new ResponseEntity<List<Restaurant>>(list, HttpStatus.OK);
			 
		}catch(Exception e)
		{
			e.printStackTrace();
			response = new ResponseEntity<String>("{\"message\": \"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
		}
		return response;
	}
	
	@PostMapping(path="/favourite_restaurants")
	public ResponseEntity<?> saveFavouriteRestaurant(@RequestBody Restaurant newRestaurant)
	{
		Restaurant restaurant = null;
		ResponseEntity<?> response = null;
		try
		{
			restaurant =  service.saveNewRestaurant(newRestaurant);
			response = new ResponseEntity<Restaurant>(restaurant, HttpStatus.CREATED);
			 
		}catch(Exception e)
		{
			e.printStackTrace();
			response = new ResponseEntity<String>("{\"message\": \"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
		}
		return response;
	}
	
	@PutMapping(path="/favourite_restaurants/{id}")
	public ResponseEntity<?> updateFavouriteRestaurant(@PathVariable Long id, @RequestBody Restaurant updateRestaurant)
	{
		Restaurant restaurant = null;
		ResponseEntity<?> response = null;
		try
		{
			restaurant =  service.updateRestaurant(updateRestaurant);
			response = new ResponseEntity<Restaurant>(restaurant, HttpStatus.OK);
			 
		}catch(Exception e)
		{
			e.printStackTrace();
			response = new ResponseEntity<String>("{\"message\": \"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
		}
		return response;
	}
	
	@DeleteMapping(path="/favourite_restaurants/{id}")
	public ResponseEntity<?> deleteFavouriteRestaurant(@PathVariable("id") Long id)
	{
		boolean status = false;
		ResponseEntity<?> response = null;
		try
		{
			status =  service.deleteRestaurant(id);
			response = new ResponseEntity<Boolean>(status, HttpStatus.OK);
			 
		}catch(Exception e)
		{
			e.printStackTrace();
			response = new ResponseEntity<String>("{\"message\": \"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
		}
		return response;
	}

}
