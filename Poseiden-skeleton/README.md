# spring-boot
## Technical:

1. Framework: Spring Boot v2.0.4
2. Java 8
3. Thymeleaf
4. Bootstrap v.4.3.1
5. BCRYPT to encode password in database


## Getting Started

Install Maven
Install Java
Install My SQL

1. Copy project from Github on your local machine
2. Create a MySQL server on your local machine with a user (username=pierredb + password=a627a158-1cd5-4ab1-a439-0d4873785246) and give credentials
3. Create database with name "demo" as configuration in application.properties
4. Run sql script in doc/data.sql to create table
5. Go to the root of the application and execute mvn spring-boot:run
6. Server port is 8080 (http://localhost:8080)
7. You can add a user directly from database in order to connect (id = "3", username="pierre", encoded password = "$2a$10$EKk78nNZehTd08Iguvvw6Ow.kdTgUyw4KcEhMb4505pN4lShVd.y2", fullname = "pierre" and role ="ADMIN")
8. Then you can connect easily with username :"pierre" and password "pierre"
9. Tests can be run with Maven




