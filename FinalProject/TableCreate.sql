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

DROP TABLE IF EXISTS `knowledge`;
CREATE TABLE `knowledge` (
  `subject` varchar(20) NOT NULL,
  `title` varchar(20) NOT NULL,
  `content` LONGTEXT,
  PRIMARY KEY (`subject`, `title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;