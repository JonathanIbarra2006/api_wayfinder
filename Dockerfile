# Etapa 1: Construcción (Descarga dependencias y compila el .jar)
FROM eclipse-temurin:17-jdk-focal AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Etapa 2: Ejecución (Crea una imagen ligera solo con lo necesario para correr)
FROM eclipse-temurin:17-jre-focal
WORKDIR /app
COPY --from=build /app/target/api_wayfinder-0.0.1-SNAPSHOT.jar app.jar
# Exponemos el puerto 8080, aunque Render inyectará el suyo dinámicamente
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]