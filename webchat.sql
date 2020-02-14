-- MySQL dump 10.13  Distrib 5.7.20, for Win64 (x86_64)
--
-- Host: localhost    Database: webchat
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `friend`
--

DROP TABLE IF EXISTS `friend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friend` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `friend_id` int(11) DEFAULT NULL COMMENT '好友id',
  `build_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '建立时间',
  `type_id` int(11) DEFAULT NULL COMMENT '好友分组id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8 COMMENT='好友表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend`
--

LOCK TABLES `friend` WRITE;
/*!40000 ALTER TABLE `friend` DISABLE KEYS */;
INSERT INTO `friend` VALUES (1,1,2,'2017-01-16 13:13:47',1),(2,1,3,'2017-01-16 13:14:01',1),(3,1,4,'2017-01-16 13:14:10',1),(4,1,5,'2017-01-16 13:14:17',1),(5,1,6,'2017-01-16 13:14:23',1),(6,1,7,'2017-01-16 13:14:35',1),(7,2,1,'2017-01-16 13:15:37',2),(8,2,3,'2017-01-16 13:15:51',2),(9,2,4,'2017-01-16 13:16:15',2),(10,2,5,'2017-01-16 13:16:25',2),(11,3,1,'2017-01-16 13:16:35',3),(12,3,2,'2017-01-16 13:16:40',3),(13,3,4,'2017-01-16 13:16:46',3),(14,3,5,'2017-01-16 13:16:53',3),(15,4,1,'2017-01-16 13:17:02',4),(16,4,2,'2017-01-16 13:17:06',4),(17,4,3,'2017-01-16 13:17:17',4),(18,4,5,'2017-01-16 13:17:23',4),(19,5,1,'2017-01-16 13:17:28',5),(20,5,2,'2017-01-16 13:17:34',5),(21,5,3,'2017-01-16 13:17:41',5),(22,5,4,'2017-01-16 13:17:59',5),(23,8,7,'2018-12-19 06:13:33',8),(24,7,8,'2018-12-19 06:13:33',7),(35,6,7,'2018-12-25 01:11:34',6),(36,7,6,'2018-12-25 01:11:34',10),(37,7,12,'2018-12-25 05:53:55',7),(38,7,13,'2018-12-25 05:53:55',7),(39,7,14,'2018-12-25 05:53:55',7),(40,7,15,'2018-12-25 05:53:55',7),(41,7,16,'2018-12-25 05:53:56',7),(42,7,17,'2018-12-25 05:53:56',7),(43,7,18,'2018-12-25 05:53:56',7),(44,7,19,'2018-12-25 05:53:56',7),(45,7,20,'2018-12-25 05:53:56',7),(46,7,21,'2018-12-25 05:53:56',7),(47,7,22,'2018-12-25 05:53:56',10),(48,7,23,'2018-12-25 05:53:56',10),(49,7,24,'2018-12-25 05:53:56',10),(50,7,25,'2018-12-25 05:53:56',10),(51,7,26,'2018-12-25 05:53:56',10),(52,7,27,'2018-12-25 05:53:56',10),(53,7,28,'2018-12-25 05:53:56',10),(54,7,29,'2018-12-25 05:53:56',10),(55,7,30,'2018-12-25 05:53:56',10),(56,7,31,'2018-12-25 05:53:56',10),(57,12,7,'2018-12-25 05:55:03',13),(58,13,7,'2018-12-25 05:55:04',14),(59,14,7,'2018-12-25 05:55:04',15),(60,15,7,'2018-12-25 05:55:04',16),(61,16,7,'2018-12-25 05:55:04',17),(62,17,7,'2018-12-25 05:55:04',18),(63,18,7,'2018-12-25 05:55:04',19),(64,19,7,'2018-12-25 05:55:04',20),(65,20,7,'2018-12-25 05:55:04',21),(66,21,7,'2018-12-25 05:55:04',22),(67,22,7,'2018-12-25 05:55:04',23),(68,23,7,'2018-12-25 05:55:04',24),(69,24,7,'2018-12-25 05:55:04',25),(70,25,7,'2018-12-25 05:55:04',26),(71,26,7,'2018-12-25 05:55:04',27),(72,27,7,'2018-12-25 05:55:04',28),(73,28,7,'2018-12-25 05:55:04',29),(74,29,7,'2018-12-25 05:55:04',30),(75,30,7,'2018-12-25 05:55:04',31),(76,31,7,'2018-12-25 05:55:04',32);
/*!40000 ALTER TABLE `friend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friend_impression`
--

DROP TABLE IF EXISTS `friend_impression`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friend_impression` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `from_user_id` int(11) DEFAULT NULL,
  `to_user_id` int(11) DEFAULT NULL,
  `content` varchar(60) DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend_impression`
--

LOCK TABLES `friend_impression` WRITE;
/*!40000 ALTER TABLE `friend_impression` DISABLE KEYS */;
INSERT INTO `friend_impression` VALUES (1,3,2,'能侃','2018-12-22 05:54:00'),(2,4,2,'阿里巴巴','2018-12-22 05:54:00'),(3,5,2,'无形之中装逼','2018-12-22 05:53:00'),(4,6,2,'无形之中装逼','2018-12-22 05:53:00'),(5,7,2,'首富','2018-12-22 05:55:00'),(6,8,2,'首富','2018-12-22 05:55:00'),(7,1,2,'首富','2018-12-22 05:55:00'),(8,2,2,'悔创阿里','2018-12-22 05:56:00'),(9,1,2,'首富','2018-12-22 05:56:00'),(10,2,2,'悔创阿里','2018-12-22 05:57:00'),(11,3,2,'能侃','2018-12-22 05:57:00'),(12,4,2,'无形之中装逼','2018-12-22 05:58:00'),(13,3,2,'能侃','2018-12-22 05:58:00'),(14,4,2,'阿里巴巴','2018-12-23 05:58:00'),(15,5,2,'无形之中装逼','2018-12-24 05:58:00'),(16,6,2,'无形之中装逼','2018-12-25 05:58:00'),(17,7,2,'首富','2018-12-26 05:58:00'),(18,8,2,'首富','2018-12-27 05:58:00'),(19,1,2,'首富','2018-12-28 05:58:00'),(20,2,2,'悔创阿里','2018-12-29 05:58:00'),(21,1,2,'首富','2018-12-30 05:58:00'),(22,2,2,'悔创阿里','2018-12-31 05:58:00'),(23,3,2,'能侃','2019-01-01 05:58:00'),(24,4,2,'无形之中装逼','2019-01-02 05:58:00'),(25,3,5,'日本艺人','2019-01-03 05:58:00'),(26,4,5,'日本艺人','2019-01-04 05:58:00'),(27,5,5,'日本艺人','2019-01-05 05:58:00'),(28,6,5,'盛世美颜','2019-01-06 05:58:00'),(29,7,5,'盛世美颜','2019-01-07 05:58:00'),(30,8,5,'盛世美颜','2019-01-08 05:58:00'),(31,1,5,'老婆','2019-01-09 05:58:00'),(32,2,5,'smile','2019-01-10 05:58:00'),(33,1,5,'smile','2019-01-11 05:58:00'),(34,2,5,'smile ','2019-01-12 05:58:00'),(35,3,5,'smile ','2019-01-13 05:58:00'),(36,4,5,'smile ','2019-01-14 05:58:00');
/*!40000 ALTER TABLE `friend_impression` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friend_message`
--

DROP TABLE IF EXISTS `friend_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friend_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `from_user_id` int(11) DEFAULT NULL COMMENT '发消息的人的id',
  `to_user_id` int(11) DEFAULT NULL COMMENT '收消息的人的id',
  `content` text COMMENT '消息内容',
  `send_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发送时间',
  `is_read` int(11) DEFAULT NULL COMMENT '是否已读，1是0否',
  `is_del` int(11) DEFAULT NULL COMMENT '是否删除，1是0否',
  `is_back` int(11) DEFAULT NULL COMMENT '是否撤回，1是0否',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COMMENT='好友消息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend_message`
--

LOCK TABLES `friend_message` WRITE;
/*!40000 ALTER TABLE `friend_message` DISABLE KEYS */;
INSERT INTO `friend_message` VALUES (22,2,3,'你好啊化腾！！','2018-12-18 13:31:09',NULL,NULL,NULL),(23,3,2,'你好马云先生','2018-12-18 13:31:21',NULL,NULL,NULL),(24,2,1,'face[微笑]','2018-12-19 05:18:09',NULL,NULL,NULL),(25,2,8,'face[黑线]','2018-12-19 05:18:24',NULL,NULL,NULL),(26,2,3,'img[http://127.0.0.1:8080/sns/downLoadFile?downLoadPath=G:\\IdeaProjects\\FixedJayIM\\target\\JayIM-0.0.1-SNAPSHOT\\upload\\sns\\2\\/0acb6ca2-f598-4777-839a-18e067aefd7d.JPG&fileName=friends.JPG]','2018-12-20 10:27:56',NULL,NULL,NULL),(27,2,4,'file(http://127.0.0.1:8080/sns/downLoadFile?downLoadPath=G:\\IdeaProjects\\FixedJayIM\\target\\JayIM-0.0.1-SNAPSHOT\\upload\\sns\\2\\8a4dcb96-067a-47a9-ac39-ed6675085b45.JPG&fileName=friends.JPG)[friends.JPG]','2018-12-20 10:45:32',NULL,NULL,NULL),(28,2,4,'file(http://127.0.0.1:8080/sns/downLoadFile?downLoadPath=G:\\IdeaProjects\\FixedJayIM\\target\\JayIM-0.0.1-SNAPSHOT\\upload\\sns\\2\\ebef0c0d-8b76-41a4-9aed-a1830c67908c.html&fileName=find.html)[find.html]','2018-12-20 10:45:48',NULL,NULL,NULL),(29,3,2,'img[http://127.0.0.1:8080/sns/downLoadFile?downLoadPath=G:\\IdeaProjects\\FixedJayIM\\src\\main\\webapp\\upload\\sns\\34e4a36e4-471c-4d92-8e35-0a36c7c33ba5.jpg&fileName=0Bookcover.jpg]','2018-12-21 10:57:42',NULL,NULL,NULL),(30,3,2,'file(http://127.0.0.1:8080/sns/downLoadFile?downLoadPath=G:\\IdeaProjects\\FixedJayIM\\src\\main\\webapp\\upload\\sns\\320e15096-eec4-4b6b-b4db-02923b1172a8.jpg&fileName=book_cover2.jpg)[book_cover2.jpg]','2018-12-21 11:00:39',NULL,NULL,NULL),(31,3,2,'img[http://127.0.0.1:8080/sns/downLoadFile?downLoadPath=G:\\IdeaProjects\\FixedJayIM\\src\\main\\webapp\\upload\\sns\\31c6953f4-12c9-4c90-9894-3730412c73f5.png&fileName=BookCover_small.png]','2018-12-21 11:07:44',NULL,NULL,NULL),(32,3,2,'file(http://127.0.0.1:8080/sns/downLoadFile?downLoadPath=G:\\IdeaProjects\\FixedJayIM\\src\\main\\webapp\\upload\\sns\\3c18cc1aa-a281-4ddf-95b2-b2f35ec134a7.png&fileName=BookCover_small.png)[BookCover_small.png]','2018-12-21 11:07:55',NULL,NULL,NULL),(33,3,2,'img[http://127.0.0.1:8080/sns/downLoadFile?downLoadPath=G:\\IdeaProjects\\FixedJayIM\\src\\main\\webapp\\upload\\sns\\337653121-d91f-40ef-afc7-f950dc476ffa.jpg&fileName=Cam01.jpg]','2018-12-21 11:10:13',NULL,NULL,NULL),(34,3,2,'img[http://127.0.0.1:8080/sns/downLoadFile?downLoadPath=G:\\IdeaProjects\\FixedJayIM\\src\\main\\webapp\\upload\\sns\\3/b2f00e64-e7d2-4ee0-851b-0c3c46ca5166.png&fileName=BookCover_small.png]','2018-12-21 11:48:54',NULL,NULL,NULL),(35,2,3,'你好你好','2018-12-25 01:03:50',NULL,NULL,NULL),(36,7,8,'fghff','2018-12-25 01:07:05',NULL,NULL,NULL),(37,7,8,'img[http://127.0.0.1:8080/sns/downLoadFile?downLoadPath=C:\\development\\WorkSpaceII\\JayIM\\src\\main\\webapp\\upload\\sns\\7/05312691-d545-4c39-bfd1-023f195bcf9e.jpg&fileName=my.jpg]','2018-12-25 01:07:10',NULL,NULL,NULL),(38,28,7,'你好~','2018-12-25 06:01:17',NULL,NULL,NULL);
/*!40000 ALTER TABLE `friend_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friend_request`
--

DROP TABLE IF EXISTS `friend_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friend_request` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `from_id` int(11) DEFAULT NULL,
  `from_group` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `href` varchar(255) DEFAULT NULL,
  `is_read` int(11) DEFAULT NULL,
  `time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend_request`
--

LOCK TABLES `friend_request` WRITE;
/*!40000 ALTER TABLE `friend_request` DISABLE KEYS */;
INSERT INTO `friend_request` VALUES (1,'申请添加你为好友',2,8,8,1,'测试下加好友',NULL,1,'2018-12-20 10:37:30'),(11,'您已同意添加papi酱为好友',6,7,0,10,'你好',NULL,2,'2018-12-25 01:11:26');
/*!40000 ALTER TABLE `friend_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friend_type`
--

DROP TABLE IF EXISTS `friend_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friend_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键\r\n            ',
  `type_name` varchar(255) DEFAULT NULL COMMENT '分组名',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `build_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_default` int(11) DEFAULT '0' COMMENT '是否为默认分组：1为默认，0为不是默认分组',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='好友分组';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend_type`
--

LOCK TABLES `friend_type` WRITE;
/*!40000 ALTER TABLE `friend_type` DISABLE KEYS */;
INSERT INTO `friend_type` VALUES (1,'好友',1,'2017-01-16 13:11:45',1),(2,'好友',2,'2017-01-16 13:12:12',1),(3,'好友',3,'2017-01-16 13:12:25',1),(4,'好友',4,'2017-01-16 13:12:35',1),(5,'好友',5,'2017-01-16 13:12:37',1),(6,'好友',6,'2017-01-16 13:13:19',1),(7,'好友',7,'2017-01-16 13:13:35',1),(8,'我的好友',8,'2018-12-12 12:00:00',1),(10,'同学',7,'2018-12-19 12:44:51',0),(11,'好友',10,'2018-12-25 05:22:03',1),(12,'好友',11,'2018-12-25 05:22:09',1),(13,'好友',12,'2018-12-25 05:31:23',1),(14,'好友',13,'2018-12-25 05:31:23',1),(15,'好友',14,'2018-12-25 05:31:23',1),(16,'好友',15,'2018-12-25 05:31:23',1),(17,'好友',16,'2018-12-25 05:31:23',1),(18,'好友',17,'2018-12-25 05:31:23',1),(19,'好友',18,'2018-12-25 05:31:24',1),(20,'好友',19,'2018-12-25 05:31:24',1),(21,'好友',20,'2018-12-25 05:31:24',1),(22,'好友',21,'2018-12-25 05:31:24',1),(23,'好友',22,'2018-12-25 05:31:24',1),(24,'好友',23,'2018-12-25 05:31:24',1),(25,'好友',24,'2018-12-25 05:31:24',1),(26,'好友',25,'2018-12-25 05:31:24',1),(27,'好友',26,'2018-12-25 05:31:24',1),(28,'好友',27,'2018-12-25 05:31:24',1),(29,'好友',28,'2018-12-25 05:31:24',1),(30,'好友',29,'2018-12-25 05:31:24',1),(31,'好友',30,'2018-12-25 05:31:24',1),(32,'好友',31,'2018-12-25 05:31:24',1);
/*!40000 ALTER TABLE `friend_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_message`
--

DROP TABLE IF EXISTS `group_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_message` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `group_id` int(11) DEFAULT NULL COMMENT '群id',
  `content` varchar(0) DEFAULT NULL COMMENT '群消息内容',
  `is_del` int(11) DEFAULT NULL COMMENT '是否删除，1是 0否',
  `is_read` int(11) DEFAULT NULL COMMENT '是否已读，1是，0否。',
  `send_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_back` int(11) DEFAULT NULL COMMENT '是否撤回，1是 0否',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='群消息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_message`
--

LOCK TABLES `group_message` WRITE;
/*!40000 ALTER TABLE `group_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_user`
--

DROP TABLE IF EXISTS `group_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `group_id` int(11) DEFAULT NULL COMMENT '群id\r\n            ',
  `join_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '入群时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='群成员表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_user`
--

LOCK TABLES `group_user` WRITE;
/*!40000 ALTER TABLE `group_user` DISABLE KEYS */;
INSERT INTO `group_user` VALUES (1,1,1,'2017-01-17 13:46:31'),(2,2,1,'2017-01-17 13:47:15'),(3,3,1,'2017-01-17 13:47:28'),(4,4,1,'2017-01-17 13:47:37'),(5,5,1,'2017-01-17 13:47:46'),(6,6,1,'2017-01-17 13:47:51'),(7,7,1,'2017-01-17 13:47:56');
/*!40000 ALTER TABLE `group_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message_box`
--

DROP TABLE IF EXISTS `message_box`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message_box` (
  `id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `from_user_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_box`
--

LOCK TABLES `message_box` WRITE;
/*!40000 ALTER TABLE `message_box` DISABLE KEYS */;
/*!40000 ALTER TABLE `message_box` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_group`
--

DROP TABLE IF EXISTS `t_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_group` (
  `id` int(11) NOT NULL COMMENT '自增主键\r\n            ',
  `group_num` varchar(255) DEFAULT NULL COMMENT '群号',
  `group_name` varchar(255) DEFAULT NULL COMMENT '群名称',
  `avator` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `build_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `description` varchar(0) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='群';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_group`
--

LOCK TABLES `t_group` WRITE;
/*!40000 ALTER TABLE `t_group` DISABLE KEYS */;
INSERT INTO `t_group` VALUES (1,'1','oldriver技术交流','/images/boy-01.png',1,'2017-01-16 14:00:07',NULL,NULL);
/*!40000 ALTER TABLE `t_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_name` varchar(255) DEFAULT NULL COMMENT '帐号',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '昵称',
  `gender` int(11) DEFAULT '0' COMMENT '性别：0为男，1为女',
  `avator` varchar(255) DEFAULT NULL COMMENT '头像',
  `email` varchar(255) DEFAULT NULL COMMENT '电子邮箱',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `role_code` int(11) DEFAULT '1' COMMENT '角色code：1为用户，2为管理员',
  `version` varchar(0) DEFAULT NULL,
  `sign` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'jk','jk','神秘人',0,'/images/me.jpg','jk@gmail.com','4008001688',2,NULL,'666'),(2,'u2','123','马云',0,'/images/boy-01.png','u2@gmail.com','75583765566',1,NULL,'我对钱没兴趣'),(3,'u3','123','马化腾',0,'/images/boy-02.png','u3@gmail.com','4001005678',1,NULL,'再充五万，你就能变强！'),(4,'u4','123','雷军',0,'/images/boy-03.png','u4@gmail.com','12414334',1,NULL,'志存高远脚踏实地'),(5,'u5','123','新垣结衣',1,'/images/girl-01.png','u5@gmail.com','67456332',1,NULL,'大家好'),(6,'u6','123','卷福',0,'/images/girl-02.png','u6@gmail.com','9864543',1,NULL,'我最聪明'),(7,'u7','123','papi酱',1,'/images/girl-03.png','u7@gmail.com','23148912',1,NULL,'美貌与才华于一身'),(8,'utest','123','罗伯特唐尼',0,'/images/animal-03.png','utest@gmail.com','12983823',1,NULL,'I am IronMan'),(10,'u8','123','小明明明',0,'/images/default.png','xiaoming@126.com','18012342345',NULL,NULL,NULL),(11,'u9','123','李雷',NULL,'/images/default.png','lilei@163.com','18122334455',NULL,NULL,NULL),(12,'u12','123','好友12',0,'/images/boy-06.png','u12@163.com','18019348653',1,NULL,'好友12的签名'),(13,'u13','123','好友13',1,'/images/girl-20.png','u13@163.com','18044683755',1,NULL,'好友13的签名'),(14,'u14','123','好友14',1,'/images/girl-01.png','u14@163.com','18073181911',1,NULL,'好友14的签名'),(15,'u15','123','好友15',1,'/images/girl-17.png','u15@163.com','18033395637',1,NULL,'好友15的签名'),(16,'u16','123','好友16',1,'/images/girl-13.png','u16@163.com','18051314685',1,NULL,'好友16的签名'),(17,'u17','123','好友17',1,'/images/girl-05.png','u17@163.com','18077083357',1,NULL,'好友17的签名'),(18,'u18','123','好友18',0,'/images/boy-08.png','u18@163.com','18079252489',1,NULL,'好友18的签名'),(19,'u19','123','好友19',0,'/images/boy-11.png','u19@163.com','18066336859',1,NULL,'好友19的签名'),(20,'u20','123','好友20',1,'/images/girl-09.png','u20@163.com','18020747498',1,NULL,'好友20的签名'),(21,'u21','123','好友21',0,'/images/boy-17.png','u21@163.com','18028369641',1,NULL,'好友21的签名'),(22,'u22','123','好友22',1,'/images/girl-12.png','u22@163.com','18023276782',1,NULL,'好友22的签名'),(23,'u23','123','好友23',0,'/images/boy-06.png','u23@163.com','18086704175',1,NULL,'好友23的签名'),(24,'u24','123','好友24',0,'/images/boy-11.png','u24@163.com','18028887964',1,NULL,'好友24的签名'),(25,'u25','123','好友25',1,'/images/girl-11.png','u25@163.com','18097843019',1,NULL,'好友25的签名'),(26,'u26','123','好友26',0,'/images/boy-10.png','u26@163.com','18036439474',1,NULL,'好友26的签名'),(27,'u27','123','好友27',1,'/images/girl-18.png','u27@163.com','18011745374',1,NULL,'好友27的签名'),(28,'u28','123','好友28',1,'/images/girl-16.png','u28@163.com','18011486486',1,NULL,'好友28的签名'),(29,'u29','123','好友29',1,'/images/girl-01.png','u29@163.com','18092778321',1,NULL,'好友29的签名'),(30,'u30','123','好友30',0,'/images/boy-01.png','u30@163.com','18090266358',1,NULL,'好友30的签名'),(31,'u31','123','好友31',1,'/images/girl-15.png','u31@163.com','18048937354',1,NULL,'好友31的签名');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `province` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `school` varchar(255) DEFAULT NULL,
  `uclass` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (1,1,'山东','青岛','山东大学','心理学111班',18),(2,2,'浙江','杭州','杭州电子科技大学','英语123班',20),(3,3,'广东','深圳','深圳大学','计算机133',21),(4,4,'湖北','武汉','武汉大学','计算机145班',22),(5,5,'北京','北京','北京大学','表演系11班',22),(6,6,'上海','上海','上海交通大学','表演系11班',23),(7,7,'北京','北京','中央戏剧学院','导演系100班',31),(8,8,'北京','北京','北京大学','表演系99班',28),(9,10,'浙江','杭州','杭州电子科技大学','自动化155班',25),(10,11,'未填写','未填写','未填写','未填写',0),(11,12,'浙江','杭州','浙江大学','英语678班',24),(12,13,'湖北','武汉','武汉大学','自动化234班',27),(13,14,'浙江','杭州','浙江大学','电器456班',28),(14,15,'山东','青岛','山东大学','表演系333班',22),(15,16,'上海','上海','上海交通大学','电器456班',21),(16,17,'湖北','武汉','武汉大学','计算机123班',25),(17,18,'广东','深圳','华南理工大学','表演系333班',27),(18,19,'广东','深圳','华南理工大学','英语678班',21),(19,20,'山东','青岛','山东大学','计算机123班',29),(20,21,'山东','青岛','山东大学','计算机123班',24),(21,22,'浙江','杭州','浙江大学','自动化234班',28),(22,23,'广东','深圳','华南理工大学','计算机123班',29),(23,24,'广东','深圳','华南理工大学','电器456班',27),(24,25,'广东','深圳','华南理工大学','计算机123班',22),(25,26,'广东','深圳','华南理工大学','英语678班',29),(26,27,'浙江','杭州','浙江大学','英语678班',25),(27,28,'湖北','武汉','武汉大学','自动化234班',29),(28,29,'上海','上海','上海交通大学','表演系333班',23),(29,30,'浙江','杭州','浙江大学','电器456班',28),(30,31,'北京','北京','清华大学','计算机123班',21);
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-25 14:06:29
