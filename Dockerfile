
FROM gradle:8.6-jdk25 AS builder
WORKDIR /app

COPY . .

RUN gradle bootjar -x test

FROM eclipse-temurin:25-jre-alpine
WORKDIR /app

COPY --from=builder /app/build/libs/*-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]