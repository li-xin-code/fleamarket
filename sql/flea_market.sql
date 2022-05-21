-- MySQL dump 10.13  Distrib 5.7.20, for macos10.12 (x86_64)
--
-- Host: localhost    Database: flea_market
-- ------------------------------------------------------
-- Server version	5.7.20

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
-- Table structure for table `goods`
--

DROP TABLE IF EXISTS `goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` char(8) DEFAULT NULL,
  `seller_id` char(8) DEFAULT NULL,
  `name` varchar(12) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `remove` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `goods_goods_id_uindex` (`goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods`
--

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` VALUES (1,'8kDRUcgv','JvinZMJ6','i3双核申舟笔记本','女生自用9成新',998,'2022-05-18 09:14:39',1),(2,'rPyOgdTd','JvinZMJ6','iphone1000','女生自用9成新',69999,'2022-05-18 10:11:00',1),(3,'B63FdLdJ','JvinZMJ6','神光棒','你相信光嘛',1,'2022-05-18 15:54:49',0),(4,'kvZ7Q7ZD','JvinZMJ6','智商','快来买～',900,'2022-05-18 15:56:05',0),(5,'q5mYW2lL','JvinZMJ6','10元人民币','用钱赚钱',20,'2022-05-18 15:57:08',0),(6,'UpuRWVtH','JvinZMJ6','唐代元宝','铜镍合金带点铅',80000,'2022-05-18 15:58:08',0),(7,'xr4Ildwq','JvinZMJ6','杀马特团长御赐头套','虎哥出品',999,'2022-05-18 15:59:28',0),(8,'Cq7ud1dC','JvinZMJ6','小面包','你会记得你吃过多少片面包吗，塞高泥high铁鸭子哒！',666,'2022-05-18 16:01:10',0),(9,'u8Tupcyp','KYsWkzEf','焦虑','量大管够',666,'2022-05-18 16:02:44',0),(10,'6s85LVtI','KYsWkzEf','祖母绿痱啐','生机盎然，题材值三千',250,'2022-05-18 23:54:57',1),(11,'MHWesuP2','KYsWkzEf','祖母绿痱啐','生机盎然，题材值三千',250,'2022-05-19 20:27:13',1),(12,'g0cyT9ND','KYsWkzEf','祖母绿痱啐','生机盎然，题材值三千',250,'2022-05-19 20:27:24',1),(13,'uO2E3jKa','KYsWkzEf','祖母绿痱啐','生机盎然，题材值三千',250,'2022-05-19 20:27:46',1),(14,'kbBFgQ2f','KYsWkzEf','祖母绿痱啐','生机盎然，题材值三千',250,'2022-05-19 20:29:24',1),(15,'SZlfR2cP','KYsWkzEf','玫瑰花（蓝色）','《二手玫瑰》',998,'2022-05-20 09:46:05',1),(16,'lcNKBqGz','KYsWkzEf','祖母绿痱啐','生机盎然，题材值三千',250,'2022-05-20 11:59:08',1),(17,'Aa3JrDEa','KYsWkzEf','祖母绿痱啐','生机盎然，题材值三千',250,'2022-05-20 11:59:09',1),(18,'0HBbbo6x','KYsWkzEf','祖母绿痱啐','生机盎然，题材值三千',250,'2022-05-20 11:59:09',1),(19,'jCRghBUK','KYsWkzEf','祖母绿痱啐','生机盎然，题材值三千',250,'2022-05-20 11:59:09',1),(20,'ZlCe00IZ','KYsWkzEf','祖母绿痱啐','生机盎然，题材值三千',250,'2022-05-20 11:59:09',1),(21,'vtVh8rql','KYsWkzEf','祖母绿痱啐','生机盎然，题材值三千',250,'2022-05-20 11:59:09',1),(22,'PTwgrVY0','KYsWkzEf','祖母绿痱啐','生机盎然，题材值三千',250,'2022-05-20 11:59:10',1),(23,'8mjS7jII','KYsWkzEf','祖母绿痱啐','生机盎然，题材值三千',250,'2022-05-20 11:59:10',1),(24,'D7Fl2e1F','KYsWkzEf','祖母绿痱啐','生机盎然，题材值三千',250,'2022-05-20 11:59:10',1),(25,'lahN7LrR','KYsWkzEf','祖母绿痱啐','生机盎然，题材值三千',250,'2022-05-20 11:59:11',1),(26,'gB2bVSIS','KYsWkzEf','祖母绿痱啐','生机盎然，题材值三千',250,'2022-05-20 11:59:11',1),(27,'zncZuHLl','KYsWkzEf','祖母绿痱啐','生机盎然，题材值三千',250,'2022-05-20 11:59:12',1),(28,'yB5Qi8C2','KYsWkzEf','祖母绿痱啐','生机盎然，题材值三千',250,'2022-05-20 11:59:12',1),(29,'UnQqmT35','KYsWkzEf','祖母绿痱啐','生机盎然，题材值三千',250,'2022-05-20 11:59:12',1),(30,'t7JaNPpf','KYsWkzEf','祖母绿痱啐','生机盎然，题材值三千',250,'2022-05-20 11:59:12',1),(31,'HbasLqMd','KYsWkzEf','祖母绿痱啐','生机盎然，题材值三千',250,'2022-05-20 11:59:13',1),(32,'ZZnVJx00','KYsWkzEf','祖母绿痱啐','生机盎然，题材值三千',250,'2022-05-20 11:59:13',1),(33,'E5dI5nap','KYsWkzEf','祖母绿痱啐','生机盎然，题材值三千',250,'2022-05-20 11:59:13',0),(34,'0LkEPDf6','KYsWkzEf','祖母绿痱啐','生机盎然，题材值三千',250,'2022-05-20 11:59:13',0),(35,'joFlJkcN','KYsWkzEf','祖母绿痱啐','生机盎然，题材值三千',250,'2022-05-20 11:59:13',0),(36,'zRWc3E7j','KYsWkzEf','祖母绿痱啐','生机盎然，题材值三千',250,'2022-05-20 11:59:22',0),(37,'xEaPuZoU','KYsWkzEf','祖母绿痱啐','生机盎然，题材值三千',250,'2022-05-20 11:59:22',0),(38,'lxrpHu8V','KYsWkzEf','祖母绿痱啐','生机盎然，题材值三千',250,'2022-05-20 11:59:23',0),(39,'NVeZ4nTV','KYsWkzEf','祖母绿痱啐','生机盎然，题材值三千',250,'2022-05-20 11:59:23',0),(40,'9jYPTfin','KYsWkzEf','祖母绿痱啐','生机盎然，题材值三千',250,'2022-05-20 11:59:23',0),(41,'wTFhVCLP','KYsWkzEf','祖母绿痱啐','生机盎然，题材值三千',250,'2022-05-20 11:59:23',0),(42,'RGw77I3F','KYsWkzEf','祖母绿痱啐','生机盎然，题材值三千',250,'2022-05-20 11:59:23',0),(43,'Wsyi1D7V','KYsWkzEf','祖母绿痱啐','生机盎然，题材值三千',250,'2022-05-20 11:59:23',0),(44,'VKJakioc','KYsWkzEf','祖母绿痱啐','生机盎然，题材值三千',250,'2022-05-20 11:59:24',0),(45,'PNY4sxFG','KYsWkzEf','祖母绿痱啐','生机盎然，题材值三千',250,'2022-05-20 11:59:24',0),(46,'fGNlazIF','KYsWkzEf','祖母绿痱啐','生机盎然，题材值三千',250,'2022-05-20 11:59:24',0),(47,'3dRwuNQN','KYsWkzEf','祖母绿痱啐','生机盎然，题材值三千',250,'2022-05-20 11:59:24',0),(48,'PtqOQP3j','KYsWkzEf','祖母绿痱啐','生机盎然，题材值三千',250,'2022-05-20 11:59:24',0);
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` char(8) DEFAULT NULL,
  `goods_id` char(8) DEFAULT NULL,
  `buyer_id` char(8) DEFAULT NULL,
  `seller_id` char(8) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `remove` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_order_id_uindex` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (11,'BGoAxWYX','8kDRUcgv','KYsWkzEf','JvinZMJ6','2022-05-18 19:33:28',0),(12,'Faj1H2yd','rPyOgdTd','KYsWkzEf','JvinZMJ6','2022-05-18 19:33:33',0),(14,'KpxgyqKq','6s85LVtI','JvinZMJ6','KYsWkzEf','2022-05-20 12:00:02',0),(15,'jx9iYwJm','MHWesuP2','JvinZMJ6','KYsWkzEf','2022-05-20 12:00:04',0),(16,'Z1ELVbxG','g0cyT9ND','JvinZMJ6','KYsWkzEf','2022-05-20 12:00:06',0),(17,'LcbYR6SU','uO2E3jKa','JvinZMJ6','KYsWkzEf','2022-05-20 12:00:08',0),(18,'MgxBp3ZO','kbBFgQ2f','JvinZMJ6','KYsWkzEf','2022-05-20 12:00:10',0),(19,'MGaShAse','SZlfR2cP','JvinZMJ6','KYsWkzEf','2022-05-20 12:00:12',0),(20,'96cmKAL7','lcNKBqGz','JvinZMJ6','KYsWkzEf','2022-05-20 12:00:14',0),(21,'NHdmsJLN','Aa3JrDEa','JvinZMJ6','KYsWkzEf','2022-05-20 12:00:16',0),(22,'DVYCwYqP','0HBbbo6x','JvinZMJ6','KYsWkzEf','2022-05-20 12:00:18',0),(23,'j73Ry2AA','jCRghBUK','JvinZMJ6','KYsWkzEf','2022-05-20 12:00:19',0),(24,'YJA2TmLg','ZlCe00IZ','JvinZMJ6','KYsWkzEf','2022-05-20 12:00:21',0),(25,'vg7yODwV','vtVh8rql','JvinZMJ6','KYsWkzEf','2022-05-20 12:00:23',0),(26,'J3wa8gar','PTwgrVY0','JvinZMJ6','KYsWkzEf','2022-05-20 12:00:25',0),(27,'URYtgGaR','8mjS7jII','JvinZMJ6','KYsWkzEf','2022-05-20 12:00:27',0),(28,'0emAcHs5','D7Fl2e1F','JvinZMJ6','KYsWkzEf','2022-05-20 12:00:29',0),(29,'i6ne1FlO','lahN7LrR','JvinZMJ6','KYsWkzEf','2022-05-20 12:00:31',0),(30,'OEjwTPCB','gB2bVSIS','JvinZMJ6','KYsWkzEf','2022-05-20 12:00:33',0),(31,'gCWoDD7M','zncZuHLl','JvinZMJ6','KYsWkzEf','2022-05-20 12:00:35',0),(32,'Yhad1rT5','yB5Qi8C2','JvinZMJ6','KYsWkzEf','2022-05-20 12:00:37',0),(33,'nknRuKFk','UnQqmT35','JvinZMJ6','KYsWkzEf','2022-05-20 12:00:39',0),(34,'YF6SvC1H','t7JaNPpf','JvinZMJ6','KYsWkzEf','2022-05-20 12:00:41',0),(35,'1Enq4pZy','HbasLqMd','JvinZMJ6','KYsWkzEf','2022-05-20 12:00:44',0),(36,'fhM7vJEn','ZZnVJx00','JvinZMJ6','KYsWkzEf','2022-05-20 12:00:46',0);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` char(8) DEFAULT NULL,
  `username` varchar(12) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_user_id_uindex` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'KYsWkzEf','张三','f379eaf3c831b04de153469d1bec345e'),(5,'JvinZMJ6','李四','f379eaf3c831b04de153469d1bec345e'),(7,'nEsKsPHr','王五','f379eaf3c831b04de153469d1bec345e');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-20 12:04:20
