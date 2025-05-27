# User Service - Xeppelin Platform

## 📋 Description

The **User Service** is a microservice of the Xeppelin platform designed to manage users and their profiles. It implements a hexagonal architecture (ports and adapters) with Domain-Driven Design (DDD) principles to ensure clean, maintainable, and scalable code.

## 🏗️ Architecture

This service follows Clean Architecture and DDD principles:
- **Hexagonal Architecture (Ports & Adapters)**
- **Domain-Driven Design (DDD)**
- **SOLID Principles**
- **API-First Design**

## 📁 Project Structure

```
user-service/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── xeppelin/
│   │   │           └── userservice/
│   │   │               ├── UserServiceApplication.java       # Application entry point
│   │   │               ├── domain/                          # Domain layer - business logic
│   │   │               │   ├── model/                       # Domain entities and aggregates
│   │   │               │   │   ├── User.java               # Main User entity
│   │   │               │   │   ├── Address.java            # Address entity
│   │   │               │   │   ├── UserRole.java           # User roles enum
│   │   │               │   │   └── UserStatus.java         # User status enum
│   │   │               │   ├── service/                     # Domain services
│   │   │               │   │   ├── UserDomainService.java  # Service interface
│   │   │               │   │   └── impl/                   # Implementations
│   │   │               │   │       └── UserDomainServiceImpl.java
│   │   │               │   └── exception/                   # Domain-specific exceptions
│   │   │               │       ├── UserDomainException.java
│   │   │               │       └── NotFoundException.java
│   │   │               ├── application/                     # Use cases and primary ports
│   │   │               │   ├── port/                        # Input and output ports
│   │   │               │   │   ├── input/                   # Input ports (primary ports)
│   │   │               │   │   │   └── UserManagementUseCase.java
│   │   │               │   │   └── output/                  # Output ports (secondary ports)
│   │   │               │   │       └── UserRepository.java
│   │   │               │   └── service/                     # Use case implementations
│   │   │               │       └── UserApplicationService.java
│   │   │               └── infrastructure/                  # Adapters and configurations
│   │   │                   ├── config/                      # Application configurations
│   │   │                   │   ├── DatabaseConfiguration.java
│   │   │                   │   ├── CacheConfiguration.java
│   │   │                   │   └── OpenApiConfiguration.java
│   │   │                   └── adapter/                     # Adapters
│   │   │                       ├── input/                   # Input adapters
│   │   │                       │   ├── rest/                # REST controllers
│   │   │                       │   │   ├── IUserController.java        # API-First interface
│   │   │                       │   │   ├── impl/                       # Implementations
│   │   │                       │   │   │   └── UserControllerImpl.java
│   │   │                       │   │   ├── request/                    # Request DTOs
│   │   │                       │   │   │   ├── UserRequest.java
│   │   │                       │   │   │   └── AddressRequest.java
│   │   │                       │   │   ├── response/                   # Response DTOs
│   │   │                       │   │   │   ├── UserResponse.java
│   │   │                       │   │   │   ├── AddressResponse.java
│   │   │                       │   │   │   └── PagedResponse.java
│   │   │                       │   │   ├── mapper/                     # REST mappers
│   │   │                       │   │   │   └── UserControllerMapper.java
│   │   │                       │   │   └── exception/                  # REST exception handling
│   │   │                       │   │       └── ErrorResponse.java
│   │   │                       │   └── messaging/           # Message consumers
│   │   │                       └── output/                  # Output adapters
│   │   │                           ├── persistence/         # JPA persistence
│   │   │                           │   ├── entity/          # JPA entities
│   │   │                           │   │   ├── UserEntity.java
│   │   │                           │   │   └── AddressEntity.java
│   │   │                           │   ├── repository/      # JPA repositories
│   │   │                           │   │   └── UserJpaRepository.java
│   │   │                           │   ├── adapter/         # Persistence adapter
│   │   │                           │   │   └── UserPersistenceAdapter.java
│   │   │                           │   └── mapper/          # JPA mappers
│   │   │                           │       └── UserEntityMapper.java
│   │   │                           └── client/              # External clients
│   │   └── resources/
│   │       ├── db/
│   │       │   └── migration/                              # Flyway migration scripts
│   │       │       ├── V1__Create_users_table.sql
│   │       │       └── V2__Create_addresses_table.sql
│   │       ├── application.yml                             # Main configuration
│   │       └── application-dev.yml                         # Development configuration
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── xeppelin/
│       │           └── userservice/
│       │               ├── integration/                    # Integration tests
│       │               └── infrastructure/
│       │                   └── adapter/
│       │                       └── input/
│       │                           └── rest/
│       │                               └── impl/
│       │                                   └── UserControllerImplTest.java
│       └── resources/
├── build.gradle                                           # Dependencies configuration
├── gradle/
│   └── wrapper/
└── README.md                                              # This file
```

