import { Component, OnInit } from '@angular/core';
import { Restaurant } from '../../restaurant'
import { RestaurantService } from '../../restaurant.service';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-restaurant-favourites',
  templateUrl: './restaurant-favourites.component.html',
  styleUrls: ['./restaurant-favourites.component.css']
})
export class RestaurantFavouritesComponent implements OnInit {
  restaurants: Array<Restaurant>;
  constructor(private restaurantService: RestaurantService, private snackBar: MatSnackBar) { }

  ngOnInit() {
    console.log("Get favourite restaurants...")
    this.restaurants = [];
    this.restaurantService.getFavouriteRestaurants().subscribe((restaurants) =>
    {
      if(restaurants.length === 0) {
        this.snackBar.open("Watchlist is empty", '', {duration: 1000})
      }
      this.restaurants.push(...restaurants);
    });
  }

}
