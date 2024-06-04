-- Crear la base de datos biblioteca si no existe
CREATE DATABASE IF NOT EXISTS biblioteca;

-- Seleccionar la base de datos biblioteca
USE biblioteca;

-- Eliminar las tablas en el orden correcto para evitar errores de claves foráneas
DROP TABLE IF EXISTS libros_listas;
DROP TABLE IF EXISTS listas;
DROP TABLE IF EXISTS usuarios;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS libros;
DROP TABLE IF EXISTS permisos;

-- Crear la tabla permisos
CREATE TABLE permisos (
    id_permiso INT AUTO_INCREMENT PRIMARY KEY,
    nombre_permiso VARCHAR(100) NOT NULL
);

-- Crear la tabla roles
CREATE TABLE roles (
    id_rol INT AUTO_INCREMENT PRIMARY KEY,
    nombre_rol VARCHAR(50) NOT NULL
);

-- Crear la tabla libros
CREATE TABLE libros (
    id_libros INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    genero VARCHAR(100),
    autor VARCHAR(100) NOT NULL,
    ano_publicacion INT,
    editorial VARCHAR(100)
);

-- Crear la tabla usuarios
CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    id_rol INT,
    FOREIGN KEY (id_rol) REFERENCES roles(id_rol)
);

-- Crear la tabla listas
CREATE TABLE listas (
    id_lista INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    nombre_lista VARCHAR(255) NOT NULL,
    creado_desde TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    actualizado_desde TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    creado_por INT,
    actualizado_por INT,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario),
    FOREIGN KEY (creado_por) REFERENCES usuarios(id_usuario),
    FOREIGN KEY (actualizado_por) REFERENCES usuarios(id_usuario)
);

-- Crear la tabla de relación entre libros y listas
CREATE TABLE libros_listas (
    id_libros_lista INT AUTO_INCREMENT PRIMARY KEY,
    id_libro INT,
    id_lista INT,
    FOREIGN KEY (id_libro) REFERENCES libros(id_libros),
    FOREIGN KEY (id_lista) REFERENCES listas(id_lista)
);

-- Modificar las claves foráneas de la tabla listas para agregar eliminación en cascada
ALTER TABLE listas
DROP FOREIGN KEY listas_ibfk_1,
DROP FOREIGN KEY listas_ibfk_2,
DROP FOREIGN KEY listas_ibfk_3;

ALTER TABLE listas
ADD CONSTRAINT fk_listas_usuario
FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
ON DELETE CASCADE,
ADD CONSTRAINT fk_listas_creado_por
FOREIGN KEY (creado_por) REFERENCES usuarios(id_usuario)
ON DELETE CASCADE,
ADD CONSTRAINT fk_listas_actualizado_por
FOREIGN KEY (actualizado_por) REFERENCES usuarios(id_usuario)
ON DELETE CASCADE;

-- Elimina las claves externas actuales

ALTER TABLE libros_listas DROP FOREIGN KEY libros_listas_ibfk_1;
ALTER TABLE libros_listas DROP FOREIGN KEY libros_listas_ibfk_2;


-- Agrega las claves externas con ON DELETE CASCADE
ALTER TABLE libros_listas ADD CONSTRAINT libros_listas_ibfk_1 FOREIGN KEY (id_libro) REFERENCES libros (id_libros) ON DELETE CASCADE,
ADD CONSTRAINT libros_listas_ibfk_2 FOREIGN KEY (id_lista) REFERENCES listas (id_lista) ON DELETE CASCADE;






