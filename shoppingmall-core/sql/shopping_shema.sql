DROP TABLE IF EXISTS `os_storage`;
CREATE TABLE `os_storage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ikey` varchar(63) NOT NULL COMMENT '文件的唯一索引',
  `name` varchar(255) NOT NULL COMMENT '文件名',
  `type` varchar(20) NOT NULL COMMENT '文件类型',
  `size` int(11) NOT NULL COMMENT '文件大小',
  `url` varchar(255) DEFAULT NULL COMMENT '文件访问链接',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除,0.正常，1 已删除',
  PRIMARY KEY (`id`),
  KEY `ikey` (`ikey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件存储表';

DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `real_name` varchar(20) NOT NULL COMMENT '真实姓名',
  `phone` varchar(20) NOT NULL COMMENT '手机号',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `last_login_ip` varchar(20) DEFAULT NULL COMMENT '最后登录ip',
  `login_count` int(11) DEFAULT '0' COMMENT '登录次数',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除,0.正常，1 已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员表';

DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `openid` varchar(255) NOT NULL COMMENT '小程序标识',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `real_name` varchar(50) NOT NULL COMMENT '真实姓名',
  `gender` tinyint(3) NOT NULL DEFAULT '0' COMMENT '性别：0 未知， 1男， 2 女',
  `avatar_url` varchar(255) NOT NULL DEFAULT '' COMMENT '用户头像图片',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(3) NOT NULL DEFAULT '1' COMMENT '1 可用, 0 禁用',
  `user_level` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0 普通用户，1 会员用户',
  `qr_code_pic` varchar(255) DEFAULT NULL COMMENT '用户二维码',
  `qr_code_url` varchar(255) DEFAULT NULL COMMENT '用户二维码链接',
  `expire_time` date DEFAULT NULL COMMENT '到期时间',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除,0.正常，1 已删除',
  PRIMARY KEY (`id`),
  KEY `openid` (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

DROP TABLE IF EXISTS `t_shop`;
CREATE TABLE `t_shop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '店铺名称',
  `info` varchar(255) NOT NULL COMMENT '店铺简介',
  `address` varchar(50) NOT NULL COMMENT '店铺地址',
  `business` varchar(50) NOT NULL COMMENT '营业时间',
  `longitude` double NOT NULL DEFAULT '0.0' COMMENT '经度',
  `latitude` double NOT NULL DEFAULT '0.0' COMMENT '纬度',
  `mobile` varchar(20) NOT NULL COMMENT '手机号',
  `shop_img` varchar(255) NOT NULL DEFAULT '' COMMENT '店铺门面图片地址',
  `pic_urls` varchar(1023) DEFAULT '[]' COMMENT '图片地址列表',
  `priority` int(11) DEFAULT '0' COMMENT '排序',
  `status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '-1.不可用 0.审核中 1.可用',
  `advice` varchar(255) DEFAULT NULL COMMENT '超级管理员给店家的提醒，包括为什么审核不通过等',
  `order_num` int(10) NOT NULL DEFAULT '0' COMMENT '客户订单量',
  `openid` varchar(255) default NULL COMMENT '小程序标识',
  `password` varchar(255) default NULL COMMENT '密码',
  `real_name` varchar(20) NOT NULL COMMENT '真实姓名',
  `phone` varchar(11) NOT NULL COMMENT '手机号',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除,0.正常，1 已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='店铺表';

DROP TABLE IF EXISTS `t_ad`;
CREATE TABLE `t_ad` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(63) NOT NULL DEFAULT '' COMMENT '广告标题',
  `link` varchar(255) NOT NULL DEFAULT '' COMMENT '所广告的产品页面或者活动页面链接地址',
  `url` varchar(255) NOT NULL COMMENT '广告宣传图片',
  `position` tinyint(3) DEFAULT '1' COMMENT '广告位置：1则是首页',
  `content` varchar(255) DEFAULT '' COMMENT '广告内容',
  `enabled` tinyint(1) DEFAULT '1' COMMENT '是否启动,1.正常，0.未启动',
  `priority` int(11) DEFAULT '0' COMMENT '排序',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除,0.正常，1 已删除',
  PRIMARY KEY (`id`),
  KEY `enabled` (`enabled`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='广告表';

DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(63) NOT NULL DEFAULT '' COMMENT '类目名称',
  `info` varchar(255) DEFAULT '' COMMENT '类目说明',
  `icon_url` varchar(255) DEFAULT '' COMMENT '类目图标',
  `priority` int(11) DEFAULT '0' COMMENT '排序',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除 1.删除，0.未删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='类目表';

DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) DEFAULT '0' COMMENT '产品所属类目ID',
  `shop_id` int(11) DEFAULT '0' COMMENT '产品所属店铺ID',
  `name` varchar(127) NOT NULL DEFAULT '' COMMENT '产品名称',
  `brief` varchar(255) DEFAULT '' COMMENT '产品简介',
  `info` text COMMENT '使用须知',
  `fen` int(11) DEFAULT '0' COMMENT '份数',
  `content` text COMMENT '产品详细介绍，是富文本格式',
  `pic_url` varchar(255) DEFAULT NULL COMMENT '产品页面产品图片',
  `gallery` varchar(1023) DEFAULT '[]' COMMENT '产品宣传图片列表',
  `keywords` varchar(255) DEFAULT '' COMMENT '产品关键字，采用逗号间隔',
  `is_on_sale` tinyint(1) DEFAULT '1' COMMENT '是否上架',
  `priority` int(11) DEFAULT '0' COMMENT '排序',
  `retail_price` decimal(10,2) DEFAULT '0.00' COMMENT '零售价格',
  `counter_price` decimal(10,2) DEFAULT '0.00' COMMENT '会员价格',
  `discount` int(11) DEFAULT '100' COMMENT '折扣',
  `during` tinyint(3) DEFAULT '1' COMMENT '使用期限，1.永久 2.自定义',
  `start_date` date DEFAULT NULL COMMENT '产品开始使用时间',
  `end_date` date DEFAULT NULL COMMENT '产品过期时间',
  `valid_date` varchar(50) DEFAULT NULL COMMENT '产品有效时间（冗余）',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除 1.删除，0.未删除',
  PRIMARY KEY (`id`),
  KEY `cat_id` (`category_id`),
  KEY `shop_id` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品基本信息表';

DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户表的用户ID',
  `sn` varchar(63) NOT NULL COMMENT '订单编号',
  `status` smallint(6) NOT NULL COMMENT '订单状态',
  `qr_code_pic` varchar(255) DEFAULT NULL COMMENT '订单二维码',
  `qr_code_url` varchar(255) DEFAULT NULL COMMENT '订单二维码链接',
  `product_id` int(11) DEFAULT '0' COMMENT '预定产品ID',
  `shop_id` int(11) DEFAULT '0' COMMENT '订单所属店铺ID',
  'order_type' int(11) COMMENT '订单类型，1 优惠产品订单 2 拼团订单 3 砍价订单',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `shop_id` (`shop_id`),
  KEY `product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

DROP TABLE IF EXISTS `t_issue`;
CREATE TABLE `t_issue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question` varchar(255) DEFAULT NULL COMMENT '问题标题',
  `answer` varchar(255) DEFAULT NULL COMMENT '问题答案',
  `priority` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除 1.删除，0.未删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='常见问题表';

DROP TABLE IF EXISTS `t_keyword`;
CREATE TABLE `t_keyword` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keyword` varchar(127) NOT NULL DEFAULT '' COMMENT '关键字',
  `priority` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='搜索关键字表';

DROP TABLE IF EXISTS `t_act`;
CREATE TABLE `t_act` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(127) NOT NULL DEFAULT '' COMMENT '商铺名称',
  `pic` varchar(255) NOT NULL DEFAULT '' COMMENT '商铺照片',
  `act_date` varchar(127) NOT NULL DEFAULT '' COMMENT '活动开始时间',
  `content` varchar(255) NOT NULL DEFAULT '' COMMENT '活动内容',
  `address` varchar(127) NOT NULL DEFAULT '' COMMENT '商铺地址',
  `is_on_sale` tinyint(1) DEFAULT '0' COMMENT '是否上架',
  `priority` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='活动专场表';

DROP TABLE IF EXISTS `t_bargain`;
CREATE TABLE `t_bargain` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_id` int(11) DEFAULT '0' COMMENT '产品所属店铺ID',
  `name` varchar(127) NOT NULL DEFAULT '' COMMENT '产品名称',
  `brief` varchar(255) DEFAULT '' COMMENT '产品简介',
  `info` text COMMENT '使用须知',
  `fen` int(11) DEFAULT '0' COMMENT '份数',
  `content` text COMMENT '产品详细介绍，是富文本格式',
  `pic_url` varchar(255) DEFAULT NULL COMMENT '产品页面产品图片',
  `gallery` varchar(1023) DEFAULT '[]' COMMENT '产品宣传图片列表',
  `keywords` varchar(255) DEFAULT '' COMMENT '产品关键字，采用逗号间隔',
  `is_on_sale` tinyint(1) DEFAULT '1' COMMENT '是否上架',
  `priority` int(11) DEFAULT '0' COMMENT '排序',
  `price` decimal(10,2) DEFAULT '0.00' COMMENT '价格',
  `low_price` decimal(10,2) DEFAULT '0.00' COMMENT '砍价最低价格',
  `now_price` decimal(10,2) DEFAULT '0.00' COMMENT '现价',
  `people` int(11) DEFAULT '0' COMMENT '砍价人数设置',
  `join_people` int(11) DEFAULT '0' COMMENT '已参与人数',
  `during` tinyint(3) DEFAULT '1' COMMENT '活动时间，1.永久 2.自定义',
  `start_date` date DEFAULT NULL COMMENT '活动开始时间',
  `end_date` date DEFAULT NULL COMMENT '活动结束时间',
  `valid_date` varchar(50) DEFAULT NULL COMMENT '产品有效时间（冗余）',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除 1.删除，0.未删除',
  PRIMARY KEY (`id`),
  KEY `shop_id` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='砍价产品表';

DROP TABLE IF EXISTS `t_bargain_user`;
CREATE TABLE `t_bargain_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT '0' COMMENT '产品ID',
  `user_id` int(11) DEFAULT '0' COMMENT '用户ID',
  `flag` tinyint(1) DEFAULT '1' COMMENT '标识 1.发起者, 2.参与者',
  `price` decimal(10,2) DEFAULT '0.00' COMMENT '砍价价格',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除 1.删除，0.未删除',
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='砍价产品用户关联表';

DROP TABLE IF EXISTS `t_collage`;
CREATE TABLE `t_collage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_id` int(11) DEFAULT '0' COMMENT '产品所属店铺ID',
  `name` varchar(127) NOT NULL DEFAULT '' COMMENT '产品名称',
  `brief` varchar(255) DEFAULT '' COMMENT '产品简介',
  `info` text COMMENT '使用须知',
  `fen` int(11) DEFAULT '0' COMMENT '份数',
  `content` text COMMENT '产品详细介绍，是富文本格式',
  `pic_url` varchar(255) DEFAULT NULL COMMENT '产品页面产品图片',
  `gallery` varchar(1023) DEFAULT '[]' COMMENT '产品宣传图片列表',
  `keywords` varchar(255) DEFAULT '' COMMENT '产品关键字，采用逗号间隔',
  `is_on_sale` tinyint(1) DEFAULT '1' COMMENT '是否上架',
  `priority` int(11) DEFAULT '0' COMMENT '排序',
  `price` decimal(10,2) DEFAULT '0.00' COMMENT '价格',
  `people` int(11) DEFAULT '0' COMMENT '拼团人数设置',
  `join_people` int(11) DEFAULT '0' COMMENT '已参与人数',
  `during` tinyint(3) DEFAULT '1' COMMENT '活动时间，1.永久 2.自定义',
  `start_date` date DEFAULT NULL COMMENT '活动开始时间',
  `end_date` date DEFAULT NULL COMMENT '活动结束时间',
  `valid_date` varchar(50) DEFAULT NULL COMMENT '产品有效时间（冗余）',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除 1.删除，0.未删除',
  PRIMARY KEY (`id`),
  KEY `shop_id` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='拼团产品表';

DROP TABLE IF EXISTS `t_collage_user`;
CREATE TABLE `t_collage_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT '0' COMMENT '产品ID',
  `user_id` int(11) DEFAULT '0' COMMENT '用户ID',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除 1.删除，0.未删除',
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='拼团产品用户关联表';

DROP TABLE IF EXISTS `t_shop_user`;
CREATE TABLE `t_shop_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_id` int(11) DEFAULT '0' COMMENT '店铺ID',
  `user_id` int(11) DEFAULT '0' COMMENT '用户ID',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除 1.删除，0.未删除',
  PRIMARY KEY (`id`),
  KEY `shop_id` (`shop_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='店铺验证会员关联表';
