-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-11-2021 a las 03:53:18
-- Versión del servidor: 10.1.40-MariaDB
-- Versión de PHP: 7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `nja`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carrito`
--

CREATE TABLE `carrito` (
  `ca_id` int(11) NOT NULL,
  `po_id` int(11) NOT NULL,
  `us_id` int(11) NOT NULL,
  `ca_precio` double NOT NULL,
  `ca_fecha_registro` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `carrito`
--

INSERT INTO `carrito` (`ca_id`, `po_id`, `us_id`, `ca_precio`, `ca_fecha_registro`) VALUES
(1, 1, 2, 0, '2021-11-16 02:49:13'),
(2, 2, 2, 0, '2021-11-16 02:50:01'),
(4, 2, 2, 0, '2021-11-16 19:31:34'),
(5, 2, 2, 50, '2021-11-25 21:46:57');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `catg_id` int(11) NOT NULL,
  `catg_nombre` varchar(100) NOT NULL,
  `catg_imagen` varchar(100) NOT NULL DEFAULT 'default.jpg',
  `catg_activo` varchar(1) NOT NULL DEFAULT 'S'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`catg_id`, `catg_nombre`, `catg_imagen`, `catg_activo`) VALUES
(1, 'Ofertas', 'default.jpg', 'S');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contactenos`
--

CREATE TABLE `contactenos` (
  `co_id` int(11) NOT NULL,
  `co_nombre` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `co_email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `co_asunto` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `co_mensaje` varchar(2000) COLLATE utf8_unicode_ci NOT NULL,
  `co_fecha` datetime NOT NULL,
  `co_leido` varchar(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'N'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `contactenos`
--

INSERT INTO `contactenos` (`co_id`, `co_nombre`, `co_email`, `co_asunto`, `co_mensaje`, `co_fecha`, `co_leido`) VALUES
(1, 'Matias', 'matias@gmail.com', 'Importante', 'Alguna vez haz sido tu propio jefe?', '2021-11-25 20:51:00', 'S'),
(2, 'Matias', 'matias@gmail.com', 'Importante', 'Alguna vez haz sido tu propio jefe?', '2021-11-25 20:59:55', 'N');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ofertas`
--

CREATE TABLE `ofertas` (
  `of_id` int(11) NOT NULL,
  `of_nombre` varchar(200) NOT NULL,
  `of_precio` double NOT NULL,
  `of_precioDescuento` double NOT NULL,
  `of_cantidad` int(11) NOT NULL,
  `of_fechaMaxima` date NOT NULL,
  `of_activo` varchar(1) NOT NULL DEFAULT 'S'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `ofertas`
--

INSERT INTO `ofertas` (`of_id`, `of_nombre`, `of_precio`, `of_precioDescuento`, `of_cantidad`, `of_fechaMaxima`, `of_activo`) VALUES
(1, 'Adidos', 34.4, 16.2, 5, '2025-11-20', 'S'),
(2, 'Adidas 2', 34.4, 16.2, 5, '2025-11-20', 'S'),
(3, 'Adidos 4', 34.4, 16.2, 5, '2025-11-20', 'S'),
(4, 'Adidos 4', 34.4, 16.2, 5, '2025-11-20', 'S');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `po_id` int(11) NOT NULL,
  `po_nombre` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `po_marca` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'NJA',
  `po_precio` double NOT NULL DEFAULT '10',
  `po_categoria` varchar(80) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Camisa',
  `po_cantidad` int(1) NOT NULL DEFAULT '1',
  `po_imagen` varchar(200) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'default.jpg',
  `po_activo` varchar(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'S'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`po_id`, `po_nombre`, `po_marca`, `po_precio`, `po_categoria`, `po_cantidad`, `po_imagen`, `po_activo`) VALUES
(1, 'Adidos', 'NJA', 34.4, 'Zapatos', 1, 'default.jpg', 'S'),
(2, 'Adidas 2', 'NJA', 34.4, 'Zapatos', 1, 'default.jpg', 'S');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `rol_id` int(11) NOT NULL,
  `rol_nombre` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`rol_id`, `rol_nombre`) VALUES
(1, 'Administrador'),
(2, 'Vendedor'),
(3, 'Usuario Normal');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `us_id` int(11) NOT NULL,
  `us_usuario` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `us_password` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `us_correo` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `us_telefono` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `us_rol` int(11) NOT NULL,
  `us_activo` varchar(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'S'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`us_id`, `us_usuario`, `us_password`, `us_correo`, `us_telefono`, `us_rol`, `us_activo`) VALUES
(2, 'Matthew', '12345', 'Matthew@gmail.com', '3132254784', 1, 'S'),
(3, 'Fernanda', '54321', 'Fernanda@gmail.com', '3182254785', 1, 'S'),
(4, 'Jorge', '12345', 'jorge@gmail.com', '3152254784', 2, 'S'),
(5, 'Raul', '54321', 'raul@gmail.com', '3202254785', 2, 'S'),
(6, 'Ricardo', '12345', 'ricardo@gmail.com', '3212254784', 3, 'S'),
(7, 'Saul', '54321', 'Saul@gmail.com', '3002254785', 3, 'S');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `carrito`
--
ALTER TABLE `carrito`
  ADD PRIMARY KEY (`ca_id`);

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`catg_id`);

--
-- Indices de la tabla `contactenos`
--
ALTER TABLE `contactenos`
  ADD PRIMARY KEY (`co_id`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`po_id`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`rol_id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`us_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `carrito`
--
ALTER TABLE `carrito`
  MODIFY `ca_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `categorias`
--
ALTER TABLE `categorias`
  MODIFY `catg_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `contactenos`
--
ALTER TABLE `contactenos`
  MODIFY `co_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `po_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `rol_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `us_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
