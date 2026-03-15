# Etapa 1: Construcción (Build)
FROM eclipse-temurin:25-jdk-alpine AS build
WORKDIR /app
COPY . .

# --- ESTA ES LA LÍNEA MÁGICA ---
RUN chmod +x mvnw

RUN ./mvnw clean install -DskipTests

# Etapa 2: Ejecución (Runtime)
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]