/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50162
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50162
File Encoding         : 65001

Date: 2014-04-29 22:11:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `punch_set`
-- ----------------------------
DROP TABLE IF EXISTS `punch_set`;
CREATE TABLE `punch_set` (
  `id` varchar(255) NOT NULL,
  `company_id` varchar(10) NOT NULL,
  `on_duty_time` time NOT NULL,
  `off_duty_time` time NOT NULL,
  `start_on_duty` time NOT NULL,
  `end_on_duty` time NOT NULL,
  `start_off_duty` time NOT NULL,
  `attend` int(11) NOT NULL,
  `late` int(11) NOT NULL,
  `early` int(11) NOT NULL,
  `absent` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`),
  KEY `company_id` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公司考勤设置';

-- ----------------------------
-- Records of punch_set
-- ----------------------------
