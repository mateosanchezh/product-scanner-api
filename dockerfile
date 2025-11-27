# Etapa de build
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

# Copiar archivos de Maven primero para aprovechar caching
COPY pom.xml mvnw ./
COPY .mvn .mvn

# Dar permisos de ejecución al wrapper
RUN chmod +x mvnw

# Descargar solo las dependencias necesarias para compilar
RUN ./mvnw clean package -DskipTests

# Copiar código fuente y compilar
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Etapa de ejecución
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiar JAR desde la etapa de build
COPY --from=build /app/target/*.jar app.jar

# Exponer puerto que Render usará
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
