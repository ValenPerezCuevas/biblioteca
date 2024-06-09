# Etapa de construcción de dependencias
FROM eclipse-temurin:17-jdk-jammy as deps

WORKDIR /build

# Descargar Maven
RUN apt-get update && \
    apt-get install -y maven

# Copiar el archivo pom.xml y descargar las dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -DskipTests

################################################################################

# Etapa de construcción del paquete

FROM deps as package

WORKDIR /build

# Copiar las fuentes y construir el paquete
COPY ./src src/
COPY ./pom.xml pom.xml
RUN mvn package -DskipTests && \
    mv target/$(mvn help:evaluate -Dexpression=project.artifactId -q -DforceStdout)-$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout).jar target/app.jar

################################################################################

# Etapa de extracción de capas
FROM package as extract

WORKDIR /build

RUN java -Djarmode=layertools -jar target/app.jar extract --destination target/extracted

################################################################################

# Etapa final para correr la aplicación
FROM eclipse-temurin:17-jre-jammy AS final

# Crear un usuario no privilegiado
# See https://docs.docker.com/go/dockerfile-user-best-practices/
ARG UID=10001
RUN adduser \
    --disabled-password \
    --gecos "" \
    --home "/nonexistent" \
    --shell "/sbin/nologin" \
    --no-create-home \
    --uid "${UID}" \
    appuser
USER appuser

# Configurar el entorno para UTF-8
ENV LANG es_ES.UTF-8
ENV LANGUAGE es_ES:es
ENV LC_ALL es_ES.UTF-8


# Copiar el contenido extraído de la etapa anterior
COPY --from=extract build/target/extracted/dependencies/ ./
COPY --from=extract build/target/extracted/spring-boot-loader/ ./
COPY --from=extract build/target/extracted/snapshot-dependencies/ ./
COPY --from=extract build/target/extracted/application/ ./

EXPOSE 8081

ENTRYPOINT [ "java", "org.springframework.boot.loader.launch.JarLauncher" ]
