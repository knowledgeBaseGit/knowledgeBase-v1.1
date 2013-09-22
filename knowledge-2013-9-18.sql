/*
SQLyog v10.2 
MySQL - 5.5.22 : Database - KnowledgeBase
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`KnowledgeBase` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `KnowledgeBase`;

/*Table structure for table `annex` */

DROP TABLE IF EXISTS `annex`;

CREATE TABLE `annex` (
  `annexId` int(20) NOT NULL AUTO_INCREMENT COMMENT '自增Id',
  `annexName` varchar(20) DEFAULT NULL COMMENT '附件名称',
  `annexUrl` varchar(200) DEFAULT NULL COMMENT '附件路径',
  `isAppear` int(1) DEFAULT '1' COMMENT '是否可见：（1：可见、0：不可见）',
  `browseTimes` int(20) DEFAULT '0' COMMENT '浏览次数',
  `loadTimes` int(20) DEFAULT '0' COMMENT '下载次数',
  PRIMARY KEY (`annexId`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

/*Data for the table `annex` */

insert  into `annex`(`annexId`,`annexName`,`annexUrl`,`isAppear`,`browseTimes`,`loadTimes`) values (1,'附件01','10.0.0.0',1,NULL,NULL),(2,'附件02','10.0.0.2',0,NULL,2),(3,'gfg','E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\annex\\jquery 监听事件.docx',NULL,NULL,NULL),(4,'fdfdf','E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\annex\\43_测试答案页_姓名.xlsx',NULL,NULL,4),(5,'fdfdf','E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\annex\\43_测试答案页_姓名.xlsx',0,NULL,0),(6,'fdfdf','E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\annex\\43_测试答案页_姓名.xlsx',NULL,NULL,NULL),(7,'fdfdf','E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\annex\\43_测试答案页_姓名.xlsx',NULL,NULL,NULL),(8,'fdfdf','E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\annex\\43_测试答案页_姓名.xlsx',NULL,NULL,NULL),(9,'fdfdf','E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\annex\\43_测试答案页_姓名.xlsx',0,NULL,NULL),(10,'fdfdf','E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\annex\\43_测试答案页_姓名.xlsx',NULL,NULL,NULL),(11,'fdfdf','E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\annex\\43_测试答案页_姓名.xlsx',NULL,NULL,NULL),(12,'fdfdf','E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\annex\\43_测试答案页_姓名.xlsx',NULL,NULL,NULL),(13,'fdfdf','E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\annex\\43_测试答案页_姓名.xlsx',NULL,NULL,NULL),(14,'fdfdf','E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\annex\\43_测试答案页_姓名.xlsx',NULL,NULL,NULL),(15,'fdfdf','E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\annex\\43_测试答案页_姓名.xlsx',NULL,NULL,NULL),(16,'fdfdf','E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\annex\\43_测试答案页_姓名.xlsx',NULL,NULL,NULL),(17,'fdfdf','E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\annex\\43_测试答案页_姓名.xlsx',NULL,NULL,NULL),(18,'fdfdf','E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\annex\\43_测试答案页_姓名.xlsx',NULL,NULL,NULL),(19,'ffffffff','E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\annex\\index.css',NULL,NULL,NULL),(20,'aaaa','E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\annex\\Staff.hbm.xml',NULL,NULL,NULL),(21,'dfdfdf','E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\annex\\QQ拼音截图未命名.png',NULL,NULL,NULL),(22,'222222','E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\annex\\TransForm.txt',NULL,NULL,NULL),(23,'ghgh','E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\annex\\TransForm.txt',0,NULL,NULL),(24,'大幅度反弹','E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\annex\\question Service.txt',1,NULL,NULL),(25,'bvb','E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\annex\\IQuestionsDao.txt',NULL,NULL,NULL),(26,'的奋斗奋斗分','E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\annex\\jquery.easyui.min.js',1,NULL,NULL),(27,'呵呵','E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\annex\\Chrysanthemum.jpg',1,NULL,NULL),(28,'反对反对法','E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\annex\\Penguins.jpg',1,NULL,NULL),(29,'涩涩涩','E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\annex\\Desert.jpg',1,NULL,0),(30,'sqlyog使用方法','E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\annex\\TransForm.txt',1,NULL,7);

/*Table structure for table `file_keyword` */

DROP TABLE IF EXISTS `file_keyword`;

CREATE TABLE `file_keyword` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fileId` int(11) DEFAULT NULL COMMENT 'ÎÄ¼þId(Íâ¼ü)',
  `keywordId` int(11) DEFAULT NULL COMMENT '¹Ø¼ü´ÊId(Íâ¼ü)',
  PRIMARY KEY (`id`),
  KEY `FKC957E267FFD237A` (`keywordId`),
  KEY `FKC957E26BA59CE1B` (`fileId`),
  CONSTRAINT `FKC957E267FFD237A` FOREIGN KEY (`keywordId`) REFERENCES `keyword` (`keywordId`),
  CONSTRAINT `FKC957E26BA59CE1B` FOREIGN KEY (`fileId`) REFERENCES `files` (`fileId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `file_keyword` */

insert  into `file_keyword`(`id`,`fileId`,`keywordId`) values (2,27,2),(3,28,6),(5,29,6),(10,30,4),(12,33,11);

/*Table structure for table `files` */

DROP TABLE IF EXISTS `files`;

CREATE TABLE `files` (
  `fileId` int(20) NOT NULL AUTO_INCREMENT COMMENT '文件Id',
  `fileName` varchar(50) DEFAULT NULL COMMENT '文件名称',
  `fileDesc` varchar(50) DEFAULT NULL COMMENT '文件描述',
  `createUser` varchar(10) DEFAULT NULL COMMENT '创建人（外键）',
  `createDate` varchar(20) DEFAULT NULL COMMENT '创建时间',
  `projectId` int(20) DEFAULT NULL COMMENT '项目名称',
  `checkUser` varchar(10) DEFAULT NULL COMMENT '审核人（外键）',
  `checkStatus` int(1) DEFAULT '0' COMMENT '审核状态(1：审核通过；0：审核未未通过)',
  `checkDate` varchar(20) DEFAULT NULL COMMENT '审核时间',
  `isAppear` int(1) DEFAULT '1' COMMENT '是否可见（1：可见、0：不可见）',
  `loadTimes` int(20) DEFAULT '0' COMMENT '下载次数',
  `browseTimes` int(20) DEFAULT '0' COMMENT '被浏览次数',
  `fileType` varchar(20) DEFAULT NULL COMMENT '文件类型',
  PRIMARY KEY (`fileId`),
  KEY `fk_files_project` (`projectId`),
  KEY `fk_files_user` (`createUser`),
  KEY `FK5CEBA7758D4BCDC` (`createUser`),
  KEY `FK5CEBA77A21CF1DA` (`projectId`),
  KEY `FK5CEBA771EFA1AE8` (`checkUser`),
  CONSTRAINT `fk_files_project` FOREIGN KEY (`projectId`) REFERENCES `project` (`projectId`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_files_user` FOREIGN KEY (`createUser`) REFERENCES `users` (`userId`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_files_user_check` FOREIGN KEY (`checkUser`) REFERENCES `users` (`userId`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

/*Data for the table `files` */

insert  into `files`(`fileId`,`fileName`,`fileDesc`,`createUser`,`createDate`,`projectId`,`checkUser`,`checkStatus`,`checkDate`,`isAppear`,`loadTimes`,`browseTimes`,`fileType`) values (27,'知识库文档','知识库的设计文档，包含概要设计和详细设计',NULL,NULL,6,NULL,0,'2013-09-10',0,0,NULL,NULL),(28,'数据库概要设计','数据库建模、概要设计',NULL,NULL,1,NULL,1,'2013-09-04',1,NULL,NULL,NULL),(29,'一站式数据库脚本','大幅度反弹',NULL,NULL,2,NULL,1,'2013-09-04',0,NULL,NULL,NULL),(30,'服务器部署文档','详细介绍巴州服务器部署流程',NULL,'2013-09-04',7,NULL,0,'2013-09-10',1,NULL,NULL,NULL),(33,'系统集成','系统集成项目工程师教程',NULL,'2013-09-04',6,NULL,0,'2013-09-10',0,1,NULL,NULL),(34,'struts配置说明','详细介struts2文件配置',NULL,'2013-09-05',7,NULL,1,'2013-09-05',1,7,NULL,NULL),(35,'版本控制','版本控制管理',NULL,'2013-09-05',6,NULL,0,'2013-09-10',0,2,NULL,NULL),(36,'数据库','知识库数据库',NULL,'2013-09-05',7,NULL,1,'2013-09-05',NULL,NULL,NULL,NULL),(38,'有关上传的实验','测试上传的文件是否可以正常提交',NULL,'2013-09-06',1,NULL,0,'2013-09-10',1,0,0,NULL),(39,'一站式数据库','一站式数据库建模',NULL,'2013-09-06',9,NULL,1,'2013-09-06',1,0,0,NULL),(40,'投稿','公司期刊投稿',NULL,'2013-09-06',NULL,NULL,1,'2013-09-06',1,0,0,NULL),(41,'投稿22doc','期刊投稿doc',NULL,'2013-09-06',6,NULL,1,'2013-09-06',1,0,0,NULL),(42,'投稿期刊','投稿期刊',NULL,'2013-09-06',4,NULL,1,'2013-09-06',1,1,0,NULL),(43,'表格','表格',NULL,'2013-09-06',7,NULL,0,'2013-09-10',0,1,0,NULL),(44,'工作日志3','工作日志模板',NULL,'2013-09-06',9,NULL,1,'2013-09-06',NULL,0,0,NULL),(45,'工作日志模板4','模板',NULL,'2013-09-06',7,NULL,NULL,NULL,1,0,0,NULL),(46,'明天会更好','明天会更好',NULL,'2013-09-09',4,NULL,0,'2013-09-10',1,0,0,NULL),(47,'知识库脚本','知识库最新数据库脚本','03039','2013-09-17',2,'03039',1,'2013-09-17',NULL,0,0,'doc'),(48,'乒乓球比赛通知','公司乒乓球比赛通知','03039','2013-09-17',3,'03039',1,'2013-09-17',1,1,0,'docx'),(49,'测试图片20130917','测试图片20130917','03007','2013-09-17',1,'03007',1,'2013-09-17',1,3,0,'jpg'),(50,'阳泉名称','阳泉明年打发打发','03039','2013-09-18',4,'03039',1,'2013-09-18',1,2,0,'docx'),(51,'aaaa','aaaaaaaaaa','03039','2013-09-18',7,'03039',1,'2013-09-18',1,0,0,'doc'),(52,'知识库流程图','知识库流程图','03039','2013-09-18',1,'03039',1,'2013-09-18',1,0,0,'vsd'),(53,'测试修改','大啊啊啊啊','03039','2013-09-18',3,'03039',1,'2013-09-18',1,0,0,'doc'),(54,'纷纷大幅','打发打发','03039','2013-09-18',2,NULL,NULL,NULL,NULL,0,0,'docx');

/*Table structure for table `fileversion` */

DROP TABLE IF EXISTS `fileversion`;

CREATE TABLE `fileversion` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `fileId` int(20) DEFAULT NULL COMMENT '文件Id(外键)',
  `versionId` int(10) DEFAULT '1' COMMENT '版本号',
  `fileUrl` varchar(200) DEFAULT NULL COMMENT '文件路径',
  `upAuthor` varchar(20) DEFAULT NULL COMMENT '上传者',
  `uploadDate` varchar(20) DEFAULT NULL COMMENT '上传时间',
  PRIMARY KEY (`id`),
  KEY `fk_fileVersion_files` (`fileId`),
  CONSTRAINT `fk_fileVersion_files` FOREIGN KEY (`fileId`) REFERENCES `files` (`fileId`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

/*Data for the table `fileversion` */

insert  into `fileversion`(`id`,`fileId`,`versionId`,`fileUrl`,`upAuthor`,`uploadDate`) values (14,27,1,'E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\files\\知识库功能模块.vsd',NULL,'2013-09-04'),(15,29,1,'E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\files\\jquery 监听事件.docx',NULL,'2013-09-04'),(16,30,1,'E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\files\\questinDaoImpl.txt',NULL,'2013-09-04'),(18,NULL,1,'E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\files\\grmain.chm',NULL,'2013-09-04'),(19,33,1,'E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\files\\TransForm.txt',NULL,'2013-09-04'),(20,34,1,'E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\files\\oracle 创建自增主键.txt',NULL,'2013-09-05'),(21,35,1,'E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\files\\Staff.java',NULL,'2013-09-05'),(22,36,1,'E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\files\\jquery 监听事件.docx',NULL,'2013-09-05'),(23,NULL,1,'D:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp3\\wtpwebapps\\KnowledgeBase\\files\\254服务器.txt',NULL,'2013-09-06'),(24,38,1,'D:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp3\\wtpwebapps\\KnowledgeBase\\files\\shiyan.txt',NULL,'2013-09-06'),(25,39,1,'E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\files\\question Service.txt',NULL,'2013-09-06'),(26,40,1,'E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\files\\投稿.docx',NULL,'2013-09-06'),(27,41,1,'E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\files\\投稿.doc',NULL,'2013-09-06'),(28,42,1,'E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\files\\投稿期刊.doc',NULL,'2013-09-06'),(29,43,1,'E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\files\\工作日志模板2.docx',NULL,'2013-09-06'),(30,44,1,'E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\files\\工作日志模板3.doc',NULL,'2013-09-06'),(31,45,1,'E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\files\\工作日志模板3.doc',NULL,'2013-09-06'),(32,46,1,'E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\files\\知识库第2期工作计划.docx',NULL,'2013-09-09'),(33,47,1,'E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\docFolder\\AAA.doc','索艳明','2013-09-17'),(34,48,1,'E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\docFolder\\pinpaqiu.docx','索艳明','2013-09-17'),(35,49,1,'E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\docFolder\\Desert.jpg','zzw','2013-09-17'),(36,50,1,'E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\docFolder\\pinpaqiu.docx','索艳明','2013-09-18'),(37,51,1,'E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\docFolder\\公司加班管理办法.doc','索艳明','2013-09-18'),(38,52,1,'E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\KnowledgeBase\\docFolder\\知识库功能模块.vsd','索艳明','2013-09-18'),(39,53,1,'E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\KnowledgeBase\\docFolder\\公司加班管理办法.doc','索艳明','2013-09-18'),(40,54,1,'E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\KnowledgeBase\\docFolder\\知识库第2期工作计划.docx','索艳明','2013-09-18');

/*Table structure for table `fun_role` */

DROP TABLE IF EXISTS `fun_role`;

CREATE TABLE `fun_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `functionId` varchar(20) DEFAULT NULL COMMENT '¹¦ÄÜId(Íâ¼ü)',
  `roleId` varchar(10) DEFAULT NULL COMMENT '½ÇÉ«Id(Íâ¼ü)',
  PRIMARY KEY (`id`),
  KEY `FK52164336EFDAA41B` (`functionId`),
  KEY `FK52164336136392DA` (`roleId`),
  CONSTRAINT `FK52164336136392DA` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`),
  CONSTRAINT `FK52164336EFDAA41B` FOREIGN KEY (`functionId`) REFERENCES `functions` (`functionId`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8;

/*Data for the table `fun_role` */

insert  into `fun_role`(`id`,`functionId`,`roleId`) values (12,'key_pro','0003'),(15,'proAdd','0001'),(16,'proAdd','0003'),(17,'key_pro','0004'),(29,'proDele','0003'),(30,'proSee','0003'),(31,'proUpdate','0003'),(32,'proAdd','0004'),(33,'proDele','0004'),(34,'proSee','0004'),(35,'proUpdate','0004'),(36,'cheFile','0004'),(37,'cheKey','0004'),(38,'fileDele','0004'),(39,'fileDown','0004'),(40,'fileSee','0004'),(41,'fileUpdate','0004'),(42,'roleGrant','0003'),(49,'fileDele','0003'),(50,'fileDown','0003'),(51,'fileSee','0003'),(52,'keyadd','0003'),(53,'keyDel','0003'),(54,'keySee','0003'),(55,'keyUpdate','0003'),(56,'quesAsk','0003'),(57,'quesDele','0003'),(58,'quesReply','0003'),(59,'quesSee','0003'),(60,'resadd','0004'),(61,'resDel','0004'),(62,'resUpdate','0004'),(63,'fileUpload','0003'),(64,'cheFile','0003'),(65,'fileDownLoad','0003'),(66,'resadd','0003'),(67,'resDel','0003'),(68,'resUpdate','0003'),(69,'roleadd','0003'),(70,'roledel','0003'),(71,'roleUpdate','0003'),(72,'userAdd','0003'),(73,'userAuth','0003'),(74,'userDele','0003'),(75,'userUpdate','0003'),(76,'cansolCheckFile','0003'),(77,'cansolFileDownLoad','0003'),(78,'seeFileChecked','0003'),(79,'seeFileUncheck','0003'),(80,'cheKey','0003'),(81,'keyUndoCheck','0003'),(82,'checkQues','0003'),(83,'qUncheck','0003'),(84,'seeQuesChecked','0003'),(85,'seeQuesUnCheck','0003'),(86,'annexDownLoad','0003'),(87,'annexNoDowLoad','0003'),(88,'checkSolu','0003'),(89,'soluDel','0003'),(90,'soluUndoCheck','0003'),(91,'soluUpdate','0003'),(92,'fileUpdate','0003'),(93,'hotSear','0003');

/*Table structure for table `functions` */

DROP TABLE IF EXISTS `functions`;

CREATE TABLE `functions` (
  `functionId` varchar(20) NOT NULL,
  `moduleId` varchar(10) DEFAULT NULL COMMENT 'Ä£¿éId(Íâ¼ü)',
  `functionName` varchar(50) DEFAULT NULL COMMENT '¹¦ÄÜÃû³Æ',
  `controlName` varchar(20) DEFAULT NULL COMMENT '¿Ø¼þÃû³Æ',
  PRIMARY KEY (`functionId`),
  KEY `FKF79F079BAC14F086` (`moduleId`),
  CONSTRAINT `FKF79F079BAC14F086` FOREIGN KEY (`moduleId`) REFERENCES `module` (`moduleId`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `functions` */

insert  into `functions`(`functionId`,`moduleId`,`functionName`,`controlName`) values ('annexDownLoad','soluCheck','附件下载权限开放','annexDownLoad'),('annexNoDowLoad','soluCheck','撤销附件下载权限','annexNoDownload'),('cansolCheckFile','fileCheck','文件审核撤销','cansolCheckFile'),('cansolFileDownLoad','fileCheck','撤销文件下载','cansolFileDownLoad'),('checkQues','quesCheck','问题审核','checkQues'),('checkSolu','soluCheck','审核方案','checkSolu'),('cheFile','fileCheck','文件审核','cheFile'),('cheKey','keyCheck','关键词审核','cheKey'),('file',NULL,NULL,NULL),('fileDele','fileMa','文件删除','deleFile'),('fileDown','fileMa','文件下载','downLoadFile'),('fileDownLoad','fileCheck','文件下载权限','fileDownLoad'),('fileSee','fileMa','文件查看','seeFile'),('fileUpdate','fileMa','文件修改','updateFile'),('fileUpload','fileMa','上传文件','uploadFile'),('hotSear','index','首页热搜','hotSear'),('keyadd','keyMa','关键词增加','addKey'),('keyDel','keyMa','删除关键词','keydel'),('keySee','keyMa','查看关键词','keySee'),('keyUndoCheck','keyCheck','撤销关键词审核','keyUndoCheck'),('keyUpdate','keyMa','修改关键词','keyUpdate'),('key_pro','typeMa','项目类别添加关键词','addKeyOfPro'),('proAdd','typeMa','添加项目类别','addPro'),('proDele','typeMa','删除项目类别','delePro'),('proSee','typeMa','查看项目类别','seePro'),('proUpdate','typeMa','项目类别修改','updatePro'),('quesAsk','quesMa','提问','askQues'),('quesDele','quesMa','删除问题','deleQues'),('quesReply','quesMa','问题提解答','replyQues'),('quesSee','quesMa','查看','seeQues'),('qUncheck','quesCheck','取消审核关键词','CansolCheck'),('resadd','resourceMa','添加新资源','addRes'),('resDel','resourceMa','删除资源','delRes'),('resUpdate','resourceMa','修改资源','updateRes'),('roleadd','roleMa','添加角色','addRole'),('roledel','roleMa','删除角色','delRole'),('roleGrant','roleMa','角色授权','grantRole'),('roleUpdate','roleMa','角色修改','updateRole'),('seeFileChecked','fileCheck','查看文件已审核','seeCheckedFile'),('seeFileUncheck','fileCheck','查看文件未审核','seeUncheckedFile'),('seeQuesChecked','quesCheck','查看已审核问题','quesChecked'),('seeQuesUnCheck','quesCheck','查看未审核问题','quesUncheck'),('soluDel','soluCheck','删除方案','deleteSolu'),('soluUndoCheck','soluCheck','撤销方案审核','soluUndoCheck'),('soluUpdate','soluCheck','修改方案','updateSolu'),('userAdd','userMa','新增用户','addUser'),('userAuth','userMa','用户授权','grantUser'),('userDele','userMa','删除用户','deleUser'),('userUpdate','userMa','用户信息修改','updateUser');

/*Table structure for table `keyword` */

DROP TABLE IF EXISTS `keyword`;

CREATE TABLE `keyword` (
  `keywordId` int(20) NOT NULL AUTO_INCREMENT COMMENT '关键词自动增长Id',
  `keywordName` varchar(20) DEFAULT NULL COMMENT '关键词名称',
  `isDstroy` int(1) DEFAULT '0' COMMENT '是否注销（1：是；0：否）',
  `destroyDate` varchar(20) DEFAULT NULL COMMENT '注销时间',
  `checkUser` varchar(10) DEFAULT NULL COMMENT '审核人',
  `checkStatus` int(1) DEFAULT '0' COMMENT '审核状态',
  `checkDate` varchar(20) DEFAULT NULL COMMENT '审核时间',
  `searchTimes` int(50) DEFAULT NULL COMMENT '搜索次数',
  `createUser` varchar(20) DEFAULT NULL COMMENT '创建人',
  `keywordDesc` varchar(50) DEFAULT NULL COMMENT '关键词描述',
  `createDate` varchar(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`keywordId`),
  KEY `FKCF751DE91EFA1AE8` (`checkUser`),
  CONSTRAINT `fk_keyword_user_check` FOREIGN KEY (`checkUser`) REFERENCES `users` (`userId`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

/*Data for the table `keyword` */

insert  into `keyword`(`keywordId`,`keywordName`,`isDstroy`,`destroyDate`,`checkUser`,`checkStatus`,`checkDate`,`searchTimes`,`createUser`,`keywordDesc`,`createDate`) values (2,'数据库',0,NULL,NULL,0,'2013-09-13',74,'张瑞祥','项目相关',NULL),(3,'一站式管理系统',NULL,NULL,NULL,1,'2013-09-11',NULL,'','',NULL),(4,'知识库',NULL,NULL,NULL,1,'2013-09-06',NULL,NULL,NULL,NULL),(5,'JSP基础',0,NULL,NULL,1,NULL,10,'张瑞祥','项目相关',NULL),(6,'JQUERY',0,NULL,NULL,1,'2013-09-06',68,'张瑞祥','项目相关的详细说明',NULL),(11,'楼宇管理',0,NULL,NULL,0,'2013-09-13',16,'张瑞祥','项目相关',NULL),(17,'系统集成考试',0,NULL,NULL,0,'2013-09-13',37,'张瑞祥','项目相关',NULL),(18,'优抚相关',0,NULL,NULL,1,'2013-09-11',16,'DF','项目相关','2013-08-27'),(19,'低保相关',0,NULL,NULL,1,'2013-09-11',27,'张瑞祥','项目相关',NULL),(20,'医疗救助',0,NULL,NULL,1,'2013-09-11',122,'张瑞祥','项目相关',NULL);

/*Table structure for table `module` */

DROP TABLE IF EXISTS `module`;

CREATE TABLE `module` (
  `moduleId` varchar(10) NOT NULL,
  `parentModule` varchar(10) DEFAULT NULL COMMENT 'ÉÏ¼¶Ä£¿é£¨Íâ¼ü£©',
  `moduleUrl` varchar(50) DEFAULT NULL COMMENT 'Ä£¿éUrl',
  `isDestroy` int(11) DEFAULT '1' COMMENT 'ÊÇ·ñ×¢Ïú£¨1£ºÊÇ£»0£º·ñ£©',
  `destroyDate` varchar(20) DEFAULT NULL COMMENT '×¢ÏúÊ±¼ä',
  `moduleName` varchar(20) DEFAULT NULL COMMENT 'Ä£¿éÃû³Æ',
  PRIMARY KEY (`moduleId`),
  KEY `FKC04BA66C3B8D2F95` (`parentModule`),
  CONSTRAINT `FKC04BA66C3B8D2F95` FOREIGN KEY (`parentModule`) REFERENCES `module` (`moduleId`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `module` */

insert  into `module`(`moduleId`,`parentModule`,`moduleUrl`,`isDestroy`,`destroyDate`,`moduleName`) values ('0',NULL,NULL,1,NULL,'系统管理'),('auth','0',NULL,1,NULL,'权限管理'),('check','0',NULL,1,NULL,'审核管理'),('fileCheck','check','/jsp/FileCheck.jsp',1,NULL,'文件审核'),('fileMa','0','/jsp/File.jsp',1,NULL,'文件管理'),('index','0','/templet/index.jsp',1,NULL,'首页'),('keyCheck','check','/jsp/KeywordCheck.jsp',1,NULL,'关键词审核'),('keyMa','0','/jsp/Keyword.jsp',1,NULL,'关键词管理'),('quesCheck','check','/jsp/QuesCheck.jsp',1,NULL,'问题审核'),('quesMa','0','/jsp/Query.jsp',1,NULL,'问题管理'),('resourceMa','auth','/jsp/resourceManage.jsp',1,NULL,'资源管理'),('roleMa','auth','/jsp/roleManage.jsp',1,NULL,'角色管理'),('soluCheck','check','/jsp/SoluCheck.jsp',1,NULL,'方案审核'),('typeMa','0','/jsp/Type.jsp',1,NULL,'类别管理'),('userMa','auth','/jsp/user.jsp',1,NULL,'用户管理');

/*Table structure for table `project` */

DROP TABLE IF EXISTS `project`;

CREATE TABLE `project` (
  `projectId` int(20) NOT NULL AUTO_INCREMENT COMMENT '项目Id',
  `projectName` varchar(50) DEFAULT NULL COMMENT '项目名称',
  `parentProject` int(20) DEFAULT NULL COMMENT '父级项目',
  `createTime` varchar(20) DEFAULT NULL COMMENT '创建时间',
  `projectDesc` varchar(50) DEFAULT NULL COMMENT '项目描述',
  `creater` varchar(20) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`projectId`),
  KEY `fk_project_self` (`parentProject`),
  KEY `FKED904B197EE70B15` (`parentProject`),
  CONSTRAINT `FKED904B197EE70B15` FOREIGN KEY (`parentProject`) REFERENCES `project` (`projectId`),
  CONSTRAINT `fk_project_self` FOREIGN KEY (`parentProject`) REFERENCES `project` (`projectId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;

/*Data for the table `project` */

insert  into `project`(`projectId`,`projectName`,`parentProject`,`createTime`,`projectDesc`,`creater`) values (1,'知识库',NULL,'2013-8-20','知识库','reson'),(2,'贵州一站式管理系统',9,'2013-8-20','贵州一站式管理系统','祥子'),(3,'黑龙江一站式管理系统',9,'2013-8-20','黑龙江一站式管理系统','reson'),(4,'阳泉一站式管理系统',9,'2013-8-20','阳泉一站式管理系统','reson'),(6,'石家庄楼宇管理',11,'2013-8-20','石家庄楼宇管理','reson'),(7,'新华区楼宇管理',11,'2013-8-20','新华区楼宇管理','reson'),(8,'知识库管理系统',1,'2013-8-20','知识库管理系统','祥子'),(9,'一站式管理系统',NULL,'2013-8-20','一站式管理系统','reson'),(11,'楼宇管理系统',NULL,'2013-8-21','楼宇管理系统',NULL),(50,'消防项目',1,'2013-09-06','消防项目','侯星亮');

/*Table structure for table `project_keyword` */

DROP TABLE IF EXISTS `project_keyword`;

CREATE TABLE `project_keyword` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keywordId` int(11) DEFAULT NULL COMMENT '¹Ø¼ü´ÊId',
  `projectId` int(11) DEFAULT NULL COMMENT 'ÏîÄ¿Id(Íâ¼ü)',
  PRIMARY KEY (`id`),
  KEY `FK40C8E6C3A21CF1DA` (`projectId`),
  KEY `FK40C8E6C37FFD237A` (`keywordId`),
  CONSTRAINT `FK40C8E6C3A21CF1DA` FOREIGN KEY (`projectId`) REFERENCES `project` (`projectId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK40C8E6C37FFD237A` FOREIGN KEY (`keywordId`) REFERENCES `keyword` (`keywordId`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=165 DEFAULT CHARSET=utf8;

/*Data for the table `project_keyword` */

insert  into `project_keyword`(`id`,`keywordId`,`projectId`) values (13,20,8),(55,2,11),(56,3,11),(144,4,6),(151,4,1),(152,2,1),(153,3,1),(154,5,1),(162,20,9),(163,5,9),(164,18,9);

/*Table structure for table `ques_keyword` */

DROP TABLE IF EXISTS `ques_keyword`;

CREATE TABLE `ques_keyword` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keywordId` int(11) DEFAULT NULL COMMENT '¹Ø¼ü´ÊId£¨Íâ¼ü£©',
  `questionId` int(11) DEFAULT NULL COMMENT 'ÎÊÌâId(Íâ¼ü)',
  PRIMARY KEY (`id`),
  KEY `FKC9ABF47C7FFD237A` (`keywordId`),
  KEY `FKC9ABF47CB4A1DA5B` (`questionId`),
  CONSTRAINT `FKC9ABF47C7FFD237A` FOREIGN KEY (`keywordId`) REFERENCES `keyword` (`keywordId`),
  CONSTRAINT `FKC9ABF47CB4A1DA5B` FOREIGN KEY (`questionId`) REFERENCES `questions` (`questionId`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

/*Data for the table `ques_keyword` */

insert  into `ques_keyword`(`id`,`keywordId`,`questionId`) values (1,2,1),(2,3,1),(3,3,2),(4,4,5),(5,6,7),(6,2,1),(7,2,3),(8,2,5),(9,2,7),(10,2,12),(11,2,8),(12,2,4),(13,3,3),(14,3,4),(15,4,5),(16,5,3),(17,6,4),(19,6,8),(20,20,1),(21,20,2),(22,20,2),(23,20,2),(24,20,2),(25,4,13),(26,3,14),(27,4,15);

/*Table structure for table `ques_solu` */

DROP TABLE IF EXISTS `ques_solu`;

CREATE TABLE `ques_solu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `solutionId` int(11) DEFAULT NULL COMMENT '½â¾ö·½°¸Id(Íâ¼ü)',
  `questionId` int(11) DEFAULT NULL COMMENT 'ÎÊÌâId(Íâ¼ü)',
  PRIMARY KEY (`id`),
  KEY `FK94A1DF32B4A1DA5B` (`questionId`),
  KEY `FK94A1DF327E3096A0` (`solutionId`),
  CONSTRAINT `FK94A1DF327E3096A0` FOREIGN KEY (`solutionId`) REFERENCES `solution` (`solutionId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK94A1DF32B4A1DA5B` FOREIGN KEY (`questionId`) REFERENCES `questions` (`questionId`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

/*Data for the table `ques_solu` */

insert  into `ques_solu`(`id`,`solutionId`,`questionId`) values (5,5,1),(6,5,3),(17,28,1),(18,29,3),(19,30,12),(20,32,12),(21,33,12);

/*Table structure for table `questions` */

DROP TABLE IF EXISTS `questions`;

CREATE TABLE `questions` (
  `questionId` int(11) NOT NULL AUTO_INCREMENT COMMENT '问题Id',
  `writeUser` varchar(10) DEFAULT NULL COMMENT '问题创建人',
  `projectId` int(11) DEFAULT NULL COMMENT '所属项目',
  `checkUser` varchar(10) DEFAULT NULL COMMENT '审核人',
  `questionDesc` varchar(100) DEFAULT NULL COMMENT '问题描述',
  `writeDate` varchar(20) DEFAULT NULL COMMENT '创建日期',
  `checkStatus` int(1) DEFAULT '0' COMMENT 'ÉóºË×´Ì¬£¨1£ºÍ¨¹ý£»0£ºÎ´Í¨¹ý£©',
  `checkDate` varchar(20) DEFAULT NULL COMMENT '审核时间',
  `browseTimes` int(11) DEFAULT NULL COMMENT '浏览次数',
  `questionTitle` varchar(20) DEFAULT NULL COMMENT '问题标题',
  `isSolve` int(1) DEFAULT '0' COMMENT 'ÊÇ·ñÒÑ½â¾ö£º£¨1£ºÊÇ£¬0£º·ñ£©',
  PRIMARY KEY (`questionId`),
  KEY `FK95C5414DA21CF1DA` (`projectId`),
  KEY `FK95C5414DB3654A3F` (`writeUser`),
  KEY `FK95C5414D1EFA1AE8` (`checkUser`),
  CONSTRAINT `FK95C5414D1EFA1AE8` FOREIGN KEY (`checkUser`) REFERENCES `users` (`userId`),
  CONSTRAINT `FK95C5414DA21CF1DA` FOREIGN KEY (`projectId`) REFERENCES `project` (`projectId`),
  CONSTRAINT `FK95C5414DB3654A3F` FOREIGN KEY (`writeUser`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `questions` */

insert  into `questions`(`questionId`,`writeUser`,`projectId`,`checkUser`,`questionDesc`,`writeDate`,`checkStatus`,`checkDate`,`browseTimes`,`questionTitle`,`isSolve`) values (1,NULL,2,NULL,'一站式系统所涉及的地区','2013-9-3',1,'2013-09-11',NULL,'aa',NULL),(2,NULL,3,NULL,'一站式管理系统的上线时间','2013-9-3',1,'2013-09-13',NULL,'一站式',0),(3,NULL,4,NULL,'知识库的管理','2013-9-3',1,'2013-09-13',NULL,'知识库',0),(4,NULL,1,NULL,'系统集成考试时间','2013-9-3',0,'2013-09-13',NULL,'LL',0),(5,NULL,2,NULL,'登陆知识库时候出现无法进入','2013-9-3',1,'2013-09-13',NULL,'OO',0),(7,NULL,3,NULL,'问题查看时候不能选择','2013-9-3',1,'2013-09-11',NULL,'II',0),(8,NULL,4,NULL,'文件管理的类型没有分类','2013-9-3',0,'2013-09-11',NULL,'JJJ',0),(11,NULL,2,NULL,'楼宇管理的地图精确问题','2013-9-3',0,'2013-9-3',NULL,NULL,0),(12,NULL,1,NULL,'关键词保存问题','2013-9-3',1,'2013-09-11',NULL,'知识库数据问题',NULL),(13,NULL,1,NULL,'知识库怎么用',NULL,1,'2013-09-11',NULL,'知识库',NULL),(14,NULL,1,NULL,'测试方法的实验',NULL,1,'2013-09-13',NULL,'实验测试',NULL),(15,NULL,1,NULL,'测试该方法',NULL,1,'2013-09-13',NULL,'测试000',NULL);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `roleId` varchar(10) NOT NULL COMMENT '角色Id',
  `roleName` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`roleId`,`roleName`,`remark`) values ('0001','普通用户','只读方式'),('0003','超级管理员','拥护系统的所有权限'),('0004','资源管理员','操作系统资源模块'),('0005','角色管理员','负责管理角色的增删改及授权');

/*Table structure for table `solution` */

DROP TABLE IF EXISTS `solution`;

CREATE TABLE `solution` (
  `solutionId` int(11) NOT NULL AUTO_INCREMENT,
  `writeUser` varchar(10) DEFAULT NULL COMMENT 'Â¼ÈëÈË£¨Íâ¼ü£©',
  `annexId` int(11) DEFAULT NULL COMMENT '¸½¼þId(Íâ¼ü)',
  `checkUser` varchar(10) DEFAULT NULL COMMENT 'ÉóºËÈË£¨Íâ¼ü£©',
  `solutionContent` varchar(100) DEFAULT NULL COMMENT '½â¾ö·½°¸ÄÚÈÝ',
  `writeDate` varchar(20) DEFAULT NULL COMMENT 'Â¼ÈëÊ±¼ä',
  `checkStatus` int(1) DEFAULT '0' COMMENT 'ÉóºË×´Ì¬£¨1£ºÍ¨¹ý£¬0£ºÎ´Í¨¹ý£©',
  `checkDate` varchar(20) DEFAULT NULL COMMENT 'ÉóºËÊ±¼ä',
  `solutionName` varchar(20) DEFAULT NULL COMMENT '·½°¸Ãû³Æ',
  PRIMARY KEY (`solutionId`),
  KEY `FK58ED4D79B3654A3F` (`writeUser`),
  KEY `FK58ED4D791EFA1AE8` (`checkUser`),
  KEY `FK58ED4D79D33C6CD0` (`annexId`),
  CONSTRAINT `FK58ED4D791EFA1AE8` FOREIGN KEY (`checkUser`) REFERENCES `users` (`userId`),
  CONSTRAINT `FK58ED4D79B3654A3F` FOREIGN KEY (`writeUser`) REFERENCES `users` (`userId`),
  CONSTRAINT `FK58ED4D79D33C6CD0` FOREIGN KEY (`annexId`) REFERENCES `annex` (`annexId`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

/*Data for the table `solution` */

insert  into `solution`(`solutionId`,`writeUser`,`annexId`,`checkUser`,`solutionContent`,`writeDate`,`checkStatus`,`checkDate`,`solutionName`) values (5,NULL,4,NULL,'解决方案5',NULL,0,'2013-09-02','fdfdf'),(9,NULL,NULL,NULL,'解决方案9',NULL,NULL,NULL,'00000'),(11,NULL,NULL,NULL,'解决方案11',NULL,NULL,NULL,'密密麻麻1321'),(13,NULL,NULL,NULL,'解决方案13',NULL,NULL,NULL,'奋斗奋斗00'),(15,NULL,NULL,NULL,'解决方案15',NULL,NULL,NULL,'哈哈哈'),(28,NULL,NULL,NULL,'132123123','2013-09-13',NULL,NULL,'1231'),(29,NULL,NULL,NULL,'知识库的解答要点分析','2013-09-14',NULL,NULL,'知识库的解答'),(30,NULL,NULL,NULL,'这是一个测试的方法','2013-09-16',1,'2013-09-16','方法测试9-16'),(31,NULL,NULL,NULL,'123',NULL,1,'2013-09-16','123'),(32,'03007',NULL,'03007','测试','2013-09-17',1,'2013-09-17','测试'),(33,'03007',NULL,'03007','明显点的','2013-09-17',1,'2013-09-17','明显点的');

/*Table structure for table `staff` */

DROP TABLE IF EXISTS `staff`;

CREATE TABLE `staff` (
  `staffId` varchar(10) NOT NULL COMMENT '员工编号',
  `staffName` varchar(10) DEFAULT NULL COMMENT '员工姓名',
  `staffTel` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `department` varchar(20) DEFAULT NULL COMMENT '所属部门',
  `entryDate` varchar(20) DEFAULT NULL COMMENT '入职日期',
  PRIMARY KEY (`staffId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `staff` */

insert  into `staff`(`staffId`,`staffName`,`staffTel`,`department`,`entryDate`) values ('03003','候星亮','1234567890','研发部','2010-1-1'),('03007','周志伟','1111111111','实施部','2012-5-1'),('03016','付成刚','9876543210','研发部','2012-10-1'),('03027','马洪亮','6398527401','研发部','2013-5-1'),('03031','高星','3698527410','研发部','2012-12-1'),('03038','代海燕','5896213470','研发部','2013-1-1'),('03039','索艳明','1239874560','研发部','2013-7-1'),('03040','张瑞祥','6549871230','研发部','2013-8-1');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `userId` varchar(10) NOT NULL COMMENT '用户Id',
  `roleId` varchar(10) DEFAULT NULL COMMENT '角色Id',
  `loginName` varchar(10) DEFAULT NULL COMMENT '登陆名称',
  `loginPassword` varchar(10) DEFAULT NULL COMMENT '登陆密码',
  `createDate` varchar(20) DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`userId`),
  KEY `fk_user_role` (`roleId`),
  KEY `FK6A68E08136392DA` (`roleId`),
  KEY `FK6A68E08D5387513` (`userId`),
  CONSTRAINT `fk_user_role` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_user_staff` FOREIGN KEY (`userId`) REFERENCES `staff` (`staffId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`userId`,`roleId`,`loginName`,`loginPassword`,`createDate`) values ('03003','0001','候星亮','000000',NULL),('03007','0003','zzw','000000','2013-09-09'),('03016','0001','付成刚','000000','2013-09-16'),('03027','0001','马洪亮','000000','2013-09-16'),('03039','0003','索艳明','000000',NULL),('03040','0001','张瑞祥','000000','2013-09-16');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
