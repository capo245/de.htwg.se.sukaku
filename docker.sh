#!/bin/sh
sudo docker login
sudo docker build -t dcbavm/sukaku:v1 .
sudo docker push dcbavm/sukaku:v1


