/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80019
Source Host           : localhost:3306
Source Database       : platform_tenant

Target Server Type    : MYSQL
Target Server Version : 80019
File Encoding         : 65001

Date: 2020-04-22 15:27:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pub_config
-- ----------------------------
DROP TABLE IF EXISTS `pub_config`;
CREATE TABLE `pub_config` (
  `id` varchar(40) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` varchar(1) NOT NULL DEFAULT '0',
  `update_date` datetime DEFAULT NULL,
  `ckey` varchar(255) DEFAULT NULL,
  `cval` varchar(400) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `type` varchar(1) NOT NULL DEFAULT '1',
  `tenant_id` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq94kelbsmeas3xanamc1bj29q` (`tenant_id`),
  CONSTRAINT `FKq94kelbsmeas3xanamc1bj29q` FOREIGN KEY (`tenant_id`) REFERENCES `pub_tenant` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pub_config
-- ----------------------------
INSERT INTO `pub_config` VALUES ('000171a017a6b202', '2020-04-22 12:13:57', '0', '2020-04-22 12:14:18', 'check_max_ceiling', '50', '号码检测每次最多个数', '1', '000148446c11ab05');
INSERT INTO `pub_config` VALUES ('000171a017a6b203', '2020-04-22 12:13:57', '0', '2020-04-22 12:13:57', 'mongodb_max_ceiling', '6', '号码库每次查询的最大个数', '1', '000148446c11ab05');
INSERT INTO `pub_config` VALUES ('000171a018891e05', '2020-04-22 12:14:55', '0', '2020-04-22 12:14:55', 'check_max_ceiling', '30', '号码检测每次最多个数', '1', '000148446c11ac09');
INSERT INTO `pub_config` VALUES ('000171a018891f06', '2020-04-22 12:14:55', '0', '2020-04-22 12:14:55', 'mongodb_max_ceiling', '10', '号码库每次查询的最大个数', '1', '000148446c11ac09');

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
  `sort` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pub_console_user
-- ----------------------------
INSERT INTO `pub_console_user` VALUES ('00014a7a496bd41d', 'admin', null, null, '0', '2020-04-22 14:53:39', '管理员', '54c820a4c737cd635b83e3e42af94fb4d75405d38d376a15102cc007', '13800000000', '0');

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
INSERT INTO `pub_login_log` VALUES ('000171a0b1327200', '2020-04-22 15:01:40', '0', '2020-04-22 15:01:40', null, '127.0.0.1', '000171a084777901', '000148446c11ab05');
INSERT INTO `pub_login_log` VALUES ('000171a0b2b53e01', '2020-04-22 15:03:19', '0', '2020-04-22 15:03:19', null, '127.0.0.1', '000171a084777901', '000148446c11ab05');
INSERT INTO `pub_login_log` VALUES ('000171a0b46fac02', '2020-04-22 15:05:12', '0', '2020-04-22 15:05:12', null, '127.0.0.1', '000148444c11ac09', '000148446c11ab05');
INSERT INTO `pub_login_log` VALUES ('000171a0b6c60b00', '2020-04-22 15:07:45', '0', '2020-04-22 15:07:45', null, '127.0.0.1', '000171a084777901', '000148446c11ab05');
INSERT INTO `pub_login_log` VALUES ('000171a0c38b0900', '2020-04-22 15:21:42', '0', '2020-04-22 15:21:42', '本地登录', '127.0.0.1', '000148444c11ac09', '000148446c11ab05');

-- ----------------------------
-- Table structure for pub_menu
-- ----------------------------
DROP TABLE IF EXISTS `pub_menu`;
CREATE TABLE `pub_menu` (
  `id` varchar(40) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` varchar(1) NOT NULL DEFAULT '0',
  `update_date` datetime DEFAULT NULL,
  `alias` varchar(40) DEFAULT NULL,
  `component` varchar(100) DEFAULT NULL,
  `icon` varchar(40) DEFAULT NULL,
  `name` varchar(40) DEFAULT NULL,
  `pid` varchar(40) DEFAULT NULL,
  `sort_index` int NOT NULL DEFAULT '1',
  `tenant_id` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2smqjjqc8jgxu813l6umhg6s0` (`tenant_id`),
  CONSTRAINT `FK2smqjjqc8jgxu813l6umhg6s0` FOREIGN KEY (`tenant_id`) REFERENCES `pub_tenant` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pub_menu
-- ----------------------------
INSERT INTO `pub_menu` VALUES ('10', '2019-09-23 15:50:51', '0', '2019-09-23 15:50:55', '修改密码', '', '', '/userManagePwd/index', '8', '20', '000148446c11ab05');
INSERT INTO `pub_menu` VALUES ('103', '2019-09-23 15:56:13', '0', '2019-09-23 15:56:23', '用户组管理', '', '', '/userGroupManage/index', '5', '30', '000148446c11ab05');
INSERT INTO `pub_menu` VALUES ('109', '2019-09-23 15:56:13', '0', '2019-09-23 15:56:23', '缓存管理', '', '', '/cacheManage/index', '5', '100', '000148446c11ab05');
INSERT INTO `pub_menu` VALUES ('11', '2020-01-15 11:58:32', '0', '2020-01-15 11:59:36', '账户安全', null, null, '/loginLogManage/index', '8', '15', '000148446c11ab05');
INSERT INTO `pub_menu` VALUES ('110', '2019-09-23 15:50:51', '0', '2019-09-23 15:50:55', '修改密码', '', '', '/userManagePwd/index', '18', '20', '000148446c11ac09');
INSERT INTO `pub_menu` VALUES ('111', '2020-01-15 11:58:32', '0', '2020-01-15 11:59:36', '账户安全', null, null, '/loginLogManage/index', '18', '15', '000148446c11ac09');
INSERT INTO `pub_menu` VALUES ('113', '2019-09-23 15:56:13', '1', '2019-09-23 15:56:23', '用户组管理', '', '', '/userGroupManage/index', '15', '30', '000148446c11ac09');
INSERT INTO `pub_menu` VALUES ('119', '2019-09-23 15:56:13', '1', '2019-09-23 15:56:23', '缓存管理', '', '', '/cacheManage/index', '15', '100', '000148446c11ac09');
INSERT INTO `pub_menu` VALUES ('123', '2019-09-23 15:56:13', '0', '2019-09-23 15:56:23', '用户组管理', '', '', '/userGroupManage/index', '15', '30', '000148446c11ac09');
INSERT INTO `pub_menu` VALUES ('129', '2019-09-23 15:50:51', '0', '2019-09-23 15:50:55', '账号资料', '', '', '/userManageOwner/index', '18', '10', '000148446c11ac09');
INSERT INTO `pub_menu` VALUES ('13', '2019-09-23 15:56:13', '1', '2019-09-23 15:56:23', '用户组管理', '', '', '/userGroupManage/index', '5', '30', '000148446c11ab05');
INSERT INTO `pub_menu` VALUES ('139', '2019-09-23 15:56:13', '0', '2019-09-23 15:56:23', '缓存管理', '', '', '/cacheManage/index', '15', '100', '000148446c11ac09');
INSERT INTO `pub_menu` VALUES ('15', '2019-09-23 15:50:51', '0', '2019-09-23 15:50:55', '系统管理', null, 'el-icon-setting', '15', null, '50', '000148446c11ac09');
INSERT INTO `pub_menu` VALUES ('16', '2019-09-23 15:56:13', '0', '2019-09-23 15:56:23', '用户管理', null, null, '/userManage/index', '15', '20', '000148446c11ac09');
INSERT INTO `pub_menu` VALUES ('17', '2019-09-23 15:56:13', '1', '2019-09-23 15:56:23', '组织管理', '', '', '/orgManage/index', '15', '60', '000148446c11ac09');
INSERT INTO `pub_menu` VALUES ('18', '2019-09-23 15:50:51', '0', '2019-09-23 15:50:55', '账号管理', '', 'el-icon-user', '18', null, '60', '000148446c11ac09');
INSERT INTO `pub_menu` VALUES ('19', '2019-09-23 15:56:13', '1', '2019-09-23 15:56:23', '缓存管理', '', '', '/cacheManage/index', '5', '100', '000148446c11ab05');
INSERT INTO `pub_menu` VALUES ('201', '2019-09-23 15:56:13', '0', '2019-09-23 15:56:23', '参数配置', '', '', '/configManage/index', '5', '110', '000148446c11ab05');
INSERT INTO `pub_menu` VALUES ('221', '2019-09-23 15:56:13', '0', '2019-09-23 15:56:23', '参数配置', '', '', '/configManage/index', '15', '110', '000148446c11ac09');
INSERT INTO `pub_menu` VALUES ('5', '2019-09-23 15:50:51', '0', '2019-09-23 15:50:55', '系统管理', null, 'el-icon-setting', '5', null, '50', '000148446c11ab05');
INSERT INTO `pub_menu` VALUES ('6', '2019-09-23 15:56:13', '0', '2019-09-23 15:56:23', '用户管理', null, null, '/userManage/index', '5', '20', '000148446c11ab05');
INSERT INTO `pub_menu` VALUES ('7', '2019-09-23 15:56:13', '1', '2019-09-23 15:56:23', '组织管理', '', '', '/orgManage/index', '5', '60', '000148446c11ab05');
INSERT INTO `pub_menu` VALUES ('8', '2019-09-23 15:50:51', '0', '2019-09-23 15:50:55', '账号管理', '', 'el-icon-user', '8', null, '60', '000148446c11ab05');
INSERT INTO `pub_menu` VALUES ('9', '2019-09-23 15:50:51', '0', '2019-09-23 15:50:55', '账号资料', '', '', '/userManageOwner/index', '8', '10', '000148446c11ab05');

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
INSERT INTO `pub_user` VALUES ('000148444c11ac09', '2020-04-21 20:22:24', '0', '2020-04-21 21:37:36', 'admin', '', '1', '管理员', '', '0b0d1ea7dd8d2263a2360e84b3639d1beba85e9d129e68e9c9848aca', '13800000000', '0', '000148446c11ab05');
INSERT INTO `pub_user` VALUES ('000171a084777901', '2020-04-22 14:12:48', '0', '2020-04-22 14:12:48', 'test', null, '0', '测试', '', 'fdacce0ebfdc68cd8b5d8647f251e3be16c07855047ff2fce89444b2', '13800000001', '1', '000148446c11ab05');

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
INSERT INTO `pub_user_group` VALUES ('000171a07321f703', '2020-04-22 13:53:52', '0', '2020-04-22 13:53:56', '组一', '000148446c11ab05');
INSERT INTO `pub_user_group` VALUES ('000171a073ccba11', '2020-04-22 13:54:36', '0', '2020-04-22 13:54:36', '组二', '000148446c11ab05');

-- ----------------------------
-- Table structure for pub_user_group_menu
-- ----------------------------
DROP TABLE IF EXISTS `pub_user_group_menu`;
CREATE TABLE `pub_user_group_menu` (
  `id` varchar(40) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` varchar(1) NOT NULL DEFAULT '0',
  `update_date` datetime DEFAULT NULL,
  `half` bit(1) NOT NULL,
  `tenant_id` varchar(40) NOT NULL,
  `menu_id` varchar(40) DEFAULT NULL,
  `gorup_id` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5w58vdcpp07129u234msotn3j` (`tenant_id`),
  KEY `FKarjqqu221mm9piabs7ptclwnd` (`menu_id`),
  KEY `FKplakd6sa9134w9vysefwnpmme` (`gorup_id`),
  CONSTRAINT `FK5w58vdcpp07129u234msotn3j` FOREIGN KEY (`tenant_id`) REFERENCES `pub_tenant` (`tenant_id`),
  CONSTRAINT `FKarjqqu221mm9piabs7ptclwnd` FOREIGN KEY (`menu_id`) REFERENCES `pub_menu` (`id`),
  CONSTRAINT `FKplakd6sa9134w9vysefwnpmme` FOREIGN KEY (`gorup_id`) REFERENCES `pub_user_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pub_user_group_menu
-- ----------------------------
INSERT INTO `pub_user_group_menu` VALUES ('000171a07331e20a', '2020-04-22 13:53:56', '0', '2020-04-22 13:53:56', '', '000148446c11ab05', '6', '000171a07321f703');
INSERT INTO `pub_user_group_menu` VALUES ('000171a07331e20b', '2020-04-22 13:53:56', '0', '2020-04-22 13:53:56', '', '000148446c11ab05', '8', '000171a07321f703');
INSERT INTO `pub_user_group_menu` VALUES ('000171a07331e20c', '2020-04-22 13:53:56', '0', '2020-04-22 13:53:56', '', '000148446c11ab05', '9', '000171a07321f703');
INSERT INTO `pub_user_group_menu` VALUES ('000171a07331e20d', '2020-04-22 13:53:56', '0', '2020-04-22 13:53:56', '', '000148446c11ab05', '11', '000171a07321f703');
INSERT INTO `pub_user_group_menu` VALUES ('000171a07331e20e', '2020-04-22 13:53:56', '0', '2020-04-22 13:53:56', '', '000148446c11ab05', '10', '000171a07321f703');
INSERT INTO `pub_user_group_menu` VALUES ('000171a07331e30f', '2020-04-22 13:53:56', '0', '2020-04-22 13:53:56', '\0', '000148446c11ab05', '5', '000171a07321f703');
INSERT INTO `pub_user_group_menu` VALUES ('000171a073ccbb12', '2020-04-22 13:54:36', '0', '2020-04-22 13:54:36', '', '000148446c11ab05', '8', '000171a073ccba11');
INSERT INTO `pub_user_group_menu` VALUES ('000171a073ccbb13', '2020-04-22 13:54:36', '0', '2020-04-22 13:54:36', '', '000148446c11ab05', '9', '000171a073ccba11');
INSERT INTO `pub_user_group_menu` VALUES ('000171a073ccbb14', '2020-04-22 13:54:36', '0', '2020-04-22 13:54:36', '', '000148446c11ab05', '11', '000171a073ccba11');
INSERT INTO `pub_user_group_menu` VALUES ('000171a073ccbb15', '2020-04-22 13:54:36', '0', '2020-04-22 13:54:36', '', '000148446c11ab05', '10', '000171a073ccba11');

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
INSERT INTO `pub_user_user_group` VALUES ('000171a084778102', '2020-04-22 14:12:48', '0', '2020-04-22 14:12:48', '000148446c11ab05', '000171a084777901', '000171a07321f703');
INSERT INTO `pub_user_user_group` VALUES ('000171a084778103', '2020-04-22 14:12:48', '0', '2020-04-22 14:12:48', '000148446c11ab05', '000171a084777901', '000171a073ccba11');
