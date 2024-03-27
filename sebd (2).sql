-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-03-2024 a las 00:45:07
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sebd`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `ID_CLIE` int(10) NOT NULL,
  `NOM_CLIE` varchar(50) NOT NULL,
  `RFC_CLIE` varchar(20) NOT NULL,
  `DIR_CLIE` varchar(100) DEFAULT NULL,
  `CP_CLIE` int(5) DEFAULT NULL,
  `TELF_CLIE` varchar(10) DEFAULT NULL,
  `MAIL_CLIE` varchar(100) DEFAULT NULL,
  `REGI_CLIE` varchar(100) DEFAULT NULL,
  `DIRFIS_CLIE` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`ID_CLIE`, `NOM_CLIE`, `RFC_CLIE`, `DIR_CLIE`, `CP_CLIE`, `TELF_CLIE`, `MAIL_CLIE`, `REGI_CLIE`, `DIRFIS_CLIE`) VALUES
(1, 'COBRO RAPIDO', 'COBRO RAPIDO', 'COBRO RAPIDO', 0, '0', 'COBRO RAPIDO', 'COBRO RAPIDO', 'COBRO RAPIDO'),
(2, 'Joannie Hill', 'QKJO161128MO9', '58370 Herman Rapids', 8370, '9618041051', 'vprosacco@example.com', '6c60:10f3:fa6:a1de:16d3:3894:34bd:811d', 'f25c:ac4:85e0:bd0c:5d85:c005:6b9:36ce\r\n'),
(3, 'Myah Sipes', 'KONT841025XD4', '9037 Hodkiewicz Street', 5378, '3863135080', 'donavon98@example.net', 'REGIMEN DE CONTRIBUYENTES', '3db2:af5c:b7de:ec3d:b73:4273:c538:1bbd'),
(6, 'Kathlyn Cummings DVM', 'KATCUMM3912', '93576 America Creek', 93576, '6282456535', 'penelope.terry@example.com', '6c60:10f3:fa6:a1de:16d3:3894:34bd:811d', '6c60:10f3:fa6:a1de:16d3:3894:34bd:811d');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `partidas`
--

CREATE TABLE `partidas` (
  `ID_PAR` int(11) NOT NULL,
  `ID_REP` int(11) NOT NULL,
  `ID_PROD` int(11) NOT NULL,
  `CAN_PAR` int(11) NOT NULL,
  `SUBT_PAR` decimal(8,2) NOT NULL,
  `IVA_PAR` decimal(8,2) NOT NULL,
  `TOT_PAR` decimal(8,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `partidas`
--

INSERT INTO `partidas` (`ID_PAR`, `ID_REP`, `ID_PROD`, `CAN_PAR`, `SUBT_PAR`, `IVA_PAR`, `TOT_PAR`) VALUES
(1, 1, 1, 1, 155.17, 24.83, 180.00),
(2, 1, 2, 1, 25.00, 4.00, 29.00),
(3, 2, 3, 2, 13.79, 2.21, 16.00),
(4, 2, 2, 2, 50.00, 8.00, 58.00),
(5, 2, 1, 1, 155.17, 24.83, 180.00),
(6, 3, 1, 2, 310.34, 49.66, 360.00),
(7, 3, 2, 1, 25.00, 4.00, 29.00),
(8, 4, 3, 1, 6.90, 1.10, 8.00),
(9, 4, 2, 1, 25.00, 4.00, 29.00),
(10, 5, 1, 3, 465.52, 74.48, 540.00),
(11, 5, 2, 1, 25.00, 4.00, 29.00),
(14, 8, 2, 1, 25.00, 4.00, 29.00),
(15, 9, 2, 2, 50.00, 8.00, 58.00),
(16, 9, 1, 1, 155.17, 24.83, 180.00),
(17, 10, 1, 1, 155.17, 24.83, 180.00),
(18, 10, 2, 1, 25.00, 4.00, 29.00),
(19, 10, 3, 1, 6.90, 1.10, 8.00),
(20, 11, 1, 1, 155.17, 24.83, 180.00),
(21, 11, 2, 4, 100.00, 16.00, 116.00),
(22, 11, 3, 1, 6.90, 1.10, 8.00),
(23, 12, 1, 1, 155.17, 24.83, 180.00),
(24, 12, 2, 1, 25.00, 4.00, 29.00),
(25, 13, 1, 2, 310.34, 49.66, 360.00),
(26, 13, 2, 1, 25.00, 4.00, 29.00),
(27, 13, 3, 1, 6.90, 1.10, 8.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `ID_PROD` int(15) NOT NULL,
  `CODIGO_PROD` varchar(20) DEFAULT NULL,
  `SERIE_PROD` varchar(20) DEFAULT NULL,
  `CAT_PROD` char(10) DEFAULT NULL,
  `UM_PROD` varchar(30) DEFAULT NULL,
  `DES_PROD` varchar(150) NOT NULL,
  `PRG_PROD` decimal(8,2) NOT NULL,
  `PRT_PROD` decimal(8,2) DEFAULT NULL,
  `DIM_PROD` varchar(100) DEFAULT NULL,
  `STOCK_PROD` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`ID_PROD`, `CODIGO_PROD`, `SERIE_PROD`, `CAT_PROD`, `UM_PROD`, `DES_PROD`, `PRG_PROD`, `PRT_PROD`, `DIM_PROD`, `STOCK_PROD`) VALUES
(1, '5901234123457', 'AA-5901', 'AA', 'PIEZA', 'CUADERNO CUADROS 25 X 30 CM', 180.00, 90.00, NULL, 98),
(2, '5266917323549', 'FE-9281', 'FERRETERIA', 'PIEZA', 'LAPIZ CARBON NEGRO A89', 29.00, 8.00, '15 X 0.5 CM', 783),
(3, '7335639814173', 'FE-7641', 'FERRETERIA', 'PIEZA', 'SACAPUNTAS ROJO', 8.00, 2.00, '2 X 3 CM', 463),
(4, '1931425849969', 'FE-2923', 'SERVICIOS', 'PIEZA', 'MOUSE LOGITECH NEGRO G-3912', 450.00, 98.42, '10 X 5 X 2 CM', 95),
(5, '7302540098275', 'VFM-942', 'SERVICIOS', 'METROS CUBICOS', 'VASO PARA LICUADORA LG', 975.00, 321.00, '75 CM', 981),
(6, '0130220856329', 'QKS-991', 'AA', '1', 'TACO DE PASTOR', 15.00, 3.00, '1', 99),
(7, NULL, NULL, 'SERVICIOS', NULL, 'AUDIFONOS SONY', 33.00, NULL, NULL, NULL),
(8, '1639391398820', 'MPG-912', 'FERRETERIA', 'LITROS', 'BOTELLA DE AGUA DE 2 L', 12.00, 2.00, '', 148);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reportes`
--

