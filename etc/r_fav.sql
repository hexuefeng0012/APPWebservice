/*
Navicat MySQL Data Transfer

Source Server         : conn
Source Server Version : 50091
Source Host           : localhost:3306
Source Database       : raincent_web

Target Server Type    : MYSQL
Target Server Version : 50091
File Encoding         : 65001

Date: 2015-02-06 09:20:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for r_fav
-- ----------------------------
DROP TABLE IF EXISTS `r_fav`;
CREATE TABLE `r_fav` (
  `uid` int(11) NOT NULL,
  `newsid` int(11) default NULL,
  KEY `FK_r_fav_r_user_idx` (`uid`),
  KEY `FK_r_fav_r_news_idx` (`newsid`),
  CONSTRAINT `FK_r_fav_r_news` FOREIGN KEY (`newsid`) REFERENCES `r_news` (`newsid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_r_fav_r_user` FOREIGN KEY (`uid`) REFERENCES `r_user` (`uid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of r_fav
-- ----------------------------
INSERT INTO `r_fav` VALUES ('1', '20');
