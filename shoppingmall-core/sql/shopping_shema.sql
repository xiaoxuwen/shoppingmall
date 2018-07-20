DROP TABLE IF EXISTS `os_storage`;
CREATE TABLE `os_storage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ikey` varchar(63) NOT NULL COMMENT '文件的唯一索引',
  `name` varchar(255) NOT NULL COMMENT '文件名',
  `type` varchar(20) NOT NULL COMMENT '文件类型',
  `size` int(11) NOT NULL COMMENT '文件大小',
  `modified` datetime DEFAULT NULL COMMENT '最后更新时间',
  `url` varchar(255) DEFAULT NULL COMMENT '文件访问链接',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件存储表';

DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `real_name` varchar(20) NOT NULL COMMENT '真实姓名',
  `phone` int(11) NOT NULL COMMENT '手机号',
  `modified` datetime DEFAULT NULL COMMENT '最后更新时间',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `last_login_ip` varchar(20) DEFAULT NULL COMMENT '最后登录ip',
  `login_count` int(11) DEFAULT '0' COMMENT '登录次数',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
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
  `status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0 可用, 1 禁用',
  `user_level` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0 普通用户，1 会员用户',
  `modified` datetime DEFAULT NULL COMMENT '最后更新时间',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';


DROP TABLE IF EXISTS `t_seller`;
CREATE TABLE `t_seller` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `openid` varchar(255) NOT NULL COMMENT '小程序标识',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `real_name` varchar(20) NOT NULL COMMENT '真实姓名',
  `phone` int(11) NOT NULL COMMENT '手机号',
  `avatar_url` varchar(255) NOT NULL DEFAULT '' COMMENT '用户头像图片',
  `status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0 可用, 1 禁用',
  `modified` datetime DEFAULT NULL COMMENT '最后更新时间',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商家表';