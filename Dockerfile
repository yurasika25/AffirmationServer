FROM openjdk:17-jdk-slim
FROM gradle:8.10.1-jdk17-alpine AS build
WORKDIR /src
COPY . .
RUN ./gradlew --no-daemon shadowJar

FROM eclipse-temurin:17-jre-alpine
RUN addgroup -S app && adduser -S app -G app && apk add --no-cache curl
USER app
WORKDIR /app
COPY build/libs/AffirmationServer-all.jar app.jar
COPY --from=build /src/build/libs/*-all.jar /app/app.jar
ENV PORT=8080 \
    JAVA_OPTS="-XX:MaxRAMPercentage=75 -XX:+ExitOnOutOfMemoryError"
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]

HEALTHCHECK --interval=30s --timeout=3s --start-period=10s --retries=3 \
  CMD curl -sf http://127.0.0.1:8080/health || exit 1
CMD ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
