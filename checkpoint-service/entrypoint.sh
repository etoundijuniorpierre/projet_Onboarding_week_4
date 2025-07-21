#!/bin/sh
# entrypoint.sh pour checkpoint-service

echo "Waiting for postgres-db to be ready..."
/usr/bin/wait-for-it.sh postgres-db:5432 --timeout=60 --strict -- echo "Postgres is up!"

echo "Checking if checkpointdb database exists..."
until PGPASSWORD="root" psql -h "postgres-db" -U "postgres" -d "checkpointdb" -c '\q'; do
  >&2 echo "checkpointdb is unavailable - sleeping"
  sleep 1
done

>&2 echo "checkpointdb is up - executing command"
exec java -jar app.jar