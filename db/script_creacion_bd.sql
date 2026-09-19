CREATE DATABASE IF NOT EXISTS ejercicio21;
USE ejercicio21;

CREATE TABLE usuario (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  clave  VARCHAR(255) NOT NULL,
  correo VARCHAR(150) NOT NULL,
  rol    VARCHAR(30)  NOT NULL
);

CREATE TABLE aplicacion (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre                VARCHAR(100),
  proveedor             VARCHAR(100),
  categoria             VARCHAR(50),
  lenguaje_principal    VARCHAR(50),
  lenguaje_secundario   VARCHAR(50),
  usa_bd                BOOLEAN,
  requiere_conexion_red BOOLEAN,
  num_bits              INT,
  sistema_operativo     VARCHAR(50),
  requisitos_hardware   VARCHAR(255),
  licencia              VARCHAR(50),
  precio                DECIMAL(10,2),
  descripcion           TEXT,
  web                   VARCHAR(150),
  correo                VARCHAR(150),
  tamano_instalador     DECIMAL(8,2)
);

INSERT INTO usuario (nombre, clave, correo, rol) VALUES
('Admin Principal', '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9', 'admin@correo.com', 'administrador'),
('Juan Perez', 'f6ccb3e8d609012238c0b39e60b2c9632b3cdede91e035dad1de43469768f4cc', 'juan@correo.com', 'usuario'),
('Maria Lopez', '626e3c805e77eeb472c42c6be607be2af7ac5c08fd7050f278e0330fe81abf57', 'maria@correo.com', 'usuario');

INSERT INTO aplicacion
(nombre, proveedor, categoria, lenguaje_principal, lenguaje_secundario, usa_bd, requiere_conexion_red, num_bits, sistema_operativo, requisitos_hardware, licencia, precio, descripcion, web, correo, tamano_instalador) VALUES
('Visual Studio Code', 'Microsoft', 'Desarrollo', 'TypeScript', 'JavaScript', FALSE, TRUE, 64, 'Windows/Linux/macOS', '4GB RAM, 1GB disco', 'Gratis', 0.00, 'Editor de código fuente ligero y extensible.', 'https://code.visualstudio.com', 'soporte@microsoft.com', 95.50),
('MySQL Workbench', 'Oracle', 'Bases de datos', 'C++', 'Python', TRUE, FALSE, 64, 'Windows/Linux/macOS', '2GB RAM, 500MB disco', 'Gratis', 0.00, 'Herramienta visual para diseño y administración de bases de datos MySQL.', 'https://dev.mysql.com', 'soporte@oracle.com', 450.00),
('Adobe Photoshop', 'Adobe', 'Diseño', 'C++', 'JavaScript', FALSE, TRUE, 64, 'Windows/macOS', '8GB RAM, 4GB disco', 'Pago', 239900.00, 'Software profesional de edición y retoque fotográfico.', 'https://adobe.com/photoshop', 'soporte@adobe.com', 3200.00),
('Spotify', 'Spotify AB', 'Multimedia', 'C++', 'Python', TRUE, TRUE, 64, 'Windows/Linux/macOS/Android/iOS', '2GB RAM', 'Freemium', 0.00, 'Plataforma de streaming de música y podcasts.', 'https://spotify.com', 'soporte@spotify.com', 120.00),
('Notepad++', 'Notepad++ Team', 'Desarrollo', 'C++', NULL, FALSE, FALSE, 32, 'Windows', '512MB RAM', 'Gratis', 0.00, 'Editor de texto simple orientado a programadores.', 'https://notepad-plus-plus.org', 'contacto@notepad-plus-plus.org', 4.50),
('Google Chrome', 'Google', 'Navegador', 'C++', 'JavaScript', FALSE, TRUE, 64, 'Windows/Linux/macOS/Android', '4GB RAM', 'Gratis', 0.00, 'Navegador web desarrollado por Google.', 'https://google.com/chrome', 'soporte@google.com', 90.00);