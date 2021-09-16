FROM openjdk:11-slim

MAINTAINER hirooka

RUN cd /tmp
COPY . .
RUN ./gradlew build
RUN mv ./build/libs/dqwapi-1.0.0-SNAPSHOT.jar /app.jar
EXPOSE 80
ENTRYPOINT ["java", "-XX:MaxRAMPercentage=75", "-Djava.security.egd=file:/dev/./urandom", "-Dserver.port=80", "-jar", "/app.jar"]
