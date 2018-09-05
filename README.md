# The Foodie App
Foodie app is for viewing restaurants, searching restaurants based on location and cuisine. User can add restaurants to favourites view and add comments.
#Instructions for building application
1. Run "ng build --prod" command for building production grade angular application
2. Run "source ./env_variables.sh" and "mvn clean package" comand for building building spring boot service (inside Server directory)
#Instructions for testing the application
1. Run "ng test", "ng e2e" for testing front end 
2. Run "mvn test" for running junit test cases for spring boot service
#Running the application
1. Go to the root directory
2. Run "docker-compose up --build" command for running the application
#Mysql database docker image used
mysql:5.5
create database foodie_db in mysql server.
