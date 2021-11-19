INSERT INTO `user` VALUES
('1150125812@qq.com', MD5('fyz020301!'), 'Yuzhen', 0, '同济大学', '1951362'),
('1150125812@qq.com', MD5('fyz020301!'), 'Yuzhen', 1, '同济大学', '1951362'),
('1150125812@qq.com', MD5('fyz020301!'), 'Yuzhen', 2, '同济大学', '1951362');

INSERT INTO `subject` (`name`, `introduction`) VALUES
('Java语言程序设计', 'Java是由Sun Microsystems公司于1995年5月推出的Java面向对象程序设计语言和 Java平台的总称。由James Gosling和同事们共同研发，并在1995年正式推出。\n\n后来Sun公司被Oracle（甲骨文）公司收购，Java也随之成为Oracle公司的产品。'),
('最优化理论', '最优化理论是关于系统的最优设计、最优控制、最优管理问题的理论与方法。最优化，就是在一定的约束条件下，使系统具有所期待的最优功能的组织过程。是从众多可能的选择中作出最优选择，使系统的目标函数在约束条件下达到最大或最小。也就是：\n\n![](https://www.zhihu.com/equation?tex=%5cmin%20x%5c%5c%20%5cmathrm{s%2et%2e%5c%20}f(x)%5cleq%200)\n\n现代优化理论及方法是在本世纪40年代发展起来的，其理论和方法愈来愈多，如线性规划、非线性规划、动态规划、排队论、对策论、决策论、博弈论等。');

INSERT INTO `knowledge` (`subject_id`, `title`, `content`) VALUES
(1, 'Java简介', '# Java 简介\nJava是由Sun Microsystems公司于1995年5月推出的Java面向对象程序设计语言和 Java平台的总称。由James Gosling和同事们共同研发，并在1995年正式推出。\n\n后来Sun公司被Oracle（甲骨文）公司收购，Java也随之成为Oracle公司的产品。'),
(1, 'Java 开发环境配置', '# Java开发环境配置\n在本章节中我们将为大家介绍如何搭建Java开发环境。\n- Windows上安装开发环境\n- Linux上安装开发环境\n- 安装Eclipse运行Java'),
(2, '凸集', '# 凸集\n## 凸集的定义'),
(2, '凸函数', '# 凸函数\n## 凸函数的定义');