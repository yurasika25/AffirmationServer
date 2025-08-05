#!/bin/bash
set -e
./gradlew clean build -x test
docker build -t affirmation-server .
docker stop affirmation-server || true
docker rm affirmation-server || true
docker run -d --name affirmation-server -p 8080:8080 affirmation-server
ngrok http 8080
