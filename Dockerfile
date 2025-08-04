FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/AffirmationServer-0.0.1.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
