#!/bin/bash
set -e

echo "Building and starting application..."
echo "Press Ctrl+C to stop the application and shut down containers."
echo ""

docker compose up --build

echo ""
echo "Application stopped."