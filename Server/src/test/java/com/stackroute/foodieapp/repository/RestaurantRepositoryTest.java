package com.stackroute.foodieapp.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.foodieapp.model.Restaurant;
import com.stackroute.foodieapp.repository.RestaurantRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class RestaurantRepositoryTest {
	
	@Autowired
	private RestaurantRepository repo;
	private Restaurant restaurant = null;
	
	
	@Test
	@Rollback
	public void testSaveRestaurant()
	{
		restaurant = new Restaurant("1001", "Sangeetha", "Velachery, Chennai", "chennai", "5",
				"chinese, south indian", 300L, "Rs.", "4.5", "390", "comment", "www.abc.com", true);
		Restaurant rest = repo.save(restaurant);
		Restaurant Restaurant1 = repo.getOne(rest.getId());
		assertThat(Restaurant1.getName()).isEqualTo("Sangeetha");
	}
	
	@Test
	@Rollback
	public void testUpdateRestaurant()
	{
		restaurant = repo.save(new Restaurant("1002", "Sangeetha", "Velachery, Chennai", "chennai", "5",
				"chinese, south indian", 300L, "Rs.", "4.5", "390", "comment", "www.abc.com", true));
		Restaurant restaurant1 = repo.getOne(restaurant.getId());
		assertThat(restaurant1.getName()).isEqualTo("Sangeetha");
		restaurant1.setComments("nice food");
		repo.save(restaurant1);
		restaurant1 = repo.getOne(restaurant1.getId());
		assertThat(restaurant1.getComments()).isEqualTo("nice food");
	}
	
	@Test
	@Rollback
	public void testDeleteRestaurant()
	{
		restaurant = repo.save(new Restaurant("1003", "Sangeetha", "Velachery, Chennai", "chennai", "5",
				"chinese, south indian", 300L, "Rs.", "4.5", "390", "comment", "www.abc.com", true));
		Restaurant restaurant2 = repo.getOne(restaurant.getId());
		assertThat(restaurant2.getId()).isEqualTo(restaurant.getId());
		repo.delete(restaurant2);
		assertEquals(Optional.empty(), repo.findById(restaurant2.getId()));
	}
	
	@Test
	@Rollback
	public void testGetRestaurant()
	{
		restaurant = repo.save(new Restaurant("1003", "Sangeetha", "Velachery, Chennai", "chennai", "5",
				"chinese, south indian", 300L, "Rs.", "4.5", "390", "comment", "www.abc.com", true));
		Restaurant restaurant2 = repo.getOne(restaurant.getId());
		assertThat(restaurant2.getName()).isEqualTo("Sangeetha");
	}
	
	@Test
	@Rollback
	public void testGetRestaurants()
	{
		repo.save(new Restaurant("1003", "Sangeetha", "Velachery, Chennai", "chennai", "5",
				"chinese, south indian", 300L, "Rs.", "4.5", "390", "comment", "www.abc.com", true));
		repo.save(new Restaurant("1004", "Sangeetha", "Velachery, Chennai", "chennai", "5",
				"chinese, south indian", 300L, "Rs.", "4.5", "390", "comment", "www.abc.com", true));
		List<Restaurant> list = repo.findAll();
		assertThat(list.size()).isGreaterThanOrEqualTo(2);
	}

}
