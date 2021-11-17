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