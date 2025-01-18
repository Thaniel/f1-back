# F1 App Backend

This project is a backend developed with Spring Boot that manages the logic and data for the f1-app-v2. 
It uses JPA and Hibernate for database interaction, allowing efficient management of entities related to the world of Formula 1, such as drivers, teams, circuits, and race results.

## Key Features:
- Full CRUD operations: RESTful services to perform Create, Read, Update, and Delete (CRUD) operations on all entities.
- Advanced relationships: Management of relationships between entities like @OneToMany, @ManyToOne, and @ManyToMany to represent associations between teams, drivers, comments, notices and topics.
- Swagger UI: Integrated API documentation to facilitate testing and consumption of endpoints.
- Relational database: Fully compatible with databases such as PostgreSQL or MySQL.

## Technologies Used:
- Spring Boot: Main framework for building the application.
- Spring Data JPA: Abstraction to simplify queries and operations with the database.
- Hibernate: ORM provider for entity persistence.
- Swagger/OpenAPI: Interactive API documentation.
- Maven: Dependency management and project build tool.