## 🚀 Technology Stack

- **Java 21** - Programming language
- **Spring Boot 3.4.6** - Main framework
- **Spring Data JPA** - Data persistence
- **Spring Cloud** - Microservices
- **PostgreSQL** - Main database
- **Redis** - Distributed cache
- **Flyway** - Database migrations
- **MapStruct** - Object mapping
- **Lombok** - Boilerplate reduction
- **Gradle 8.13** - Build tool
- **JUnit 5** - Testing
- **Testcontainers** - Integration testing
- **OpenAPI 3** - API documentation

## 📡 API Endpoints

### Base URL
```
http://localhost:9001/api/user-service
```

### Available Endpoints

#### 1. Create User
```http
POST /users
Content-Type: application/json
```

**Request Body:**
```json
{
  "name": "John Doe",
  "email": "john.doe@xeppelin.com",
  "role": "ATTENDEE",
  "status": "ACTIVE",
  "address": {
    "line1": "123 Main Street",
    "line2": "Apt 4B",
    "city": "New York",
    "state": "NY",
    "postalCode": "10001",
    "country": "United States",
    "phoneNumber": "+1-555-123-4567"
  }
}
```

**Response (201 Created):**
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "name": "John Doe",
  "email": "john.doe@xeppelin.com",
  "role": "ATTENDEE",
  "status": "ACTIVE",
  "address": {
    "id": "660e8400-e29b-41d4-a716-446655440001",
    "line1": "123 Main Street",
    "line2": "Apt 4B",
    "city": "New York",
    "state": "NY",
    "postalCode": "10001",
    "country": "United States",
    "phoneNumber": "+1-555-123-4567",
    "formattedAddress": "123 Main Street, Apt 4B, New York, NY 10001, United States"
  }
}
```

#### 2. Get User by ID
```http
GET /users/{userId}
```

**Response (200 OK):**
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "name": "John Doe",
  "email": "john.doe@xeppelin.com",
  "role": "ATTENDEE",
  "status": "ACTIVE",
  "address": { ... }
}
```

#### 3. Get All Users (Paginated)
```http
GET /users?page=0&size=10&sort=name,asc
```

**Query Parameters:**
- `page` (int): Page number (0-based, default: 0)
- `size` (int): Page size (default: 20)
- `sort` (string): Field and direction for sorting (e.g., `name,asc`)

**Response (200 OK):**
```json
{
  "content": [
    {
      "id": "550e8400-e29b-41d4-a716-446655440000",
      "name": "John Doe",
      "email": "john.doe@xeppelin.com",
      "role": "ATTENDEE",
      "status": "ACTIVE",
      "address": { ... }
    }
  ],
  "metadata": {
    "size": 10,
    "page": 0,
    "totalElements": 1,
    "totalPages": 1
  }
}
```

#### 4. Update User
```http
PUT /users/{userId}
Content-Type: application/json
```

**Request Body:** (same format as POST)

**Response (200 OK):** (updated user)

#### 5. Delete User
```http
DELETE /users/{userId}
```

**Response (204 No Content)**

### Status Codes

| Code | Description |
|------|-------------|
| 200 | OK - Successful operation |
| 201 | Created - User created successfully |
| 204 | No Content - User deleted successfully |
| 400 | Bad Request - Validation error |
| 404 | Not Found - User not found |
| 409 | Conflict - Duplicate email |
| 500 | Internal Server Error - Internal error |

### User Roles

