import { Component, OnInit } from '@angular/core';
import { Restaurant } from '../../restaurant';
import { RestaurantService } from '../../restaurant.service';

@Component({
  selector: 'restaurant-home',
  templateUrl: './restaurant-home.component.html',
  styleUrls: ['./restaurant-home.component.css']
})
export class RestaurantHomeComponent implements OnInit {
  restaurants: Array<Restaurant>;
  constructor(private restaurantService: RestaurantService) { 
    this.restaurants = [];
  }

  ngOnInit() {
    this.restaurantService.getRestaurants().subscribe((rest)=>{
      this.restaurants.push(...rest);
    });
    console.log("Home Page Restaurants: " + this.restaurants);
  }

}
