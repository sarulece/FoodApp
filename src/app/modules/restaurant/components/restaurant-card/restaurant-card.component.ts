import { Component, OnInit, Input, Output } from '@angular/core';
import { EventEmitter } from '@angular/core';
import { RestaurantService } from '../../restaurant.service';
import { Restaurant } from '../../restaurant';
import { Router } from '@angular/router';
import { RestaurantDetailService } from '../../restaurant-detail.service';

@Component({
  selector: 'restaurant-card',
  templateUrl: './restaurant-card.component.html',
  styleUrls: ['./restaurant-card.component.css']
})
export class RestaurantCardComponent implements OnInit {
  @Input()
  restaurant: Restaurant;
  @Output()
  addFavourite: EventEmitter<Restaurant>;
  @Output()
  removeFavourite: EventEmitter<Restaurant>;
  @Output()
  updateFavourite: EventEmitter<Restaurant>;
  buttonText: string;
  buttonClasses = {};
  
  constructor(private router: Router, private restaurantDetailService: RestaurantDetailService) { 
    this.addFavourite = new EventEmitter();
    this.removeFavourite = new EventEmitter();
    this.updateFavourite = new EventEmitter();
  }

  ngOnInit() {
    if(this.restaurant.favourite) {
      this.buttonText = "Remove";
    }else{
      this.buttonText = "Add favourite";
    }
    this.buttonClasses = {
      "favourite": this.restaurant.favourite,
      "remove": !this.restaurant.favourite
    };
  }

  addRemoveFavourite() {
    if(this.restaurant.favourite) {
      this.removeFromFavourites();
      this.restaurant.favourite = false;
      this.buttonText = "Add favourite";
      this.restaurant.favourite = true;      
    }else{
      this.addToFavourites();
      this.restaurant.favourite = true;
      this.buttonText = "Remove";
      this.restaurant.favourite = false;     
    }
  }

  displayRestaurantDetail() {
    console.log("Navigate to restaurant detail");
    this.restaurantData = this.restaurant;
    this.router.navigate(["/restaurant-detail"]);
  }

  set restaurantData(rest: Restaurant) {
    this.restaurantDetailService.restaurant = rest;
  }

  addToFavourites() {
    console.log("add to favourites: " + this.restaurant.name);
    this.addFavourite.emit(this.restaurant);
  }

  removeFromFavourites() {
    console.log("Remove from favourites: " + this.restaurant.name);
    this.removeFavourite.emit(this.restaurant); 
  } 

}
