-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: bilibili
-- ------------------------------------------------------
-- Server version	5.5.58

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
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(45) NOT NULL,
  `video_id` char(10) NOT NULL,
  `value` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_comment_user_id_idx` (`user_id`),
  KEY `FK_comment_video_id_idx` (`video_id`),
  CONSTRAINT `FK_comment_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_comment_video_id` FOREIGN KEY (`video_id`) REFERENCES `video` (`video_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'17633572016','av00786721','一楼祭天'),(2,'17633572016','av00786721','挤挤'),(3,'17633572016','av00786721','火钳刘明'),(4,'klay','av00786722',''),(5,'klay','av00786721','哈哈哈哈，快到碗里来'),(6,'bili','av00786722','上善若水'),(7,'dangdang','av00786721','fafdsaf'),(8,'dangdang','av00786721','其二微软'),(9,'dangdang','av00786721','张三李四'),(10,'dangdang','av00786721','我是谁'),(11,'dangdang','av00786721','为了谁'),(12,'dangdang','av00786721','我是dangdang'),(13,'klay','av00786721','我是klay'),(14,'dangdang','av00786721','上海'),(15,'dangdang','av00786721','不错'),(16,'dangdang','av00786721','不错'),(17,'dangdang','av00786723','哈哈'),(18,'dangdang','av00786723','厉害'),(19,'dangdang','av00786722','清华北大'),(20,'dangdang','av00786722','得到'),(21,'dangdang','av00786722','没得打'),(22,'dangdang','av00786722','没得打'),(23,'dangdang','av00786722','没得打'),(24,'dangdang','av00786722','没得打'),(25,'dangdang','av00786722','没得打'),(26,'dangdang','av00786722','没得打'),(27,'dangdang','av00786722','weishenm'),(28,'dangdang','av00786722','不错');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` varchar(50) NOT NULL,
  `user_name` varchar(30) DEFAULT NULL,
  `user_password` varchar(20) NOT NULL,
  `user_email` varchar(100) DEFAULT NULL,
  `user_gender` varchar(5) DEFAULT '保密',
  `user_birthday` date DEFAULT '1990-01-01',
  `avatar` varchar(100) DEFAULT 'image/default.jpg',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('','','','','',NULL,NULL),('1','wenchao','123456',NULL,'保密','1990-01-01','image/default.jpg'),('17633572016','klay','123456','1368570953@qq.com','male','1996-01-09','image/default.PNG'),('2','wenchao2','123456',NULL,'保密','1990-01-01','image/default.jpg'),('6','','123456','','',NULL,NULL),('abcd','abcd','123456','','',NULL,NULL),('adf','','','','',NULL,NULL),('bili','','123456','','',NULL,NULL),('d\'d','','123456','','',NULL,NULL),('dangdang','张三','123456','kad@kingland.com','男','1996-01-10','image/dongman.jpg'),('klay','klay','123456','klay@kingland.com','男','1996-01-09',NULL),('zhanghsna','张三','123456','kingland@kingland.com','男',NULL,NULL),('张三','','123456','','',NULL,NULL),('狗蛋','够胆','123456','','',NULL,NULL),('飞虎队','','123456','','',NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `sys_user_id` varchar(50) DEFAULT NULL,
  `sys_role_id` varchar(45) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES ('1','1',1),('1','2',2),('2','1',3);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `video`
--

DROP TABLE IF EXISTS `video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `video` (
  `video_id` char(10) NOT NULL,
  `video_title` varchar(200) NOT NULL,
  `video_path` varchar(300) DEFAULT 'video_cover/default.jpg',
  `video_cover_path` varchar(300) DEFAULT NULL,
  `video_watch_count` int(11) DEFAULT '0',
  `video_type` varchar(10) DEFAULT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`video_id`),
  KEY `FK_video_user_userid` (`user_id`),
  CONSTRAINT `FK_video_user_userid` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video`
--

LOCK TABLES `video` WRITE;
/*!40000 ALTER TABLE `video` DISABLE KEYS */;
INSERT INTO `video` VALUES ('av00786721','那是一条神奇的天路','/video/video.mp4','/video_cover/video.PNG',10,'动漫','17633572016'),('av00786722','佳人难再得','/video/video.mp4','/video_cover/cover1.png',34,'动漫','17633572016'),('av00786723','北方有佳人','/video/video.mp4','/video_cover/cover1.png',23,'动漫','17633572016'),('av00786724','绝世而独立','/video/video2.mp4','/video_cover/cover1.png',53,'动漫','17633572016'),('av00786726','一笑倾人城','/video/video2.mp4','/video_cover/cover2.png',2,'电影','17633572016'),('av00786727','在笑倾人国','/video/video2.mp4','/video_cover/cover2.png',35,'电影','17633572016'),('av00786728','倾城与倾国','/video/video2.mp4','/video_cover/cover2.png',456,'电影','17633572016'),('av00786729','我是谁','/video/video.mp4','/video_cover/cover2.png',45,'电影','17633572016'),('av00786730','我是谁','/video/video.mp4','/video_cover/cover3.png',34,'科技','17633572016'),('av00786731','我是谁','/video/video.mp4','/video_cover/cover3.png',45,'科技','17633572016'),('av00786732','我是谁','/video/video.mp4','/video_cover/cover3.png',20,'科技','17633572016'),('av00786733','我是谁','/video/video.mp4','/video_cover/cover3.png',45,'科技','17633572016'),('av00786734','我是谁','/video/video.mp4','/video_cover/cover3.png',45,'科技','17633572016'),('av00786735','我是谁','/video/video.mp4','/video_cover/cover4.PNG',45,'科技','17633572016'),('av00786736','我是谁','/video/video.mp4','/video_cover/cover4.PNG',45,'科技','17633572016'),('av00786737','我是谁','/video/video.mp4','/video_cover/cover4.PNG',45,'科技','17633572016'),('av00786738','我是谁','/video/video.mp4','/video_cover/cover4.PNG',45,'科技','17633572016'),('av00786739','我是谁','/video/video.mp4','/video_cover/cover4.PNG',45,'科技','17633572016'),('av00786740','我是谁','/video/video.mp4','/video_cover/cover4.PNG',45,'科技','17633572016');
/*!40000 ALTER TABLE `video` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `video_like`
--

DROP TABLE IF EXISTS `video_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `video_like` (
  `user_id` varchar(50) NOT NULL,
  `video_id` char(10) NOT NULL,
  PRIMARY KEY (`user_id`,`video_id`),
  KEY `FK_videolike_video_videoid` (`video_id`),
  CONSTRAINT `FK_videolike_user_userid` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FK_videolike_video_videoid` FOREIGN KEY (`video_id`) REFERENCES `video` (`video_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video_like`
--

LOCK TABLES `video_like` WRITE;
/*!40000 ALTER TABLE `video_like` DISABLE KEYS */;
INSERT INTO `video_like` VALUES ('dangdang','av00786721'),('dangdang','av00786722'),('dangdang','av00786723'),('dangdang','av00786726'),('dangdang','av00786727'),('dangdang','av00786728'),('dangdang','av00786729'),('dangdang','av00786730'),('dangdang','av00786732'),('dangdang','av00786733');
/*!40000 ALTER TABLE `video_like` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-17 16:51:32
