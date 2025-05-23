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