import { Component, OnInit, Input } from '@angular/core';
import { Restaurant } from '../../restaurant';
import { RestaurantDetailService } from '../../restaurant-detail.service';
import { RestaurantService } from '../../restaurant.service';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';
@Component({
  selector: 'restaurant-detail',
  templateUrl: './restaurant-detail.component.html',
  styleUrls: ['./restaurant-detail.component.css']
})
export class RestaurantDetailComponent implements OnInit {
 
  constructor(private restaurantDetailService: RestaurantDetailService
    , private restaurantService: RestaurantService, private snackBar: MatSnackBar
    , private router: Router) { }

  ngOnInit() {
    if(this.restaurantData == null){
      this.router.navigate(["/"]);
    }
  }

  get restaurantData(): Restaurant {
    return this.restaurantDetailService.restaurant;
  }

  updateToFavourites() {
    console.log("Restaurant Container - Update favourite");
    this.restaurantService.updateFavouriteRestaurant(this.restaurantData).subscribe((restaurant: Restaurant) =>
    {
      this.snackBar.open("Restaurant " + restaurant.name + " updated", "", {duration: 1000});
    },
    (error) =>
    {
      console.log(JSON.stringify(error));
      this.snackBar.open((error['error'])['message'], "", {duration: 2000});
    });
  } 

}
