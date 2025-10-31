# Spring Security JWT Project

## Overview

This project is a **Spring Boot application** demonstrating **JWT-based authentication** with **role-based access control**.
It features **three user roles**: `ADMIN`, `USER`, and `DEVELOPER`, and uses a **stateless security model** with **Spring Security**.

---

## Features

* **JWT Authentication** – Users authenticate and receive a JWT for stateless security.
* **Role-Based Access Control** – Endpoints restricted based on roles:

  * `ADMIN` → Admin endpoints
  * `USER` → User endpoints
  * `DEVELOPER` → Developer endpoints
* **Secure Password Storage** – Passwords are hashed using **BCrypt**.
* **In-Memory User Store** – Demo-friendly, easily extendable to a database.
* **Modular Controllers** – Separate controllers for each role for clean design.

---

## Technologies Used

* Java 17+
* Spring Boot 3.x
* Spring Security
* JWT (io.jsonwebtoken)
* Maven
* Lombok (optional)
* REST API

---

## Project Structure

```
SpringSecurityProject/
├─ pom.xml
├─ src/main/java/com/security/
│  ├─ SpringSecurityProjectApplication.java
│  ├─ config/
│  │   └─ JwtConfig.java
│  ├─ controller/
│  │   ├─ AuthController.java
│  │   ├─ AdminController.java
│  │   ├─ UserController.java
│  │   └─ DeveloperController.java
│  ├─ security/
│  │   ├─ SecurityConfig.java
│  │   └─ JwtFilter.java
│  └─ token/
│      └─ JWTUtil.java
├─ src/main/resources/
│  └─ application.properties
```

---

## User Roles and Credentials

| Username | Password      | Role      |
| -------- | --------      | --------- |
| admin    | admin123      | ADMIN     |
| user     | user123       | USER      |
| dev      | developer123  | DEVELOPER |

---

## API Endpoints

### Authentication

* `POST /auth/login` – Accepts `username` and `password`, returns JWT.

### Admin Endpoints

* `GET /api/admin/**` – Only accessible by `ADMIN`.

### User Endpoints

* `GET /api/user/**` – Accessible by `USER`, `ADMIN`, `DEVELOPER`.

### Developer Endpoints

* `GET /api/dev/**` – Accessible by `DEVELOPER` and `ADMIN`.

---

## How It Works

1. User sends **login request** to `/auth/login`.
2. Spring Security validates credentials using **AuthenticationManager**.
3. If successful, **JWTUtil** generates a token and sends it back.
4. Client includes JWT in `Authorization: Bearer <token>` header for subsequent requests.
5. **JwtFilter** intercepts requests, validates token, and sets user in SecurityContext.
6. Access to endpoints is checked based on **user roles**.

---

## Configuration

* JWT secret and expiration are defined in `JWTUtil.java`.
* Passwords are hashed using **BCryptPasswordEncoder**.
* Spring Security configuration is in `SecurityConfig.java`.

---

## How to Run

1. Clone the repository:

```bash
git clone https://github.com/mokshoswalEnc/SpringSecurityProject.git
cd SpringSecurityProject
```

2. Build the project:

```bash
mvn clean install
```

3. Run the Spring Boot application:

```bash
mvn spring-boot:run
```

4. Use **Postman** or **curl** to test the endpoints.

---

## Testing the Project

**Example Login Request:**

```http
POST /auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "admin123"
}
```

**Example Protected Request:**

```http
GET /api/admin/dashboard
Authorization: Bearer <JWT_TOKEN>
```

---
