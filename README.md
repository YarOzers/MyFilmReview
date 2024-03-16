# FilmReview

Complete simple web application using Spring Boot, Thymeleaf and PostgreSQL with login and registration backend system using Spring Security.

## Requirements for run project
- [JDK 14](https://jdk.java.net/14/)
- [Maven 3.6.3](https://maven.apache.org/download.cgi)
- [PostgreSQL 2.4.1](https://postgresapp.com/downloads.html)
- [Maildev](https://github.com/maildev/maildev)

Creation database in Postgres:
```sql
CREATE DATABASE filmreviewdb;
```
Go to:
```bash
 src/main/resources/application.properties
```
and change where "???":
```java
spring.datasource.url=jdbc:postgresql://localhost:5432/filmreviewdb
spring.datasource.username=???
spring.datasource.password=???
```
## Build and run project

Clone the repository:
```bash
git clone https://github.com/YuriiP1/FilmReview
```
Navigate to the newly created folder:
```bash
cd FilmReview
```
Run the project with:
```bash
mvn spring-boot:run
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
[ByMe](https://github.com/YuriiP1)
