# Spring Boot User Management API

## Overview
A RESTful backend API built using Spring Boot following clean layered architecture principles.

## Tech Stack
- Java 17
- Spring Boot 3
- Spring Data JPA
- Hibernate
- H2 Database (file mode)
- Maven

## Architecture
Controller → Service → Repository → Database

## Features
- Create User (POST /users)
- Get All Users (GET /users)
- Get User by ID (GET /users/{id})
- Update User (PUT /users/{id})
- Delete User (DELETE /users/{id})
- Unique email validation
- Global exception handling
- Structured JSON error response

## Run Locally
```bash
mvn spring-boot:run