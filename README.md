# Banking Microservice

This microservice is designed to process transactions, manage spending limits, and handle exchange rates in a banking system.

## Features

- Real-time transaction handling with limit checks.
- Storage and retrieval of monthly spending limits for goods and services.
- Fetching and caching of exchange rates for currency conversion.
- REST API endpoints for managing transactions, limits, and exchange rates.
- Integrated Swagger/OpenAPI documentation.

## Requirements

- Java 21 or higher
- PostgreSQL database
- Docker
- Docker Compose

### Running the Application

1. Clone this repository:
   ```https://github.com/AlexisIndustries/banktransactions```

2. Install Docker on your machine
3. Run:
   ```docker-compose up -d```
4. Go to http://localhost:8080/swagger-ui/index.html#/

### Swagger Documentation
After starting the application, the Swagger UI can be accessed at:
- [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Running Database Migrations
Liquibase is used for database migrations. Migrations will be applied automatically once app connects to DB.

## Notes
- Default spending limit is `$1000` for both categories if not specified.
- Exchange rates are cached and reused for better performance.
