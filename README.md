# OpenConnect Project

## Description
OpenConnect is a Spring Boot-based application designed to provide secure connections to a MySQL database. It offers features for web applications with Thymeleaf-based frontend rendering and integration with VNPAY for payment processing.

## Prerequisites
- Java 17
- MySQL 8+ (running on localhost:3307)
- Maven (for building the project)

## Setup and Installation

### 1. Clone the repository
```bash
git clone https://github.com/QuanTA92/Graduation-Project_SEP490_NongSan.git
cd openconnect
```

### 2. Configure the `application.properties` file
Update the following properties for your MySQL connection and other configurations:

```properties
spring.datasource.url=jdbc:mysql://localhost:3307/openconnect
spring.datasource.username=<your.username>
spring.datasource.password=<your.password>
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=none
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

root.folder=D:\\b8\\exe\\openconnect\\openconnect\\src\\main\\resources\\templates\\image

server.port=8080
```

### 3. Build the project
Use Maven to build the project:
```bash
mvn clean install
```

### 4. Run the application
To run the project, execute:
```bash
mvn spring-boot:run
```
The application will be available at `http://localhost:8080`.

## Features
- Thymeleaf integration for HTML rendering
- MySQL database connection
- VNPAY integration for payment processing
