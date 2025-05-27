-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: db
-- Tiempo de generación: 27-05-2025 a las 06:15:48
-- Versión del servidor: 8.1.0
-- Versión de PHP: 8.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `biblioteca`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libro`
--

CREATE TABLE `libro` (
  `id` bigint NOT NULL,
  `titulo` varchar(255) NOT NULL,
  `autor` varchar(150) DEFAULT NULL,
  `isbn` varchar(20) DEFAULT NULL,
  `fecha_publicacion` date DEFAULT NULL,
  `genero` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `libro`
--

INSERT INTO `libro` (`id`, `titulo`, `autor`, `isbn`, `fecha_publicacion`, `genero`) VALUES
(1, '1984', 'George Orwell', '9780451524935', '1949-06-08', 'Distopía'),
(2, 'El nombre del viento', 'Patrick Rothfuss', '9788401352836', '2007-03-27', 'Fantasía'),
(3, 'Clean Code', 'Robert C. Martin', '9780132350884', '2008-08-01', 'Tecnología'),
(4, 'Orgullo y prejuicio', 'Jane Austen', '9780141439518', '1813-01-28', 'Romance');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` bigint NOT NULL,
  `username` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `apellidos` varchar(150) DEFAULT NULL,
  `fecha_registro` datetime DEFAULT CURRENT_TIMESTAMP,
  `role` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `username`, `email`, `password`, `nombre`, `apellidos`, `fecha_registro`, `role`) VALUES
(1, 'juan', 'juan@example.com', '$2a$10$74zaGMFqofMkK4lXZbBMteTFdF9vm7Ih5icesJrs7XQMDDqv5aAaK', 'Juan', 'Pérez García', '2025-05-25 08:08:40', 'user'),
(2, 'maria_lg', 'maria@example.com', '$2a$10$74zaGMFqofMkK4lXZbBMteTFdF9vm7Ih5icesJrs7XQMDDqv5aAaK', 'María', 'López Gómez', '2025-05-25 08:08:40', 'user'),
(3, 'carlos_dev', 'carlos@example.com', '$2a$10$74zaGMFqofMkK4lXZbBMteTFdF9vm7Ih5icesJrs7XQMDDqv5aAaK', 'Carlos', 'Delgado Ruiz', '2025-05-25 08:08:40', 'user'),
(5, 'ioliasa', 'ioliasa@gmail.com', '$2a$10$74zaGMFqofMkK4lXZbBMteTFdF9vm7Ih5icesJrs7XQMDDqv5aAaK', 'Inma', 'Olias', NULL, 'admin');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_libro`
--

CREATE TABLE `usuario_libro` (
  `usuario_id` bigint NOT NULL,
  `libro_id` bigint NOT NULL,
  `fecha_adquisicion` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `usuario_libro`
--

INSERT INTO `usuario_libro` (`usuario_id`, `libro_id`, `fecha_adquisicion`) VALUES
(1, 1, '2023-01-10'),
(1, 3, '2023-03-22'),
(2, 2, '2023-05-05'),
(2, 4, '2023-06-15'),
(3, 1, '2024-02-12'),
(3, 3, '2024-01-10');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `libro`
--
ALTER TABLE `libro`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `isbn` (`isbn`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indices de la tabla `usuario_libro`
--
ALTER TABLE `usuario_libro`
  ADD PRIMARY KEY (`usuario_id`,`libro_id`),
  ADD KEY `libro_id` (`libro_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `libro`
--
ALTER TABLE `libro`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `usuario_libro`
--
ALTER TABLE `usuario_libro`
  ADD CONSTRAINT `usuario_libro_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `usuario_libro_ibfk_2` FOREIGN KEY (`libro_id`) REFERENCES `libro` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
