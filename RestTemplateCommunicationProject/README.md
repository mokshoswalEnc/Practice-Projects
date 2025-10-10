# Employee & Order Microservices Project

This project consists of two Spring Boot microservices: **EmpOrderService** and **EmpEmployeeService**.  
The Employee service communicates with the Order service using **REST API calls**. Both services have **Swagger UI** enabled for easy API testing.

---

## 📂 Project Structure

### 1. EmpOrderService
- **Port:** `8081`
- **Purpose:** Provides order data (mocked).
- **Main Classes:**
  - `EmpOrderServiceApplication` → Spring Boot main application.
  - `OrderController` → REST controller for `/order/{id}` endpoint.
  - `Order` → Model class for order details.
- **Endpoints:**
  | Endpoint | HTTP Method | Description |
  |----------|-------------|-------------|
  | `/order/{id}` | GET | Returns order details for a given order ID |

- **Swagger UI:** [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)

---

### 2. EmpEmployeeService
- **Port:** `8083`
- **Purpose:** Acts as a client to consume Order service APIs and enhance responses.
- **Main Classes:**
  - `EmpEmployeeServiceApplication` → Spring Boot main application.
  - `EmployeeController` → REST controller for `/employee` endpoints.
- **Endpoints:**
  | Endpoint | HTTP Method | Description |
  |----------|-------------|-------------|
  | `/employee/getOrder/{id}` | GET | Fetches order details from Order service and adds a success message |
  | `/employee/Info` | GET | Returns a simple status message |

- **Swagger UI:** [http://localhost:8083/swagger-ui/index.html](http://localhost:8083/swagger-ui/index.html)

---

## ⚙️ How It Works

1. **Start the services**:
   - Run `EmpOrderServiceApplication` → listens on **8081**
   - Run `EmpEmployeeServiceApplication` → listens on **8083**

2. **Order service** exposes order data:
   ```json
   GET http://localhost:8081/order/101

   Response:
   {
     "id": 101,
     "name": "Laptop",
     "price": 25000.0
   }


🔹 Technology Stack

Java 17+

Spring Boot

Spring Web (REST APIs)

Springdoc OpenAPI (Swagger)

Maven

Optional: H2/MySQL for future database integration

Dependencies (Maven)
<!-- Spring Boot Web Starter -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- Springdoc OpenAPI Starter -->
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.5.0</version>
</dependency>

Notes

Ensure Order service is running before calling Employee service endpoints.

Swagger UI provides interactive testing, including path parameters and responses.

You can test all endpoints via Postman or directly using Swagger UI.
