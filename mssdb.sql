/*
SQLyog  v12.2.6 (64 bit)
MySQL - 5.7.22 : Database - mssdb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mssdb` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `mssdb`;

/*Table structure for table `base_dict` */

DROP TABLE IF EXISTS `base_dict`;

CREATE TABLE `base_dict` (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dict_code` varchar(32) NOT NULL COMMENT '字典编码',
  `dict_name` varchar(32) DEFAULT NULL COMMENT '字典名称',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  `notes` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL,
  `create_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`dict_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典';

/*Table structure for table `base_dict_item` */

DROP TABLE IF EXISTS `base_dict_item`;

CREATE TABLE `base_dict_item` (
  `item_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item_key` varchar(100) NOT NULL,
  `item_name` varchar(100) NOT NULL,
  `dict_code` varchar(32) DEFAULT NULL,
  `state` int(11) DEFAULT NULL COMMENT '状态',
  `seq_no` int(11) DEFAULT NULL COMMENT '顺序',
  `create_time` datetime DEFAULT NULL,
  `create_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典项';

/*Table structure for table `base_member` */

DROP TABLE IF EXISTS `base_member`;

CREATE TABLE `base_member` (
  `member_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `member_name` varchar(32) NOT NULL COMMENT '会员姓名',
  `member_phone` varchar(11) DEFAULT NULL COMMENT '会员手机号码',
  `nickname` varchar(32) DEFAULT NULL COMMENT '昵称',
  `idcard_no` varchar(18) DEFAULT NULL COMMENT '会员身份证号',
  `sex` int(11) DEFAULT NULL COMMENT '性别，1-男，2-女',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `leval` int(11) DEFAULT NULL COMMENT '级别',
  `avatar` varchar(100) DEFAULT NULL COMMENT '头像图片URL',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  `source` int(11) DEFAULT NULL COMMENT '来源',
  `notes` varchar(200) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员基本信息';

/*Table structure for table `base_member_address` */

DROP TABLE IF EXISTS `base_member_address`;

CREATE TABLE `base_member_address` (
  `address_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) NOT NULL COMMENT '会员',
  `contacts` varchar(32) DEFAULT NULL COMMENT '联系人',
  `contacts_phone` varchar(15) DEFAULT NULL COMMENT '联系人电话',
  `province` bigint(20) DEFAULT NULL COMMENT '省',
  `city` bigint(20) DEFAULT NULL COMMENT '市',
  `county` bigint(20) DEFAULT NULL COMMENT '区县',
  `street` bigint(20) DEFAULT NULL COMMENT '街道乡镇',
  `address` varchar(100) DEFAULT NULL COMMENT '详细地址',
  `all_address` varchar(200) DEFAULT NULL COMMENT '包含省市区县街道的全部地址',
  `postcode` varchar(6) DEFAULT NULL COMMENT '邮编',
  `is_default` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否默认,0-否，1-是',
  `notes` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL,
  `create_user` bigint(20) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `base_member_open` */

DROP TABLE IF EXISTS `base_member_open`;

CREATE TABLE `base_member_open` (
  `pid` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) NOT NULL COMMENT '会员',
  `open_id` varchar(64) DEFAULT NULL COMMENT '三方OPENID',
  `device_id` varchar(15) DEFAULT NULL COMMENT '设备id',
  `state` int(1) DEFAULT NULL COMMENT '状态',
  `create_time` datetime NOT NULL,
  `create_user` bigint(20) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `base_permission_resource` */

DROP TABLE IF EXISTS `base_permission_resource`;

CREATE TABLE `base_permission_resource` (
  `res_id` bigint(20) NOT NULL,
  `res_code` varchar(32) NOT NULL COMMENT '编码',
  `res_name` varchar(100) NOT NULL,
  `res_type` int(11) NOT NULL COMMENT '权限资源类型，1-菜单，2-页面URL，3-按钮，4-逻辑权限，5-app',
  `parent_id` bigint(20) NOT NULL COMMENT '父节点',
  `res_icon` varchar(100) DEFAULT NULL COMMENT '权限图标',
  `entry_path` varchar(200) DEFAULT NULL COMMENT '入口路径',
  `seq_no` int(11) DEFAULT NULL COMMENT '排序的顺序号',
  `state` int(11) DEFAULT NULL COMMENT '状态，0-未启用，1-已启用，2-已停用',
  `is_show` tinyint(4) DEFAULT NULL COMMENT '是否页面展示，针对菜单等需要展示使用',
  `node_type` int(11) DEFAULT NULL COMMENT '节点类型，1-目录节点，2-叶子节点',
  `notes` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL,
  `create_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`res_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限资源表，包括菜单，按钮等等资源';

/*Table structure for table `base_permission_role` */

DROP TABLE IF EXISTS `base_permission_role`;

CREATE TABLE `base_permission_role` (
  `pid` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `res_id` bigint(20) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='权限角色关联关系表';

/*Table structure for table `base_role` */

DROP TABLE IF EXISTS `base_role`;

CREATE TABLE `base_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(32) NOT NULL COMMENT '角色编码',
  `parent_id` bigint(11) DEFAULT NULL COMMENT '上级角色',
  `role_name` varchar(32) NOT NULL COMMENT '角色名称',
  `role_desc` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `state` int(11) NOT NULL COMMENT '角色状态',
  `create_time` datetime DEFAULT NULL,
  `create_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `base_store` */

DROP TABLE IF EXISTS `base_store`;

CREATE TABLE `base_store` (
  `store_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `store_name` varchar(30) NOT NULL,
  `cust_id` bigint(20) NOT NULL COMMENT '店铺归属客户',
  `contacts` varchar(32) DEFAULT NULL COMMENT '联系人',
  `contacts_phone` varchar(20) DEFAULT NULL COMMENT '联系人电话，可是手机号码也可以是固定电话',
  `province` int(11) DEFAULT NULL COMMENT '省份',
  `city` int(11) DEFAULT NULL COMMENT '城市',
  `county` int(11) DEFAULT NULL COMMENT '区县',
  `address` varchar(100) DEFAULT NULL,
  `all_address` varchar(200) DEFAULT NULL,
  `longitude` double DEFAULT NULL COMMENT '经度',
  `latitude` double DEFAULT NULL COMMENT '维度',
  `head_image` varchar(200) DEFAULT NULL COMMENT '商店门头照',
  `store_image` varchar(1000) DEFAULT NULL COMMENT '商店图片,最多5张',
  `open_begin_time` time DEFAULT NULL COMMENT '起始营业时间',
  `open_end_time` time DEFAULT NULL COMMENT '结束营业时间',
  `seq_no` int(6) DEFAULT NULL,
  `state` int(1) NOT NULL COMMENT '状态，0-关闭，1-启用',
  `store_desc` text COMMENT '店铺图文简介,html编辑器完成',
  `store_aptitude` int(1) DEFAULT '0' COMMENT '店铺资质 0没有资质 1有资质',
  `check_state` int(1) NOT NULL DEFAULT '1' COMMENT '审核状态，1-未审核，2-审核通过，3-审核不通过',
  `idcard_no` varchar(20) DEFAULT NULL COMMENT '店主身份证号',
  `idcard_image` varchar(200) DEFAULT NULL COMMENT '身份证号码照片',
  `store_license` varchar(20) DEFAULT NULL COMMENT '营业执照号码',
  `license_image` varchar(100) DEFAULT NULL COMMENT '营业执照照片',
  `create_user` bigint(20) NOT NULL COMMENT '信息创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '信息修改人',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`store_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='店铺的基础信息';

/*Table structure for table `base_store_detail` */

DROP TABLE IF EXISTS `base_store_detail`;

CREATE TABLE `base_store_detail` (
  `pid` bigint(20) NOT NULL AUTO_INCREMENT,
  `store_id` bigint(20) NOT NULL COMMENT '店铺ID',
  `detail` text COMMENT '详情',
  `create_time` datetime DEFAULT NULL,
  `create_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `base_user` */

DROP TABLE IF EXISTS `base_user`;

CREATE TABLE `base_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `login_type` int(4) NOT NULL DEFAULT '0' COMMENT '登录类型，0-都可以，1-手机号码，2-用户名，3-邮箱',
  `user_phone` varchar(11) DEFAULT NULL,
  `user_name` varchar(32) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `nickname` varchar(32) DEFAULT NULL COMMENT '昵称',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `effective_date` date NOT NULL COMMENT '生效日期',
  `expire_date` date NOT NULL COMMENT '失效日期',
  `reg_channel` int(4) DEFAULT NULL COMMENT '注册渠道',
  `last_login` datetime DEFAULT NULL COMMENT '最后登录时间',
  `state` int(4) DEFAULT NULL COMMENT '启停状态，0-未启用，1已启用，2已停用',
  `lock_state` tinyint(4) DEFAULT NULL COMMENT '锁定状态，0-未锁定，1-已锁定',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` bigint(20) NOT NULL COMMENT '创建用户',
  `update_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Table structure for table `base_user_role` */

DROP TABLE IF EXISTS `base_user_role`;

CREATE TABLE `base_user_role` (
  `pid` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Table structure for table `base_zuul_api_limiter` */

DROP TABLE IF EXISTS `base_zuul_api_limiter`;

CREATE TABLE `base_zuul_api_limiter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `route_id` bigint(20) DEFAULT NULL COMMENT '路由ID',
  `path` varchar(255) NOT NULL COMMENT 'API路径',
  `service_id` varchar(50) NOT NULL DEFAULT '' COMMENT '注册中心的服务ID',
  `limiter_enabled` tinyint(1) DEFAULT '0' COMMENT '是否启用限流',
  `permits_per_second` int(11) DEFAULT NULL COMMENT '每秒限制的请求数量',
  `permits` int(11) DEFAULT NULL COMMENT '获取许可数量，默认1',
  `timeout` bigint(20) DEFAULT NULL COMMENT '获取许可超时时间，默认0',
  `time_unit` varchar(64) DEFAULT NULL COMMENT '获取许可超时时间单位，默认MICROSECONDS',
  `error_code` int(11) DEFAULT NULL COMMENT '超过限流时的错误码',
  `error_msg` varchar(100) DEFAULT NULL COMMENT '超过限流时的错误信息',
  `create_user` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Table structure for table `base_zuul_route` */

DROP TABLE IF EXISTS `base_zuul_route`;

CREATE TABLE `base_zuul_route` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '路由ID',
  `path` varchar(255) NOT NULL COMMENT '访问路径',
  `service_id` varchar(50) NOT NULL DEFAULT '' COMMENT '注册中心的服务ID',
  `url` varchar(255) DEFAULT '' COMMENT '路由目的地址',
  `strip_prefix` tinyint(1) DEFAULT '0' COMMENT '是否去掉前缀',
  `retryable` tinyint(1) DEFAULT '0' COMMENT '是否重试',
  `sensitive_headers` varchar(255) DEFAULT '' COMMENT '敏感头信息',
  `custom_sensitive_headers` tinyint(1) DEFAULT '0' COMMENT '是否自定义敏感头信息',
  `enabled` tinyint(1) DEFAULT '0' COMMENT '是否启用',
  `route_name` varchar(255) DEFAULT '' COMMENT '路由配置名称',
  `limiter_enabled` tinyint(1) DEFAULT '0' COMMENT '是否启用限流',
  `permits_per_second` int(11) DEFAULT NULL COMMENT '每秒限制的请求数量',
  `permits` int(11) DEFAULT NULL COMMENT '获取许可数量，默认1',
  `timeout` bigint(20) DEFAULT NULL COMMENT '获取许可超时时间，默认0',
  `time_unit` varchar(64) DEFAULT NULL COMMENT '获取许可超时时间单位，默认MICROSECONDS',
  `create_user` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Table structure for table `brand_info` */

DROP TABLE IF EXISTS `brand_info`;

CREATE TABLE `brand_info` (
  `brand_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `brand_name` varchar(64) NOT NULL COMMENT '品牌名称',
  `brand_logo` varchar(100) DEFAULT NULL COMMENT '品牌图标的URL',
  `brand_url` varchar(100) DEFAULT NULL COMMENT '品牌官方网址',
  `state` int(1) NOT NULL DEFAULT '1' COMMENT '状态，0-禁用，1-启用',
  `brand_desc` varchar(200) DEFAULT NULL COMMENT '品牌描述',
  `create_user` bigint(20) DEFAULT NULL COMMENT '信息创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '信息修改人',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`brand_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='品牌信息';

/*Table structure for table `order_info` */

DROP TABLE IF EXISTS `order_info`;

CREATE TABLE `order_info` (
  `order_id` bigint(20) NOT NULL COMMENT '主键，订单号',
  `order_no` varchar(32) NOT NULL,
  `store_id` bigint(20) NOT NULL COMMENT '店铺ID',
  `member_id` bigint(20) NOT NULL COMMENT '用户ID',
  `pay_type` int(1) NOT NULL COMMENT '支付方式 1-货到付款，2-网银，3-支付宝，4-微信，5-充值卡抵扣（预留）',
  `total_amount` bigint(20) DEFAULT NULL COMMENT '订单总价，单位分',
  `ded_amount` bigint(20) DEFAULT NULL COMMENT '抵扣金额，单位分',
  `pay_amount` bigint(20) DEFAULT NULL COMMENT '实际支付金额，单位分',
  `discount_amount` bigint(20) DEFAULT NULL COMMENT '优惠金额，单位分',
  `point` bigint(20) DEFAULT NULL COMMENT '使用积分数量',
  `voucher_id` bigint(20) DEFAULT NULL COMMENT '使用的卡券ID',
  `state` int(2) DEFAULT NULL COMMENT '订单流转状态 ',
  `is_invoice` tinyint(1) DEFAULT NULL COMMENT '是否要发票，0-是，1-不是',
  `send_type` int(1) DEFAULT NULL COMMENT '送货方式，1-自取，2-送货上门',
  `is_pay` tinyint(1) DEFAULT NULL COMMENT '是否支付 0-未支付，1-支付',
  `source` int(1) DEFAULT NULL COMMENT '来源',
  `notes` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_user` bigint(20) DEFAULT NULL COMMENT '信息创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '信息修改人',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `order_product` */

DROP TABLE IF EXISTS `order_product`;

CREATE TABLE `order_product` (
  `pid` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL COMMENT '订单id',
  `product_id` bigint(20) NOT NULL,
  `sku_id` bigint(20) NOT NULL,
  `product_price` bigint(20) NOT NULL COMMENT 'sku的实际销售价格，单位分',
  `quantity` int(11) NOT NULL COMMENT '购买的sku数量',
  `create_time` datetime NOT NULL,
  `create_user` bigint(20) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `order_send` */

DROP TABLE IF EXISTS `order_send`;

CREATE TABLE `order_send` (
  `pid` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL COMMENT '订单id',
  `address_id` bigint(20) NOT NULL COMMENT '会员默认地址id',
  `delivery_date` date DEFAULT NULL COMMENT '配送日期',
  `express` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '快递公司名',
  `express_no` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '快递单号',
  `create_time` datetime NOT NULL,
  `create_user` bigint(20) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `product_attr` */

DROP TABLE IF EXISTS `product_attr`;

CREATE TABLE `product_attr` (
  `attr_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `attr_name` varchar(32) NOT NULL COMMENT '属性名称',
  `category_id` bigint(20) NOT NULL,
  `is_search` tinyint(1) DEFAULT '0' COMMENT '是否搜索属性,0-否，1-是',
  `is_required` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否必填,0-否，1-必填',
  `attr_type` int(1) NOT NULL COMMENT '属性类型，1-多选，2-单选，3-输入，4-下拉列表，5-颜色选项,6-时间选项',
  `is_sale` tinyint(1) NOT NULL COMMENT '是否销售属性，展现到销售选择页上',
  `state` int(1) NOT NULL COMMENT '状态',
  `seq_no` int(10) NOT NULL COMMENT '排序',
  `notes` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_user` bigint(20) DEFAULT NULL COMMENT '信息创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '信息修改人',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`attr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `product_attr_item` */

DROP TABLE IF EXISTS `product_attr_item`;

CREATE TABLE `product_attr_item` (
  `item_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '属性项ID',
  `attr_id` bigint(20) NOT NULL COMMENT '属性ID',
  `item_name` varchar(100) NOT NULL COMMENT '属性项名称',
  `alias` varchar(100) DEFAULT NULL COMMENT '值别名',
  `state` int(1) NOT NULL COMMENT '状态',
  `seq_no` int(10) NOT NULL COMMENT '排序',
  `create_user` bigint(20) DEFAULT NULL COMMENT '信息创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '信息修改人',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `product_attr_rel` */

DROP TABLE IF EXISTS `product_attr_rel`;

CREATE TABLE `product_attr_rel` (
  `pid` bigint(20) NOT NULL,
  `product_id` int(11) DEFAULT NULL,
  `attr_id` int(11) DEFAULT NULL COMMENT '属性ID',
  `item_id` int(11) DEFAULT NULL COMMENT '属性值ID',
  `is_sale` tinyint(1) DEFAULT NULL COMMENT '是否销售属性；0否，1-是',
  `attr_value` varchar(500) DEFAULT NULL COMMENT '自定义属性值',
  `attr_image` varchar(100) DEFAULT NULL COMMENT '图片路径',
  `create_user` bigint(20) DEFAULT NULL COMMENT '信息创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '信息修改人',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品基本属性信息';

/*Table structure for table `product_category` */

DROP TABLE IF EXISTS `product_category`;

CREATE TABLE `product_category` (
  `category_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，uuid',
  `category_name` varchar(20) NOT NULL COMMENT '类别名称',
  `parent_id` bigint(20) NOT NULL COMMENT '父类别，若无则为0',
  `intro` varchar(200) DEFAULT NULL COMMENT '简介',
  `category_icon` varchar(100) DEFAULT NULL COMMENT '图标URL',
  `state` tinyint(1) DEFAULT NULL COMMENT '状态',
  `seq_no` int(2) DEFAULT NULL COMMENT '顺序',
  `create_user` bigint(20) DEFAULT NULL COMMENT '信息创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '信息修改人',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Table structure for table `product_detail` */

DROP TABLE IF EXISTS `product_detail`;

CREATE TABLE `product_detail` (
  `pid` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) NOT NULL,
  `product_images` varchar(1000) DEFAULT NULL,
  `intro` text COMMENT '商品介绍',
  `product_url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `product_info` */

DROP TABLE IF EXISTS `product_info`;

CREATE TABLE `product_info` (
  `product_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_code` varchar(60) NOT NULL COMMENT '产品编码',
  `product_name` varchar(60) NOT NULL COMMENT '商品名称',
  `product_subname` varchar(60) NOT NULL COMMENT '商品副名称，记录一些促销信息等',
  `category_id` bigint(20) NOT NULL COMMENT '商品大类',
  `product_icon` varchar(100) DEFAULT NULL COMMENT '产品图标',
  `brand_id` bigint(20) DEFAULT NULL COMMENT '品牌名称',
  `unit` varchar(32) DEFAULT NULL COMMENT '单位',
  `tags` varchar(200) DEFAULT NULL COMMENT '关键词，多个用英文分号隔开，不超过10个',
  `belong_type` bigint(20) DEFAULT '1' COMMENT '归属类型，1-通用，2-归属到商户',
  `belong_cust` bigint(20) DEFAULT NULL COMMENT '归属商户',
  `state` int(2) NOT NULL DEFAULT '20' COMMENT '商品状态，10-草稿，20-提交审核，30-已发布，40-审核不通过',
  `create_user` bigint(20) DEFAULT NULL COMMENT '信息创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '信息修改人',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Table structure for table `product_sku` */

DROP TABLE IF EXISTS `product_sku`;

CREATE TABLE `product_sku` (
  `sku_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '库存单元ID',
  `sku_code` varchar(64) NOT NULL COMMENT '库存单元编码',
  `sku_name` varchar(100) NOT NULL COMMENT '库存单元名称',
  `product_id` bigint(20) NOT NULL COMMENT '商品ID',
  `sku_items` varchar(1000) DEFAULT '0' COMMENT '打包属性ID组合，英文逗号隔开',
  `item_name` varchar(1000) DEFAULT NULL COMMENT 'sale名称',
  `market_price` bigint(20) DEFAULT NULL COMMENT '市场价，单位分',
  `sell_price` bigint(20) DEFAULT NULL COMMENT '销售价格，单位分',
  `member_price` bigint(20) DEFAULT NULL COMMENT '会员价，单位分',
  `cost_price` bigint(20) DEFAULT NULL COMMENT '成本价格，单位分',
  `percent_point` int(11) DEFAULT NULL COMMENT '积分最大使用比例0-100',
  `postage` int(11) DEFAULT NULL COMMENT '邮费，免邮费用为0',
  `stock_num` int(11) DEFAULT NULL COMMENT '库存数量',
  `sale_num` int(11) DEFAULT NULL COMMENT '自定义已销售的数量，默认与真实一致',
  `real_sale_num` int(11) DEFAULT NULL COMMENT '真实销售数量',
  `qr_code` varchar(100) DEFAULT NULL COMMENT '二维码,图片url',
  `bar_code` varchar(100) DEFAULT NULL COMMENT '条码',
  `belong_store` bigint(20) DEFAULT NULL COMMENT '归属店铺',
  `state` varchar(100) DEFAULT NULL COMMENT '状态',
  `create_user` bigint(20) DEFAULT NULL COMMENT '信息创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '信息修改人',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`sku_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Table structure for table `promo_info` */

DROP TABLE IF EXISTS `promo_info`;

CREATE TABLE `promo_info` (
  `promo_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `promo_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '促销名称',
  `promo_icon` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `promo_type` bigint(20) NOT NULL COMMENT '促销类别ID',
  `product_id` bigint(20) NOT NULL COMMENT '产品id',
  `sku_id` bigint(20) NOT NULL COMMENT 'sku',
  `store_id` bigint(20) DEFAULT NULL COMMENT '促销发起店铺ID',
  `begin_time` bigint(20) NOT NULL COMMENT '开始时间',
  `end_time` bigint(20) NOT NULL COMMENT '结束时间',
  `promo_price` bigint(20) NOT NULL COMMENT '促销价格，单位分',
  `is_apply` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否需要申请',
  `stock_quantity` int(11) NOT NULL COMMENT '库存数量',
  `sell_quantity` int(11) DEFAULT '0' COMMENT '销售数量',
  `limit_quantity` int(11) DEFAULT NULL COMMENT '每人限购数量',
  `state` int(11) NOT NULL COMMENT '活动状态',
  `create_time` datetime NOT NULL,
  `create_user` bigint(20) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`promo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

/*Table structure for table `promo_type` */

DROP TABLE IF EXISTS `promo_type`;

CREATE TABLE `promo_type` (
  `pid` bigint(20) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '活动类型名称',
  `type_icon` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '活动分类的图标',
  `notes` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `state` int(1) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL,
  `create_user` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `reliable_message` */

DROP TABLE IF EXISTS `reliable_message`;

CREATE TABLE `reliable_message` (
  `message_id` bigint(20) NOT NULL COMMENT '消息ID',
  `message_body` text NOT NULL COMMENT '消息内容',
  `message_data_type` varchar(50) DEFAULT NULL COMMENT '消息数据类型',
  `consumer_queue` varchar(100) NOT NULL DEFAULT '' COMMENT 'MQ队列',
  `routing_key` varchar(100) DEFAULT '' COMMENT 'MQ路由键值',
  `message_send_times` int(6) NOT NULL DEFAULT '0' COMMENT '消息重发次数',
  `already_dead` char(1) NOT NULL DEFAULT '' COMMENT '是否死亡(Y：已死亡,N：未死亡)',
  `status` varchar(20) NOT NULL DEFAULT '' COMMENT '状态(WAIT_VERIFY：待确认,SENDING：发送中)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建者',
  `update_user` bigint(20) DEFAULT NULL COMMENT '修改者',
  `notes` varchar(200) DEFAULT NULL COMMENT '备注',
  `version` bigint(20) NOT NULL DEFAULT '0' COMMENT '版本号',
  `biz_unique_id` bigint(20) DEFAULT NULL COMMENT '业务系统唯一id',
  PRIMARY KEY (`message_id`),
  KEY `rpmsg_key_2` (`message_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='可靠事物消息表';

/*Table structure for table `shopping_cart` */

DROP TABLE IF EXISTS `shopping_cart`;

CREATE TABLE `shopping_cart` (
  `cart_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '购物车ID，主键',
  `member_id` bigint(11) NOT NULL COMMENT '会员ID',
  `product_id` varchar(32) NOT NULL COMMENT '商品ID',
  `sku_id` bigint(11) NOT NULL COMMENT '商品',
  `quantity` int(3) NOT NULL COMMENT '商品数量',
  `create_user` bigint(20) DEFAULT NULL COMMENT '信息创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(20) DEFAULT NULL COMMENT '信息修改人',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`cart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
