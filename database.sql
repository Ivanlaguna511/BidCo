
drop table IF exists puja_sorteo;
DROP TABLE IF EXISTS puja;
DROP TABLE IF EXISTS comentario;
DROP TABLE IF EXISTS sorteo;
DROP TABLE IF EXISTS subasta;
DROP TABLE IF EXISTS trabajador;
DROP TABLE IF EXISTS usuario;

CREATE TABLE usuario (
    usuario_id BIGINT AUTO_INCREMENT PRIMARY KEY,
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
    letra_piso VARCHAR(10) DEFAULT ' ',
    privacidad_anonimo_pujas BOOLEAN DEFAULT FALSE,
    privacidad_estadisticas BOOLEAN DEFAULT TRUE,
    privacidad_perfil_visible BOOLEAN DEFAULT TRUE
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
    precio_final DECIMAL(10,2) DEFAULT 0.00,
    subasta_normal BOOLEAN NOT NULL,
    nombre_articulo VARCHAR(255) NOT NULL,
    descripcion TEXT NOT NULL,
    imagen VARCHAR(255), 
    creador_id BIGINT NOT NULL,
    ganador_id BIGINT,
    categoria VARCHAR(255),
    FOREIGN KEY (creador_id) REFERENCES usuario(usuario_id)
);

CREATE TABLE puja (
    puja_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
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
    precio_estimado DECIMAL(10,2) NOT NULL,
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    trabajador_id BIGINT NOT NULL,
    subasta_id BIGINT NOT NULL,
    FOREIGN KEY (trabajador_id) REFERENCES trabajador(trabajador_id),
    FOREIGN KEY (subasta_id) REFERENCES subasta(subasta_id)
);

CREATE TABLE sorteo (
    sorteo_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_articulo VARCHAR(255) NOT NULL,
    descripcion TEXT NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    puntos_necesarios INT NOT NULL,
    puntos_finales INT default 0,  -- Este campo ha sido añadido según tu solicitud
    trabajador_id BIGINT NOT NULL,  -- Asumiendo que 'creador' es un trabajador, y 'creador_id' es la clave foránea
    ganador_id BIGINT,
    imagen VARCHAR(255),
    categoria VARCHAR(255),
    FOREIGN KEY (trabajador_id) REFERENCES trabajador(trabajador_id)  -- Asumiendo que existe una tabla 'trabajador' con la columna 'trabajador_id'
);