CREATE TABLE `reportes` (
  `ID_REP` int(10) NOT NULL,
  `ID_USU` int(11) NOT NULL,
  `ID_CLIE` int(11) DEFAULT NULL,
  `NOTA_REP` varchar(5) NOT NULL,
  `DATE_REP` varchar(12) NOT NULL,
  `FP_REP` varchar(15) NOT NULL,
  `TOTAL_REP` decimal(8,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `reportes`
--

INSERT INTO `reportes` (`ID_REP`, `ID_USU`, `ID_CLIE`, `NOTA_REP`, `DATE_REP`, `FP_REP`, `TOTAL_REP`) VALUES
(1, 1, 1, '0001', '03-26-2024', 'EFECTIVO', 209.00),
(2, 1, 3, '0002', '03-26-2024', 'TARJETA', 204.00),
(3, 1, 2, '0003', '03-26-2024', 'TARJETA', 389.00),
(4, 1, 1, '0004', '03-26-2024', 'EFECTIVO', 37.00),
(5, 1, 1, '0005', '03-27-2024', 'TARJETA', 569.00),
(7, 1, 3, '0006', '03-27-2024', 'EFECTIVO', 29.00),
(8, 1, 3, '0008', '03-27-2024', 'EFECTIVO', 29.00),
(9, 1, 2, '0009', '03-27-2024', 'TARJETA', 238.00),
(10, 1, 6, '0010', '03-27-2024', 'TARJETA', 217.00),
(11, 1, 3, '0011', '03-27-2024', 'TRANSFERENCIA', 304.00),
(12, 1, 3, '0012', '03-27-2024', 'EFECTIVO', 209.00),
(13, 1, 1, '0013', '03-27-2024', 'TARJETA', 397.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `ID_USU` int(11) NOT NULL,
  `NOM_USU` varchar(50) NOT NULL,
  `TELF_USU` varchar(11) NOT NULL,
  `CARGO_USU` varchar(20) NOT NULL,
  `PSSWD_USU` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`ID_USU`, `NOM_USU`, `TELF_USU`, `CARGO_USU`, `PSSWD_USU`) VALUES
(1, 'HECTOR MANUEL PADILLA GOVIN', '9381447244', 'VENDEDOR', 'AAA111'),
(2, 'ANA VALERIA LOPEZ ROMERO', '8451046562', 'VENDEDOR', 'AAA111'),
(6, 'SAID CORREA PEREZ', '9381447243', 'MOSTRADOR', 'MOS19312');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`ID_CLIE`);

--
-- Indices de la tabla `partidas`
--
ALTER TABLE `partidas`
  ADD PRIMARY KEY (`ID_PAR`),
  ADD KEY `ID_PROD` (`ID_PROD`),
  ADD KEY `fk_partidas_rep` (`ID_REP`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`ID_PROD`),
  ADD UNIQUE KEY `CODIGO_PROD` (`CODIGO_PROD`);

--
-- Indices de la tabla `reportes`
--
ALTER TABLE `reportes`
  ADD PRIMARY KEY (`ID_REP`),
  ADD KEY `fk_reporte_usuario` (`ID_USU`),
  ADD KEY `fk_reporte_clientes` (`ID_CLIE`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`ID_USU`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `ID_CLIE` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `partidas`
--
ALTER TABLE `partidas`
  MODIFY `ID_PAR` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `ID_PROD` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `reportes`
--
ALTER TABLE `reportes`
  MODIFY `ID_REP` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `ID_USU` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `partidas`
--
ALTER TABLE `partidas`
  ADD CONSTRAINT `fk_partidas_rep` FOREIGN KEY (`ID_REP`) REFERENCES `reportes` (`ID_REP`),
  ADD CONSTRAINT `partidas_ibfk_1` FOREIGN KEY (`ID_PROD`) REFERENCES `productos` (`ID_PROD`);

--
-- Filtros para la tabla `reportes`
--
ALTER TABLE `reportes`
  ADD CONSTRAINT `fk_reporte_clientes` FOREIGN KEY (`ID_CLIE`) REFERENCES `clientes` (`ID_CLIE`),
  ADD CONSTRAINT `fk_reporte_usuario` FOREIGN KEY (`ID_USU`) REFERENCES `usuarios` (`ID_USU`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
