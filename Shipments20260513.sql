CREATE DATABASE  IF NOT EXISTS `shipments` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `shipments`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: shipments
-- ------------------------------------------------------
-- Server version	5.0.26-community-nt

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Not dumping tablespaces as no INFORMATION_SCHEMA.FILES table on this server
--

--
-- Table structure for table `shipment_current_state`
--

DROP TABLE IF EXISTS `shipment_current_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shipment_current_state` (
  `shipment_id` varchar(100) NOT NULL default '',
  `current_status` varchar(50) NOT NULL,
  `status_occurred_at` datetime default NULL,
  `location` varchar(255) default NULL,
  `last_event_id` varchar(20) default NULL,
  `event_count` int(11) default '0',
  `state_reason` varchar(500) default NULL,
  `updated_at` datetime default NULL,
  PRIMARY KEY  (`shipment_id`),
  KEY `last_event_id_idx` (`last_event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipment_current_state`
--

LOCK TABLES `shipment_current_state` WRITE;
/*!40000 ALTER TABLE `shipment_current_state` DISABLE KEYS */;
INSERT INTO `shipment_current_state` VALUES ('ship-456','PICKED_UP','2026-03-11 12:10:00','Amsterdam North','evt-125',NULL,NULL,'2026-03-11 12:12:05'),('ship-786','CONFLICT_ACCEPTED','2026-05-11 12:10:00','Clock tower','evt-788',NULL,'\"Matching shipment/status/occurredAt/location in in conflict\"','2026-05-11 12:12:05');
/*!40000 ALTER TABLE `shipment_current_state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipment_events`
--

DROP TABLE IF EXISTS `shipment_events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shipment_events` (
  `id` bigint(20) NOT NULL auto_increment,
  `event_id` varchar(100) default NULL,
  `partner` varchar(50) default NULL,
  `shipment_id` varchar(100) NOT NULL,
  `status` varchar(50) default NULL,
  `occurred_at` datetime default NULL,
  `received_at` datetime default NULL,
  `location` varchar(255) default NULL,
  `payload_json` varchar(500) default NULL,
  `is_duplicate` tinyint(1) NOT NULL default '0',
  `duplicate_reason` varchar(255) default NULL,
  `processing_result` varchar(50) default NULL,
  `created_at` datetime default NULL,
  PRIMARY KEY  (`id`),
  KEY `idx_shipment_id` (`shipment_id`),
  KEY `idx_occurred_at` (`occurred_at`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipment_events`
--

LOCK TABLES `shipment_events` WRITE;
/*!40000 ALTER TABLE `shipment_events` DISABLE KEYS */;
INSERT INTO `shipment_events` VALUES (1,'evt-123','dhl','ship-456','IN_TRANSIT','2026-03-10 12:00:00','2026-03-10 12:00:05','Amstadam','{ \"eventId\": \"evt-123\", \"partner\": \"dhl\", \"shipmentId\": \"ship-456\", \"status\": \"IN_TRANSIT\", \"occurredAt\": \"2026-03-10T12:00:00Z\", \"receivedAt\": \"2026-03-10T12:00:05Z\", \"location\": \"Amsterdam\" } ',0,NULL,'ACCEPTED','2026-03-10 12:00:00'),(14,'evt-123','dhl','ship-456','OUT_FOR_DELIVERY','2026-03-10 14:00:10','2026-03-10 14:00:05','Amsterdam North','com.shipping.api.dto.ShipmentEventRequest@1a4c483',0,NULL,'ACCEPTED','2026-03-10 14:00:10'),(19,'evt-123','dhl','ship-456','OUT_FOR_DELIVERY','2026-03-10 12:10:00','2026-03-10 12:12:05','Amsterdam North','com.shipping.api.dto.ShipmentEventRequest@a548ab',0,NULL,'ACCEPTED','2026-03-10 12:10:00'),(20,'evt-124','dhl','ship-456','FAILED_DELIVERY','2026-03-10 12:10:00','2026-03-10 12:12:05','Amsterdam North','com.shipping.api.dto.ShipmentEventRequest@26d110',0,NULL,'ACCEPTED','2026-03-10 12:10:00'),(21,'evt-125','dhl','ship-456','PICKED_UP','2026-03-11 12:10:00','2026-03-11 12:12:05','Amsterdam North','com.shipping.api.dto.ShipmentEventRequest@cf80cb',0,NULL,'ACCEPTED','2026-03-11 12:10:00'),(23,'evt-786','courier guy','ship-786','PICKED_UP','2026-05-11 12:10:00','2026-05-11 12:12:05','Cape town CBD','com.shipping.api.dto.ShipmentEventRequest@f24a83',0,NULL,'ACCEPTED','2026-05-11 12:10:00'),(24,'evt-787','courier guy','ship-786','IN_TRANSIT','2026-05-11 12:10:00','2026-05-11 12:12:05','Clock tower','com.shipping.api.dto.ShipmentEventRequest@118266',0,NULL,'ACCEPTED','2026-05-11 12:10:00'),(25,'evt-787','courier guy','ship-786','IN_TRANSIT','2026-05-11 12:10:00','2026-05-11 12:12:05','Clock tower','com.shipping.api.dto.ShipmentEventRequest@1106ff9',0,NULL,'ACCEPTED','2026-05-11 12:10:00'),(26,'evt-787','courier guy','ship-786','IN_TRANSIT','2026-05-11 12:10:00','2026-05-11 12:12:05','Clock tower','com.shipping.api.dto.ShipmentEventRequest@1747713',0,NULL,'ACCEPTED','2026-05-11 12:10:00'),(28,'evt-787','courier guy','ship-786','IN_TRANSIT','2026-05-11 12:10:00','2026-05-11 12:12:05','Clock tower','com.shipping.api.dto.ShipmentEventRequest@99ff76',0,NULL,'ACCEPTED','2026-05-11 12:10:00'),(29,'evt-787','courier guy','ship-786','IN_TRANSIT','2026-05-11 12:10:00','2026-05-11 12:12:05','Clock tower','com.shipping.api.dto.ShipmentEventRequest@16fec4b',0,NULL,'ACCEPTED','2026-05-11 12:10:00'),(30,'evt-787','courier guy','ship-786','IN_TRANSIT','2026-05-11 12:10:00','2026-05-11 12:12:05','Clock tower','com.shipping.api.dto.ShipmentEventRequest@5a5822',0,NULL,'ACCEPTED','2026-05-11 12:10:00'),(31,'evt-787','courier guy','ship-786','IN_TRANSIT','2026-05-11 12:10:00','2026-05-11 12:12:05','Clock tower','com.shipping.api.dto.ShipmentEventRequest@a3d496',0,NULL,'ACCEPTED','2026-05-11 12:10:00'),(32,'evt-788','courier guy','ship-786','DELIVERED','2026-05-11 12:10:00','2026-05-11 12:12:05','Clock tower','com.shipping.api.dto.ShipmentEventRequest@4f21f1',0,NULL,'ACCEPTED','2026-05-11 12:10:00'),(33,'evt-788','courier guy','ship-786','DELIVERED','2026-05-11 12:10:00','2026-05-11 12:12:05','Clock tower','com.shipping.api.dto.ShipmentEventRequest@e00d94',0,NULL,'ACCEPTED','2026-05-11 12:10:00'),(34,'evt-788','courier guy','ship-786','DELIVERED','2026-05-11 12:10:00','2026-05-11 12:12:05','Clock tower','com.shipping.api.dto.ShipmentEventRequest@1753207',0,NULL,'ACCEPTED','2026-05-11 12:10:00'),(36,'evt-788','courier guy','ship-786','DELIVERED','2026-05-11 12:10:00','2026-05-11 12:12:05','Clock tower','com.shipping.api.dto.ShipmentEventRequest@1948848',0,NULL,'ACCEPTED','2026-05-11 12:10:00'),(37,'evt-788','courier guy','ship-786','DELIVERED','2026-05-11 12:10:00','2026-05-11 12:12:05','Clock tower','com.shipping.api.dto.ShipmentEventRequest@16e45cd',0,NULL,'ACCEPTED','2026-05-11 12:10:00'),(38,'evt-788','courier guy','ship-786','DELIVERED','2026-05-11 12:10:00','2026-05-11 12:12:05','Clock tower','com.shipping.api.dto.ShipmentEventRequest@1c5980f',0,NULL,'ACCEPTED','2026-05-11 12:10:00'),(39,'evt-788','courier guy','ship-786','DELIVERED','2026-05-11 12:10:00','2026-05-11 12:12:05','Clock tower','com.shipping.api.dto.ShipmentEventRequest@4597f6',0,NULL,'ACCEPTED','2026-05-11 12:10:00'),(40,'evt-788','courier guy','ship-786','DELIVERED','2026-05-11 12:10:00','2026-05-11 12:12:05','Clock tower','com.shipping.api.dto.ShipmentEventRequest@151be00',0,NULL,'ACCEPTED','2026-05-11 12:10:00'),(41,'evt-788','courier guy','ship-786','DELIVERED','2026-05-11 12:10:00','2026-05-11 12:12:05','Clock tower','com.shipping.api.dto.ShipmentEventRequest@b0042f',0,NULL,'ACCEPTED','2026-05-11 12:10:00'),(42,'evt-788','courier guy','ship-786','DELIVERED','2026-05-11 12:10:00','2026-05-11 12:12:05','Clock tower','{\"eventId\":\"evt-788\",\"partner\":\"courier guy\",\"shipmentId\":\"ship-786\",\"status\":\"DELIVERED\",\"occurredAt\":\"2026-05-11 10:10:00\",\"receivedAt\":\"2026-05-11 10:12:05\",\"location\":\"Clock tower\"}',0,NULL,'ACCEPTED','2026-05-11 12:10:00'),(43,'evt-788','courier guy','ship-786','DELIVERED','2026-05-11 12:10:00','2026-05-11 12:12:05','Clock tower','{\"eventId\":\"evt-788\",\"partner\":\"courier guy\",\"shipmentId\":\"ship-786\",\"status\":\"DELIVERED\",\"occurredAt\":\"2026-05-11 10:10:00\",\"receivedAt\":\"2026-05-11 10:12:05\",\"location\":\"Clock tower\"}',0,NULL,'ACCEPTED','2026-05-11 12:10:00'),(44,'evt-788','courier guy','ship-786','DELIVERED','2026-05-11 12:10:00','2026-05-11 12:12:05','Clock tower','{\"eventId\":\"evt-788\",\"partner\":\"courier guy\",\"shipmentId\":\"ship-786\",\"status\":\"DELIVERED\",\"occurredAt\":\"2026-05-11 10:10:00\",\"receivedAt\":\"2026-05-11 10:12:05\",\"location\":\"Clock tower\"}',0,NULL,'ACCEPTED','2026-05-11 12:10:00'),(45,'evt-788','courier guy','ship-786','DELIVERED','2026-05-11 12:10:00','2026-05-11 12:12:05','Clock tower','{\"eventId\":\"evt-788\",\"partner\":\"courier guy\",\"shipmentId\":\"ship-786\",\"status\":\"DELIVERED\",\"occurredAt\":\"2026-05-11 10:10:00\",\"receivedAt\":\"2026-05-11 10:12:05\",\"location\":\"Clock tower\"}',0,NULL,'DUPLICATE_REJECTED','2026-05-11 12:10:00'),(46,'evt-788','courier guy','ship-786','DELIVERED','2026-05-11 12:10:00','2026-05-11 12:12:05','Clock tower','{\"eventId\":\"evt-788\",\"partner\":\"courier guy\",\"shipmentId\":\"ship-786\",\"status\":\"DELIVERED\",\"occurredAt\":\"2026-05-11 10:10:00\",\"receivedAt\":\"2026-05-11 10:12:05\",\"location\":\"Clock tower\"}',0,NULL,'CONFLICT_ACCEPTED','2026-05-11 12:10:00');
/*!40000 ALTER TABLE `shipment_events` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-13 15:02:18
