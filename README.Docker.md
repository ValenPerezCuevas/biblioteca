### Building and running your application

When you're ready, start your application by running:
`docker compose up --build`.

Your application will be available at http://localhost:8081.

### Deploying your application to the cloud

First, build your image, e.g.: `docker build -t myapp .`.
If your cloud uses a different CPU architecture than your development
machine (e.g., you are on a Mac M1 and your cloud provider is amd64),
you'll want to build the image for that platform, e.g.:
`docker build --platform=linux/amd64 -t myapp .`.

Then, push it to your registry, e.g. `docker push myregistry.com/myapp`.

Consult Docker's [getting started](https://docs.docker.com/go/get-started-sharing/)
docs for more detail on building and pushing.

# CREAR IMAGEN
docker build -t "nombreimagen"

# CREAR COMPOSE DOCKERFILES BBDD Y APP

docker compose up -d

docker-compose up --build -d


# When developing with Docker, you may need to automatically update and preview your running services as you edit and save your code. We use docker compose watch for this.

docker compose watch


# PUBLICAR IMAGEN-Before you can publish your image, you need to rename it so that Docker Hub knows that the image is yours. Run the following command to rename your image. Replace YOUR-USERNAME with your Docker ID.

docker tag docker/welcome-to-docker YOUR-USERNAME/welcome-to-docker


# Acceder al contenedor de MySQL
docker exec -it mysql_db bash

# Conectarse a MySQL
mysql -u root -p
# Introduce la contraseña: admin

# Seleccionar la base de datos
USE biblioteca;

# Listar todas las tablas
SHOW TABLES;

# Ver datos en una tabla específica
SELECT * FROM nombre_de_la_tabla;


################IMPORTAR MANUALMENTE EN BASH SQL
SOURCE /docker-entrypoint-initdb.d/creaciontablas.sql;
SOURCE /docker-entrypoint-initdb.d/insertdata.sql;
##########