| Role | Description |
|------|-------------|
| `ADMIN` | System administrator |
| `ORGANIZER` | Event organizer |
| `STAFF` | Support staff |
| `ATTENDEE` | Event attendee |

### User Status

| Status | Description |
|--------|-------------|
| `ACTIVE` | Active user |
| `INACTIVE` | Inactive user |
| `SUSPENDED` | Suspended user |

## 🛠️ Gradle Commands

### Build and Run

```bash
# Clean project
./gradlew clean

# Compile project
./gradlew build

# Compile without tests
./gradlew build -x test

# Run application
./gradlew bootRun

# Run in development mode (with hot reload)
./gradlew bootRun --continuous
```

### Testing

```bash
# Run all tests
./gradlew test

# Run tests with coverage report
./gradlew test jacocoTestReport

# Run integration tests
./gradlew integrationTest

# Run specific test
./gradlew test --tests "UserControllerImplTest"

# Run tests in continuous mode
./gradlew test --continuous
```

### Database

```bash
# Run Flyway migrations
./gradlew flywayMigrate

# Flyway information
./gradlew flywayInfo

# Clean database (CAUTION!)
./gradlew flywayClean
```

### Dependencies

```bash
# View project dependencies
./gradlew dependencies

# View outdated dependencies
./gradlew dependencyUpdates

# Generate dependency report
./gradlew dependencyInsight --dependency spring-boot-starter-web
```

### Code Analysis

```bash
# Static analysis with Checkstyle
./gradlew checkstyleMain

# Analysis with SpotBugs
./gradlew spotbugsMain

# Quality verification
./gradlew check
```

### Docker

```bash
# Build Docker image
./gradlew bootBuildImage

# Build image with custom tag
./gradlew bootBuildImage --imageName=xeppelin/user-service:latest
```

## ⚙️ Configuration

### Environment Variables

| Variable | Description | Default Value |
|----------|-------------|---------------|
| `DB_HOST` | PostgreSQL host | `localhost` |
| `DB_PORT` | PostgreSQL port | `5432` |
| `DB_NAME` | Database name | `user_db` |
| `DB_USER` | Database user | `user` |
| `DB_PASSWORD` | Database password | `password` |
| `REDIS_HOST` | Redis host | `localhost` |
| `REDIS_PORT` | Redis port | `6379` |
| `REDIS_PASSWORD` | Redis password | *(empty)* |

### Spring Profiles

#### Development (default)
```bash
./gradlew bootRun
# or
./gradlew bootRun --args='--spring.profiles.active=dev'
```

#### Production
```bash
./gradlew bootRun --args='--spring.profiles.active=prod'
```

#### Testing
```bash
./gradlew bootRun --args='--spring.profiles.active=test'
```

## 🔧 Development Setup

### Prerequisites

1. **Java 21** or higher
2. **PostgreSQL 13+**
3. **Redis 6+**
4. **Gradle 8.13** (included via wrapper)

### Local Configuration

1. **Clone the repository:**
```bash
git clone <repository-url>
cd Xeppelin-Platform/user-service
```

2. **Configure PostgreSQL:**
```sql
-- Create database
CREATE DATABASE user_db;

-- Create user
CREATE USER user WITH PASSWORD 'password';
GRANT ALL PRIVILEGES ON DATABASE user_db TO user;
```

3. **Start Redis:**
```bash
# With Docker
docker run -d -p 6379:6379 redis:latest

# Or local installation
redis-server
```

4. **Run the application:**
```bash
./gradlew bootRun
```

### Docker Compose Configuration

Create `docker-compose.yml` in the root directory:

```yaml
version: '3.8'
services:
  postgres:
    image: postgres:15
    environment:
      POSTGRES_DB: user_db
      POSTGRES_USER: user
      POSTGRES_PASSWORD: password
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

  redis:
    image: redis:7
    ports:
      - "6379:6379"

  user-service:
    build: .
    ports:
      - "9001:9001"
    environment:
      DB_HOST: postgres
      REDIS_HOST: redis
    depends_on:
      - postgres
      - redis

volumes:
  postgres_data:
```

Run:
```bash
docker-compose up -d
```

## 🐳 Docker Guide

This section provides a comprehensive guide to build, run, and manage the User Service Docker image.

### Prerequisites

