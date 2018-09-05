# The Foodie App

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 1.5.0.

## Development server

Run `npm serve` for a dev server. Navigate to `http://localhost:3000/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `-prod` flag for a production build.

## Running lint

Run `npm run lint` to check linting errors.(https://eslint.org/).

## Running unit tests

Run `npm run test` to execute both Mocha and angular test cases [Mocha] & [Karma] (https://mochajs.org/) & (https://karma-runner.github.io) 

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).
Before running the tests make sure you are serving the app via `ng serve`.

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).

## Command used to generate this project
Project is originally generated usign Angular CLI, and was added the express part manually

## The Zomato API to be used as data source
- To get zomato location id use the following end point of location API provided by Zomato, user needs to provide API key in headers. [https://developers.zomato.com/api/v2.1/locations?query='city name']
- To get list of restaurant use the following rest-endpoint [https://developers.zomato.com/api/v2.1/search?entity_id=3&entity_type=city&q=mumbai&sort=rating].
- The location input can be specified using Zomato location ID or coordinates. Cuisine / Establishment / Collection IDs can be obtained from respective api calls. 
- Get up to 100 restaurants by changing the 'start' and 'count' parameters with the maximum value of count being 20. Partner Access is required to access photos and reviews.
Examples:
To search for 'Italian' restaurants in 'Manhattan, New York City', set cuisines = 55, entity_id = 94741 and entity_type = zone
To search for 'cafes' in 'Manhattan, New York City', set establishment_type = 1, entity_type = zone and entity_id = 94741
Get list of all restaurants in 'Trending this Week' collection in 'New York City' by using entity_id = 280, entity_type = city and collection_id = 1
- To get restaurant reviews using the Zomato restaurant ID  Only 5 latest reviews are available under the Basic API plan.. [https://developers.zomato.com/api/v2.1/reviews?res_id='resturant id'].
- Get a list of all cuisines of restaurants listed in a city. The location/city input can be provided in the following ways -
  Using Zomato City ID
  Using coordinates of any location within a city
  List of all restaurants serving a particular cuisine can be obtained using '/search' API with cuisine ID and location details. [https://developers.zomato.com/api/v2.1/cuisines?city_id='cityid'&lat='lattitude'&lon='longitude']

P.S : The Above are just indicative usage of the API ... However, please do check the list of Issues to get the exact data that you need to fetch in order for the App to get passed through Hobbes Evaluation. 
You need to generate the API_KEY for the above endpoints and replace  `<<api_key>>` with it.

## To register for an API key, follow these steps:

You need to visit to [zomato] (https://developers.zomato.com/api) and login with either google account or facebook acoount.

- After login,click on "Generate API key" and follow the instaruction to fill necessary details.
-Once necessary details are filled, a prompt will appear on the screen which will have API key. Kindly make sure you save it for your future use as well.
-One can make 1000 calls per day using API key.
