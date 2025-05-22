CREATE DATABASE sakuramemory;

use sakuramemory;
CREATE TABLE Usuarios (
                          id_usuario INT AUTO_INCREMENT PRIMARY KEY,
                          nombre_usuario VARCHAR(50) NOT NULL UNIQUE,
                          contrasena CHAR(60) NOT NULL,
                          fecha_registro DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Puntuaciones (
                              id_puntuacion INT AUTO_INCREMENT PRIMARY KEY,
                              id_usuario INT,
                              puntos INT NOT NULL,
                              modo_juego VARCHAR(20),
                              FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario)
);
