# Build stage
FROM gradle:8.0-jdk11 AS build

WORKDIR /home/gradle/src

COPY --chown=gradle:gradle . .

RUN gradle buildFatJar --no-daemon

# Runtime stage
FROM openjdk:11-jre-slim

EXPOSE 8080

WORKDIR /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
