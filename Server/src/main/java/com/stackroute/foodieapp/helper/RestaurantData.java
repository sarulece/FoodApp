package com.stackroute.foodieapp.helper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonDeserialize(using= RestaurantCustomDeserializer.class)
public class RestaurantData {

	private Long id;
	@JsonProperty("res_id")
	private String resId;
	private String name;
	private String address;
	private String city;
	@JsonProperty("city_id")
	private String cityId;
	private String cuisines;
	@JsonProperty("average_cost_for_two")
	private Long averageCostForTwo;
	private String currency;
	@JsonProperty("aggregate_rating")
	private String aggregateRating;
	private String votes;
	private String comments;
	private String thumb;
	private boolean favourite;
	public RestaurantData() {
		super();
	}
	
	public RestaurantData(Long id, String resId, String name, String address, String city, String cityId,
			String cuisines, Long averageCostForTwo, String currency, String aggregateRating, String votes,
			String comments, String thumb, boolean favourite) {
		super();
		this.id = id;
		this.resId = resId;
		this.name = name;
		this.address = address;
		this.city = city;
		this.cityId = cityId;
		this.cuisines = cuisines;
		this.averageCostForTwo = averageCostForTwo;
		this.currency = currency;
		this.aggregateRating = aggregateRating;
		this.votes = votes;
		this.comments = comments;
		this.thumb = thumb;
		this.favourite = favourite;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getCuisines() {
		return cuisines;
	}
	public void setCuisines(String cuisines) {
		this.cuisines = cuisines;
	}
	public Long getAverageCostForTwo() {
		return averageCostForTwo;
	}
	public void setAverageCostForTwo(Long averageCostForTwo) {
		this.averageCostForTwo = averageCostForTwo;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getAggregateRating() {
		return aggregateRating;
	}
	public void setAggregateRating(String aggregateRating) {
		this.aggregateRating = aggregateRating;
	}
	public String getVotes() {
		return votes;
	}
	public void setVotes(String votes) {
		this.votes = votes;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public boolean isFavourite() {
		return favourite;
	}

	public void setFavourite(boolean favourite) {
		this.favourite = favourite;
	}
	
	
	
}
