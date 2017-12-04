/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : bookmark

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-12-04 21:08:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for m_tag
-- ----------------------------
DROP TABLE IF EXISTS `m_tag`;
CREATE TABLE `m_tag` (
  `tagId` varchar(10) NOT NULL,
  `tagName` varchar(64) NOT NULL,
  `tagKey` varchar(128) NOT NULL,
  PRIMARY KEY (`tagId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_tag
-- ----------------------------
INSERT INTO `m_tag` VALUES ('100', 'Spring', 'spring,springmvc');
