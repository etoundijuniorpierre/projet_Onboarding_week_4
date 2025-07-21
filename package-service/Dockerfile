# Étape de construction (Build stage)
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline -B
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Étape d'exécution (Runtime stage)
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copie le JAR construit
COPY --from=builder /app/target/*.jar app.jar

# Copie le script wait-for-it.sh et le rend exécutable
COPY wait-for-it.sh /usr/bin/wait-for-it.sh
RUN chmod +x /usr/bin/wait-for-it.sh

# *** CORRECTION ICI : Installer le client PostgreSQL ***
# 'apk add' est le gestionnaire de paquets pour les images Alpine.
RUN apk add postgresql-client

# Copie votre script d'entrée personnalisé et le rend exécutable
COPY entrypoint.sh .
RUN chmod +x entrypoint.sh

EXPOSE 8080

ENTRYPOINT ["./entrypoint.sh"]
