package com.stackroute.foodieapp.helper;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class RestaurantCustomDeserializer  extends StdDeserializer<RestaurantData>{

	public RestaurantCustomDeserializer()
	{
		this(null);
	}
	public RestaurantCustomDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public RestaurantData deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		ObjectCodec oc = jp.getCodec();
		JsonNode node = oc.readTree(jp);
		RestaurantData res = new RestaurantData();
		res.setResId(node.get("id").asText());
		res.setName(node.get("name").asText());
		res.setCuisines(node.get("cuisines").asText());
		res.setAverageCostForTwo(node.get("average_cost_for_two").asLong());
		res.setCurrency(node.get("currency").asText());
		JsonNode locationNode = node.get("location");
		res.setAddress(locationNode.get("address").asText());
		res.setCity(locationNode.get("city").asText());
		res.setCityId(locationNode.get("city_id").asText());
		JsonNode ratingNode = node.get("user_rating");
		res.setAggregateRating(ratingNode.get("aggregate_rating").asText());
		res.setVotes(ratingNode.get("votes").asText());
		res.setThumb(node.get("thumb").asText());

		return res;
	}

}