- **Docker 20.10+** installed and running
- **Docker Compose 2.0+** (for multi-container setup)
- **Java 21** (for local builds)

### Building Docker Image

#### Method 1: Using Gradle (Recommended)

Spring Boot provides built-in support for creating optimized Docker images using Cloud Native Buildpacks:

```bash
# Navigate to user-service directory
cd user-service

# Build Docker image with default name
./gradlew bootBuildImage

# Build with custom image name and tag
./gradlew bootBuildImage --imageName=xeppelin/user-service:latest

# Build with specific tag version
./gradlew bootBuildImage --imageName=xeppelin/user-service:1.0.0
```

**Image Details:**
- **Base Image**: `paketobuildpacks/run:base-cnb` (optimized for Spring Boot)
- **Size**: ~300MB (layered and optimized)
- **Architecture**: linux/amd64
- **JVM**: Eclipse Temurin 21

#### Method 2: Using Dockerfile

Create a `Dockerfile` in the user-service root directory:

```dockerfile
# Multi-stage build for optimization
FROM gradle:8.13-jdk21 AS builder

WORKDIR /app
COPY build.gradle settings.gradle ./
COPY gradle gradle
COPY src src

# Build the application
RUN gradle bootJar --no-daemon

# Runtime stage
FROM eclipse-temurin:21-jre-alpine

# Create application user for security
RUN addgroup -g 1001 -S appgroup && \
    adduser -u 1001 -S appuser -G appgroup

# Set working directory
WORKDIR /app

# Copy JAR from builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Change ownership to app user
RUN chown -R appuser:appgroup /app
USER appuser

# Expose port
EXPOSE 9001

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
    CMD curl -f http://localhost:9001/api/user-service/actuator/health || exit 1

# Run application
ENTRYPOINT ["java", "-jar", "app.jar"]
```

Build the image:

```bash
# Build with Dockerfile
docker build -t xeppelin/user-service:latest .

# Build with specific context and tag
docker build -t xeppelin/user-service:1.0.0 -f Dockerfile .
```

### Running Docker Container

#### Standalone Container

```bash
# Run with default configuration
docker run -p 9001:9001 xeppelin/user-service:latest

# Run with environment variables
docker run -p 9001:9001 \
  -e DB_HOST=host.docker.internal \
  -e DB_PORT=5432 \
  -e DB_NAME=user_db \
  -e DB_USER=user \
  -e DB_PASSWORD=password \
  -e REDIS_HOST=host.docker.internal \
  -e REDIS_PORT=6379 \
  xeppelin/user-service:latest

# Run in background (detached mode)
docker run -d -p 9001:9001 --name user-service xeppelin/user-service:latest

# Run with custom JVM options
docker run -p 9001:9001 \
  -e JAVA_OPTS="-Xms512m -Xmx1024m -XX:+UseG1GC" \
  xeppelin/user-service:latest
```

#### Using Docker Compose (Complete Stack)

Create `docker-compose.yml`:

```yaml
version: '3.8'

services:
  # PostgreSQL Database
  postgres:
    image: postgres:15-alpine
    container_name: user-service-postgres
    environment:
      POSTGRES_DB: user_db
      POSTGRES_USER: user
      POSTGRES_PASSWORD: password
      POSTGRES_INITDB_ARGS: "--encoding=UTF-8"
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data
      - ./init-db:/docker-entrypoint-initdb.d
    networks:
      - user-service-network
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U user -d user_db"]
      interval: 30s
      timeout: 10s
      retries: 3

  # Redis Cache
  redis:
    image: redis:7-alpine
    container_name: user-service-redis
    ports:
      - "6379:6379"
    volumes:
      - redis_data:/data
    networks:
      - user-service-network
    healthcheck:
      test: ["CMD", "redis-cli", "ping"]
      interval: 30s
      timeout: 10s
      retries: 3
    command: redis-server --appendonly yes

  # User Service Application
  user-service:
    image: xeppelin/user-service:latest
    container_name: user-service-app
    ports:
      - "9001:9001"
    environment:
      # Database configuration
      DB_HOST: postgres
      DB_PORT: 5432
      DB_NAME: user_db
      DB_USER: user
      DB_PASSWORD: password
      
      # Redis configuration
      REDIS_HOST: redis
      REDIS_PORT: 6379
      
      # Spring profiles
      SPRING_PROFILES_ACTIVE: docker
      
      # JVM tuning
      JAVA_OPTS: >-
        -Xms512m 
        -Xmx1024m 
        -XX:+UseG1GC 
        -XX:MaxGCPauseMillis=200
        -Dspring.jpa.show-sql=false
    depends_on:
      postgres:
        condition: service_healthy
      redis:
        condition: service_healthy
    networks:
      - user-service-network
    healthcheck:
      test: ["CMD", "curl", "-f", "http://localhost:9001/api/user-service/actuator/health"]
      interval: 30s
      timeout: 10s
      retries: 3
      start_period: 60s
    restart: unless-stopped

volumes:
  postgres_data:
    driver: local
  redis_data:
    driver: local

networks:
  user-service-network:
    driver: bridge
```

