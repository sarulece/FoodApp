package com.stackroute.foodieapp.exceptions;

public class RestaurantNotFoundException extends Exception{
	
	public RestaurantNotFoundException()
	{
		
	}
	
	public RestaurantNotFoundException(String message)
	{
		super(message);
	}

}
