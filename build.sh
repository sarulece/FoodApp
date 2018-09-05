#!bin/bash
source ./env-variables.sh
cd Server
mvn clean package
docker build -t restaurant-service .
cd ..
docker build -t restaurant-client .
