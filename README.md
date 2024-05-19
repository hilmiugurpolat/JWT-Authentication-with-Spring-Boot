# JWT Authentication with Spring Boot

This project demonstrates how to implement JWT (JSON Web Token) authentication using Spring Boot. It includes user registration, login, and securing endpoints using JWT tokens.

## Technologies Used

- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Hibernate (JPA)
- H2 Database (for simplicity)
- Maven

## Getting Started


### Installation

1. **Clone the repository:**

    ```sh
    git clone https://github.com/yourusername/jwt-auth-spring-boot.git
    cd jwt-auth-spring-boot
    ```

2. **Build the project:**

    ```sh
    mvn clean install
    ```

3. **Run the application:**

    ```sh
    mvn spring-boot:run
    ```

The application will start on `http://localhost:8080`.

## API Endpoints

### Register

- **URL:** `/api/auth/register`
- **Method:** `POST`
- **Request Body:**

    ```json
    {
        "email": "example@example.com",
        "username": "exampleuser",
        "password": "password123"
    }
    ```

- **Response:**

    ```json
    {
        "message": "User registered successfully"
    }
    ```

### Login

- **URL:** `/api/auth/login`
- **Method:** `POST`
- **Request Body:**

    ```json
    {
        "username": "exampleuser",
        "password": "password123"
    }
    ```

- **Response:**

    ```json
    {
        "token": "<JWT_TOKEN>"
    }
    ```

### Secured Endpoint

- **URL:** `/api/test`
- **Method:** `GET`
- **Headers:**
    - Key: `Authorization`
    - Value: `Bearer <JWT_TOKEN>`

- **Response:**

    ```json
    {
        "message": "Access granted!"
    }
    ```

## Configuration

### JWT Secret Key

The secret key used to sign the JWT is defined in the `JwtUtil` class:

```java
private String SECRET_KEY = "secret";
