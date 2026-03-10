
FROM eclipse-temurin:25-jdk-alpine AS builder
WORKDIR /app

COPY . .

RUN sed -i 's/\r$//' gradlew

RUN chmod +x ./gradlew

RUN ./gradlew bootJar -x test

FROM eclipse-temurin:25-jre-alpine
WORKDIR /app

COPY build/libs/*-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]