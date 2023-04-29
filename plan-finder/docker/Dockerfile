# Use a Java 11 runtime environment
FROM openjdk:11-jre-slim

# Set the working directory to /app
WORKDIR /app

# Copy the packaged Spring Boot app to the working directory
COPY plan-finder-0.0.1-SNAPSHOT.jar /app

# Expose the port that the app will run on
EXPOSE 8080

# Set the command to run the app when the container starts
CMD ["java", "-jar", "plan-finder-0.0.1-SNAPSHOT.jar"]