# Spring Boot User Management API — Master Documentation

---

# 1. Project Overview

This project is a RESTful backend API built using Spring Boot following clean layered architecture principles. It implements a complete User Management system with proper separation of concerns, business logic enforcement, validation, structured error handling, and persistent database configuration.

The goal of this project was not just to build CRUD endpoints, but to understand backend architecture fundamentals deeply.

---

# 2. Tech Stack Used

## 2.1 Java 17

Primary programming language used for building the backend.

## 2.2 Spring Boot 3

Framework used to simplify backend application development.

* Auto configuration
* Embedded Tomcat
* Dependency management
* Production-ready setup

## 2.3 Spring Web

Used to create REST APIs.

## 2.4 Spring Data JPA

Abstraction layer for database access.

## 2.5 Hibernate

JPA implementation used as ORM provider.

## 2.6 H2 Database

Initially used in-memory database.
Later converted to file-based persistent mode.

## 2.7 Maven

Build tool used for:

* Dependency management
* Project packaging
* Build lifecycle

## 2.8 Git & GitHub

Used for:

* Version control
* Repository management
* Code hosting

---

# 3. Architectural Pattern — Layered Architecture

The project follows a strict layered architecture:

Controller → Service → Repository → Database

Each layer has a single responsibility.

---

# 4. Layer Responsibilities

## 4.1 Controller Layer

### Purpose

* Handles HTTP requests
* Accepts JSON input
* Returns HTTP responses
* Does NOT contain business logic

### Annotations Used

* @RestController
* @RequestMapping
* @GetMapping
* @PostMapping
* @PutMapping
* @DeleteMapping

### Key Concept Learned

Controllers must not directly interact with repositories.
They must delegate logic to the service layer.

---

## 4.2 Service Layer

### Purpose

* Contains business logic
* Validates domain rules
* Throws exceptions
* Converts entities to DTOs

### Annotation Used

* @Service
* @Transactional

### Key Learning

Business logic belongs in the service layer, not in controllers.

---

## 4.3 Repository Layer

### Purpose

* Handles database operations
* Provides CRUD methods

### Implementation

Extends JpaRepository<User, Long>

### Advanced Feature Used

Method name query generation:

* existsByEmail(String email)

Spring automatically generates SQL based on method name.

---

# 5. REST Principles Implemented

## Resource-Based URL Design

Correct:

* GET /users
* GET /users/{id}
* PUT /users/{id}
* DELETE /users/{id}

Incorrect (not used):

* /delete/1
* /update/1

In REST:

* URL identifies resource
* HTTP method defines action

---

# 6. HTTP Methods Used

| Method | Purpose         |
| ------ | --------------- |
| POST   | Create resource |
| GET    | Read resource   |
| PUT    | Update resource |
| DELETE | Remove resource |

---

# 7. HTTP Status Codes Implemented

| Code | Meaning     | Usage              |
| ---- | ----------- | ------------------ |
| 200  | OK          | Successful GET/PUT |
| 204  | No Content  | Successful DELETE  |
| 400  | Bad Request | Validation errors  |
| 404  | Not Found   | User not found     |
| 409  | Conflict    | Duplicate email    |

---

# 8. DTO (Data Transfer Object) Pattern

## Why DTO is Needed

Returning entity directly is dangerous because:

* Internal fields may get exposed
* Database structure leaks into API
* API contract becomes unstable

## DTOs Created

* CreateUserRequest
* UserResponse
* ErrorResponse

DTO ensures clean separation between:
Database Model ≠ API Model

---

# 9. Validation

Used Jakarta Validation annotations:

* @NotBlank
* @Email
* @Valid

Validation happens before controller method execution.
Automatically returns 400 if invalid.

---

# 10. Business Logic Enforcement

Implemented unique email validation.

Service checks:
existsByEmail(email)

If true → throw EmailAlreadyExistsException.

Returns 409 Conflict.

Difference learned:
Validation checks format.
Business logic checks domain rules.

---

# 11. Exception Handling

## Custom Exceptions

* UserNotFoundException
* EmailAlreadyExistsException

## Global Exception Handler

Used @ControllerAdvice.

Purpose:

* Centralized error handling
* Clean controllers
* Consistent API error format

---

# 12. Structured Error Response

Implemented ErrorResponse DTO containing:

* timestamp
* status
* error
* message
* path

Professional API design.

---

# 13. JPA & Hibernate Concepts Learned

## 13.1 ORM

Object Relational Mapping converts:
Java Objects ↔ Database Tables

## 13.2 Dirty Checking

Hibernate automatically detects field changes inside transaction.

If fields are modified:
→ Hibernate generates UPDATE query.

If fields are not modified:
→ No UPDATE executed.

Major debugging lesson: Empty setters prevented dirty checking.

## 13.3 Transaction Management

Used @Transactional.

Purpose:

* Maintain database consistency
* Wrap operations in atomic transaction

---

# 14. Database Configuration

## Initial Mode

In-memory H2:
jdbc:h2:mem:testdb

Problem:
Data lost after restart.

## Final Mode

File-based H2:
jdbc:h2:file:./data/authdb

Data persists between restarts.

---

# 15. CRUD Operations Implemented

## Create User

POST /users

## Get All Users

GET /users

## Get User by ID

GET /users/{id}

## Update User

PUT /users/{id}

## Delete User

DELETE /users/{id}

---

# 16. Complete Application Flow

Client → Controller → Service → Repository → Hibernate → Database

Each layer communicates only with adjacent layer.

---

# 17. Git Workflow Learned

Commands used:

* git init
* git add .
* git commit
* git remote add origin
* git push

Configured Git identity.

Uploaded project to GitHub successfully.

---

# 18. Key Debugging Lessons

1. Empty setters prevent updates.
2. Dirty checking requires actual field change.
3. In-memory database resets on restart.
4. Always inspect Hibernate SQL logs.


---

# 19. Final Project Summary

This project demonstrates:

* Clean layered architecture
* RESTful API design
* Business rule enforcement
* DTO separation
* Structured error handling
* Transaction management
* ORM understanding
* Git workflow

This forms a strong backend foundation.

---


