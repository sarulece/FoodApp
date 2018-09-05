import { Component, OnInit, Input, EventEmitter } from '@angular/core';
import { Restaurant } from '../../restaurant';
import { RestaurantService } from '../../restaurant.service';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'restaurant-container',
  templateUrl: './restaurant-container.component.html',
  styleUrls: ['./restaurant-container.component.css']
})
export class RestaurantContainerComponent implements OnInit {
  @Input()
  restaurants: Array<Restaurant>;
  @Input()
  isFavourites: boolean;
  constructor(private restaurantService: RestaurantService, private snackBar: MatSnackBar) { }

  ngOnInit() {
  }

  addToFavourites(restaurant: Restaurant) {
    console.log("Restaurant Container - Add favourite");
    this.restaurantService.addFavouriteRestaurant(restaurant).subscribe((rest: Restaurant) => {
      const index = this.restaurants.indexOf(restaurant);
      console.log("Index: " + index);
      console.log("Id= " + rest.id + " favourite= " + rest.favourite);
      this.restaurants[index].id = rest.id;
      this.restaurants[index].favourite = true;
      this.snackBar.open("Restaurant added to Favourites list", "", {duration: 1000});
    },
    (error) => {
      console.log(JSON.stringify(error));
      this.snackBar.open((error['error'])['message'], "", {duration: 2000});
    }
  );
  }

  deleteFromFavourites(restaurant: Restaurant) {
    console.log("Restaurant Container - remove favourite");
    let message = `${restaurant.name} deleted from favourites list`
    this.restaurantService.removeFavouriteRestaurant(restaurant).subscribe((rest) =>
    {
      console.log("source: " + this.isFavourites);
      if(this.isFavourites){
        const index = this.restaurants.indexOf(restaurant);
        this.restaurants.splice(index, 1);
      }else{
        restaurant.favourite = false;
      }
      
      this.snackBar.open(message, "", {duration: 1000});
    },
    (error) =>
    {
      console.log(JSON.stringify(error));
      this.snackBar.open((error['error'])['message'], "", {duration: 2000});
    }
  );
  }

  updateToFavourites(restaurant) {
    console.log("Restaurant Container - Update favourite");
    this.restaurantService.updateFavouriteRestaurant(restaurant).subscribe((restaurant: Restaurant) =>
    {
      this.snackBar.open("Restaurant " + restaurant.name + " updated", "", {duration: 1000});
    },
    (error) =>
    {
      console.log(JSON.stringify(error));
      this.snackBar.open((error['error'])['message'], "", {duration: 1000});
    });
  } 

}
