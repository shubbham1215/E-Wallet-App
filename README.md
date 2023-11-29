E-Wallet Transaction Management System
Project Overview

This e-wallet application is a robust and scalable system designed for efficient transaction management and user account handling. The system utilizes a microservices architecture, integrating technologies like Spring Boot, Spring Security, JPA, Kafka, and RESTful services.
Key Features

    User Registration and Management: Secure signup and user management functionalities.
    Transaction Processing: Handling various types of transactions including credit and debit operations.
    Wallet Management: Each user is associated with a personal wallet for managing funds.
    Secure Authentication: Leveraging Spring Security for robust authentication and authorization.
    Event-Driven Architecture: Utilizing Kafka for asynchronous messaging and event handling.

Technologies Used

    Spring Boot: For creating stand-alone, production-grade Spring-based applications.
    Spring Security: For authentication and access control.
    Apache Kafka: As a distributed event streaming platform.
    Java Persistence API (JPA): For database interaction.
    MySQL: As the primary database (modifiable as per requirements).
    

Getting Started
Prerequisites

    JDK 1.8 or later
    Maven 3.2+
    MySQL or PostgreSQL
    Apache Kafka

Running the Application

Clone the Repository



Configure Database Settings

Update application.properties with your database credentials and settings.

Start Apache Kafka

Make sure Kafka and Zookeeper are running. Refer to Kafka documentation for starting the Kafka server.

Build and Run

Navigate to the project directory and execute:


mvn clean install
mvn spring-boot:run

Accessing the Application

The application will be running at http://localhost:8080.

API Endpoints

User Registration: POST /signUp
User Retrieval: GET /user/{id}
Create Transaction: POST /transaction
Get Transaction: GET /transaction/{id}
