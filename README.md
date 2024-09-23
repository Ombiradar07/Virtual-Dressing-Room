# 👗 Virtual Dressing Room Microservices Project

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![MongoDB](https://img.shields.io/badge/MongoDB-47A248?style=for-the-badge&logo=mongodb&logoColor=white)](https://www.mongodb.com/)
[![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)
[![Feign Client](https://img.shields.io/badge/Feign_Client-2962FF?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-cloud-openfeign)
[![Razorpay](https://img.shields.io/badge/Razorpay-02042B?style=for-the-badge&logo=razorpay&logoColor=white)](https://razorpay.com/)
[![Twilio](https://img.shields.io/badge/Twilio-F22F46?style=for-the-badge&logo=twilio&logoColor=white)](https://www.twilio.com/)

## 🏗️ Project Overview

The **Virtual Dressing Room Microservices Project** is a cutting-edge e-commerce platform designed to allow users to virtually try on clothes before making purchases. Built using **Microservices Architecture**, the project leverages various databases and technology stacks to handle different functionalities such as customer management, product management, orders, payments, and notifications. The system is fully scalable and modular, allowing for seamless integration of new features.

## 🌟 Key Features

- **Virtual Dressing Room**: Allows customers to virtually try on products using advanced avatar technology (future release)
- **Customer Management**: Store and retrieve customer profiles and data
- **Product Management**: Manage an extensive catalog of products and categories
- **Order Management**: Enables customers to place and track orders
- **Payment Processing**: Razorpay integration for seamless payment transactions
- **Notification System**: Sends email and SMS notifications on order updates and payments
- **Microservices Architecture**: Each service functions independently for maximum scalability
- **Inter-Service Communication**: Utilizes **Feign Client** and **Eureka Server** for efficient service-to-service communication

## ⚙️ Technologies Used

| Service             | Technology Stack                                                 | Database     |
|---------------------|------------------------------------------------------------------|--------------|
| **Customer Service**| Spring Boot, Feign Client, Spring Data JPA                        | MongoDB      |
| **Product Service** | Spring Boot, Spring Data JPA                                      | PostgreSQL   |
| **Order Service**   | Spring Boot, Spring Data JPA, Feign Client                        | PostgreSQL   |
| **Payment Service** | Spring Boot, Razorpay Integration                                | MySQL        |
| **Notification Service** | Spring Boot, Java MailSender (Email), Twilio (SMS/WhatsApp) | No Database  |

## 📜 Microservices Overview

### 1. **Customer Service**
- Handles customer profile creation, management, and retrieval.
- **Database**: MongoDB
- **Endpoints**:
  - `GET /customers/{id}`: Retrieve customer by ID
  - `POST /customers`: Create a new customer

### 2. **Product Service**
- Manages the product catalog and categories for the virtual dressing room.
- **Database**: PostgreSQL
- **Endpoints**:
  - `GET /products`: Fetch all available products
  - `POST /products`: Create new products
  - `GET /categories`: Fetch all product categories

### 3. **Order Service**
- Manages order placements and interactions between the product, customer, and payment services.
- **Database**: PostgreSQL
- **Inter-service Communication**: Feign Client to interact with **Customer Service** and **Product Service**.
- **Endpoints**:
  - `POST /orders`: Place a new order with product and customer details
  - `GET /orders/{id}`: Get order details by ID

### 4. **Payment Service**
- Handles payment processing using Razorpay for orders.
- **Database**: MySQL
- **Endpoints**:
  - `POST /payments`: Initiate payment for an order
  - `GET /payments/{id}`: Get payment details

### 5. **Notification Service**
- Sends notifications via email and SMS for order confirmations and payment status.
- **Database**: No database required.
- **Endpoints**:
  - `POST /notifications/email`: Send an email notification
  - `POST /notifications/sms`: Send an SMS notification

## 📦 Architecture Overview

This project follows a microservices-based architecture, with each service independently deployed. All services register with the **Eureka Server** for service discovery and communicate via **Feign Client**. The system can be easily scaled and extended with new features.

![Architecture Diagram](https://user-images.githubusercontent.com/1234567/architecture-diagram.png)

## 🔧 Setup and Installation

### Prerequisites
- Java 17+
- Maven
- Docker
- MongoDB, MySQL, PostgreSQL
