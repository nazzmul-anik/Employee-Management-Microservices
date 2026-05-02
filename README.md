# 🧩 Employee Management Microservices System

A **production-style microservices architecture** built using **Spring Boot, Spring Cloud, and JWT-based security**, designed to manage employees and their addresses with secure, scalable, and maintainable service communication.

---

<img width="1536" height="1024" alt="image" src="https://github.com/user-attachments/assets/75ab5f52-7110-4aaf-a3e0-f6661560ac3d" />

---

## 🚀 Overview

This project demonstrates a **fully decoupled microservices system** with:

* 🔐 Secure authentication using JWT
* 🌐 Centralized API Gateway
* 📡 Service discovery with Eureka
* ⚙️ Shared exception handling via Common Library
* 🔁 Inter-service communication using Feign Client

---

## 🏗️ Architecture

```
Client → API Gateway → (Auth / Employee / Address Services)
                        ↓
                  Eureka Server
                        ↓
                 Common Library
```

---

## 📦 Services Description

### 🔹 API Gateway

* Entry point for all client requests
* Handles routing to appropriate services
* Performs **JWT validation and authorization**
* Filters public and secured endpoints

---

### 🔹 Auth Service

* User registration & login
* Generates **JWT access tokens**
* Implements **role-based authorization**
* Provides token validation endpoint

---

### 🔹 Employee Service

* CRUD operations for employees:

  * Create
  * Update
  * Get
  * Delete
* Communicates with Address Service via Feign

---

### 🔹 Address Service

* Manages employee address data:

  * Create
  * Update
  * Get
  * Delete
* Linked with Employee Service

---

### 🔹 Eureka Server

* Service registry and discovery
* Enables dynamic service communication
* Eliminates hardcoded service URLs

---

### 🔹 Common Library

* Centralized exception handling
* Shared DTOs and response models
* Ensures consistent error response across all services

---

## 🔐 Security Flow

1. User registers/logs in via **Auth Service**
2. Receives a **JWT token**
3. Sends token in request headers:

   ```
   Authorization: Bearer <token>
   ```
4. API Gateway:

   * Validates token
   * Allows/blocks access based on endpoint

---

## 🛠️ Tech Stack

* **Java 25 & 21**
* **Spring Boot**
* **Java Persistent API (JPA)**
* **Spring Cloud (Gateway, Eureka)**
* **Spring Security**
* **JWT (JSON Web Token)**
* **Feign Client**
* **Lombok**
* **MySQL**

---

## ⚙️ Features

* ✅ Microservices-based architecture
* ✅ Centralized API Gateway
* ✅ JWT Authentication & Authorization
* ✅ Role-based access control
* ✅ Service Discovery (Eureka)
* ✅ Inter-service communication (Feign)
* ✅ Global Exception Handling (Common Library)

---

## 📂 Project Structure

```
employee-management/
│
├── api-gateway/
├── auth-service/
├── employee-service/
├── address-service/
├── common-library/
├── eureka-server/
```

---

## ▶️ How to Run

1. Start **Eureka Server**
2. Start all microservices:

   * Auth Service
   * Employee Service
   * Address Service
3. Start **API Gateway**
4. Access APIs via Gateway

---

## 📌 Sample Flow

1. Register user → `/api/auth/register`
2. Login → `/api/auth/login`
3. Get JWT token
4. Call secured APIs:

   * `/api/employees`
   * `/api/addresses`

---

## ⚠️ Error Handling Strategy

* All services use **Common Library**
* Standard error response format:

  ```
  {
    "message": "Error message",
    "status": 404,
    "timestamp": "..."
  }
  ```
* Feign client handles remote service errors via custom decoder

---


## 👨‍💻 Author

**Md. Nazmul Hasan Anik**
Former Associate Software Engineer at **NextKraft Ltd**

---

## ⭐ If you like this project

Give it a ⭐ on this project.
