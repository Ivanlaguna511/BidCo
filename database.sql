
drop table IF exists puja_sorteo;
DROP TABLE IF EXISTS puja;
DROP TABLE IF EXISTS comentario;
DROP TABLE IF EXISTS sorteo;
DROP TABLE IF EXISTS subasta;
DROP TABLE IF EXISTS trabajador;
DROP TABLE IF EXISTS usuario;

CREATE TABLE usuario (
    usuario_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    primer_apellido VARCHAR(255) NOT NULL,
    segundo_apellido VARCHAR(255) NOT NULL,
    nombre_usuario VARCHAR(255) NOT NULL UNIQUE,
    correo_electronico VARCHAR(255) NOT NULL UNIQUE,
    contraseña VARCHAR(255) NOT NULL,
    saldo DECIMAL(10,2) NOT NULL,
    puntos INT NOT NULL,
    pais VARCHAR(255) NOT NULL,
    ciudad VARCHAR(255) NOT NULL,
    codigo_postal VARCHAR(255) NOT NULL,
    calle VARCHAR(255) NOT NULL,
    numero_piso INT DEFAULT 0,
    letra_piso VARCHAR(10) DEFAULT ' '
);

CREATE TABLE trabajador (
    trabajador_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_usuario VARCHAR(255) NOT NULL UNIQUE,
    correo_electronico VARCHAR(255) NOT NULL UNIQUE,
    contraseña VARCHAR(255) NOT NULL,
    rol_trabajador BOOLEAN NOT NULL
);

CREATE TABLE subasta (
    subasta_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha_inicial DATE NOT NULL,
    fecha_final DATE NOT NULL,
    precio_inicial DECIMAL(10,2) DEFAULT 0.00,
    subasta_normal BOOLEAN NOT NULL,
    nombre_articulo VARCHAR(255) NOT NULL,
    descripcion TEXT NOT NULL,
    precio_final DECIMAL(10,2) DEFAULT 0.00,
    creador_id BIGINT NOT NULL,
    FOREIGN KEY (creador_id) REFERENCES usuario(usuario_id)
);

CREATE TABLE puja (
    puja_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    importe DECIMAL(10,2) NOT NULL,
    ganadora BOOLEAN DEFAULT FALSE,
    usuario_id BIGINT NOT NULL,
    subasta_id BIGINT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(usuario_id),
    FOREIGN KEY (subasta_id) REFERENCES subasta(subasta_id)
);

CREATE TABLE comentario (
    comentario_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    comentario TEXT NOT NULL,
    trabajador_id BIGINT NOT NULL,
    subasta_id BIGINT NOT NULL,
    FOREIGN KEY (trabajador_id) REFERENCES trabajador(trabajador_id),
    FOREIGN KEY (subasta_id) REFERENCES subasta(subasta_id)
);

CREATE TABLE sorteo (
    sorteo_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_articulo VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    puntos_necesarios INT NOT NULL,
    puntos_finales INT default 0,  -- Este campo ha sido añadido según tu solicitud
    trabajador_id BIGINT NOT NULL,  -- Asumiendo que 'creador' es un trabajador, y 'creador_id' es la clave foránea
    FOREIGN KEY (trabajador_id) REFERENCES trabajador(trabajador_id)  -- Asumiendo que existe una tabla 'trabajador' con la columna 'trabajador_id'
);



CREATE TABLE puja_sorteo (
    puja_id  BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    puntos DECIMAL(10, 2) NOT NULL,
    usuario_id BIGINT NOT NULL,
    sorteo_id BIGINT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(usuario_id),  -- Asumiendo que existe una tabla 'usuario' con una columna 'usuario_id'
    FOREIGN KEY (sorteo_id) REFERENCES sorteo(sorteo_id)  -- Asumiendo que existe una tabla 'sorteo' con una columna 'sorteo_id'
);

