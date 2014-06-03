/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50162
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50162
File Encoding         : 65001

Date: 2014-04-29 22:20:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `punch_count`
-- ----------------------------
DROP TABLE IF EXISTS `punch_count`;
CREATE TABLE `punch_count` (
  `id` varchar(255) NOT NULL,
  `employee_id` varchar(10) NOT NULL,
  `attend_count` int(11) NOT NULL,
  `late_count` int(11) NOT NULL,
  `leave_count` int(11) NOT NULL,
  `absent_count` int(11) NOT NULL,
  `out_count` int(11) NOT NULL,
  `travel_count` int(11) NOT NULL,
  `ask_count` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`),
  KEY `employee_id` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工考勤统计表';

-- ----------------------------
-- Records of punch_count
-- ----------------------------
