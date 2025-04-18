-- Eliminar tablas existentes (si es necesario)
DROP TABLE IF EXISTS puja_sorteo;
DROP TABLE IF EXISTS comentario;
DROP TABLE IF EXISTS puja;
DROP TABLE IF EXISTS subasta;
DROP TABLE IF EXISTS sorteo;
DROP TABLE IF EXISTS trabajador;
DROP TABLE IF EXISTS usuario;

-- Tabla de Usuarios (ampliada para estadísticas)
CREATE TABLE usuario (
    usuario_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_usuario VARCHAR(255) NOT NULL UNIQUE,
    correo_electronico VARCHAR(255) NOT NULL UNIQUE,
    contraseña VARCHAR(255) NOT NULL,
    saldo DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    puntos INT NOT NULL DEFAULT 0,
    pais VARCHAR(255) NOT NULL,
    ciudad VARCHAR(255) NOT NULL,
    codigo_postal VARCHAR(255) NOT NULL,
    calle VARCHAR(255) NOT NULL,
    numero_piso INT NOT NULL DEFAULT 0,
    letra_piso VARCHAR(10) DEFAULT '',
    privacidad_anonimo_pujas BOOLEAN NOT NULL DEFAULT FALSE,
    privacidad_estadisticas BOOLEAN NOT NULL DEFAULT TRUE,
    privacidad_perfil_visible BOOLEAN NOT NULL DEFAULT TRUE
);

-- Tabla de Trabajadores
CREATE TABLE trabajador (
    trabajador_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_usuario VARCHAR(255) NOT NULL UNIQUE,
    correo_electronico VARCHAR(255) NOT NULL UNIQUE,
    contraseña VARCHAR(255) NOT NULL,
    rol_trabajador BOOLEAN NOT NULL DEFAULT FALSE
);

-- Tabla de Subastas (con relaciones para estadísticas)
CREATE TABLE subasta (
    subasta_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha_inicial DATE NOT NULL,
    fecha_final DATE NOT NULL,
    precio_inicial DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    precio_final DECIMAL(10,2) DEFAULT 0.00,
    subasta_normal BOOLEAN NOT NULL,
    nombre_articulo VARCHAR(255) NOT NULL,
    descripcion TEXT NOT NULL,
    imagen VARCHAR(255),
    creador_id BIGINT NOT NULL,
    ganador_id BIGINT,
    FOREIGN KEY (creador_id) REFERENCES usuario(usuario_id),
    FOREIGN KEY (ganador_id) REFERENCES usuario(usuario_id)
);

-- Tabla de Pujas (registro detallado para estadísticas)
CREATE TABLE puja (
    puja_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    importe DECIMAL(10,2) NOT NULL,
    ganadora BOOLEAN NOT NULL DEFAULT FALSE,
    usuario_id BIGINT NOT NULL,
    subasta_id BIGINT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(usuario_id),
    FOREIGN KEY (subasta_id) REFERENCES subasta(subasta_id)
);

-- Tabla de Sorteos (con seguimiento de ganadores)
CREATE TABLE sorteo (
    sorteo_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_articulo VARCHAR(255) NOT NULL,
    descripcion TEXT NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    puntos_necesarios INT NOT NULL,
    trabajador_id BIGINT NOT NULL,
    ganador_id BIGINT,
    FOREIGN KEY (trabajador_id) REFERENCES trabajador(trabajador_id),
    FOREIGN KEY (ganador_id) REFERENCES usuario(usuario_id)
);

-- Tabla de Participación en Sorteos
CREATE TABLE puja_sorteo (
    puja_sorteo_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    puntos INT NOT NULL,
    usuario_id BIGINT NOT NULL,
    sorteo_id BIGINT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(usuario_id),
    FOREIGN KEY (sorteo_id) REFERENCES sorteo(sorteo_id)
);

-- Tabla de Comentarios
CREATE TABLE comentario (
    comentario_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    contenido TEXT NOT NULL,
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    trabajador_id BIGINT NOT NULL,
    subasta_id BIGINT NOT NULL,
    FOREIGN KEY (trabajador_id) REFERENCES trabajador(trabajador_id),
    FOREIGN KEY (subasta_id) REFERENCES subasta(subasta_id)
);

-- Datos de ejemplo para pruebas
INSERT INTO usuario (
    nombre_usuario, 
    correo_electronico, 
    contraseña, 
    saldo, 
    puntos, 
    pais, 
    ciudad, 
    codigo_postal, 
    calle, 
    numero_piso
) VALUES 
('usuario1', 'usuario1@example.com', 'Password123!', 500.00, 1500, 'España', 'Madrid', '28001', 'Calle Principal', 10),
('usuario2', 'usuario2@example.com', 'Password123!', 750.00, 2500, 'España', 'Barcelona', '08001', 'Avenida Central', 20);

INSERT INTO trabajador (
    nombre_usuario, 
    correo_electronico, 
    contraseña, 
    rol_trabajador
) VALUES 
('admin', 'admin@bidco.com', 'AdminPassword123!', TRUE),
('soporte', 'soporte@bidco.com', 'SoportePassword123!', FALSE);

INSERT INTO subasta (
    fecha_inicial, 
    fecha_final, 
    precio_inicial, 
    subasta_normal, 
    nombre_articulo, 
    descripcion, 
    creador_id, 
    ganador_id
) VALUES 
('2025-01-01', '2025-02-01', 100.00, TRUE, 'Reloj Vintage', 'Reloj de colección en perfecto estado', 1, 2),
('2025-03-01', '2025-04-01', 200.00, FALSE, 'Cámara Profesional', 'Cámara DSLR con lentes incluidos', 2, 1);

INSERT INTO puja (
    fecha, 
    importe, 
    ganadora, 
    usuario_id, 
    subasta_id
) VALUES 
('2025-01-15 14:30:00', 150.00, TRUE, 2, 1),
('2025-03-15 10:45:00', 250.00, TRUE, 1, 2),
('2025-01-14 16:20:00', 140.00, FALSE, 1, 1),
('2025-03-14 09:15:00', 220.00, FALSE, 2, 2);

INSERT INTO sorteo (
    nombre_articulo, 
    descripcion, 
    fecha_inicio, 
    fecha_fin, 
    puntos_necesarios, 
    trabajador_id, 
    ganador_id
) VALUES 
('Smart TV 55"', 'Televisor 4K Ultra HD', '2025-05-01', '2025-06-01', 5000, 1, 2),
('Consola Gaming', 'Última generación con juegos', '2025-07-01', '2025-08-01', 7500, 2, 1);

INSERT INTO puja_sorteo (
    fecha, 
    puntos, 
    usuario_id, 
    sorteo_id
) VALUES 
('2025-05-10 12:00:00', 5000, 1, 1),
('2025-05-11 14:30:00', 5000, 2, 1),
('2025-07-10 10:00:00', 7500, 1, 2),
('2025-07-11 11:45:00', 7500, 2, 2);

INSERT INTO comentario (
    contenido, 
    trabajador_id, 
    subasta_id
) VALUES 
('Excelente estado de conservación', 1, 1),
('Garantía extendida disponible', 2, 2);