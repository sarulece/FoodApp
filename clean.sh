#!bin/bash
cd Server
mvn clean
sudo docker rmi restaurant-service
cd ..
sudo docker rmi restaurant-client
