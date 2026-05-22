CREATE DATABASE  IF NOT EXISTS `refugio` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `refugio`;
-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: refugio
-- ------------------------------------------------------
-- Server version	8.0.45

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
-- Table structure for table `animal`
--

DROP TABLE IF EXISTS `animal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `animal` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) NOT NULL,
  `raza` varchar(100) DEFAULT NULL,
  `sexo` enum('hembra','macho') NOT NULL,
  `color` varchar(100) DEFAULT NULL,
  `edad` varchar(20) DEFAULT NULL,
  `marcasDistintivas` text,
  `numeroChip` varchar(15) DEFAULT NULL,
  `esterilizado` tinyint(1) DEFAULT '0',
  `historia` longtext,
  `observaciones` mediumtext,
  `fechaIngreso` date DEFAULT (curdate()),
  `adoptado` tinyint(1) DEFAULT '0',
  `fechaAlta` date DEFAULT NULL,
  `dniAdoptante` char(9) DEFAULT NULL,
  `idUbicacion` int unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `dniAdoptante` (`dniAdoptante`),
  KEY `idUbicacion` (`idUbicacion`),
  CONSTRAINT `animal_ibfk_1` FOREIGN KEY (`dniAdoptante`) REFERENCES `adoptante` (`dniAdoptante`) ON UPDATE CASCADE,
  CONSTRAINT `animal_ibfk_2` FOREIGN KEY (`idUbicacion`) REFERENCES `ubicacion` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animal`
--

