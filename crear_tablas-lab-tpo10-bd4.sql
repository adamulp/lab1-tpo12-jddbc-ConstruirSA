



-- TUDS-A-24 - Laboratorio de Programación l C2
-- Grupo 3:
    -- Enzo Casalegno
    -- Nicolas Hollmaann
    -- Alexis Allendez
    -- Adam Rigg


-- ----------------------------------------------------------------------------


-- Trabajo Practico 10 (TP bases de datos 4)

CREATE DATABASE IF NOT EXISTS tpo10_db4_herramientasConstruirSA_g3;
USE tpo10_db4_herramientasConstruirSA_g3;

CREATE TABLE empleado (
    idEmpleado INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    dni BIGINT NOT NULL UNIQUE,
    apellido VARCHAR(58) NOT NULL,
    nombre VARCHAR(58) NOT NULL,
    acceso INT,
    estado TINYINT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

CREATE TABLE herramienta (
    idHerramienta INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL,
    descripcion VARCHAR(100) NOT NULL,
    stock INT NOT NULL,
    estado TINYINT  NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

CREATE TABLE movimiento (
    idMovimiento INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    idEmpleado INT NOT NULL,
    idHerramienta INT NOT NULL,
    fechaPrestamo DATE NOT NULL,
    fechaDevolucion DATE DEFAULT NULL,
    cantidadRetirada INT NOT NULL,
    CONSTRAINT fk_id_empleado FOREIGN KEY (idEmpleado) REFERENCES empleado(idEmpleado),
    CONSTRAINT fk_id_herramienta FOREIGN KEY (idHerramienta) REFERENCES herramienta(idHerramienta)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

