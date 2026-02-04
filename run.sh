#!/bin/bash
echo "Building and starting application..."
docker-compose up --build -d
echo "Application running at http://localhost:8080"
echo "Swagger UI at http://localhost:8080/swagger-ui.html"