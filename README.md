# Transaction API

RESTful API for managing accounts and processing financial transactions.

## Technologies

* **Java 21**
* **Spring Boot 3.2.5**
* **Gradle** (Build Tool)
* **H2 Database** (In-memory storage)
* **Flyway** (Database Migrations)
* **Docker & Docker Compose**

## Prerequisites

* Docker and Docker Compose installed.
* Java 21 (Only if running outside Docker).

## Running the Application

The project includes a helper script to build and run the application using Docker Compose.

1.  Grant execution permission to the script:
    ```bash
    chmod +x run.sh
    ```

2.  Run the application:
    ```bash
    ./run.sh
    ```

This command will:
* Compile the project using the Gradle Wrapper (Multi-stage build).
* Start the application container on port **8080**.
* Display application logs in the terminal.

To stop the application, press **Ctrl+C**.

### Manual Execution

To run locally without Docker:
```bash
./gradlew bootRun
```

## API Documentation

The application uses **SpringDoc** (OpenAPI 3) for documentation.

* **Swagger UI:** http://localhost:8080/swagger-ui/index.html
* **Root Redirect:** Accessing http://localhost:8080/ will automatically redirect to the Swagger UI.

## Database Access

The application uses an in-memory H2 database. You can access the console via the browser.

* **URL:** http://localhost:8080/h2-console
* **JDBC URL:** `jdbc:h2:mem:transactiondb`
* **User:** `dev`
* **Password:** `123`

## Testing

The project contains unit and integration tests. The integration tests utilize **Testcontainers** to ensure environment consistency.

To run tests, use the following command:
```bash
./gradlew test
```

## Architecture & Design Decisions

* **RFC 7807 Error Handling:** The API returns standardized error responses using the `ProblemDetail` specification via a global exception handler.
* **DTO Pattern:** Java Records are used for Request/Response objects to ensure immutability and decouple the internal domain model from the external API contract.
* **Database Migration:** Flyway is used to manage database schema changes, ensuring reproducible states between environments.
* **Dockerized Build:** The Dockerfile utilizes a multi-stage build process with a specific Gradle user home configuration to optimize caching and reduce image size.

