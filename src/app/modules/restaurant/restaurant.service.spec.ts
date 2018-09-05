import { TestBed, inject, fakeAsync } from '@angular/core/testing';
import { HttpClientModule, HttpClient } from  '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { Observable } from 'rxjs';
import { RestaurantService } from './restaurant.service';
import { DEFAULT_SCROLL_TIME } from '@angular/cdk/scrolling';

const testConfig={
  restaurants: [{
      id: 100,
      name: 'Sangeetha',
      thumb: 'abc.com',
      res_id: '1001',
      city: 'Chennai',
      comments: 'good food',
      cuisines: 'chinese',
      address: 'chennai',
      average_cost_for_two: 200,
      currency: "Rs.",
      aggregate_rating: "4.5",
      votes: "420",
      thumbUrl: "www.abc.com",
      favourite: false            
  }],
  restaurant:{
    id: 100,
    name: 'Sangeetha',
    thumb: 'abc.com',
    res_id: '1001',
    city: 'Chennai',
    comments: 'good food',
    cuisines: 'chinese',
    address: 'chennai',
    average_cost_for_two: 200,
    currency: "Rs.",
    aggregate_rating: "4.5",
    votes: "420",
    thumbUrl: "www.abc.com",
    favourite: false 
  }
}

describe('RestaurantService', () => {
  let restaurantService: RestaurantService;
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule, HttpClientTestingModule],
      providers: [RestaurantService]
    });
    restaurantService = TestBed.get(RestaurantService);
  });

  it('should be created', inject([RestaurantService], (service: RestaurantService) => {
    expect(service).toBeTruthy();
  }));

  it('should add restaurant to favourites', fakeAsync(() => {

    let  data = testConfig.restaurant;
    inject([RestaurantService, HttpTestingController], (backend: HttpTestingController) =>
    {
        const mockReq = backend.expectOne(restaurantService.favouriteBackEndUrl);
        expect(mockReq.request.url).toEqual(restaurantService.favouriteBackEndUrl, "request url should match");
        expect(mockReq.request.method).toEqual('POST', 'should handle requested method type');
        mockReq.flush(data);
        backend.verify();
    });

    restaurantService.addFavouriteRestaurant(data).subscribe((res:any) => 
    {
        expect(res).toBeDefined();
        expect(res._body).toBe(data, 'data should be same');
    });
  }));

  it('should update favourite restaurant', fakeAsync(() => {

    let  data = testConfig.restaurant;
    inject([RestaurantService, HttpTestingController], (backend: HttpTestingController) =>
    {
        const mockReq = backend.expectOne(restaurantService.favouriteBackEndUrl);
        expect(mockReq.request.url).toEqual(restaurantService.favouriteBackEndUrl, "request url should match");
        expect(mockReq.request.method).toEqual('POST', 'should handle requested method type');
        mockReq.flush(data);
        backend.verify();
    });

    restaurantService.updateFavouriteRestaurant(data).subscribe((res:any) => 
    {
        expect(res).toBeDefined();
        expect(res._body).toBe(data, 'data should be same');
    });
  }));

  it('should delete restaurant from favourites', fakeAsync(() => {

    let  data = testConfig.restaurant;
    inject([RestaurantService, HttpTestingController], (backend: HttpTestingController) =>
    {
        const mockReq = backend.expectOne(restaurantService.favouriteBackEndUrl);
        expect(mockReq.request.url).toEqual(restaurantService.favouriteBackEndUrl, "request url should match");
        expect(mockReq.request.method).toEqual('POST', 'should handle requested method type');
        mockReq.flush(data);
        backend.verify();
    });

    restaurantService.removeFavouriteRestaurant(data).subscribe((res:any) => 
    {
        expect(res).toBeDefined();
        expect(res._body).toBe(data, 'data should be same');
    });
  }));

  it('should fetch favourite restaurants', fakeAsync(() => {

    let  data = testConfig.restaurants;
    inject([RestaurantService, HttpTestingController], (backend: HttpTestingController) =>
    {
        const mockReq = backend.expectOne(restaurantService.favouriteBackEndUrl);
        expect(mockReq.request.url).toEqual(restaurantService.favouriteBackEndUrl, "request url should match");
        expect(mockReq.request.method).toEqual('POST', 'should handle requested method type');
        mockReq.flush(data);
        backend.verify();
    });

    restaurantService.getFavouriteRestaurants().subscribe((res:any) => 
    {
        expect(res).toBeDefined();
        expect(res._body).toBe(data, 'data should be same');
    });
  }));

  it('should fetch default restaurants', fakeAsync(() => {

    let  data = testConfig.restaurants;
    inject([RestaurantService, HttpTestingController], (backend: HttpTestingController) =>
    {
        const mockReq = backend.expectOne(restaurantService.backEndUrl);
        expect(mockReq.request.url).toEqual(restaurantService.backEndUrl, "request url should match");
        expect(mockReq.request.method).toEqual('POST', 'should handle requested method type');
        mockReq.flush(data);
        backend.verify();
    });

    restaurantService.getRestaurants().subscribe((res:any) => 
    {
        expect(res).toBeDefined();
        expect(res._body).toBe(data, 'data should be same');
    });
  }));

  it('should fetch restaurants by location and cuisine', fakeAsync(() => {

    let  data = testConfig.restaurants;
    inject([RestaurantService, HttpTestingController], (backend: HttpTestingController) =>
    {
        const mockReq = backend.expectOne(restaurantService.backEndUrl);
        expect(mockReq.request.url).toEqual(restaurantService.backEndUrl, "request url should match");
        expect(mockReq.request.method).toEqual('POST', 'should handle requested method type');
        mockReq.flush(data);
        backend.verify();
    });

    restaurantService.getRestaurantsByLocationAndCuisine("Bangalore", "chinese").subscribe((res:any) => 
    {
        expect(res).toBeDefined();
        expect(res._body).toBe(data, 'data should be same');
    });
  }));

});
