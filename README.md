# Shopcore API

A production-oriented REST API for an e-commerce platform built with **Java 21 and Spring Boot**.

Shopcore API was designed as a backend portfolio project focused on real-world business rules, clean code, security, data consistency, automated testing, and maintainability.

The application covers the core e-commerce flow from authentication and product management to shopping cart, checkout, inventory control, order processing, and payment simulation.

---

## 🚀 Tech Stack

* Java 21
* Spring Boot
* Spring Web
* Spring Data JPA
* Spring Security
* JWT Authentication
* PostgreSQL
* Flyway
* Bean Validation
* OpenAPI / Swagger
* Docker
* Docker Compose
* JUnit 5
* Mockito
* Testcontainers
* GitHub Actions

---

## 📌 Main Features

### Authentication & Authorization

* User registration
* User authentication
* JWT-based authentication
* Role-based authorization
* `CUSTOMER` and `ADMIN` roles
* Protected endpoints

### Product Catalog

* Create products
* Update products
* Retrieve products
* Delete products
* Product categories
* Pagination
* Product filtering
* Administrative product management

### Inventory

* Stock management
* Stock validation during checkout
* Inventory reservation
* Automatic stock restoration after cancelled or failed orders
* Optimistic locking for concurrent stock updates

### Shopping Cart

* Add products to cart
* Update cart items
* Remove products
* View current cart
* Automatic total calculation

### Orders

* Checkout process
* Order creation
* Order item snapshots
* Order history
* Order status management
* Order cancellation
* Business-rule validation

### Payments

The project contains a simulated payment workflow to demonstrate backend transaction handling without depending on an external payment provider.

Supported scenarios include:

* Payment approval
* Payment rejection
* Order status updates
* Stock restoration after failed payments
* Idempotent payment requests

---

## 🏗️ Architecture

Shopcore follows a **modular monolith** approach.

Instead of organizing the entire application only by technical layers, the codebase is separated by business domain.

```text
src/main/java/.../shopcore

├── auth
├── user
├── product
├── category
├── inventory
├── cart
├── order
├── payment
├── security
├── exception
└── config
```

Each module owns the components related to its business responsibility.

This structure keeps the application simple to deploy while providing clear domain boundaries and making future evolution easier.

---

## 🔄 Order Flow

The main purchase flow is:

```text
Customer
   │
   ▼
Shopping Cart
   │
   ▼
Checkout
   │
   ▼
Stock Validation
   │
   ▼
Order Creation
   │
   ▼
Payment
   │
   ├──── Approved ────► Order Confirmed
   │
   └──── Rejected ────► Order Cancelled
                              │
                              ▼
                       Stock Restored
```

This flow demonstrates transaction management and business-rule orchestration between multiple application domains.

---

## 🔐 Security

Authentication is based on JWT.

After successful login, the API returns an access token that must be sent in protected requests:

```http
Authorization: Bearer <token>
```

Authorization rules distinguish operations available to customers from administrative operations.

---

## 🛡️ Idempotency

Payment requests support an `Idempotency-Key`.

Example:

```http
Idempotency-Key: payment-123456
```

This prevents the same payment operation from being processed multiple times when a client retries a request because of network failures or timeouts.

Idempotency is especially relevant in payment and order-processing systems.

---

## 🔒 Concurrency Control

Inventory operations use optimistic locking to reduce the risk of inconsistent stock updates when multiple customers attempt to purchase the same product simultaneously.

The implementation uses JPA's:

```java
@Version
```

This allows conflicting concurrent updates to be detected instead of silently overwriting inventory data.

---

## 🗄️ Database

The application uses PostgreSQL.

Database schema evolution is managed with **Flyway migrations**, allowing database changes to be versioned alongside the source code.

---

## 🐳 Running with Docker

### Requirements

Make sure you have installed:

* Docker
* Docker Compose

Clone the repository:

```bash
git clone https://github.com/YOUR_USERNAME/shopcore-api.git
cd shopcore-api
```

Create your environment configuration based on:

```text
.env.example
```

Then start the application:

```bash
docker compose up --build
```

Docker Compose starts the required infrastructure, including PostgreSQL and the Shopcore application.

---

## 💻 Running Locally

Requirements:

```text
Java 21+
PostgreSQL
Maven
```

Configure the required environment variables and run:

```bash
mvn spring-boot:run
```

To execute the test suite:

```bash
mvn test
```

---

## 📖 API Documentation

After starting the application, the interactive API documentation is available through Swagger UI.

It can be used to inspect endpoints, request schemas, responses, and authentication requirements.

Typical local endpoint:

```text
http://localhost:8080/swagger-ui/index.html
```

---

## 🔌 API Examples

### Authentication

```http
POST /api/auth/register
POST /api/auth/login
```

### Products

```http
GET    /api/products
GET    /api/products/{id}
POST   /api/products
PUT    /api/products/{id}
DELETE /api/products/{id}
```

### Cart

```http
GET    /api/cart
POST   /api/cart/items
DELETE /api/cart/items/{id}
```

### Orders

```http
POST  /api/orders
GET   /api/orders
GET   /api/orders/{id}
PATCH /api/orders/{id}/cancel
```

### Payments

```http
POST /api/orders/{id}/payment
```

---

## 🧪 Testing

The project includes automated tests using:

* JUnit 5
* Mockito
* Spring Boot Test
* Testcontainers

The testing strategy covers business logic and provides infrastructure for integration tests using real PostgreSQL containers.

---

## ⚙️ Continuous Integration

The repository includes a GitHub Actions workflow.

The CI pipeline automatically validates the project when changes are pushed or submitted through pull requests.

Typical pipeline:

```text
Checkout
   ↓
Setup Java
   ↓
Build
   ↓
Tests
   ↓
Package
```

This helps prevent broken code from being merged into the main branch.

---

## ⚠️ Error Handling

The API provides centralized exception handling with standardized HTTP responses.

Examples include:

* Validation errors
* Resource not found
* Unauthorized access
* Forbidden operations
* Insufficient stock
* Invalid order state
* Duplicate operations
* Business-rule violations

---

## 🗺️ Roadmap

Future improvements planned for the project include:

* Redis caching
* RabbitMQ or Apache Kafka
* Event-driven order processing
* Email notification service
* Real payment gateway integration
* Metrics with Micrometer
* Prometheus
* Grafana
* Distributed tracing
* Cloud deployment
* Enhanced integration tests
* Performance testing

---

## 🎯 Project Goals

Shopcore API was created to apply backend engineering concepts in a realistic e-commerce domain.

The project focuses on:

* REST API design
* Authentication and authorization
* Relational database modeling
* Transaction management
* Business-rule implementation
* Data consistency
* Concurrency handling
* Idempotent operations
* Automated testing
* Containerization
* Continuous integration
* Maintainable architecture

The goal is not only to implement CRUD operations, but to explore problems commonly found in real backend systems.

---

## 👨‍💻 Author

**Emanoel Busson Vale**

Backend Developer focused on **Java and Spring Boot**.

---

## 📄 License

This project is licensed under the MIT License.
