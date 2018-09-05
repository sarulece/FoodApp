package com.stackroute.foodieapp.exceptions;

public class RestaurantAlreadyExistsException extends Exception{
	
	public RestaurantAlreadyExistsException()
	{
		
	}
	
	public RestaurantAlreadyExistsException(String message)
	{
		super(message);
	}

}
