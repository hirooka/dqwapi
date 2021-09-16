FROM openjdk:11-slim

MAINTAINER hirooka

RUN cd /tmp
COPY . .
RUN ./gradlew build
RUN mv ./build/libs/dqwapi-1.0.0-SNAPSHOT.jar /app.jar
EXPOSE 8888
ENTRYPOINT ["java", "-XX:MaxRAMPercentage=75", "-Djava.security.egd=file:/dev/./urandom", "-Dserver.port=8888", "-jar", "/app.jar"]
