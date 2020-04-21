/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50712
Source Host           : 127.0.0.1:3306
Source Database       : platform_tenant

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2020-04-21 21:41:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pub_console_user
-- ----------------------------
DROP TABLE IF EXISTS `pub_console_user`;
CREATE TABLE `pub_console_user` (
  `id` varchar(40) NOT NULL,
  `account` varchar(60) NOT NULL,
  `avatar_id` varchar(255) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `gender` varchar(1) NOT NULL,
  `last_login_date` datetime DEFAULT NULL,
  `name` varchar(40) NOT NULL,
  `password` varchar(80) NOT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `sort` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pub_console_user
-- ----------------------------
INSERT INTO `pub_console_user` VALUES ('00014a7a496bd41d', 'admin', null, null, '0', '2020-04-21 19:50:05', '管理员', '54c820a4c737cd635b83e3e42af94fb4d75405d38d376a15102cc007', '13800000000', '0');

-- ----------------------------
-- Table structure for pub_login_log
-- ----------------------------
DROP TABLE IF EXISTS `pub_login_log`;
CREATE TABLE `pub_login_log` (
  `id` varchar(40) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` varchar(1) NOT NULL DEFAULT '0',
  `update_date` datetime DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `user_id` varchar(40) DEFAULT NULL,
  `tenant_id` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `index_pub_login_log_user_id` (`user_id`),
  KEY `FKpe4u1xjyjld5teqjsns11el25` (`tenant_id`),
  CONSTRAINT `FKpe4u1xjyjld5teqjsns11el25` FOREIGN KEY (`tenant_id`) REFERENCES `pub_tenant` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pub_login_log
-- ----------------------------
INSERT INTO `pub_login_log` VALUES ('0001719cbbe88101', '2020-04-21 20:34:53', '0', '2020-04-21 20:34:53', null, '127.0.0.1', '000148444c11ac09', '000148446c11ab05');
INSERT INTO `pub_login_log` VALUES ('0001719cbd8a1802', '2020-04-21 20:36:40', '0', '2020-04-21 20:36:40', null, '127.0.0.1', '000148444c11ac09', '000148446c11ab05');
INSERT INTO `pub_login_log` VALUES ('0001719cc3578a00', '2020-04-21 20:43:00', '0', '2020-04-21 20:43:00', null, '127.0.0.1', '000148444c11ac09', '000148446c11ab05');
INSERT INTO `pub_login_log` VALUES ('0001719ccb668000', '2020-04-21 20:51:48', '0', '2020-04-21 20:51:48', null, '127.0.0.1', '000148444c11ac09', '000148446c11ab05');
INSERT INTO `pub_login_log` VALUES ('0001719ccd7cd001', '2020-04-21 20:54:05', '0', '2020-04-21 20:54:05', null, '127.0.0.1', '000148444c11ac09', '000148446c11ab05');
INSERT INTO `pub_login_log` VALUES ('0001719cd2a11800', '2020-04-21 20:59:42', '0', '2020-04-21 20:59:42', null, '127.0.0.1', '000148444c11ac09', '000148446c11ab05');
INSERT INTO `pub_login_log` VALUES ('0001719cd598cd00', '2020-04-21 21:02:56', '0', '2020-04-21 21:02:56', null, '127.0.0.1', '000148444c11ac09', '000148446c11ab05');
INSERT INTO `pub_login_log` VALUES ('0001719cd8f6f501', '2020-04-21 21:06:37', '0', '2020-04-21 21:06:37', null, '127.0.0.1', '000148444c11ac00', '000148446c11ac09');
INSERT INTO `pub_login_log` VALUES ('0001719cde36f600', '2020-04-21 21:12:21', '0', '2020-04-21 21:12:21', null, '127.0.0.1', '000148444c11ac09', '000148446c11ab05');
INSERT INTO `pub_login_log` VALUES ('0001719cdf082f01', '2020-04-21 21:13:15', '0', '2020-04-21 21:13:15', null, '127.0.0.1', '000148444c11ac00', '000148446c11ac09');
INSERT INTO `pub_login_log` VALUES ('0001719ce1caad02', '2020-04-21 21:16:15', '0', '2020-04-21 21:16:15', null, '127.0.0.1', '000148444c11ac00', '000148446c11ac09');
INSERT INTO `pub_login_log` VALUES ('0001719ce898b400', '2020-04-21 21:23:41', '0', '2020-04-21 21:23:41', null, '127.0.0.1', '000148444c11ac09', '000148446c11ab05');
INSERT INTO `pub_login_log` VALUES ('0001719cea313801', '2020-04-21 21:25:26', '0', '2020-04-21 21:25:26', null, '127.0.0.1', '000148444c11ac09', '000148446c11ab05');
INSERT INTO `pub_login_log` VALUES ('0001719cea82eb02', '2020-04-21 21:25:47', '0', '2020-04-21 21:25:47', null, '127.0.0.1', '000148444c11ac09', '000148446c11ab05');
INSERT INTO `pub_login_log` VALUES ('0001719cee95cb00', '2020-04-21 21:30:14', '0', '2020-04-21 21:30:14', null, '127.0.0.1', '000148444c11ac09', '000148446c11ab05');
INSERT INTO `pub_login_log` VALUES ('0001719cf28f4b00', '2020-04-21 21:34:34', '0', '2020-04-21 21:34:34', null, '127.0.0.1', '000148444c11ac09', '000148446c11ab05');
INSERT INTO `pub_login_log` VALUES ('0001719cf5144100', '2020-04-21 21:37:19', '0', '2020-04-21 21:37:19', null, '127.0.0.1', '000148444c11ac09', '000148446c11ab05');
INSERT INTO `pub_login_log` VALUES ('0001719cf7c3e701', '2020-04-21 21:40:15', '0', '2020-04-21 21:40:15', null, '127.0.0.1', '000148444c11ac09', '000148446c11ab05');

-- ----------------------------
-- Table structure for pub_menu
-- ----------------------------
DROP TABLE IF EXISTS `pub_menu`;
CREATE TABLE `pub_menu` (
  `id` varchar(40) NOT NULL,
  `alias` varchar(40) DEFAULT NULL,
  `component` varchar(100) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` varchar(1) NOT NULL DEFAULT '0',
  `icon` varchar(40) DEFAULT NULL,
  `name` varchar(40) DEFAULT NULL,
  `pid` varchar(40) DEFAULT NULL,
  `sort_index` int(2) NOT NULL DEFAULT '1',
  `update_date` datetime DEFAULT NULL,
  `tenant_id` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2smqjjqc8jgxu813l6umhg6s0` (`tenant_id`),
  CONSTRAINT `FK2smqjjqc8jgxu813l6umhg6s0` FOREIGN KEY (`tenant_id`) REFERENCES `pub_tenant` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pub_menu
-- ----------------------------
INSERT INTO `pub_menu` VALUES ('10', '修改密码', '', '2019-09-23 15:50:51', '0', '', '/userManagePwd/index', '8', '20', '2019-09-23 15:50:55', '000148446c11ab05');
INSERT INTO `pub_menu` VALUES ('11', '账户安全', null, '2020-01-15 11:58:32', '0', null, '/loginLogManage/index', '8', '15', '2020-01-15 11:59:36', '000148446c11ab05');
INSERT INTO `pub_menu` VALUES ('110', '修改密码', '', '2019-09-23 15:50:51', '0', '', '/userManagePwd/index', '18', '20', '2019-09-23 15:50:55', '000148446c11ac09');
INSERT INTO `pub_menu` VALUES ('111', '账户安全', null, '2020-01-15 11:58:32', '0', null, '/loginLogManage/index', '18', '15', '2020-01-15 11:59:36', '000148446c11ac09');
INSERT INTO `pub_menu` VALUES ('113', '用户组管理', '', '2019-09-23 15:56:13', '1', '', '/userGroupManage/index', '15', '30', '2019-09-23 15:56:23', '000148446c11ac09');
INSERT INTO `pub_menu` VALUES ('119', '缓存管理', '', '2019-09-23 15:56:13', '1', '', '/cacheManage/index', '15', '100', '2019-09-23 15:56:23', '000148446c11ac09');
INSERT INTO `pub_menu` VALUES ('129', '账号资料', '', '2019-09-23 15:50:51', '0', '', '/userManageOwner/index', '18', '10', '2019-09-23 15:50:55', '000148446c11ac09');
INSERT INTO `pub_menu` VALUES ('13', '用户组管理', '', '2019-09-23 15:56:13', '1', '', '/userGroupManage/index', '5', '30', '2019-09-23 15:56:23', '000148446c11ab05');
INSERT INTO `pub_menu` VALUES ('15', '系统管理', null, '2019-09-23 15:50:51', '0', 'el-icon-setting', '15', null, '50', '2019-09-23 15:50:55', '000148446c11ac09');
INSERT INTO `pub_menu` VALUES ('16', '用户管理', null, '2019-09-23 15:56:13', '0', null, '/userManage/index', '15', '20', '2019-09-23 15:56:23', '000148446c11ac09');
INSERT INTO `pub_menu` VALUES ('17', '组织管理', '', '2019-09-23 15:56:13', '1', '', '/orgManage/index', '15', '60', '2019-09-23 15:56:23', '000148446c11ac09');
INSERT INTO `pub_menu` VALUES ('18', '账号管理', '', '2019-09-23 15:50:51', '0', 'el-icon-user', '18', null, '60', '2019-09-23 15:50:55', '000148446c11ac09');
INSERT INTO `pub_menu` VALUES ('19', '缓存管理', '', '2019-09-23 15:56:13', '1', '', '/cacheManage/index', '5', '100', '2019-09-23 15:56:23', '000148446c11ab05');
INSERT INTO `pub_menu` VALUES ('5', '系统管理', null, '2019-09-23 15:50:51', '0', 'el-icon-setting', '5', null, '50', '2019-09-23 15:50:55', '000148446c11ab05');
INSERT INTO `pub_menu` VALUES ('6', '用户管理', null, '2019-09-23 15:56:13', '0', null, '/userManage/index', '5', '20', '2019-09-23 15:56:23', '000148446c11ab05');
INSERT INTO `pub_menu` VALUES ('7', '组织管理', '', '2019-09-23 15:56:13', '1', '', '/orgManage/index', '5', '60', '2019-09-23 15:56:23', '000148446c11ab05');
INSERT INTO `pub_menu` VALUES ('8', '账号管理', '', '2019-09-23 15:50:51', '0', 'el-icon-user', '8', null, '60', '2019-09-23 15:50:55', '000148446c11ab05');
INSERT INTO `pub_menu` VALUES ('9', '账号资料', '', '2019-09-23 15:50:51', '0', '', '/userManageOwner/index', '8', '10', '2019-09-23 15:50:55', '000148446c11ab05');

-- ----------------------------
-- Table structure for pub_tenant
-- ----------------------------
DROP TABLE IF EXISTS `pub_tenant`;
CREATE TABLE `pub_tenant` (
  `tenant_id` varchar(40) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `avatar_id` varchar(40) DEFAULT NULL,
  `code` varchar(40) NOT NULL,
  `contact` varchar(20) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` varchar(1) NOT NULL DEFAULT '0',
  `domain_name` varchar(64) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enter_code` varchar(40) NOT NULL,
  `enter_name` varchar(255) NOT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`tenant_id`),
  UNIQUE KEY `UK_6fresqxk42532d7bgd727q2ig` (`domain_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pub_tenant
-- ----------------------------
INSERT INTO `pub_tenant` VALUES ('000148446c11ab05', null, null, '46a474', '新企业', '2014-09-05 14:06:46', '0', 'cs.com', 'sk@a.com', '0192a9', '新企业', null, null, null, '2014-09-18 14:20:57');
INSERT INTO `pub_tenant` VALUES ('000148446c11ac09', null, null, '46a474', '小企业', '2014-09-05 14:06:46', '0', 'cs1.com', 'sk@a.com', '0192a9', '小企业', null, null, null, '2014-09-18 14:20:57');

-- ----------------------------
-- Table structure for pub_user
-- ----------------------------
DROP TABLE IF EXISTS `pub_user`;
CREATE TABLE `pub_user` (
  `user_id` varchar(40) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` varchar(1) NOT NULL DEFAULT '0',
  `update_date` datetime DEFAULT NULL,
  `account` varchar(50) DEFAULT NULL,
  `avatar_url` varchar(255) DEFAULT NULL,
  `gender` varchar(1) DEFAULT NULL,
  `name` varchar(40) DEFAULT NULL,
  `nick` varchar(20) DEFAULT NULL,
  `password` varchar(80) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `type` varchar(1) DEFAULT NULL,
  `tenant_id` varchar(40) NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `index_pub_user_account` (`account`),
  KEY `index_pub_user_phone` (`phone`),
  KEY `FKran5s30dfgjwvm7iw5r5i4bm3` (`tenant_id`),
  CONSTRAINT `FKran5s30dfgjwvm7iw5r5i4bm3` FOREIGN KEY (`tenant_id`) REFERENCES `pub_tenant` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pub_user
-- ----------------------------
INSERT INTO `pub_user` VALUES ('000148444c11ac00', '2020-04-21 20:22:24', '0', '2020-04-21 20:22:27', 'admin', null, '0', '管理员', null, '54c820a4c737cd635b83e3e42af94fb4d75405d38d376a15102cc007', '13800000000', '0', '000148446c11ac09');
INSERT INTO `pub_user` VALUES ('000148444c11ac09', '2020-04-21 20:22:24', '0', '2020-04-21 21:37:36', 'admin', '/platform/open/file/view/4660d17a-7e90-4621-ab2b-633277c7ea92', '1', '管理员', '', '0b0d1ea7dd8d2263a2360e84b3639d1beba85e9d129e68e9c9848aca', '13800000000', '0', '000148446c11ab05');

-- ----------------------------
-- Table structure for pub_user_group
-- ----------------------------
DROP TABLE IF EXISTS `pub_user_group`;
CREATE TABLE `pub_user_group` (
  `id` varchar(40) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` varchar(1) NOT NULL DEFAULT '0',
  `update_date` datetime DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `tenant_id` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKik5gp16flwvk72fot2nim6ebc` (`tenant_id`),
  CONSTRAINT `FKik5gp16flwvk72fot2nim6ebc` FOREIGN KEY (`tenant_id`) REFERENCES `pub_tenant` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pub_user_group
-- ----------------------------

-- ----------------------------
-- Table structure for pub_user_user_group
-- ----------------------------
DROP TABLE IF EXISTS `pub_user_user_group`;
CREATE TABLE `pub_user_user_group` (
  `id` varchar(80) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` varchar(1) NOT NULL DEFAULT '0',
  `update_date` datetime DEFAULT NULL,
  `tenant_id` varchar(40) NOT NULL,
  `user_id` varchar(40) DEFAULT NULL,
  `group_id` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKeqdtepy0ph8j1ybcjppgmk2p8` (`tenant_id`),
  KEY `FKio1rxnn16xr0gkd54bv9r1mko` (`user_id`),
  KEY `FKano1v3d177etkupwovnat156a` (`group_id`),
  CONSTRAINT `FKano1v3d177etkupwovnat156a` FOREIGN KEY (`group_id`) REFERENCES `pub_user_group` (`id`),
  CONSTRAINT `FKeqdtepy0ph8j1ybcjppgmk2p8` FOREIGN KEY (`tenant_id`) REFERENCES `pub_tenant` (`tenant_id`),
  CONSTRAINT `FKio1rxnn16xr0gkd54bv9r1mko` FOREIGN KEY (`user_id`) REFERENCES `pub_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pub_user_user_group
-- ----------------------------

SET FOREIGN_KEY_CHECKS=1;

