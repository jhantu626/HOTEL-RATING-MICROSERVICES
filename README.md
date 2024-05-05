# Hotel Rating Microservice with Spring Boot

This project implements a hotel rating system using Spring Boot microservices for modularity, scalability, and fault tolerance.

## Project Structure

The project consists of six independent Spring Boot applications:

1. **User Service**: Manages user accounts, including CRUD operations and authentication.
2. **Hotel Service**: Manages hotel details, including CRUD operations.
3. **Rating Service**: Handles user ratings and reviews for hotels, storing user and hotel IDs for association.
4. **Service Registry (Eureka Server)**: Enables service discovery and registration for microservices communication.
5. **Config Server**: Provides centralized configuration management using remote YAML files.
6. **API Gateway**: Acts as a single entry point for clients to access APIs from all microservices.

## Microservice Interactions

- **User Service interacts with:**
  - Rating Service to retrieve ratings associated with a user.
  - Hotel Service to access hotel details related to user ratings.
- **Rating Service stores user and hotel IDs to track ratings.**

## Technologies Used

- Spring Boot: Rapid application development framework
- Spring Cloud: Collection of libraries for building microservices
- Eureka Server: Service discovery and registration
- Config Server: Centralized configuration management
- API Gateway: Single point of entry for API access
- Feign Client: Declarative HTTP client for microservice communication
- MySQL: Database for persistent storage

## Getting Started

### Prerequisites

- Java 17+
- Maven

### Clone the Project

```bash
https://github.com/jhantu626/HOTEL-RATING-MICROSERVICES.git
```


## Use code with caution.

- Replace `dev` with your desired profile (e.g., prod).
- Do `Database` configuraton or change as per your machine.
- Relode `Maven` dependencies.

## Config Server

By default, you will not get application.yaml file code because of security reason i had security credential so i did not prodive that.

## Deployment (Optional)

Consider containerization with Docker for deployment using Docker Compose or Kubernetes.

## Further Considerations

- Implement security measures like authentication and authorization (e.g., Spring Security) to protect your APIs.
- Integrate with a front-end application for user interactions.
- Consider asynchronous communication techniques (e.g., Spring Kafka) for handling high-volume rating requests.
- Implement logging and monitoring for service health and performance tracking.

## Disclaimer

This README.md is a high-level overview. For detailed configuration and usage instructions, refer to individual microservice documentation.

## Additional Notes

- Adhere to best practices for code organization, naming conventions, and testing.
- Document your code clearly for maintainability.
- Consider version control with Git for collaboration and version tracking.
