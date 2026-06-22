# syntax=docker/dockerfile:1.7

# --- build stage ---
FROM maven:3.9-eclipse-temurin-26 AS build
WORKDIR /build

COPY pom.xml ./
COPY domain/pom.xml domain/
COPY application/pom.xml application/
COPY infrastructure/pom.xml infrastructure/
COPY presentation/pom.xml presentation/

RUN mvn -B -q -ntp dependency:go-offline

COPY domain domain
COPY application application
COPY infrastructure infrastructure
COPY presentation presentation

RUN mvn -B -ntp -pl presentation -am clean package -DskipTests

# --- runtime stage ---
FROM eclipse-temurin:26-jre
WORKDIR /app

COPY --from=build /build/presentation/target/*.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
