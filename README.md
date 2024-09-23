# Products App - SpringBoot

## Overview

This is a simple **Product Management Application** built with **Java Spring Boot**, allowing users to perform basic CRUD (Create, Read, Update, Delete) operations on products. The application also includes pagination and search functionality for efficient browsing and filtering of products. The backend interacts with a MySQL database and provides a REST API for managing the product data.

## Technologies Used

- **Java 8**: The core programming language for the application.
- **Spring Boot**: For creating the REST API and managing the backend.
- **Hibernate**: As the ORM (Object Relational Mapping) tool for interacting with the MySQL database.
- **MySQL 8**: The relational database used for storing product information.
- **Maven**: For managing dependencies and building the project.
- **JUnit 5**: For basic testing on the controller layer.
- **Mockito**: For mocking services in unit tests.

## Features

- Add a new product
- View all products (with pagination)
- Search products by name or ID
- Update product details
- Delete a product
- Basic tests for the controller layer using JUnit and Mockito

## Database Schema

The `Product` entity corresponds to the following table in MySQL:

```sql
CREATE TABLE PRODUCT_TBL (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    price DOUBLE NOT NULL,
    description VARCHAR(255) NOT NULL
);
```
- This table will be automatically created based on the Product entity when you run the application if the ddl-auto setting is update.

## How to Run the Application

1. **Clone the repository**:
    ```bash
    git clone https://github.com/jaggehns/products-app-springboot.git
    cd products-app-springboot
    ```

2. **Database Setup Instructions**:
    - Create a MySQL database:
        ```sql
        CREATE DATABASE product_db;
        ```
    - Update the `src/main/resources/application.properties` file with your MySQL credentials:
        ```properties
        spring.datasource.url=jdbc:mysql://localhost:3306/product_db
        spring.datasource.username=your_mysql_username
        spring.datasource.password=your_mysql_password
        ```
     - Once the database is created and credentials are updated, the Spring Boot application will handle table creation and data management automatically based on the `Product` entity.

3. **Build and run the application**:
    - Build the project using Maven:
        ```bash
        mvn clean install
        ```
    - Run the application:
        ```bash
        mvn spring-boot:run
        ```

4. **Seed the database** (Optional):
    - If you want to seed the database with sample data, run the following command:
        ```bash
        mvn spring-boot:run -Dspring-boot.run.arguments="--app.seedDatabase=true"
        ```
    - This will help with testing, especially the pagination and search.

## REST API Endpoints

Below are the main API endpoints for the application:

- **GET** `/api/product/getAllProducts?page=0&size=10`: Get a paginated list of all products.
- **POST** `/api/product/addProduct`: Add a new product.
  - Example payload:
    ```json
    {
      "name": "Product Name",
      "quantity": 10,
      "price": 100.0,
      "description": "Product description"
    }
    ```
- **GET** `/api/product/productById/{id}`: Get details of a product by ID.
- **GET** `/api/product/getAllProducts?search={query}`: Search for products by name or ID.
- **PUT** `/api/product/updateProduct`: Update an existing product.
  - Example payload:
    ```json
    {
      "id": 1,
      "name": "Updated Product Name",
      "quantity": 20,
      "price": 150.0,
      "description": "Updated description"
    }
    ```
- **DELETE** `/api/product/delete/{id}`: Delete a product by ID.

## Running Tests

Basic unit tests are written for the controller layer, testing the endpoints and service interactions. To run the tests, use the following command:
```bash
mvn test
```

The test results will indicate whether the controller's basic functionality works as expected.

# Repository Structure

The following is the structure of the project repository:

```bash
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── jaggehn/
│   │           ├── SpringBootProductsApplication.java
│   │           ├── config/
│   │           │   ├── CorsConfig.java
│   │           │   └── ModelMapperConfig.java
│   │           ├── controller/
│   │           │   └── ProductController.java
│   │           ├── dto/
│   │           │   └── ProductDTO.java
│   │           ├── entity/
│   │           │   └── Product.java
│   │           ├── exceptions/
│   │           │   └── GlobalExceptionHandler.java
│   │           ├── repository/
│   │           │   └── ProductRepository.java
│   │           ├── scripts/
│   │           │   └── DataLoader.java
│   │           └── service/
│   │               └── ProductService.java
├── resources/
│   ├── application.properties
├── test/
│   ├── java/
│   │   └── com/
│   │       └── jaggehn/
│   │           ├── controller/
│   │           │   └── ProductControllerTest.java
│   │           ├── SpringBootProductsApplicationTests.java
├── .gitignore
├── pom.xml
├── mvnw
├── mvnw.cmd

```
## Key Folders and Files

- **`src/main/java/com/jaggehn`**: Contains all the source code, including:
  - **`controller/`**: Holds the controllers such as `ProductController.java` that handle the HTTP requests.
  - **`service/`**: Contains business logic and service-level code such as `ProductService.java`.
  - **`repository/`**: Contains the repository interface to interact with the database, such as `ProductRepository.java`.
  - **`dto/`**: Data Transfer Object to handle communication between services and controllers.
  - **`entity/`**: Defines the database entity `Product.java`.
  - **`exceptions/`**: Global exception handling for the application.
  - **`config/`**: Configuration files for CORS and `ModelMapper` setup.

- **`src/main/resources/`**: Contains the configuration files and static resources, including:
  - **`application.properties`**: Configuration file for database settings and other properties.

- **`src/test/java/com/jaggehn/`**: Contains the test classes for controllers and services.

- **`pom.xml`**: Maven build configuration file.

- **`mvnw` and `mvnw.cmd`**: Maven wrapper scripts for UNIX and Windows, respectively.

## Future Work

- **Additional Tests**: While basic tests for the controller layer are already included, additional tests can be written for the service layer and repository layer to ensure all edge cases are handled.

- **Validation**: Adding more robust validation, such as constraints on product name length or price ranges, and handling specific validation errors gracefully.

- **Security**: Implement security features using **Spring Security** for authentication and authorization to protect sensitive endpoints like adding, updating, or deleting products.

- **Caching**: Introduce caching mechanisms (e.g., **Redis** or **Ehcache**) to improve performance by caching frequently accessed product data.
