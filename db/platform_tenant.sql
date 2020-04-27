-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.12-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出  表 platform_tenant.pub_banner 结构
DROP TABLE IF EXISTS `pub_banner`;
CREATE TABLE IF NOT EXISTS `pub_banner` (
  `id` varchar(40) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` varchar(1) NOT NULL DEFAULT '0',
  `update_date` datetime DEFAULT NULL,
  `fid` varchar(40) DEFAULT NULL,
  `href` varchar(200) DEFAULT NULL,
  `sort_index` int(2) NOT NULL DEFAULT '1',
  `url` varchar(400) DEFAULT NULL,
  `tenant_id` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfiqmo5p3k7cm4cu5xr3cuemme` (`tenant_id`),
  CONSTRAINT `FKfiqmo5p3k7cm4cu5xr3cuemme` FOREIGN KEY (`tenant_id`) REFERENCES `pub_tenant` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  platform_tenant.pub_banner 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `pub_banner` DISABLE KEYS */;
/*!40000 ALTER TABLE `pub_banner` ENABLE KEYS */;


-- 导出  表 platform_tenant.pub_classify 结构
DROP TABLE IF EXISTS `pub_classify`;
CREATE TABLE IF NOT EXISTS `pub_classify` (
  `id` varchar(40) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` varchar(1) NOT NULL DEFAULT '0',
  `update_date` datetime DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `sort_index` int(2) NOT NULL DEFAULT '1',
  `url` varchar(400) DEFAULT NULL,
  `tenant_id` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqxxgbl7u2y93i498ppw71579x` (`tenant_id`),
  CONSTRAINT `FKqxxgbl7u2y93i498ppw71579x` FOREIGN KEY (`tenant_id`) REFERENCES `pub_tenant` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  platform_tenant.pub_classify 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `pub_classify` DISABLE KEYS */;
INSERT INTO `pub_classify` (`id`, `create_date`, `del_flag`, `update_date`, `name`, `sort_index`, `url`, `tenant_id`) VALUES
	('000171a4eaa53701', '2020-04-23 10:42:53', '0', '2020-04-23 10:45:27', '分类一3', 2, '/platform/open/file/view/a6db0abc-abe2-4bbe-9cd2-4e35493fb99e', '000148446c11ab05'),
	('000171a4ecd52801', '2020-04-23 10:45:17', '0', '2020-04-23 10:45:17', '分类二', 1, '/platform/open/file/view/fee52598-2e98-4856-812a-d9ebae247005', '000148446c11ab05'),
	('000171a4ed277502', '2020-04-23 10:45:38', '1', '2020-04-23 10:45:45', '2323', 1, '/platform/open/file/view/b0627f14-24a8-4aa6-a1b3-6b80133e1a56', '000148446c11ab05');
/*!40000 ALTER TABLE `pub_classify` ENABLE KEYS */;


-- 导出  表 platform_tenant.pub_classify_product 结构
DROP TABLE IF EXISTS `pub_classify_product`;
CREATE TABLE IF NOT EXISTS `pub_classify_product` (
  `id` varchar(40) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` varchar(1) NOT NULL DEFAULT '0',
  `update_date` datetime DEFAULT NULL,
  `tenant_id` varchar(40) NOT NULL,
  `classify_id` varchar(40) DEFAULT NULL,
  `product_id` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK91eby0ell98v4ujwpj54cgl95` (`tenant_id`),
  KEY `FKjfnnesc1j60ew8wqd0m38w860` (`classify_id`),
  KEY `FKi7bxy13yb85ua9d2ix61chwm4` (`product_id`),
  CONSTRAINT `FK91eby0ell98v4ujwpj54cgl95` FOREIGN KEY (`tenant_id`) REFERENCES `pub_tenant` (`tenant_id`),
  CONSTRAINT `FKi7bxy13yb85ua9d2ix61chwm4` FOREIGN KEY (`product_id`) REFERENCES `pub_product` (`id`),
  CONSTRAINT `FKjfnnesc1j60ew8wqd0m38w860` FOREIGN KEY (`classify_id`) REFERENCES `pub_classify` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  platform_tenant.pub_classify_product 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `pub_classify_product` DISABLE KEYS */;
INSERT INTO `pub_classify_product` (`id`, `create_date`, `del_flag`, `update_date`, `tenant_id`, `classify_id`, `product_id`) VALUES
	('000171a509bfb102', '2020-04-23 11:16:52', '1', '2020-04-23 11:16:52', '000148446c11ab05', '000171a4eaa53701', '000171a509bfa801'),
	('000171a509f5a403', '2020-04-23 11:17:06', '0', '2020-04-23 11:17:06', '000148446c11ab05', '000171a4eaa53701', '000171a509bfa801');
/*!40000 ALTER TABLE `pub_classify_product` ENABLE KEYS */;


-- 导出  表 platform_tenant.pub_config 结构
DROP TABLE IF EXISTS `pub_config`;
CREATE TABLE IF NOT EXISTS `pub_config` (
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

-- 导出  表 platform_tenant.pub_console_user 结构
DROP TABLE IF EXISTS `pub_console_user`;
CREATE TABLE IF NOT EXISTS `pub_console_user` (
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

-- 正在导出表  platform_tenant.pub_console_user 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `pub_console_user` DISABLE KEYS */;
INSERT INTO `pub_console_user` (`id`, `account`, `avatar_id`, `email`, `gender`, `last_login_date`, `name`, `password`, `phone`, `sort`) VALUES
	('00014a7a496bd41d', 'admin', NULL, NULL, '0', '2020-04-22 14:53:39', '管理员', '54c820a4c737cd635b83e3e42af94fb4d75405d38d376a15102cc007', '13800000000', 0);
/*!40000 ALTER TABLE `pub_console_user` ENABLE KEYS */;


-- 导出  表 platform_tenant.pub_login_log 结构
DROP TABLE IF EXISTS `pub_login_log`;
CREATE TABLE IF NOT EXISTS `pub_login_log` (
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



-- 导出  表 platform_tenant.pub_menu 结构
DROP TABLE IF EXISTS `pub_menu`;
CREATE TABLE IF NOT EXISTS `pub_menu` (
  `id` varchar(40) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` varchar(1) NOT NULL DEFAULT '0',
  `update_date` datetime DEFAULT NULL,
  `alias` varchar(40) DEFAULT NULL,
  `component` varchar(100) DEFAULT NULL,
  `icon` varchar(40) DEFAULT NULL,
  `name` varchar(40) DEFAULT NULL,
  `pid` varchar(40) DEFAULT NULL,
  `sort_index` int(11) NOT NULL DEFAULT '1',
  `tenant_id` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2smqjjqc8jgxu813l6umhg6s0` (`tenant_id`),
  CONSTRAINT `FK2smqjjqc8jgxu813l6umhg6s0` FOREIGN KEY (`tenant_id`) REFERENCES `pub_tenant` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  platform_tenant.pub_menu 的数据：~32 rows (大约)
/*!40000 ALTER TABLE `pub_menu` DISABLE KEYS */;
INSERT INTO `pub_menu` (`id`, `create_date`, `del_flag`, `update_date`, `alias`, `component`, `icon`, `name`, `pid`, `sort_index`, `tenant_id`) VALUES
	('10', '2019-09-23 15:50:51', '0', '2019-09-23 15:50:55', '修改密码', '', '', '/userManagePwd/index', '8', 20, '000148446c11ab05'),
	('103', '2019-09-23 15:56:13', '0', '2019-09-23 15:56:23', '用户组管理', '', '', '/userGroupManage/index', '5', 30, '000148446c11ab05'),
	('109', '2019-09-23 15:56:13', '0', '2019-09-23 15:56:23', '缓存管理', '', '', '/cacheManage/index', '5', 100, '000148446c11ab05'),
	('11', '2020-01-15 11:58:32', '0', '2020-01-15 11:59:36', '账户安全', NULL, NULL, '/loginLogManage/index', '8', 15, '000148446c11ab05'),
	('110', '2019-09-23 15:50:51', '0', '2019-09-23 15:50:55', '修改密码', '', '', '/userManagePwd/index', '18', 20, '000148446c11ac09'),
	('111', '2020-01-15 11:58:32', '0', '2020-01-15 11:59:36', '账户安全', NULL, NULL, '/loginLogManage/index', '18', 15, '000148446c11ac09'),
	('113', '2019-09-23 15:56:13', '1', '2019-09-23 15:56:23', '用户组管理', '', '', '/userGroupManage/index', '15', 30, '000148446c11ac09'),
	('115', '2019-11-04 20:39:13', '0', '2019-11-04 20:39:39', '宝贝管理', NULL, 'el-icon-s-shop', '15', NULL, 10, '000148446c11ab05'),
	('116', '2019-11-04 20:39:13', '0', '2019-11-04 20:39:39', '宝贝管理', NULL, 'el-icon-s-shop', '15', NULL, 10, '000148446c11ac09'),
	('119', '2019-09-23 15:56:13', '1', '2019-09-23 15:56:23', '缓存管理', '', '', '/cacheManage/index', '15', 100, '000148446c11ac09'),
	('123', '2019-09-23 15:56:13', '0', '2019-09-23 15:56:23', '用户组管理', '', '', '/userGroupManage/index', '15', 30, '000148446c11ac09'),
	('129', '2019-09-23 15:50:51', '0', '2019-09-23 15:50:55', '账号资料', '', '', '/userManageOwner/index', '18', 10, '000148446c11ac09'),
	('13', '2019-09-23 15:56:13', '1', '2019-09-23 15:56:23', '用户组管理', '', '', '/userGroupManage/index', '5', 30, '000148446c11ab05'),
	('139', '2019-09-23 15:56:13', '0', '2019-09-23 15:56:23', '缓存管理', '', '', '/cacheManage/index', '15', 100, '000148446c11ac09'),
	('15', '2019-09-23 15:50:51', '0', '2019-09-23 15:50:55', '系统管理', NULL, 'el-icon-setting', '15', NULL, 50, '000148446c11ac09'),
	('16', '2019-09-23 15:56:13', '0', '2019-09-23 15:56:23', '用户管理', NULL, NULL, '/userManage/index', '15', 20, '000148446c11ac09'),
	('17', '2019-09-23 15:56:13', '1', '2019-09-23 15:56:23', '组织管理', '', '', '/orgManage/index', '15', 60, '000148446c11ac09'),
	('18', '2019-09-23 15:50:51', '0', '2019-09-23 15:50:55', '账号管理', '', 'el-icon-user', '18', NULL, 60, '000148446c11ac09'),
	('19', '2019-09-23 15:56:13', '1', '2019-09-23 15:56:23', '缓存管理', '', '', '/cacheManage/index', '5', 100, '000148446c11ab05'),
	('201', '2019-09-23 15:56:13', '0', '2019-09-23 15:56:23', '参数配置', '', '', '/configManage/index', '5', 110, '000148446c11ab05'),
	('221', '2019-09-23 15:56:13', '0', '2019-09-23 15:56:23', '参数配置', '', '', '/configManage/index', '15', 110, '000148446c11ac09'),
	('5', '2019-09-23 15:50:51', '0', '2019-09-23 15:50:55', '系统管理', NULL, 'el-icon-setting', '5', NULL, 50, '000148446c11ab05'),
	('56', '2019-11-04 20:41:12', '0', '2019-11-04 20:41:30', '商品管理', NULL, NULL, '/productManage/index', '115', 30, '000148446c11ab05'),
	('57', '2019-11-04 20:41:12', '0', '2019-11-04 20:41:30', '商品管理', NULL, NULL, '/productManage/index', '116', 30, '000148446c11ac09'),
	('6', '2019-09-23 15:56:13', '0', '2019-09-23 15:56:23', '用户管理', NULL, NULL, '/userManage/index', '5', 20, '000148446c11ab05'),
	('7', '2019-09-23 15:56:13', '1', '2019-09-23 15:56:23', '组织管理', '', '', '/orgManage/index', '5', 60, '000148446c11ab05'),
	('77', '2019-11-04 20:41:49', '0', '2019-11-04 20:41:59', '分类管理', NULL, NULL, '/typeManage/index', '116', 20, '000148446c11ac09'),
	('78', '2019-11-04 20:41:49', '0', '2019-11-04 20:41:59', '分类管理', NULL, NULL, '/typeManage/index', '115', 20, '000148446c11ab05'),
	('8', '2019-09-23 15:50:51', '0', '2019-09-23 15:50:55', '账号管理', '', 'el-icon-user', '8', NULL, 60, '000148446c11ab05'),
	('88', '2019-11-04 20:47:03', '0', '2019-11-04 20:47:10', 'Banner管理', NULL, 'el-icon-postcard', '/bannerManage/index', NULL, 1, '000148446c11ac09'),
	('9', '2019-09-23 15:50:51', '0', '2019-09-23 15:50:55', '账号资料', '', '', '/userManageOwner/index', '8', 10, '000148446c11ab05'),
	('98', '2019-11-04 20:47:03', '0', '2019-11-04 20:47:10', 'Banner管理', NULL, 'el-icon-postcard', '/bannerManage/index', NULL, 1, '000148446c11ab05');
/*!40000 ALTER TABLE `pub_menu` ENABLE KEYS */;


-- 导出  表 platform_tenant.pub_product 结构
DROP TABLE IF EXISTS `pub_product`;
CREATE TABLE IF NOT EXISTS `pub_product` (
  `id` varchar(40) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `del_flag` varchar(1) NOT NULL DEFAULT '0',
  `update_date` datetime DEFAULT NULL,
  `content` text,
  `content_html` text,
  `cover` varchar(200) DEFAULT NULL,
  `img_src1` varchar(200) DEFAULT NULL,
  `img_src2` varchar(200) DEFAULT NULL,
  `img_src3` varchar(200) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `price` float(8,2) DEFAULT NULL,
  `state` int(1) NOT NULL DEFAULT '0',
  `stock` int(6) NOT NULL DEFAULT '0',
  `tenant_id` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKevvow56s61h97x9g6qah91mvb` (`tenant_id`),
  CONSTRAINT `FKevvow56s61h97x9g6qah91mvb` FOREIGN KEY (`tenant_id`) REFERENCES `pub_tenant` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  platform_tenant.pub_product 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `pub_product` DISABLE KEYS */;
INSERT INTO `pub_product` (`id`, `create_date`, `del_flag`, `update_date`, `content`, `content_html`, `cover`, `img_src1`, `img_src2`, `img_src3`, `name`, `price`, `state`, `stock`, `tenant_id`) VALUES
	('000171a509bfa801', '2020-04-23 11:16:52', '0', '2020-04-23 11:17:06', NULL, '', '/platform/open/file/view/1232d2d4-5b07-4dcf-9658-749e41218596', '/platform/open/file/view/a1f7e7f2-71fc-4bc0-b515-d89547818742', '/platform/open/file/view/8ae17ecd-c09f-4f8f-9650-61bcac8bf7b6', '/platform/open/file/view/7940f90f-70a3-4bce-b241-e0824b7bc608', '宝贝一', 1.00, 0, 4, '000148446c11ab05');
/*!40000 ALTER TABLE `pub_product` ENABLE KEYS */;


-- 导出  表 platform_tenant.pub_tenant 结构
DROP TABLE IF EXISTS `pub_tenant`;
CREATE TABLE IF NOT EXISTS `pub_tenant` (
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

-- 正在导出表  platform_tenant.pub_tenant 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `pub_tenant` DISABLE KEYS */;
INSERT INTO `pub_tenant` (`tenant_id`, `address`, `avatar_id`, `code`, `contact`, `create_date`, `del_flag`, `domain_name`, `email`, `enter_code`, `enter_name`, `fax`, `mobile`, `tel`, `update_date`) VALUES
	('000148446c11ab05', NULL, NULL, '46a474', '新企业', '2014-09-05 14:06:46', '0', 'cs.com', 'sk@a.com', '0192a9', '新企业', NULL, NULL, NULL, '2014-09-18 14:20:57'),
	('000148446c11ac09', NULL, NULL, '46a474', '小企业', '2014-09-05 14:06:46', '0', 'cs1.com', 'sk@a.com', '0192a9', '小企业', NULL, NULL, NULL, '2014-09-18 14:20:57');
/*!40000 ALTER TABLE `pub_tenant` ENABLE KEYS */;


-- 导出  表 platform_tenant.pub_user 结构
DROP TABLE IF EXISTS `pub_user`;
CREATE TABLE IF NOT EXISTS `pub_user` (
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

-- 正在导出表  platform_tenant.pub_user 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `pub_user` DISABLE KEYS */;
INSERT INTO `pub_user` (`user_id`, `create_date`, `del_flag`, `update_date`, `account`, `avatar_url`, `gender`, `name`, `nick`, `password`, `phone`, `type`, `tenant_id`) VALUES
	('000148444c11ac00', '2020-04-21 20:22:24', '0', '2020-04-21 20:22:27', 'admin', NULL, '0', '管理员', NULL, '54c820a4c737cd635b83e3e42af94fb4d75405d38d376a15102cc007', '13800000000', '0', '000148446c11ac09'),
	('000148444c11ac09', '2020-04-21 20:22:24', '0', '2020-04-21 21:37:36', 'admin', '', '1', '管理员', '', '0b0d1ea7dd8d2263a2360e84b3639d1beba85e9d129e68e9c9848aca', '13800000000', '0', '000148446c11ab05'),
	('000171a084777901', '2020-04-22 14:12:48', '0', '2020-04-22 14:12:48', 'test', NULL, '0', '测试', '', 'fdacce0ebfdc68cd8b5d8647f251e3be16c07855047ff2fce89444b2', '13800000001', '1', '000148446c11ab05');
/*!40000 ALTER TABLE `pub_user` ENABLE KEYS */;


-- 导出  表 platform_tenant.pub_user_group 结构
DROP TABLE IF EXISTS `pub_user_group`;
CREATE TABLE IF NOT EXISTS `pub_user_group` (
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

-- 正在导出表  platform_tenant.pub_user_group 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `pub_user_group` DISABLE KEYS */;
INSERT INTO `pub_user_group` (`id`, `create_date`, `del_flag`, `update_date`, `name`, `tenant_id`) VALUES
	('000171a07321f703', '2020-04-22 13:53:52', '0', '2020-04-22 13:53:56', '组一', '000148446c11ab05'),
	('000171a073ccba11', '2020-04-22 13:54:36', '0', '2020-04-22 13:54:36', '组二', '000148446c11ab05');
/*!40000 ALTER TABLE `pub_user_group` ENABLE KEYS */;


-- 导出  表 platform_tenant.pub_user_group_menu 结构
DROP TABLE IF EXISTS `pub_user_group_menu`;
CREATE TABLE IF NOT EXISTS `pub_user_group_menu` (
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

-- 正在导出表  platform_tenant.pub_user_group_menu 的数据：~10 rows (大约)
/*!40000 ALTER TABLE `pub_user_group_menu` DISABLE KEYS */;
INSERT INTO `pub_user_group_menu` (`id`, `create_date`, `del_flag`, `update_date`, `half`, `tenant_id`, `menu_id`, `gorup_id`) VALUES
	('000171a07331e20a', '2020-04-22 13:53:56', '0', '2020-04-22 13:53:56', b'1', '000148446c11ab05', '6', '000171a07321f703'),
	('000171a07331e20b', '2020-04-22 13:53:56', '0', '2020-04-22 13:53:56', b'1', '000148446c11ab05', '8', '000171a07321f703'),
	('000171a07331e20c', '2020-04-22 13:53:56', '0', '2020-04-22 13:53:56', b'1', '000148446c11ab05', '9', '000171a07321f703'),
	('000171a07331e20d', '2020-04-22 13:53:56', '0', '2020-04-22 13:53:56', b'1', '000148446c11ab05', '11', '000171a07321f703'),
	('000171a07331e20e', '2020-04-22 13:53:56', '0', '2020-04-22 13:53:56', b'1', '000148446c11ab05', '10', '000171a07321f703'),
	('000171a07331e30f', '2020-04-22 13:53:56', '0', '2020-04-22 13:53:56', b'0', '000148446c11ab05', '5', '000171a07321f703'),
	('000171a073ccbb12', '2020-04-22 13:54:36', '0', '2020-04-22 13:54:36', b'1', '000148446c11ab05', '8', '000171a073ccba11'),
	('000171a073ccbb13', '2020-04-22 13:54:36', '0', '2020-04-22 13:54:36', b'1', '000148446c11ab05', '9', '000171a073ccba11'),
	('000171a073ccbb14', '2020-04-22 13:54:36', '0', '2020-04-22 13:54:36', b'1', '000148446c11ab05', '11', '000171a073ccba11'),
	('000171a073ccbb15', '2020-04-22 13:54:36', '0', '2020-04-22 13:54:36', b'1', '000148446c11ab05', '10', '000171a073ccba11');
/*!40000 ALTER TABLE `pub_user_group_menu` ENABLE KEYS */;


-- 导出  表 platform_tenant.pub_user_user_group 结构
DROP TABLE IF EXISTS `pub_user_user_group`;
CREATE TABLE IF NOT EXISTS `pub_user_user_group` (
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

-- 正在导出表  platform_tenant.pub_user_user_group 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `pub_user_user_group` DISABLE KEYS */;
INSERT INTO `pub_user_user_group` (`id`, `create_date`, `del_flag`, `update_date`, `tenant_id`, `user_id`, `group_id`) VALUES
	('000171a084778102', '2020-04-22 14:12:48', '0', '2020-04-22 14:12:48', '000148446c11ab05', '000171a084777901', '000171a07321f703'),
	('000171a084778103', '2020-04-22 14:12:48', '0', '2020-04-22 14:12:48', '000148446c11ab05', '000171a084777901', '000171a073ccba11');
/*!40000 ALTER TABLE `pub_user_user_group` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
