#FROM eclipse-temurin:17-jdk-focal
#
#WORKDIR /app
#
#COPY .mvn/ .mvn
#
#COPY mvnw pom.xml ./
#
#RUN chmod +x mvnw && ./mvnw dependency:go-offline
#
#COPY src ./src
#
#CMD ["./mvnw", "spring-boot:run"]

FROM maven:3.9-amazoncorretto-21-al2023 as build

WORKDIR /app

## TODO: Set user non-root

COPY pom.xml .

COPY . .

RUN mvn package

FROM amazoncorretto:21-al2023

WORKDIR /app

## TODO: Set user non-root

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]