CREATE DATABASE login_app;

USE login_app;

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL
);

CREATE TABLE datos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50),
    apellido VARCHAR(50),
    edad INT
);

-- Inserta algunos datos de prueba
INSERT INTO usuarios (username, password) VALUES ('admin', '12345');

INSERT INTO datos (nombre, apellido, edad) VALUES 
('Juan', 'Perez', 30),
('Maria', 'Lopez', 25),
('Luis', 'Gonzalez', 40);