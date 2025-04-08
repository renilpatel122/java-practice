# Use OpenJDK 17 as base image
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8081 8082
ENTRYPOINT ["sh", "-c", "java -jar app.jar --spring.profiles.active=${PROFILE}"]
