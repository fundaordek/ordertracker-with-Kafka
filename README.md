# Kafka + MongoDB Order Tracker

This project is a minimal demo of Java Spring Boot + Kafka + MongoDB + Docker.

## Run

- Make sure Docker and Docker Compose are installed.
- From project root:
  docker-compose up --build

## Endpoints

POST http://localhost:8080/orders

GET http://localhost:8080/orders

Notes:
- The controller saves to MongoDB and produces a Kafka event.
- A consumer listens on the 'orders' topic and also saves incoming orders to MongoDB.
