FROM eclipse-temurin

# Set the working directory to /app
WORKDIR /app

# Copy the JAR file into the container at /app
COPY target/spring-0.0.1-SNAPSHOT.jar /app/

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the application when the container launches
CMD ["java", "-jar", "spring-0.0.1-SNAPSHOT.jar"]