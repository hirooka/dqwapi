FROM openjdk:17-slim as builder
WORKDIR /app
COPY . .
RUN ./gradlew build

# TODO: 17-jre-slim
FROM openjdk:17-slim
COPY --from=builder /app/build/libs/dqwapi-0.9.5.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-XX:MaxRAMPercentage=75", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=dwh-gcp-bigquery", "-Dserver.port=8080", "-jar", "/app.jar"]
