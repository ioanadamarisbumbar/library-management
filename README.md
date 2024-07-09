# library-management

**This project implements a Spring Boot API for managing library resources, including authors and books.**

## Technologies Used
* Spring Boot: Framework for building microservices
* Spring Data MongoDB: Provides interaction with MongoDB databases
* Mockito: Mocking framework for unit testing
* JUnit: Unit testing framework


## Project Structure
* src/main/java: Contains the main application codebase.
    * com.library.library_management: Main package for the application classes
        * controller: Provides REST controllers for interacting with the API.
        * dto: Data Transfer Objects (DTOs) for representing data exchanged with the API.
        * model: Domain model classes representing entities (e.g., Author, Book).
        * service: Interfaces and implementations for business logic of the application.
        * exception: Custom exception classes for handling errors.
* src/test/java: Contains unit and integration tests for the application code.
* pom.xml: Defines project dependencies and build configuration.

## Running the Application

### MongoDB
* Ensure you have an account on MongoDB Atlas Database
* Connect to the cluster using the Shell option
* Download or install mongosh based on your operating system
* In a terminal run the connection string provided in the installation guide

### Java Project
* Ensure you have Java 17 and Maven installed.
* Clone or download the project repository.
* Open a terminal in the project directory.
* Run ```mvn clean install``` to build the project.
* Run ```mvn spring-boot:run``` to start the application.

_By default, the application will run on port 8082. You can access the API endpoints at the following base URLs:_ 
* http://localhost:8082/authors for author management.
* http://localhost:8082/books for book management.

### OpenAPI
* Access _http://localhost:8080/api-docs.yaml_ to download the .yaml file.
* Go to _http://localhost:8080/swagger-ui/index.html_ to test the endpoints.

## API Endpoints

### Authors:
* /authors (GET): Retrieves a list of all authors.
* /authors/{id} (GET): Retrieves a specific author by their ID.
* /authors (POST): Creates a new author.
* /authors/{id} (PATCH): Updates an existing author.
* /authors/{id} (DELETE): Deletes an author.

### Books:
* /books (GET): Retrieves a list of all books.
* /books/{id} (GET): Retrieves a specific book by their ID.
* /books (POST): Creates a new book.
* /books/{id} (PATCH): Updates an existing book.
* /books/{id} (DELETE): Deletes a book.

## Testing
This project includes unit and integration tests to ensure code quality and functionality.

* Unit Tests: Use Mockito to mock dependencies and test individual service methods in isolation.
* Integration Tests: Simulate API requests using MockMvc to test controller logic and integration with the service layer.
