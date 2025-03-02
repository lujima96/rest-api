```markdown
# Pet Store REST API

This project is a Spring Boot-based REST API for a Pet Store application. It was built as part of a coding assignment to demonstrate the following:

- **Designing an Entity Relationship Diagram (ERD):** Created using Draw.io to map out our database schema.
- **Maven Project Setup:** Initialized and configured via Spring Initializr with a custom `pom.xml`.
- **JPA Entity Creation:** Developed `Customer`, `Employee`, and `PetStore` classes with proper annotations and relationships.
- **Spring Boot & JPA Configuration:** Configured the application to automatically generate MySQL database tables from our entity classes.
- **Database Schema Generation:** Verified that the `customer`, `employee`, `pet_store`, and `pet_store_customer` tables are automatically created in the MySQL `pet_store` schema.
- **REST Endpoints & Global Error Handling:** Implemented CRUD operations for creating and updating pet stores, along with a global error handler to return clear error messages.

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

> **Note:** Make sure to create the `pet_store` schema in your MySQL database and grant the appropriate permissions to the user.

## How It Works

1. **Entity Relationship Diagram (ERD):**  
   The ERD defines the relationships between our entities:
   - A **many-to-many** relationship between `Customer` and `PetStore` (using a join table `pet_store_customer`).
   - A **one-to-many** relationship between `PetStore` and `Employee`.
   - A **many-to-one** relationship between `Employee` and `PetStore`.

2. **JPA Entities:**  
   Each entity is annotated with `@Entity` and uses Lombok’s `@Data` for boilerplate code reduction. Recursive relationships are managed with `@EqualsAndHashCode.Exclude` and `@ToString.Exclude` to prevent infinite loops.

3. **Spring Boot Initialization:**  
   The `PetStoreApplication` class (annotated with `@SpringBootApplication`) bootstraps the application. On startup, Hibernate uses the JPA entity definitions to automatically generate the required tables in the MySQL schema.

4. **REST API Endpoints:**  
   - **Create Pet Store:** `POST /pet_store` – Accepts JSON data to create a new pet store.
   - **Update Pet Store:** `PUT /pet_store/{petStoreId}` – Updates existing pet store details.
   - **Global Error Handling:** If an operation fails (e.g., updating a non-existent pet store), the API returns a 404 Not Found error with a clear message.

## Running the Application

### Prerequisites

- **Java 17** must be installed.
- **MySQL** should be installed and running.
- Create the `pet_store` schema and set up the user credentials as defined in `application.yaml`.

### Steps

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/lujima96/rest-api.git
   cd rest-api
   ```

2. **Build the Project Using Maven:**

   ```bash
   mvn clean install
   ```

3. **Run the Application:**

   ```bash
   mvn spring-boot:run
   ```

4. **Verify the Schema:**
   - Check your MySQL database (using DBeaver or MySQL Workbench) to see the tables `customer`, `employee`, `pet_store`, and `pet_store_customer` automatically created.

## Video Walkthrough

For a detailed video demonstration of the project, check out the walkthrough here:  
[YouTube Video](https://youtu.be/dJ0F3ouH1pY)

## Future Enhancements

- **Implement Additional REST Endpoints:** Expand CRUD operations for managing customers, employees, and further pet store functionalities.
- **Data Seeding:** Use SQL scripts or a CommandLineRunner to insert sample data.
- **Business Logic & Validations:** Enhance the application with additional business rules and validations.
- **Security:** Add authentication and authorization for secure access to the API.

## Conclusion

This project demonstrates the foundational steps for building a RESTful API using Spring Boot, JPA, and MySQL. It showcases automatic table generation through proper entity mapping and configuration, providing a solid base for further development.

Feel free to open an issue or contribute via pull requests for any improvements or suggestions!
```
