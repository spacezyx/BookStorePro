SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(11)  AUTO_INCREMENT,
  `isbn` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `inventory` int(11) DEFAULT NULL,
  `image_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY(image_id) REFERENCES image(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------

INSERT INTO `book` VALUES (1,'1','三体：全三册', '科幻', '刘慈欣', '50.20', '"The Three-Body Problem is the first chance for English-speaking readers to experience the Hugo Award-winning phenomenon from China''s most beloved science fiction author, Liu Cixin."

                "Set against the backdrop of China''s Cultural Revolution, a secret military project sends signals into space to establish contact with aliens. ',  '10000','1'),
                          (2,'2','追风筝的人', '世界名著', '卡勒德·胡赛尼', '35.30', 'The unforgettable, heartbreaking story of the unlikely friendship between a wealthy boy and the son of his father’s servant, caught in the tragic sweep of history, The Kite Runner transports readers to Afghanistan at a tense and crucial moment of change and destruction. A powerful story of friendship, it is also about the power of reading, the price of betrayal, and the possibility of redemption.', '1000', '2'),
                          (3,'3','红楼梦', '世界名著', '曹雪芹', '18.80', 'The Story of the Stone , also known by the title of The Dream of the Red Chamber, is the great novel of manners in Chinese literature.
Divided into five volumes, The Story of the Stone charts the glory and decline of the illustrious Jia family. This novel re-creates the ritualized hurly-burly of Chinese family life that would otherwise be lost and infuses it with affirming Buddhist belief.', '100', '3'),
                          (4,'4','小王子','儿童文学','圣-埃克苏佩里',8.89,'Few stories are as widely read and as universally cherished by children and adults alike as The Little Prince. Howard, an acclaimed poet and one of the preeminent translators of our time, has excelled in bringing the English text as close as possible to the French, in language, style, and most important, spirit.','500','4'),
                          (5,'5','天龙八部(全五册)', '武侠小说', '金庸', '102.30', 'Qiao Feng, leader of the Beggars Association, possessed strong leadership qualities and exceptional prowess in martial arts. After he was being accused as the descendant of Qidan, Qiao Feng was distained and rejected, finally became an outcast of the Wulin.','1000', '5'),
                          (6,'6','哈利波特与魔法石', '魔幻小说', 'J·K·罗琳', '30.20', 'A dazzling new edition of J.K. Rowling''s Harry Potter and the Sorcerer''s Stone, fully illustrated in brilliant color and featuring exclusive interactive paper craft elements, including a foldout Hogwarts letter and more!', '1000','6');
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) AUTO_INCREMENT,
  `nickname` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'handsome', 'handsome', '', ''),
                          ('2', 'ugly', 'ugly', '',''),
                          ('3', 'root', 'root', '','');

-- ----------------------------
-- Table structure for user_auth
-- ----------------------------
DROP TABLE IF EXISTS `user_auth`;
CREATE TABLE `user_auth` (
  `user_id` int(11) AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
    PRIMARY KEY (`user_id`),
    FOREIGN KEY(user_id) REFERENCES user(user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_auth
-- ----------------------------
INSERT INTO `user_auth` VALUES ('1', 'handsome', 'handsome'),
                               ('2', 'ugly', 'ugly'),
                               ('3', 'root', 'root');


DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `userRole` int(11),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
    `id` int(11) AUTO_INCREMENT,
    `image_file` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `image` VALUES ('1','http://img3m4.ddimg.cn/32/35/23579654-1_u_3.jpg' ),
                            ('2','http://img3m5.ddimg.cn/26/14/25238195-1_w_3.jpg'),
                            ('3', 'http://img3m6.ddimg.cn/31/22/23828836-1_w_8.jpg'),
                           ('4','http://img3m9.ddimg.cn/75/6/25067469-1_u_2.jpg' ),
                           ('5','http://img3m2.ddimg.cn/84/17/23273202-1_w_1.jpg'),
                           ('6', 'http://img3m1.ddimg.cn/88/0/25479421-1_w_1.jpg');

DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
                         `id` int(11) AUTO_INCREMENT,
                         `user_id` int(11) DEFAULT NULL,
                         `book_id` int(11) DEFAULT NULL,
                         `num` int(11) DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         FOREIGN KEY(user_id) REFERENCES user(user_id),
                         FOREIGN KEY(book_id) REFERENCES book(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
                        `id` int(11) AUTO_INCREMENT,
                        `user_id` int(11) DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        FOREIGN KEY(user_id) REFERENCES user(user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
                        `id` int(11) AUTO_INCREMENT,
                        `book_id` int(11) DEFAULT NULL,
                        `order_id` int(11) DEFAULT NULL,
                        `num` int(11) DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        FOREIGN KEY(book_id) REFERENCES book(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `visit`;
CREATE TABLE `visit` (
                         `id` int(11) AUTO_INCREMENT,
                         `times` int(11) DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into visit values (1,0);