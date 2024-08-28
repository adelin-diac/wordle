FROM eclipse-temurin:21

# Set the working directory
WORKDIR /app

CMD ["v2_with_server/wordle/gradlew", "clean", "bootJar"]

# Copy the Gradle project files
COPY v2_with_server/wordle/build/libs/*jar app.jar

# Expose the port that the application will run on
EXPOSE 8080

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