**Start the complete stack:**

```bash
# Start all services
docker-compose up -d

# View logs
docker-compose logs -f user-service

# Stop all services
docker-compose down

# Stop and remove volumes
docker-compose down -v
```

### Docker Commands Reference

#### Image Management

```bash
# List Docker images
docker images

# Remove image
docker rmi xeppelin/user-service:latest

# Pull image from registry
docker pull xeppelin/user-service:latest

# Tag image
docker tag xeppelin/user-service:latest xeppelin/user-service:1.0.0

# Push to registry (if you have access)
docker push xeppelin/user-service:latest

# Inspect image details
docker inspect xeppelin/user-service:latest

# View image history/layers
docker history xeppelin/user-service:latest
```

#### Container Management

```bash
# List running containers
docker ps

# List all containers
docker ps -a

# Stop container
docker stop user-service

# Start stopped container
docker start user-service

# Restart container
docker restart user-service

# Remove container
docker rm user-service

# View container logs
docker logs user-service

# Follow logs in real-time
docker logs -f user-service

# Execute command in running container
docker exec -it user-service bash

# View container resource usage
docker stats user-service
```

#### Troubleshooting

```bash
# Check container health
docker inspect user-service | grep -A 10 '"Health"'

# View detailed container information
docker inspect user-service

# Check port bindings
docker port user-service

# View container processes
docker top user-service

# Copy files from container
docker cp user-service:/app/logs ./local-logs

# View container filesystem changes
docker diff user-service
```

### Docker Registry

#### Pushing to Registry

```bash
# Login to Docker Hub (or your registry)
docker login

# Tag for registry
docker tag xeppelin/user-service:latest your-registry/xeppelin/user-service:latest

# Push to registry
docker push your-registry/xeppelin/user-service:latest
```

#### Pulling from Registry

```bash
# Pull specific version
docker pull your-registry/xeppelin/user-service:1.0.0

# Pull latest version
docker pull your-registry/xeppelin/user-service:latest
```

### Performance Optimization

#### Memory Settings

```bash
# Run with optimized memory settings
docker run -p 9001:9001 \
  -e JAVA_OPTS="-Xms256m -Xmx512m -XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0" \
  xeppelin/user-service:latest
```

#### Resource Limits

```bash
# Run with resource constraints
docker run -p 9001:9001 \
  --memory=1g \
  --cpus=1.0 \
  --memory-swap=1g \
  xeppelin/user-service:latest
```

### Security Best Practices

The Docker image includes several security measures:

- **Non-root user**: Application runs as `appuser` (UID 1001)
- **Minimal base image**: Uses Alpine Linux for smaller attack surface
- **Health checks**: Built-in health monitoring
- **No secrets in image**: Environment variables for sensitive data
- **Read-only filesystem**: Application directory is read-only

### Environment Configuration

Create a `.env` file for environment variables:

```bash
# Database
DB_HOST=localhost
DB_PORT=5432
DB_NAME=user_db
DB_USER=user
DB_PASSWORD=secure_password

# Redis
REDIS_HOST=localhost
REDIS_PORT=6379
REDIS_PASSWORD=

# Application
SPRING_PROFILES_ACTIVE=docker
SERVER_PORT=9001

# JVM
JAVA_OPTS=-Xms512m -Xmx1024m -XX:+UseG1GC
```

