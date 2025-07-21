#!/bin/sh
# entrypoint.sh pour package-service

# Attendre que la base de données PostgreSQL soit prête
# Utilise 'postgres-db' comme nom d'hôte Docker et 'packagedb' comme nom de la base de données
echo "Waiting for postgres-db to be ready..."
/usr/bin/wait-for-it.sh postgres-db:5432 --timeout=60 --strict -- echo "Postgres is up!"

echo "Checking if packagedb database exists..."
# Boucle d'attente pour la base de données spécifique
until PGPASSWORD="root" psql -h "postgres-db" -U "postgres" -d "packagedb" -c '\q'; do
  >&2 echo "packagedb is unavailable - sleeping"
  sleep 1
done

>&2 echo "packagedb is up - executing command"
exec java -jar app.jar