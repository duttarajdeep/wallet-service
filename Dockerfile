# Stage 1 - Build the app using a Gradle image
FROM gradle:8.5.0-jdk21 AS builder
COPY --chown=gradle:gradle . /app
WORKDIR /app
RUN gradle bootJar --no-daemon

# Stage 2 - Run the built app
FROM eclipse-temurin:21-jdk-alpine
EXPOSE 8080
COPY --from=builder /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
