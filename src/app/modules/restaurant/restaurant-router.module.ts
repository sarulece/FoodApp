import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Route } from '@angular/router/src/config';
import { RestaurantSearchComponent } from './components/restaurant-search/restaurant-search.component';
import { RestaurantFavouritesComponent } from './components/restaurant-favourites/restaurant-favourites.component';
import { RestaurantDetailComponent } from './components/restaurant-detail/restaurant-detail.component';
const restaurantRoutes =[
    {
        path: 'search',
        pathMatch: 'full',
        component: RestaurantSearchComponent
    },
    {
        path: 'favourites',
        pathMatch: 'full',
        component: RestaurantFavouritesComponent
    },
    {
        path: 'restaurant-detail',
        pathMatch: 'full',
        component: RestaurantDetailComponent
    }
];
@NgModule({
    imports: [
        RouterModule.forRoot(restaurantRoutes)
    ],
    declarations: [ ],
    exports: [ RouterModule],
    providers: [],
  })
  export class RestaurantRouterModule { }
  