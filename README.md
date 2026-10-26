# 🏦 Spring Boot Transaction Management

### A Bank Account & Fund Transfer REST API using Spring Boot, Spring Data JPA, Hibernate, and PostgreSQL

<p align="center">

  ![Java](https://img.shields.io/badge/Java-23-orange?logo=openjdk)
  ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-REST%20API-6DB33F?logo=springboot)
  ![Spring Data JPA](https://img.shields.io/badge/Spring%20Data-JPA-blue)
  ![PostgreSQL](https://img.shields.io/badge/Database-PostgreSQL-4169E1?logo=postgresql)
  ![Maven](https://img.shields.io/badge/Build-Maven-C71A36?logo=apachemaven)
  
</p>

## 📌 About the Project

**Spring Boot Transaction Management** is a backend application that demonstrates how to manage bank accounts and transfer money securely using Spring's declarative transaction management.

The application provides REST APIs to create accounts, retrieve account information, and transfer funds between accounts. It uses **Spring Data JPA** and **Hibernate** to manage database operations, while PostgreSQL stores account details and transfer records.

The main objective of this project is to understand how the `@Transactional` annotation helps maintain data consistency when multiple database operations must succeed or roll back together.

## ✨ Key Features

- 🏦 **Account Management** — Create bank accounts and retrieve account details.
- 💸 **Fund Transfer** — Transfer money between two existing accounts.
- 🔄 **Transaction Management** — Use `@Transactional` to coordinate related database operations.
- 🗄️ **PostgreSQL Integration** — Store account balances and transfer records in a relational database.
- ⚙️ **Spring Data JPA** — Simplify persistence with repository interfaces.
- 🧠 **Hibernate Dirty Checking** — Automatically detect changes to managed entities.
- 🧱 **Layered Architecture** — Separate controllers, services, entities, and repositories.
- 🧪 **REST API Testing** — Test endpoints using Postman.

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Java 23 | Programming language |
| Spring Boot | Application framework |
| Spring Web MVC | REST API development |
| Spring Data JPA | Database access and repositories |
| Hibernate ORM | Object-relational mapping |
| PostgreSQL | Relational database |
| Maven | Dependency management and build |
| Lombok | Reduce boilerplate code, where configured |
| Postman | API testing |

## 🏗️ Project Architecture

The application follows a layered architecture.

```text
                 ┌─────────────────────┐
                 │   Client / Postman  │
                 └──────────┬──────────┘
                            │ HTTP Requests
                 ┌──────────▼──────────┐
                 │    Controllers      │
                 │ Account / Transfer  │
                 └──────────┬──────────┘
                            │
                 ┌──────────▼──────────┐
                 │      Services       │
                 │ Business Logic      │
                 │ @Transactional      │
                 └──────────┬──────────┘
                            │
                 ┌──────────▼──────────┐
                 │    Repositories     │
                 │   Spring Data JPA   │
                 └──────────┬──────────┘
                            │
                 ┌──────────▼──────────┐
                 │ Hibernate / JPA     │
                 └──────────┬──────────┘
                            │
                 ┌──────────▼──────────┐
                 │    PostgreSQL       │
                 └─────────────────────┘
```

### 📂 Project Structure

```text
Transactiondemo/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/qspider/Transactiondemo/
│       │       ├── controller/
│       │       │   ├── AccountController.java
│       │       │   └── TransferController.java
│       │       ├── entity/
│       │       │   ├── Account.java
│       │       │   └── TransferRecord.java
│       │       ├── repository/
│       │       │   ├── AccountRepository.java
│       │       │   └── TransferRepository.java
│       │       ├── service/
│       │       │   ├── AccountService.java
│       │       │   └── TransferService.java
│       │       └── TransactiondemoApplication.java
│       └── resources/
│           └── application.properties
├── .gitignore
├── pom.xml
└── README.md
```

*Note: The structure above reflects the expected package layout; adjust it if your actual project differs.*

## ⚙️ Getting Started

### 1. Prerequisites

Install the following before running the application:

- JDK 23, or the JDK version configured in your project
- Maven
- PostgreSQL
- Postman (optional, for API testing)

### 2. Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/spring-boot-transaction-management.git
```

Navigate to the project directory:

```bash
cd spring-boot-transaction-management
```

### 3. Create the Database

Open PostgreSQL and execute:

```sql
CREATE DATABASE student_db;
```

### 4. Configure Database Properties

Update `src/main/resources/application.properties`:

```properties
spring.application.name=Transactiondemo

spring.datasource.url=${DB_URL:jdbc:postgresql://localhost:5432/student_db}
spring.datasource.username=${DB_USERNAME:postgres}
spring.datasource.password=${DB_PASSWORD}

spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

Set the `DB_PASSWORD` environment variable to your local PostgreSQL password before starting the application. Never commit real credentials to GitHub.

### 5. Build and Run

Build the project:

```bash
mvn clean install
```

Start the application:

```bash
mvn spring-boot:run
```

Once started, the application should be available at:

`http://localhost:8080`

## 🔗 REST API Endpoints

The following endpoints correspond to the project's controller design.

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/accounts` | Create a bank account |
| `GET` | `/api/accounts` | Retrieve all accounts |
| `GET` | `/api/accounts/{id}` | Retrieve an account by ID |
| `POST` | `/api/transfers` | Transfer funds between accounts |

### 📝 1. Create a Bank Account

**Request**

```http
POST http://localhost:8080/api/accounts
Content-Type: application/json
```

**Request Body**

```json
{
  "holderName": "Rahul",
  "balance": 10000.00
}
```

Create a second account for the transfer example:

```json
{
  "holderName": "Amit",
  "balance": 5000.00
}
```

### 📋 2. Retrieve All Accounts

```http
GET http://localhost:8080/api/accounts
```

Returns the accounts available in the database.

### 🔍 3. Retrieve an Account by ID

```http
GET http://localhost:8080/api/accounts/1
```

Replace `1` with an existing account ID.

### 💸 4. Transfer Money

**Request**

```http
POST http://localhost:8080/api/transfers
Content-Type: application/json
```

**Request Body**

```json
{
  "fromAccountId": 1,
  "toAccountId": 2,
  "amount": 1000.00
}
```

Replace the IDs with the actual IDs returned when you create your accounts.

## 🔄 How Transaction Management Works

The fund-transfer operation involves multiple related database changes:

1. Retrieve the sender's account.
2. Retrieve the receiver's account.
3. Deduct the transfer amount from the sender.
4. Add the same amount to the receiver.
5. Save the transfer record.
6. Commit the transaction if the operation completes successfully.

The service layer uses Spring's `@Transactional` annotation to coordinate these operations.

```java
@Transactional
public TransferRecord transferMoney(/* transfer details */) {
    // Retrieve sender and receiver
    // Validate the transfer
    // Update both account balances
    // Save the transfer record
    // Return the transfer result
}
```

*This is an illustrative snippet, not a complete method implementation.*

### 🧩 Important Concepts

| Concept | Explanation |
|---|---|
| `@Transactional` | Defines a transaction boundary for the method. |
| `save()` | Persists a new entity or merges changes to an existing entity. |
| Dirty checking | Hibernate detects changes to managed entities and synchronizes them with the database. |
| Rollback | Reverses transactional database changes when a rollback is triggered. |
| Commit | Finalizes the transaction when it succeeds. |

**Important:** By default, Spring rolls back for unchecked exceptions and errors, not every possible exception. Correct rollback behavior depends on the transaction configuration and how failures are handled.

## 📊 Example Fund Transfer

Assume these initial account balances:

| Account Holder | Initial Balance |
|---|---:|
| Rahul | ₹10,000 |
| Amit | ₹5,000 |

After transferring ₹1,000 from Rahul to Amit:

| Account Holder | Final Balance |
|---|---:|
| Rahul | ₹9,000 |
| Amit | ₹6,000 |

The expected result is that the sender's balance decreases, the receiver's balance increases, and a transfer record is stored.

## 🎯 Learning Outcomes

Through this project, you can practice:

- Developing REST APIs using Spring Boot
- Implementing controller-service-repository architecture
- Connecting Spring Boot to PostgreSQL
- Mapping Java entities to database tables
- Using `JpaRepository` for CRUD operations
- Understanding `save()` and Hibernate dirty checking
- Applying `@Transactional` to business operations
- Understanding transaction commit and rollback

## 🚀 Future Enhancements

- [ ] Add transaction history APIs.
- [ ] Add validation for zero or negative transfer amounts.
- [ ] Prevent transfers when the sender has insufficient funds.
- [ ] Add centralized exception handling.
- [ ] Add unit and integration tests.
- [ ] Handle concurrent transfers safely.
- [ ] Add Spring Security and authentication.
- [ ] Add API documentation using OpenAPI/Swagger.

## 👨‍💻 Author

**Java & Spring Boot Learning Project**

Built to practice backend development, database integration, and transaction management.

## 📄 License

This project is intended for educational and learning purposes. Add a license file if you plan to distribute it under a specific open-source license.

---

⭐ If you find this project useful for learning Spring Boot transaction management, consider giving the repository a star!
