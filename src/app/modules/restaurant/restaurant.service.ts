import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Restaurant } from './restaurant';
import { Observable } from 'rxjs/Observable';
import { map } from 'rxjs/operators/map';
import { HttpHeaders } from '@angular/common/http';

@Injectable()
export class RestaurantService {
  backEndUrl: string;
  favouriteBackEndUrl: string;

  constructor(private http: HttpClient) { 
    this.backEndUrl = 'http://localhost:8081/api/restaurants';
    this.favouriteBackEndUrl = 'http://localhost:8081/api/restaurants/favourite_restaurants';
  }

  getRestaurants(): Observable<Array<Restaurant>> {
    const url = `${this.backEndUrl}/city/Bangalore/cuisine/Chinese`;
    return this.http.get<Array<Restaurant>>(url).pipe
    (
      map(this.transformThumbToUrl.bind(this))
    );
  }

  getRestaurantsByLocationAndCuisine(location: string, cuisine: string): Observable<Array<Restaurant>> {
    const url = `${this.backEndUrl}/city/${location}/cuisine/${cuisine}`;
    return this.http.get<Array<Restaurant>>(url).pipe
    (
      map(this.transformThumbToUrl.bind(this))
    );
  }

  transformThumbToUrl(restaurants: Array<Restaurant>): Array<Restaurant> {
      return restaurants.map( rest => {
        rest.thumbUrl = `url(${rest.thumb})`;
        console.log("favourite: " + rest.favourite);
        return rest;
      });
  }

  getFavouriteRestaurants(): Observable<Array<Restaurant>> {
    return this.http.get<Array<Restaurant>>(this.favouriteBackEndUrl).pipe
    (
      map(this.transformThumbToUrl.bind(this)),
      map(this.updateFavouriteAttribute.bind(this))
    );
  }

  updateFavouriteAttribute(restaurants: Array<Restaurant>): Array<Restaurant> {
    return restaurants.map( rest => {
      rest.favourite = true;
      return rest;
    });
  }

  addFavouriteRestaurant(restaurant: Restaurant): Observable<Restaurant> {
    console.log("Service - Add Favourite: " + restaurant);
    return this.http.post<Restaurant>(this.favouriteBackEndUrl, restaurant);
  }

  removeFavouriteRestaurant(restaurant: Restaurant) {
    console.log("Service - Remove Favourite: " + restaurant);
    const url = `${this.favouriteBackEndUrl}/${restaurant.id}`;
    return this.http.delete(url, {responseType: 'text'});
  }

  updateFavouriteRestaurant(restaurant: Restaurant): Observable<Restaurant> {
    console.log("Service - Update Favourite: " + restaurant);
    const url = `${this.favouriteBackEndUrl}/${restaurant.id}`;
    return this.http.put<Restaurant>(url, restaurant);
  }

}
