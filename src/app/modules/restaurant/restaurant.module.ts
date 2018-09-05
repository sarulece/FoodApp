import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { MatCardModule } from '@angular/material/card';
import { MatSnackBarModule } from '@angular/material';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { RestaurantCardComponent } from './components/restaurant-card/restaurant-card.component';
import { RestaurantService } from './restaurant.service';
import { RestaurantDetailService } from './restaurant-detail.service';
import { RestaurantSearchComponent } from './components/restaurant-search/restaurant-search.component';
import { RestaurantContainerComponent } from './components/restaurant-container/restaurant-container.component';
import { RestaurantHomeComponent } from './components/restaurant-home/restaurant-home.component';
import { RestaurantFavouritesComponent } from './components/restaurant-favourites/restaurant-favourites.component';
import { RestaurantRouterModule } from './restaurant-router.module';
import { RestaurantDetailComponent } from './components/restaurant-detail/restaurant-detail.component';

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    FormsModule,
    MatCardModule,
    MatSnackBarModule,
    MatInputModule,
    RestaurantRouterModule,
  ],
  declarations: [RestaurantCardComponent,
     RestaurantSearchComponent, 
     RestaurantContainerComponent, 
     RestaurantHomeComponent, 
     RestaurantFavouritesComponent, RestaurantDetailComponent],
  exports: [RestaurantHomeComponent, RestaurantRouterModule],
  providers: [RestaurantService, RestaurantDetailService]
})
export class RestaurantModule { }
