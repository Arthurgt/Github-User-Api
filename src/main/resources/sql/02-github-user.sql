CREATE DATABASE IF NOT EXISTS `github_user_api`;
USE `github_user_api`;

--
-- Table structure for table `github_user`
--
DROP TABLE IF EXISTS `github_user`;

CREATE TABLE `github_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `login`varchar(45) DEFAULT NULL,
  `request_count` int DEFAULT 1,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

