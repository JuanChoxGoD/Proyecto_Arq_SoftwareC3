# --- Etapa 1: Build (Construcción) ---
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copiamos archivos de dependencias y código
COPY pom.xml .
COPY src ./src

# 1. Empaquetamos (sin tests para ir rápido en la creación de imagen)
RUN mvn clean package -DskipTests

# 2. TRUCO: Buscamos el .jar generado (excluyendo el 'original') y lo renombramos a 'app.jar'
# Esto evita errores de "file not found" por nombres de versión
RUN find target -name "*.jar" -not -name "*original*" -exec cp {} target/app.jar \;

# --- Etapa 2: Run (Ejecución) ---
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiamos el archivo que renombramos en la etapa anterior
COPY --from=build /app/target/app.jar app.jar

# Configuración de seguridad (Usuario no root)
RUN addgroup -S mygroup && adduser -S myuser -G mygroup
USER myuser

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
