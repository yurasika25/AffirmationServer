# Build stage
FROM gradle:8.10.1-jdk17-alpine AS build
WORKDIR /src
COPY . .
RUN ./gradlew --no-daemon shadowJar

# Run stage
FROM eclipse-temurin:17-jre
RUN addgroup --system app && adduser --system --ingroup app app && apt-get update && apt-get install -y curl
USER app
WORKDIR /app

COPY --from=build /src/build/libs/*-all.jar /app/app.jar

ENV PORT=8080 \
    JAVA_OPTS="-XX:MaxRAMPercentage=75 -XX:+ExitOnOutOfMemoryError"

EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=3s --start-period=10s --retries=3 \
  CMD curl -sf http://127.0.0.1:$PORT/health || exit 1

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
