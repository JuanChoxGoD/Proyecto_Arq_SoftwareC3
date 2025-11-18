# Etapa 1: Build (Construcción)
# Usamos una imagen con Maven/Gradle para compilar
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Empaquetamos la aplicación omitiendo los tests aquí (se corren en el pipeline de CI)
RUN mvn clean package -DskipTests

# Etapa 2: Run (Ejecución)
# Usamos una imagen JRE ligera para producción (Mejora seguridad y tamaño)
FROM openjdk:17-jdk-slim
WORKDIR /app
# Copiamos solo el jar generado en la etapa anterior
COPY --from=build /app/target/mi-proyecto-backend.jar app.jar

# Usuario no root para seguridad (Best Practice DevSecOps)
RUN useradd -m myuser
USER myuser

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
