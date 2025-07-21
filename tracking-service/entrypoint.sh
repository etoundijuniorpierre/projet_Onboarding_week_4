#!/bin/sh
# entrypoint.sh pour tracking-service

echo "Waiting for postgres-db to be ready..."
/usr/bin/wait-for-it.sh postgres-db:5432 --timeout=60 --strict -- echo "Postgres is up!"

echo "Checking if trackingdb database exists..."
until PGPASSWORD="root" psql -h "postgres-db" -U "postgres" -d "trackingdb" -c '\q'; do
  >&2 echo "trackingdb is unavailable - sleeping"
  sleep 1
done

>&2 echo "trackingdb is up - executing command"
exec java -jar app.jar