CREATE TABLE puja_sorteo (
    puja_sorteo_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
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
INSERT INTO subasta (fecha_inicial, fecha_final, precio_inicial, precio_final, subasta_normal, nombre_articulo, descripcion, creador_id, imagen, categoria) 
VALUES 
('2023-05-01', '2025-10-10', 50.00, 70.00, TRUE, 'Reloj de bolsillo', 'Elegante y atemporal, este reloj de bolsillo combina artesanía clásica con precisión moderna. 
                    Su diseño vintage, con una fina cadena y detalles grabados, lo convierte en un accesorio 
                    sofisticado para cualquier ocasión.', 1, 'Reloj.jpg', 'moda'),  -- Creador es 'Juan Pérez'
('2025-04-01', '2025-06-10', 20.00, 30.00, TRUE, 'Sudadera', 'Disfruta del equilibrio perfecto entre comodidad y estilo con esta sudadera de diseño moderno. 
                    Confeccionada con materiales suaves y transpirables, ofrece un ajuste cómodo y versátil para cualquier ocasión. 
                    Ideal para los días fríos, su interior afelpado te mantendrá abrigado sin perder el estilo.', 2, 'sudadera_capucha_55_azulmarino_827_1024.jpg', 'moda'),  -- Creador es 'Ana García'
('2023-05-01', '2025-09-05', 70.00, 100.00, FALSE, 'Camara de fotos', 'Captura cada momento con precisión y claridad con esta cámara de fotos de alta resolución. 
                    Equipada con tecnología avanzada, ofrece imágenes nítidas, colores vibrantes y un enfoque rápido 
                    para no perder ningún detalle. Su diseño ergonómico y ligero la hace perfecta para llevar a cualquier aventura.', 1, 'portada-las-mejores-camaras-reflex-2019.jpg', 'tecnologia'),  -- Creador es 'Juan Pérez'
('2025-04-01', '2025-08-12', 50.00, 55.00, FALSE, 'Flexo', 'Un flexo moderno y funcional, ideal para iluminar tu espacio de trabajo o estudio. 
                    Su diseño ajustable permite dirigir la luz con precisión, mientras que su estructura resistente y 
                    elegante se adapta a cualquier entorno. Perfecto para leer, escribir o trabajar con comodidad.', 2, 'flexo-cadiz.jpg', 'hogar');  -- Creador es 'Ana García'


-- Insertar pujas
INSERT INTO puja (fecha, importe, ganadora, usuario_id, subasta_id) 
VALUES 
('2025-05-02', 70.00, FALSE, 1, 1),  -- Puja de 'Juan Pérez' en la subasta 1 (Reloj)
('2025-05-03', 30.00, FALSE, 2, 2),  -- Puja de 'Ana García' en la subasta 2 (Sudadera)
('2025-05-04', 100.00, FALSE, 1, 3),  -- Puja de 'Juan Pérez' en la subasta 3 (Camara)
('2025-05-03', 55.00, FALSE, 2, 4);  -- Puja de 'Ana García' en la subasta 4 (Flexo)

-- Insertar un sorteo
INSERT INTO sorteo (nombre_articulo, descripcion, fecha_inicio, fecha_fin, puntos_necesarios, trabajador_id, imagen, categoria)
VALUES
('LEGO McLaren F1', 'Revive la emoción de la Fórmula 1 con este increíble set de LEGO. Diseñado con gran detalle, este modelo 
                    captura la esencia de un monoplaza de carreras, con neumáticos realistas, alerones aerodinámicos y un diseño fiel a 
                    la competición. Perfecto para fans del automovilismo y constructores apasionados, este set ofrece una experiencia de ensamblaje 
                    envolvente y un resultado espectacular para exhibir. ¡Siente la velocidad y la adrenalina en cada pieza!', '2025-04-01', '2025-06-15', 3500,  
                    1, '77251_boxprod_v39_en-gb.png', 'juguetes'),  -- Ejemplo con un trabajador con id 2
('Televisor 4K', 'Un televisor de alta definición 4K de 50 pulgadas', '2025-05-01', '2024-05-15', 500,  1, 'tele.jpg', 'tecnologia'),  -- Ejemplo con un trabajador con id 1
('Smartphone', 'Smartphone de última tecnología con pantalla AMOLED', '2025-07-01', '2025-07-15', 200,  1, 'movil.jpg', 'tecnologia');  -- Ejemplo con un trabajador con id 3




INSERT INTO puja_sorteo (fecha, puntos, usuario_id, sorteo_id)
VALUES
('2024-04-10', 4100, 1, 1),  -- Ejemplo de una puja en el sorteo con ID 1, realizada por el usuario con ID 1, por 100 puntos
('2024-04-11', 3500, 2, 1),  -- Ejemplo de una puja en el sorteo con ID 1, realizada por el usuario con ID 2, por 150 puntos
('2025-04-12', 500, 2, 2),  -- Ejemplo de una puja en el sorteo con ID 2, realizada por el usuario con ID 2, por 200 puntos
('2025-04-13', 550, 1, 2),  -- Ejemplo de una puja en el sorteo con ID 2, realizada por el usuario con ID 1, por 250 puntos
('2025-04-14', 300, 2, 3);  -- Ejemplo de una puja en el sorteo con ID 3, realizada por el usuario con ID 2, por 300 puntos



INSERT INTO comentario (comentario, precio_estimado, trabajador_id, subasta_id) 
VALUES 
('Este es un comentario sobre la subasta 2', 30,  1, 2),
('Excelente subasta, muy recomendable', 50,  2, 1),
('La subasta fue muy interesante', 25 , 1, 2),
('Este comentario tiene detalles importantes sobre la subasta 4',60, 1, 1);




