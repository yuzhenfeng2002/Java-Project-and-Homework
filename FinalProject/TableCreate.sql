SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `username` varchar(20) NOT NULL,
  `usertype` tinyint NOT NULL,
  `university` varchar(20),
  `ID` varchar(20),
  PRIMARY KEY (`email`, `usertype`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `introduction` TEXT,
  PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `knowledge`;
CREATE TABLE `knowledge` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `subject_id` INT NOT NULL,
  `title` varchar(20) NOT NULL,
  `content` LONGTEXT,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`subject_id`) REFERENCES subject(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;