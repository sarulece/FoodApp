import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { MatToolbarModule, MatButtonModule } from '@angular/material';
import { AppComponent } from './app.component';
import { RestaurantModule } from './modules/restaurant/restaurant.module';
import { RestaurantHomeComponent } from './modules/restaurant/components/restaurant-home/restaurant-home.component';

const appRoutes = [
  {
    path: '',
    pathMatch: 'full',
    component: RestaurantHomeComponent
  }
];
export const myComponents = [
  AppComponent
];

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    RestaurantModule,
    MatToolbarModule,
    MatButtonModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
