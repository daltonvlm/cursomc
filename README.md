# Cursomc

Spring Boot REST API for a case study of an online order management system

![Badge](https://img.shields.io/badge/status-in_progress-yellow)

![Diagrama de classes](diagrama-de-classes.png)
Diagram's author: [Nelio Alves](https://github.com/acenelio)

## Endpoints example

| Endpoint         | Description                                                      |
|------------------|------------------------------------------------------------------|
| /categories/{id} | A category and its products                                      
| /clients/{id}    | A client, its phone nubmers, and addresses                       
| /orders/{id}     | An order, its client, payment, order items, and delivery address 

## Concepts and tools covered

* Spring Boot REST API
* JPA / Hibernate
* Postgresql
* Heroku deployment
* Spring Security / JWT
* Gmail
* Amazon S3

This project is based on the Spring Boot course [cursomc](https://github.com/acenelio/springboot2-ionic-backend), by
Nelio Alves
