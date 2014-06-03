/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50162
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50162
File Encoding         : 65001

Date: 2014-04-29 21:40:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `employee_login`
-- ----------------------------
DROP TABLE IF EXISTS `employee_login`;
CREATE TABLE `employee_login` (
  `id` varchar(255) NOT NULL,
  `employee_id` varchar(30) NOT NULL,
  `employee_name` varchar(20) NOT NULL,
  `employee_tel` varchar(20) NOT NULL,
  `company_id` varchar(10) NOT NULL,
  `employee_identity_id` varchar(30) NOT NULL,
  `employee_password` varchar(255) NOT NULL,
  `status` varchar(10) NOT NULL,
  `is_del` double NOT NULL,
  `create_time` date NOT NULL,
  `modify_time` date NOT NULL,
  `modify_id` varchar(255) NOT NULL,
  `register_time` date NOT NULL,
  `activate_time` date NOT NULL,
  `login_status` varchar(255) NOT NULL,
  `login_time` date NOT NULL,
  `authenticate_info` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`),
  KEY `employee_id` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工登录信息';

-- ----------------------------
-- Records of employee_login
-- ----------------------------
