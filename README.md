

```markdown
# Pet Store REST API

This project is a Spring Boot-based REST API for a Pet Store application. It was built as part of a coding assignment to demonstrate the following:

- **Designing an Entity Relationship Diagram (ERD):** Created using Draw.io to map out our database schema.
- **Maven Project Setup:** Initialized and configured via Spring Initializr with a custom `pom.xml`.
- **JPA Entity Creation:** Developed `Customer`, `Employee`, and `PetStore` classes with proper annotations and relationships.
- **Spring Boot & JPA Configuration:** Configured the application to automatically generate MySQL database tables from our entity classes.
- **Database Schema Generation:** Verified that the `customer`, `employee`, `pet_store`, and `pet_store_customer` tables are automatically created in the MySQL `pet_store` schema.
- **REST Endpoints & Global Error Handling:** Implemented CRUD operations for creating, updating, retrieving, and deleting pet stores, as well as endpoints to add employees and customers.

## Technologies Used

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **MySQL**
- **Lombok**
- **Maven**

## Project Structure

```bash
pet-store/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── pet/
│   │   │       └── store/
│   │   │           ├── controller/
│   │   │           │   ├── error/
│   │   │           │   │   └── GlobalErrorHandler.java
│   │   │           │   └── model/
│   │   │           │       └── PetStoreData.java
│   │   │           ├── dao/
│   │   │           │   ├── CustomerDao.java
│   │   │           │   ├── EmployeeDao.java
│   │   │           │   └── PetStoreDao.java
│   │   │           ├── entity/
│   │   │           │   ├── Customer.java
│   │   │           │   ├── Employee.java
│   │   │           │   └── PetStore.java
│   │   │           ├── service/
│   │   │           │   └── PetStoreService.java
│   │   │           └── PetStoreApplication.java
│   │   └── resources/
│   │       └── application.yaml
├── pom.xml
└── README.md
```

## Database Configuration

The application uses MySQL. The key configuration is found in `src/main/resources/application.yaml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/pet_store
    username: pet_store
    password: pet_store
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update  # Use 'update' to preserve schema between restarts
    show-sql: true
  sql:
    init:
      mode: always
```

> **Note:** Ensure that the `pet_store` schema is created in your MySQL database and that the user has the appropriate permissions.

## How It Works

### Entity Relationships

- **PetStore Entity:**  
  Represents a pet store with attributes such as name, address, and phone.  
  - **One-to-Many Relationship:** Each pet store has many employees.
  - **Many-to-Many Relationship:** Each pet store can be associated with many customers via a join table (`pet_store_customer`).

- **Employee & Customer Entities:**  
  - **Employee:** Has a many-to-one relationship with a pet store.
  - **Customer:** Maintains a many-to-many relationship with pet stores.

### REST API Endpoints

The API exposes the following endpoints:

- **Create Pet Store:**  
  - **Endpoint:** `POST /pet_store`  
  - **Description:** Creates a new pet store.  
  - **Sample JSON:**
    ```json
    {
      "petStoreName": "Happy Pets",
      "petStoreAddress": "123 Pet Lane",
      "petStoreCity": "Petville",
      "petStoreState": "CA",
      "petStoreZip": "90001",
      "petStorePhone": "123-456-7890",
      "customers": [],
      "employees": []
    }
    ```

- **Update Pet Store:**  
  - **Endpoint:** `PUT /pet_store/{petStoreId}`  
  - **Description:** Updates an existing pet store identified by `{petStoreId}`.

- **Retrieve Pet Store by ID:**  
  - **Endpoint:** `GET /pet_store/{petStoreId}`  
  - **Description:** Retrieves the complete pet store details (including customers and employees) for the specified `{petStoreId}`.

- **List All Pet Stores (Summary):**  
  - **Endpoint:** `GET /pet_store`  
  - **Description:** Returns a summary list of all pet stores without customer and employee details.

- **Add Pet Store Employee:**  
  - **Endpoint:** `POST /pet_store/{petStoreId}/employee`  
  - **Description:** Adds an employee to the pet store identified by `{petStoreId}`.  
  - **Sample JSON:**
    ```json
    {
      "employeeId": null,
      "employeeFirstName": "Jane",
      "employeeLastName": "Doe",
      "employeePhone": "555-123-4567",
      "employeeJobTitle": "Sales Associate"
    }
    ```

- **Add Pet Store Customer:**  
  - **Endpoint:** `POST /pet_store/{petStoreId}/customer`  
  - **Description:** Adds a customer to the pet store identified by `{petStoreId}`.  
  - **Sample JSON:**
    ```json
    {
      "customerId": null,
      "customerFirstName": "Alice",
      "customerLastName": "Smith",
      "customerEmail": "alice.smith@example.com"
    }
    ```

- **Delete Pet Store:**  
  - **Endpoint:** `DELETE /pet_store/{petStoreId}`  
  - **Description:** Deletes the pet store specified by `{petStoreId}`. When deleted, all employees associated with that pet store and the join table records linking customers are also removed (without deleting the actual customer records).

## Running the Application

### Prerequisites

- **Java 17** installed.
- **MySQL** installed and running.
- Create the `pet_store` schema in your MySQL database with proper user credentials as specified in `application.yaml`.

### Steps

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/your_github_user_name/repository_name_here.git
   cd repository_name_here
   ```

2. **Build the Project Using Maven:**

   ```bash
   mvn clean install
   ```

3. **Run the Application:**

   ```bash
   mvn spring-boot:run
   ```

4. **Test the Endpoints:**  
   Use a REST client (like Postman or ARC) to interact with the API. For example:
   - **Add an Employee:**  
     `POST http://localhost:8080/pet_store/1/employee` with the sample JSON payload provided above.
   - **Add a Customer:**  
     `POST http://localhost:8080/pet_store/1/customer` with the sample JSON payload provided above.
   - **List All Pet Stores:**  
     `GET http://localhost:8080/pet_store`
   - **Retrieve a Specific Pet Store:**  
     `GET http://localhost:8080/pet_store/1`
   - **Delete a Pet Store:**  
     `DELETE http://localhost:8080/pet_store/1`

## Video Walkthrough

For a detailed demonstration of the project, please see the walkthrough video here:  
https://www.youtube.com/watch?v=zQm3aQJ_mDM

## Future Enhancements

- **Expand CRUD for Customers and Employees:** Additional endpoints for updating or retrieving individual customer/employee records.
- **Data Seeding:** Implement a data seeder using SQL scripts or a `CommandLineRunner`.
- **Enhanced Validations & Business Rules:** Add validations and more complex business logic.
- **Security Enhancements:** Implement authentication and authorization.

## Conclusion

This project demonstrates building a RESTful API with Spring Boot, JPA, and MySQL. It showcases the management of one-to-many and many-to-many relationships, automatic schema generation, and a complete set of CRUD operations for a pet store application.

Feel free to contribute or open an issue if you have suggestions or improvements!

---
```
