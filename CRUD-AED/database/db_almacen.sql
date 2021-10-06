-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: db_almacen
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `almacen`
--

DROP TABLE IF EXISTS `almacen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `almacen` (
  `id_almacen` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(50) DEFAULT NULL,
  `direccion` varchar(50) NOT NULL,
  PRIMARY KEY (`id_almacen`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `almacen`
--

LOCK TABLES `almacen` WRITE;
/*!40000 ALTER TABLE `almacen` DISABLE KEYS */;
INSERT INTO `almacen` VALUES (1,'almacen primario','calle de mi casa n1'),(2,'almacen secundario','calle de tu casa n2'),(3,'almacen terciarioo','calle de su casa n3');
/*!40000 ALTER TABLE `almacen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `composicionpieza`
--

DROP TABLE IF EXISTS `composicionpieza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `composicionpieza` (
  `id_pieza_padre` varchar(10) NOT NULL,
  `id_pieza_hija` varchar(10) NOT NULL,
  PRIMARY KEY (`id_pieza_padre`,`id_pieza_hija`),
  KEY `id_pieza_hija` (`id_pieza_hija`),
  CONSTRAINT `composicionpieza_ibfk_1` FOREIGN KEY (`id_pieza_padre`) REFERENCES `pieza` (`id_pieza`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `composicionpieza_ibfk_2` FOREIGN KEY (`id_pieza_hija`) REFERENCES `pieza` (`id_pieza`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `composicionpieza`
--

LOCK TABLES `composicionpieza` WRITE;
/*!40000 ALTER TABLE `composicionpieza` DISABLE KEYS */;
/*!40000 ALTER TABLE `composicionpieza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estanteria`
--

DROP TABLE IF EXISTS `estanteria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estanteria` (
  `id_estanteria` char(3) NOT NULL,
  `id_almacen` int NOT NULL,
  PRIMARY KEY (`id_estanteria`,`id_almacen`),
  KEY `id_almacen` (`id_almacen`),
  CONSTRAINT `estanteria_ibfk_1` FOREIGN KEY (`id_almacen`) REFERENCES `almacen` (`id_almacen`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estanteria`
--

LOCK TABLES `estanteria` WRITE;
/*!40000 ALTER TABLE `estanteria` DISABLE KEYS */;
INSERT INTO `estanteria` VALUES ('ABC',1),('DBC',1),('GHJ',1),('ABC',2),('DBC',2),('ABC',3),('DBC',3);
/*!40000 ALTER TABLE `estanteria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pieza`
--

DROP TABLE IF EXISTS `pieza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pieza` (
  `id_pieza` varchar(10) NOT NULL,
  `descripcion` varchar(50) DEFAULT NULL,
  `precio` double NOT NULL,
  PRIMARY KEY (`id_pieza`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pieza`
--

LOCK TABLES `pieza` WRITE;
/*!40000 ALTER TABLE `pieza` DISABLE KEYS */;
INSERT INTO `pieza` VALUES ('ce10','Saco de cemento',32.67),('de5','Destornillador',10.6),('la11','Caja de ladrillos',51.83),('ll8','Llave inglesa',15.8),('ma4','Martillo',12.4),('Ow0','a',1),('pl3','Plancha de madera',20.3),('pl9','Plancha de metal',25.25),('po6','Pomo',5.45),('pu7','Puerta',40.7),('to2','Tornillo',2),('tu1','Tuerca',1.5),('ur92','Uranio',1546.69),('Uw4','b',2);
/*!40000 ALTER TABLE `pieza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `piezaenestanteria`
--

DROP TABLE IF EXISTS `piezaenestanteria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `piezaenestanteria` (
  `id_almacen` int NOT NULL,
  `id_pieza` varchar(10) NOT NULL,
  `id_estanteria` char(3) NOT NULL,
  `cantidad` int DEFAULT NULL,
  PRIMARY KEY (`id_estanteria`,`id_almacen`,`id_pieza`),
  KEY `id_almacen` (`id_almacen`),
  KEY `id_pieza` (`id_pieza`),
  CONSTRAINT `piezaenestanteria_ibfk_1` FOREIGN KEY (`id_almacen`) REFERENCES `almacen` (`id_almacen`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `piezaenestanteria_ibfk_2` FOREIGN KEY (`id_estanteria`) REFERENCES `estanteria` (`id_estanteria`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `piezaenestanteria_ibfk_3` FOREIGN KEY (`id_pieza`) REFERENCES `pieza` (`id_pieza`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `piezaenestanteria`
--

LOCK TABLES `piezaenestanteria` WRITE;
/*!40000 ALTER TABLE `piezaenestanteria` DISABLE KEYS */;
INSERT INTO `piezaenestanteria` VALUES (1,'to2','ABC',12),(1,'tu1','ABC',7),(2,'de5','ABC',3),(2,'po6','ABC',8),(3,'ce10','ABC',1),(3,'pl9','ABC',25),(1,'ma4','DBC',24),(1,'pl3','DBC',5),(2,'ll8','DBC',892),(2,'pu7','DBC',11),(3,'la11','DBC',9),(3,'ur92','DBC',15);
/*!40000 ALTER TABLE `piezaenestanteria` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-06 22:05:32
