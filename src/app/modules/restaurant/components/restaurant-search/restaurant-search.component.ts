import { Component, OnInit, Input } from '@angular/core';
import { RestaurantService } from '../../restaurant.service';
import { Restaurant } from '../../restaurant';

@Component({
  selector: 'restaurant-search',
  templateUrl: './restaurant-search.component.html',
  styleUrls: ['./restaurant-search.component.css']
})
export class RestaurantSearchComponent implements OnInit {
  location: string;
  cuisine: string;
  restaurants: Array<Restaurant>;
  constructor(private restaurantService: RestaurantService) { 
    this.restaurants = [];
  }

  ngOnInit() {
  }

  searchRestaurants() {
    console.log("Location: " + this.location + ", cuisine: " + this.cuisine);
    this.restaurantService.getRestaurantsByLocationAndCuisine(this.location, this.cuisine).subscribe( restaurants => {
      this.restaurants = restaurants;
      console.log("Search restaurants: " + restaurants);
    });
  }

}
