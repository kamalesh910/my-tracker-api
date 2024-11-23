# Use Maven to build the project in the container
FROM maven:3.8.4-openjdk-17-slim AS build

# Set the working directory for the build
WORKDIR /app

# Copy the Maven project files to the container
COPY pom.xml .
COPY src ./src

# Build the project
RUN mvn clean package -DskipTests

# Use a smaller base image to run the app
FROM openjdk:17-jdk-slim

# Set the working directory for the running app
WORKDIR /app

# Copy the JAR file from the build container
COPY --from=build /app/target/tracker-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Command to run the app
CMD ["java", "-jar", "app.jar"]
