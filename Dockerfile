FROM eclipse-temurin:21 AS builder

WORKDIR /app

COPY v2_with_server/wordle/ .

RUN ./gradlew clean bootJar

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
