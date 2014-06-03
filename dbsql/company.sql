/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50162
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50162
File Encoding         : 65001

Date: 2014-04-29 21:40:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `company`
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `id` varchar(255) NOT NULL,
  `company_id` varchar(10) NOT NULL,
  `company_name` varchar(40) NOT NULL,
  `company_type` varchar(40) NOT NULL,
  `company_phone` varchar(20) DEFAULT NULL,
  `company_email` varchar(100) DEFAULT NULL,
  `company_address` varchar(200) NOT NULL,
  `company_web` varchar(255) DEFAULT NULL,
  `linkman_id` varchar(10) NOT NULL,
  `status` varchar(10) NOT NULL,
  `is_del` double NOT NULL,
  `create_time` date NOT NULL,
  `modify_time` date NOT NULL,
  `modify_id` varchar(255) NOT NULL,
  `register_time` date NOT NULL,
  `activate_time` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `company_id` (`company_id`),
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公司信息';

-- ----------------------------
-- Records of company
-- ----------------------------
