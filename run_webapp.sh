#!/bin/bash
set -xue -o pipefail

mvn compile

while ! exec 6<>/dev/tcp/${DATABASE_HOST}/${DATABASE_PORT}; do
    echo "Database not up yet, sleeping"
    sleep 10
done

mvn migration:status
mvn migration:up spring-boot:run -Dspring.profiles.active=container
