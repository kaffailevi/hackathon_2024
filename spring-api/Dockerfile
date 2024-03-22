# Fázis 1: Maven Build
FROM maven AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install

# Fázis 2: Spring Boot Run
FROM openjdk:20
WORKDIR /app
COPY --from=build /app/target/spring-api.jar ./app.jar
EXPOSE 8080
ENTRYPOINT  ["java", "-jar", "app.jar"]