-- Insertar usuarios
INSERT INTO usuario (nombre_usuario, correo_electronico, contraseña, saldo, puntos, pais, ciudad, codigo_postal, calle, numero_piso, letra_piso) 
VALUES 
('juanperez', 'juan@example.com', 'password123', 100.00, 50, 'España', 'Madrid', '28001', 'Calle Falsa', 1, 'A'),
('anagarcia', 'ana@example.com', 'password123', 200.00, 80, 'España', 'Madrid', '28002', 'Calle Real', 2, 'B');


-- Insertar trabajadores
INSERT INTO trabajador (nombre_usuario, correo_electronico, contraseña, rol_trabajador)
VALUES 
('admin', 'admin@bidco.com', 'adminpassword', TRUE),
('moderador', 'moderador@bidco.com', 'modpassword', FALSE);


-- Insertar subastas
INSERT INTO subasta (fecha_inicial, fecha_final, precio_inicial, subasta_normal, nombre_articulo, descripcion, creador_id) 
VALUES 
('2023-05-01', '2024-10-10', 50.00, TRUE, 'Laptop de 15"', 'Laptop de segunda mano en buen estado', 1),  -- Creador es 'Juan Pérez'
('2025-06-01', '2025-06-10', 150.00, TRUE, 'Cámara fotográfica', 'Cámara DSLR en excelente estado', 2);  -- Creador es 'Ana García'


-- Insertar pujas
INSERT INTO puja (fecha, importe, ganadora, usuario_id, subasta_id) 
VALUES 
('2025-06-02', 60.00, FALSE, 1, 1),  -- Puja de 'Juan Pérez' en la subasta 1 (Laptop)
('2025-05-03', 70.00, FALSE, 2, 1),  -- Puja de 'Ana García' en la subasta 1 (Laptop)
('2025-06-02', 160.00, TRUE, 1, 2);  -- Puja de 'Juan Pérez' en la subasta 2 (Cámara)

-- Insertar un sorteo
INSERT INTO sorteo (nombre_articulo, descripcion, fecha_inicio, fecha_fin, puntos_necesarios, trabajador_id)
VALUES
('Televisor 4K', 'Un televisor de alta definición 4K de 50 pulgadas', '2025-05-01', '2024-05-15', 500,  1),  -- Ejemplo con un trabajador con id 1
('Cámara Fotográfica', 'Cámara fotográfica digital de última generación', '2025-06-01', '2025-06-15', 300,  1),  -- Ejemplo con un trabajador con id 2
('Smartphone', 'Smartphone de última tecnología con pantalla AMOLED', '2025-07-01', '2025-07-15', 200,  1);  -- Ejemplo con un trabajador con id 3




INSERT INTO puja_sorteo (fecha, puntos, usuario_id, sorteo_id)
VALUES
('2024-04-10', 100, 1, 1),  -- Ejemplo de una puja en el sorteo con ID 1, realizada por el usuario con ID 1, por 100 puntos
('2024-04-11', 150, 2, 1),  -- Ejemplo de una puja en el sorteo con ID 1, realizada por el usuario con ID 2, por 150 puntos
('2025-04-12', 200, 1, 2),  -- Ejemplo de una puja en el sorteo con ID 2, realizada por el usuario con ID 3, por 200 puntos
('2025-04-13', 250, 1, 2),  -- Ejemplo de una puja en el sorteo con ID 2, realizada por el usuario con ID 1, por 250 puntos
('2025-04-14', 300, 2, 2);  -- Ejemplo de una puja en el sorteo con ID 3, realizada por el usuario con ID 2, por 300 puntos



INSERT INTO comentario (comentario, trabajador_id, subasta_id) 
VALUES ('Este es un comentario sobre la subasta 2', 1, 2);


INSERT INTO comentario (comentario, trabajador_id, subasta_id) 
VALUES ('Excelente subasta, muy recomendable', 2, 1);

INSERT INTO comentario (comentario, trabajador_id, subasta_id) 
VALUES ('La subasta fue muy interesante', 1, 2);


INSERT INTO comentario (comentario, trabajador_id, subasta_id) 
VALUES ('Este comentario tiene detalles importantes sobre la subasta 4', 1, 1);





