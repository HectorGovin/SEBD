-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-03-2024 a las 01:37:56
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

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
  `DIR_CLIE` varchar(100) NOT NULL,
  `CP_CLIE` int(5) NOT NULL,
  `TELF_CLIE` varchar(10) NOT NULL,
  `MAIL_CLIE` varchar(100) NOT NULL,
  `REGI_CLIE` varchar(100) NOT NULL,
  `DIRFIS_CLIE` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`ID_CLIE`, `NOM_CLIE`, `RFC_CLIE`, `DIR_CLIE`, `CP_CLIE`, `TELF_CLIE`, `MAIL_CLIE`, `REGI_CLIE`, `DIRFIS_CLIE`) VALUES
(1, 'DANIEL MARTINEZ LARA', 'MALD8708169C0', 'DANIEL MARTINEZ', 24180, 'NULL', 'tabas_refaccionaria@hotmail.com', 'Régimen de las Personas Físicas con Actividades Empresariales y Profesionales', 'AV. 10 DE JULIO NUM. EXT. 374 COLONIA BENITO JUAREZ CIUDAD DEL CARMEN CAMPECHE C.P. 24180');

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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `ID_PROD` int(15) NOT NULL,
  `CODIGO_PROD` varchar(20) NOT NULL,
  `SERIE_PROD` varchar(20) NOT NULL,
  `CAT_PROD` char(10) NOT NULL,
  `UM_PROD` varchar(30) NOT NULL,
  `DES_PROD` varchar(150) NOT NULL,
  `PRG_PROD` decimal(8,2) NOT NULL,
  `PRT_PROD` decimal(8,2) NOT NULL,
  `DIM_PROD` varchar(100) DEFAULT NULL,
  `STOCK_PROD` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`ID_PROD`, `CODIGO_PROD`, `SERIE_PROD`, `CAT_PROD`, `UM_PROD`, `DES_PROD`, `PRG_PROD`, `PRT_PROD`, `DIM_PROD`, `STOCK_PROD`) VALUES
(1, '7502224244343', 'ALQ-150', 'AA', 'LITROS', 'Aceite alquilbenceno-150 para compresor gas R-12, R22 y amoniaco 1LT mca. Texas', 210.00, 205.00, NULL, 37),
(2, '7503009088664', 'VACUUM', 'AA', 'MILILITROS', 'Aceite alquilbencenoVacuum para bomba de vacio  50ml mca. Texas', 110.00, 110.00, NULL, 16),
(3, '', 'C-10013', 'FERRETERIA', 'PIEZAS', 'Abrazadera  de acero inoxidable blister 2 pza. mca. Fiero\r\n', 35.00, 30.00, NULL, 108),
(4, '', 'C-44989', 'FERRETERIA', 'PIEZA', 'Alcayata roscada 21X80MM 10 pzas mca. Fiero\r\n', 30.00, 30.00, '21 X 80 MM', 95),
(5, '', 'SV-1013', 'SERVICIOS', 'SERVICIOS', 'Servicio de mantenimiento preventivo a equipo de AA tipo ventana 1 ton marca Mirage\r\n', 600.00, 600.00, NULL, NULL),
(6, '', 'SV-1015', 'SERVICIOS', 'SERVICIOS', 'Servicio de mantenimiento preventivo a equipo  de AA tipo minisplit 1.5 ton 220v mod. Xlife marca mirage\r\n', 800.00, 800.00, NULL, NULL),
(7, '4005900513069', 'DESODORANTE', 'FERRETERIA', 'ml', 'DESODORANTE NIVEA MEN DEEP BLACK CARBON', 100.00, 120.00, '', 87),
(8, '729513102123', 'MAS-0391', 'FERRETERIA', 'PIEZA', 'CUBREBOCA DESECHABLE PLISADO CON DISEÑO', 190.00, 170.00, '', 39),
(9, '7506129448264', 'LIB-9712', 'FERRETERIA', 'PIEZA', 'LIBRETA SCRIBE', 90.00, 100.00, '14,8 X 20,5 cm', 100),
(10, '7502258102282', 'VDCX-13VA', 'AA', 'ML', 'ZUUM KLIN SSPRAY', 90.00, 40.00, '', 99),
(11, '5901234123457', 'A-457', 'AA', 'PIEZA', 'Cuaderno de cuadros tamaño normal.', 400.00, 350.00, '', 50),
(12, '5266917323549', 'F-549', 'FERRETERIA', 'PIEZA', 'Lapiz Mapad NB-Z', 30.00, 22.00, '', 541),
(13, '7335639814173', 'S-173', 'SERVICIOS', 'PIEZA', 'Borrador ', 15.00, 8.75, '', 100),
(14, '1931425849969', 'A-969', 'AA', 'Pieza', 'Mouse', 150.00, 200.00, '87', 50),
(15, '8794124843002', 'F-002', 'AA', 'MILILITROS', 'Botella de agua de 400 ml', 10.00, 2.00, '', 500),
(16, '7302540098275', 'S-275', 'SERVICIOS', 'KILOGRAMOS', 'Bolsa de azucar 1 kg.', 50.00, 8.00, '', 100);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reportes`
--

CREATE TABLE `reportes` (
  `ID_REP` int(10) NOT NULL,
  `ID_USU` int(11) NOT NULL,
  `NOTA_REP` varchar(5) NOT NULL,
  `DATE_REP` date NOT NULL,
  `FP_REP` varchar(8) NOT NULL,
  `ID_CLIE` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sucursales`
--

CREATE TABLE `sucursales` (
  `ID_SUC` int(11) NOT NULL,
  `ID_NOM` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `sucursales`
--

INSERT INTO `sucursales` (`ID_SUC`, `ID_NOM`) VALUES
(1, 'SUCURSAL DE 42 B'),
(2, 'SUCURSAL DE 42 A');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `ID_USU` int(11) NOT NULL,
  `NOM_USU` varchar(50) NOT NULL,
  `TELF_USU` varchar(11) NOT NULL,
  `CARGO_USU` varchar(20) NOT NULL,
  `PSSWD_USU` varchar(12) NOT NULL,
  `ID_SUC` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`ID_USU`, `NOM_USU`, `TELF_USU`, `CARGO_USU`, `PSSWD_USU`, `ID_SUC`) VALUES
(1, 'HECTOR MANUEL PADILLA GOVIN', '9381447243', 'VENDEDOR', 'rIH9PQ47Nn', 1);

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
  ADD PRIMARY KEY (`ID_PROD`);

--
-- Indices de la tabla `reportes`
--
ALTER TABLE `reportes`
  ADD PRIMARY KEY (`ID_REP`),
  ADD KEY `fk_reporte_usuario` (`ID_USU`),
  ADD KEY `fk_reporte_clientes` (`ID_CLIE`);

--
-- Indices de la tabla `sucursales`
--
ALTER TABLE `sucursales`
  ADD PRIMARY KEY (`ID_SUC`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`ID_USU`),
  ADD KEY `RES1` (`ID_SUC`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `ID_CLIE` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `partidas`
--
ALTER TABLE `partidas`
  MODIFY `ID_PAR` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `ID_PROD` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `reportes`
--
ALTER TABLE `reportes`
  MODIFY `ID_REP` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `sucursales`
--
ALTER TABLE `sucursales`
  MODIFY `ID_SUC` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `ID_USU` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

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

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `RES1` FOREIGN KEY (`ID_SUC`) REFERENCES `sucursales` (`ID_SUC`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
