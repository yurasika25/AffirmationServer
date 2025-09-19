FROM gradle:8.10.1-jdk17-alpine AS build
WORKDIR /src

COPY . .
RUN chmod +x gradlew || true

RUN ./gradlew --no-daemon clean shadowJar

FROM eclipse-temurin:17-jre-alpine

RUN addgroup -S app && adduser -S app -G app \
    && apk add --no-cache curl

USER app
WORKDIR /app

COPY --from=build /src/build/libs/*-all.jar /app/app.jar

ENV PORT=8080
ENV JAVA_OPTS="-XX:MaxRAMPercentage=75 -XX:+ExitOnOutOfMemoryError -Dio.ktor.deployment.host=0.0.0.0"

EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=3s --start-period=10s --retries=3 \
  CMD curl -fsS http://127.0.0.1:${PORT}/health || exit 1

CMD ["sh","-lc","exec java $JAVA_OPTS -Dio.ktor.deployment.port=${PORT} -jar /app/app.jar"]