Use with Docker Compose:

```bash
docker-compose --env-file .env up -d
```

### Quick Start Commands

```bash
# Complete setup from scratch
git clone <repository-url>
cd Xeppelin-Platform/user-service

# Build image
./gradlew bootBuildImage --imageName=xeppelin/user-service:latest

# Start with Docker Compose
docker-compose up -d

# Verify service is running
curl http://localhost:9001/api/user-service/actuator/health

# View logs
docker-compose logs -f user-service

# Access Swagger UI
open http://localhost:9001/api/user-service/swagger-ui.html
```

## 📚 API Documentation

### Swagger UI
Once the application is running, you can access the interactive documentation:

- **Swagger UI**: http://localhost:9001/api/user-service/swagger-ui.html
- **OpenAPI JSON**: http://localhost:9001/api/user-service/v3/api-docs
- **OpenAPI YAML**: http://localhost:9001/api/user-service/v3/api-docs.yaml

### Health Check
```bash
curl http://localhost:9001/api/user-service/actuator/health
```

### Metrics
```bash
# General metrics
curl http://localhost:9001/api/user-service/actuator/metrics

# Specific metric
curl http://localhost:9001/api/user-service/actuator/metrics/jvm.memory.used
```

## 🧪 Testing

### Postman Collection

Import the `User-Service-API.postman_collection.json` collection into Postman to test all endpoints automatically.

### cURL Examples

```bash
# Create user
curl -X POST http://localhost:9001/api/user-service/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john.doe@xeppelin.com",
    "role": "ATTENDEE",
    "status": "ACTIVE",
    "address": {
      "line1": "123 Main Street",
      "city": "New York",
      "state": "NY",
      "postalCode": "10001",
      "country": "United States",
      "phoneNumber": "+1-555-123-4567"
    }
  }'

# Get user
curl http://localhost:9001/api/user-service/users/{userId}

# List users
curl "http://localhost:9001/api/user-service/users?page=0&size=10&sort=name,asc"
```

## 🐛 Troubleshooting

### Common Issues

1. **Database connection error:**
   - Verify PostgreSQL is running
   - Confirm credentials in `application.yml`
   - Verify `user_db` database exists

2. **Redis connection error:**
   - Verify Redis is running
   - Confirm host and port configuration

3. **Port 9001 in use:**
   ```bash
   # Change port in application.yml
   server:
     port: 9002
   ```

4. **Tests failing:**
   ```bash
   # Clean and run tests
   ./gradlew clean test
   
   # Verify Testcontainers (Docker must be running)
   docker ps
   ```

## 📈 Monitoring and Observability

### Actuator Endpoints

| Endpoint | Description |
|----------|-------------|
| `/actuator/health` | Application health status |
| `/actuator/info` | Application information |
| `/actuator/metrics` | Application metrics |
| `/actuator/prometheus` | Prometheus format metrics |
| `/actuator/loggers` | Logging configuration |

### Custom Metrics

The service includes custom metrics for:
- Number of users created
- Endpoint response times
- Cache usage
- Validation errors

## 🔒 Security

### Implemented Validations

- Email format validation
- Required field validation
- Email uniqueness
- Input sanitization

### Security Headers

The service includes standard security headers configured via Spring Security.

## 🚀 Deployment

### Production Build

```bash
# Generate executable JAR
./gradlew bootJar

# JAR will be in: build/libs/user-service-0.0.1-SNAPSHOT.jar

# Run JAR
java -jar build/libs/user-service-0.0.1-SNAPSHOT.jar
```

### Production Environment Variables

```bash
export DB_HOST=prod-postgres-host
export DB_PORT=5432
export DB_NAME=user_db_prod
export DB_USER=user_prod
export DB_PASSWORD=secure_password
export REDIS_HOST=prod-redis-host
export REDIS_PORT=6379
export SPRING_PROFILES_ACTIVE=prod
```

## 📞 Support

For technical support or to report issues:

- **Email**: platform@xeppelin.com
- **Documentation**: [Project Wiki]
- **Issues**: [GitHub Issues]

---

**Version**: 1.0.0  
**Last Updated**: January 2025  
**Team**: Xeppelin Platform Team