LOCK TABLES `animal` WRITE;
/*!40000 ALTER TABLE `animal` DISABLE KEYS */;
INSERT INTO `animal` VALUES (1,'Curie','Común Europeo','hembra','Calicó','1 mes',NULL,NULL,1,'Encontrada en una depuradora de una piscina, su madre les había abandonado, única superviviente de la camada ya que tenían todos cojuntivitis y gripe felina',NULL,'2025-05-16',1,'2025-06-16','12345678Z',1),(2,'Daphne','Común Europeo','hembra','Gris atigrado','4 años',NULL,NULL,1,'Vivía cerca de una comunidad de vecinos donde era alimentada por una persona pero la comunidad decidió echar a Daphne de allí',NULL,'2025-11-15',1,'2026-01-23','87654321A',1),(3,'Bianca','Cruce de Podenco','hembra','Blanco','2 años',NULL,NULL,1,'Encontrada deambulando sola por la calle con frío. Fue rescatada y pasó a casa de acogida.','Muy sociable, simpática y graciosa. Le encanta el sofá y pasear explorando.','2026-03-15',1,'2026-05-20','12345678Z',13),(4,'Milo','Angora','macho','Negro','2 años',NULL,NULL,1,'Rescatado de una casa abandonada. Es muy tranquilo y noble.',NULL,'2025-12-01',0,'2026-05-18','12345678z',1),(6,'Zeus','Rottweiler','macho','Negro y fuego','6 años',NULL,NULL,1,NULL,'Nivel de agresividad alto con otros machos. Requiere manejo experto.','2025-09-14',1,'2026-05-19','12345678Z',5),(7,'Chispa','Caniche','hembra','Canela clara','4 años',NULL,NULL,1,'Recogida tras el fin de la temporada de caza. Muy asustadiza pero sociable.',NULL,'2026-02-15',1,'2026-05-17','12345678z',13),(8,'Duque','Golden Retriever','macho','Dorado','7 años',NULL,'900123123123124',1,'Su familia no podía atenderlo por enfermedad del dueño.','Medicación','2024-02-01',1,'2026-05-17','12345678z',4),(13,'Firulais','cruce','macho','marron claro',NULL,'Ninguna','',1,'en la calle','ninguna','2026-05-15',0,NULL,NULL,7),(16,'Luna','cruce','hembra','marron','3 años','','724098765432100',0,'','','2026-05-12',1,'2026-05-20','12345678Z',18),(17,'Toby','Pastor Alemán','macho','Negro y marrón','5 años','Manchas despigmentadas en el hocico, cola parcialmente pelada','900182736450012',0,'Dueño falleció. Familia no podía hacerse cargo. Entrega voluntaria.','Requiere paseo diario mínimo de 45 min. No convive con gatos.\n','2025-03-03',1,'2026-05-22','12345678Z',7),(18,'Mia','Podenco','hembra','Blanco','1 año','Delgadez moderada.',NULL,0,'Recogida por protectora en zona rural. Posible abandono tras cacería.','Come bien. Se lleva bien con otros perros. Muy activa y juguetona.','2025-04-28',0,NULL,NULL,12),(19,'Empanadilla','Común Europeo','hembra','Naranja','1 mes','Muy pequeña',NULL,0,'Encontrada en un agujero en un muro, avandonada por la camada.','No sabemos si sobrevivirá','2026-05-20',0,NULL,NULL,1),(20,'Aceituna','Común Europeo','hembra','Negro','2 años','Le falta el ojo derecho',NULL,1,'','Asustadiza, hay que asegurarse de que los otros gatos le dejen comer.','2022-05-04',1,'2026-05-20','34567890V',1),(21,'Simba','Persa','macho','Naranja','6 años','',NULL,1,'Cesión voluntaria por incapacidad económica del propietario para costear tratamientos veterinarios.','Tratamiento actual para ácaros en los oídos. Es pacífico pero asustadizo.','2026-03-18',0,NULL,NULL,2),(22,'Thor','Común Europeo','macho','Negro','2 años','Falta de pelaje en una pequeña zona del cuello debido a un collar antiguo demasiado apretado.','724112233445566',1,'Retirado por las autoridades de un domicilio por malas condiciones de salubridad.','Muy juguetón, obsesionado con los juguetes de plumas. Convive perfectamente con perros.','2026-04-30',0,NULL,NULL,1),(23,'Mimi','Azul Ruso','hembra','Gris azulado',' 1 año','Una pequeña muesca en la oreja izquierda.','982111222333444',0,' Rescatada de una colonia felina','Muy territorial.','2026-05-14',0,NULL,NULL,1),(24,'Balto','Husky Siberiano','hembra','Gris y blanco','4 años','Heterocromía (un ojo azul y otro marrón). Pelaje denso de invierno.','724011223344551',1,'Encontrado vagando por el monte. Tras el periodo legal de espera, el dueño no apareció.','Gran tendencia a escaparse saltando vallas altas. Necesita mucho ejercicio.','2026-01-02',0,NULL,NULL,13),(25,'Garfio','Común Europeo','macho','Romano (Atigrado gris y negro)','5 años','Falta el ojo derecho debido a una infección antigua ya curada.','900111222333445',0,'Rescatado de una situación de acumulación de animales (síndrome de Noé).','Es el \"líder\" pacífico de la gatería. Muy tranquilo, pasa el día durmiendo al sol.','2026-02-14',0,NULL,NULL,1),(26,'Kira','American Staffordshire Terrier','hembra','Marrón leonado con pecho blanco','3 años','Musculosa y fuerte','276012345678901',1,'Intervención policial por no cumplir los requisitos legales de tenencia','Sociable con personas.','2026-03-19',0,NULL,NULL,15),(27,'Chiki','Chihuahua','macho','Crema','10 años','Le falta algún diente debido a la edad. Soplo cardíaco leve en tratamiento.','982012345678902',0,'Renuncia de una persona mayor que ingresó de forma permanente en una residencia.','Perro senior. Sufre mucho el frío y el estrés','2026-04-11',0,NULL,NULL,4),(28,'Bruno','Mastín Español','macho','Lobero','2 años','Doble espolón en las patas traseras (típico de la raza).','900123456000111',0,'Abandonado en una gasolinera de carretera rural. ','Noble, bonachón y algo perezoso. Ideal para fincas o casas con terreno.','2026-05-01',0,NULL,NULL,17);
/*!40000 ALTER TABLE `animal` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-22 12:46:11
