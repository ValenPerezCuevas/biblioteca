# Utilizamos una imagen de OpenJDK como base
FROM openjdk:17-jdk-slim AS build

# Definimos el directorio de trabajo
WORKDIR /app

# Copiamos los archivos necesarios al directorio de trabajo
COPY .mvn/wrapper/maven-wrapper.jar .mvn/wrapper/maven-wrapper.properties .mvn/wrapper/

# Copiamos el archivo pom.xml
COPY pom.xml .

# Copiamos el resto del código
COPY src ./src

# Descargamos las dependencias
RUN apt-get update && apt-get install -y maven
RUN mvn -N io.takari:maven:wrapper

# Descargamos las dependencias
RUN ./mvnw dependency:go-offline

# Empaquetamos la aplicación
RUN ./mvnw package -DskipTests

# Segunda etapa para optimizar la imagen final
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto
EXPOSE 8080

# Comando de inicio
CMD ["java", "-jar", "app.jar"]

