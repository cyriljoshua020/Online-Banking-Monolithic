# 🏦 Online Banking System (Spring Boot + MySQL)

This project is a **Monolithic Full-Stack Java application** that simulates an Online Banking System, developed using **Spring Boot** with **MySQL** as the database.

## 🚀 Project Overview
- Manage Users, Accounts, and Transactions.
- Perform all CRUD operations along with search functionalities.
- RESTful APIs built using Spring Boot.
- Database operations using Spring Data JPA (Hibernate).
- API testing can be done using Postman or Thunder Client.

## 📚 Tech Stack
- Java 17
- Spring Boot 3.4.5
- Spring Data JPA (Hibernate ORM)
- MySQL 8+
- Maven
- Eclipse IDE
- Git and GitHub

## ✨ Features
- Create, Read, Update, Delete (CRUD) operations on:
  - Users
  - Accounts
  - Transactions
- Search functionality for Users (by name), Accounts (by username), and Transactions (by type).
- Custom Exception Handling for invalid operations (e.g., invalid ID, duplicate username/account number).
- Validation using Jakarta Bean Validation (e.g., Age limits, Username length).

## 📖 Project Structure
- `controller/` - REST API endpoints for Users, Accounts, Transactions.
- `entity/` - JPA entities mapped to database tables.
- `repo/` - JPA repositories for database access.
- `service/` - Business logic interfaces.
- `service/impl/` - Implementations of service interfaces.
- `dto/` - Data Transfer Objects for secure data transfer.

## ⚙️ How to Run
1. Clone this repository.
2. Configure MySQL database settings in `application.properties`.
3. Run the project as a Spring Boot application.
4. Access API endpoints on:
   - `http://localhost:8081/onlinebankingapplication`

## 🛠 Sample Endpoints
- `POST /users` ➔ Create new user
- `GET /users` ➔ List all users
- `GET /users/{id}` ➔ Get user by ID
- `PUT /users/{id}` ➔ Update user
- `DELETE /users/{id}` ➔ Delete user
- `GET /users/search?name={name}` ➔ Search users by name

(Similar APIs exist for Accounts and Transactions.)
