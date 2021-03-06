/*
Navicat MySQL Data Transfer

Source Server         : 192.168.0.250_3306
Source Server Version : 50505
Source Host           : 192.168.0.250:3306
Source Database       : shoppingmall

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2018-07-31 18:06:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `os_storage`
-- ----------------------------
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
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8 COMMENT='文件存储表';

-- ----------------------------
-- Records of os_storage
-- ----------------------------
INSERT INTO `os_storage` VALUES ('1', '8ts9fwnbhwzsjvvrbpnm.png', 'classify.png', 'image/png', '2281', 'http://127.0.0.1:8082/os/storage/fetch/8ts9fwnbhwzsjvvrbpnm.png', '2018-07-26 11:42:27', '0');
INSERT INTO `os_storage` VALUES ('2', 'ciyw2gndvps5zmozt4kx.png', 'classify.png', 'image/png', '2281', 'http://127.0.0.1:8082/os/storage/fetch/ciyw2gndvps5zmozt4kx.png', '2018-07-26 11:47:23', '0');
INSERT INTO `os_storage` VALUES ('3', 'ok7ko8w652cn90hb75v3.png', 'classify.png', 'image/png', '2281', 'http://127.0.0.1:8082/os/storage/fetch/ok7ko8w652cn90hb75v3.png', '2018-07-26 11:50:58', '0');
INSERT INTO `os_storage` VALUES ('4', 'yia3lvb39f3rqt1pzk9c.png', 'classify.png', 'image/png', '2281', 'http://127.0.0.1:8082/os/storage/fetch/yia3lvb39f3rqt1pzk9c.png', '2018-07-26 14:35:37', '0');
INSERT INTO `os_storage` VALUES ('5', 'y3wwx4wzet9r1as6y0ks.png', 'classify.png', 'image/png', '2281', 'http://127.0.0.1:8082/os/storage/fetch/y3wwx4wzet9r1as6y0ks.png', '2018-07-26 14:36:04', '0');
INSERT INTO `os_storage` VALUES ('6', 's3tbyxc18ln5bp2i99xj.png', 'address.png', 'image/png', '1943', 'http://127.0.0.1:8082/os/storage/fetch/s3tbyxc18ln5bp2i99xj.png', '2018-07-26 14:40:20', '0');
INSERT INTO `os_storage` VALUES ('7', 'rg5ln5hdkkj7x2ooze5j.png', 'ban1.png', 'image/png', '248025', 'http://127.0.0.1:8082/os/storage/fetch/rg5ln5hdkkj7x2ooze5j.png', '2018-07-26 14:49:05', '0');
INSERT INTO `os_storage` VALUES ('8', 'jgws85che6j989mxipt0.png', 'ban1.png', 'image/png', '248025', 'http://127.0.0.1:8082/os/storage/fetch/jgws85che6j989mxipt0.png', '2018-07-26 14:49:29', '0');
INSERT INTO `os_storage` VALUES ('9', '8oko0hfn4bsdughv8s0p.png', 'ban1.png', 'image/png', '248025', 'http://127.0.0.1:8082/os/storage/fetch/8oko0hfn4bsdughv8s0p.png', '2018-07-26 14:49:53', '0');
INSERT INTO `os_storage` VALUES ('10', 'jujzp2r7xdy4gz6k0h6k.png', 'ban1.png', 'image/png', '248025', 'http://127.0.0.1:8082/os/storage/fetch/jujzp2r7xdy4gz6k0h6k.png', '2018-07-26 14:50:33', '0');
INSERT INTO `os_storage` VALUES ('11', 's76li5hredfwz0it7u7n.png', 'ban1.png', 'image/png', '248025', 'http://127.0.0.1:8082/os/storage/fetch/s76li5hredfwz0it7u7n.png', '2018-07-26 16:39:48', '0');
INSERT INTO `os_storage` VALUES ('12', 'pzw53vrywzit4aeth3y1.png', '鲜花.png', 'image/png', '3720', 'http://127.0.0.1:8082/os/storage/fetch/pzw53vrywzit4aeth3y1.png', '2018-07-26 16:40:28', '0');
INSERT INTO `os_storage` VALUES ('13', '9cgvsts1hjgirs8vlqhj.png', '娱乐.png', 'image/png', '4476', 'http://127.0.0.1:8082/os/storage/fetch/9cgvsts1hjgirs8vlqhj.png', '2018-07-26 16:43:22', '0');
INSERT INTO `os_storage` VALUES ('14', '5itgewa9m7rfrf1q0eh1.png', '娱乐.png', 'image/png', '4476', 'http://127.0.0.1:8082/os/storage/fetch/5itgewa9m7rfrf1q0eh1.png', '2018-07-26 16:46:20', '0');
INSERT INTO `os_storage` VALUES ('15', 'bac6glrfphpqa12ln5qe.png', 'ban1.png', 'image/png', '248025', 'http://127.0.0.1:8082/os/storage/fetch/bac6glrfphpqa12ln5qe.png', '2018-07-26 16:46:36', '0');
INSERT INTO `os_storage` VALUES ('16', 'di44cqxjnvhg7n1l2odr.png', 'ban1.png', 'image/png', '248025', 'http://127.0.0.1:8082/os/storage/fetch/di44cqxjnvhg7n1l2odr.png', '2018-07-26 16:47:21', '0');
INSERT INTO `os_storage` VALUES ('17', 'np8y2l6ujv81xxhvhzsj.png', 'ban1.png', 'image/png', '248025', 'http://127.0.0.1:8082/os/storage/fetch/np8y2l6ujv81xxhvhzsj.png', '2018-07-26 16:48:28', '0');
INSERT INTO `os_storage` VALUES ('18', 'h52alc9rv5y9oyuaq0pw.png', 'ban1.png', 'image/png', '248025', 'http://127.0.0.1:8082/os/storage/fetch/h52alc9rv5y9oyuaq0pw.png', '2018-07-26 16:52:16', '0');
INSERT INTO `os_storage` VALUES ('19', 'mcqu27b2v8225iurh9jl.png', 'ban1.png', 'image/png', '248025', 'http://127.0.0.1:8082/os/storage/fetch/mcqu27b2v8225iurh9jl.png', '2018-07-26 17:00:14', '0');
INSERT INTO `os_storage` VALUES ('20', '6t17kebsjn3rf6hwafkw.png', 'ban1.png', 'image/png', '248025', 'http://127.0.0.1:8082/os/storage/fetch/6t17kebsjn3rf6hwafkw.png', '2018-07-27 10:18:11', '0');
INSERT INTO `os_storage` VALUES ('21', 'ce5spq71kf4zl4c6a9cr.png', 'ban1.png', 'image/png', '248025', 'http://127.0.0.1:8082/os/storage/fetch/ce5spq71kf4zl4c6a9cr.png', '2018-07-27 10:18:26', '0');
INSERT INTO `os_storage` VALUES ('22', '12bav4yk3npx9lzzfmm4.png', '首页-哎呦小程序原型图.png', 'image/png', '1917098', 'http://127.0.0.1:8082/os/storage/fetch/12bav4yk3npx9lzzfmm4.png', '2018-07-30 11:59:44', '0');
INSERT INTO `os_storage` VALUES ('23', 'goy1pgzkrwylh0bh6gmp.png', '首页-哎呦小程序原型图.png', 'image/png', '1917098', 'http://127.0.0.1:8082/os/storage/fetch/goy1pgzkrwylh0bh6gmp.png', '2018-07-30 14:13:30', '0');
INSERT INTO `os_storage` VALUES ('24', '74z3jy4eci9ir9xpi25k.png', '健身.png', 'image/png', '3174', 'http://127.0.0.1:8082/os/storage/fetch/74z3jy4eci9ir9xpi25k.png', '2018-07-30 14:33:49', '0');
INSERT INTO `os_storage` VALUES ('25', 'tdwtplefmt0eeqm6m7tg.png', '健身.png', 'image/png', '3174', 'http://127.0.0.1:8082/os/storage/fetch/tdwtplefmt0eeqm6m7tg.png', '2018-07-30 14:35:18', '0');
INSERT INTO `os_storage` VALUES ('26', 'zshzy997xym8rsto46e9.png', '美食 (1).png', 'image/png', '3789', 'http://127.0.0.1:8082/os/storage/fetch/zshzy997xym8rsto46e9.png', '2018-07-30 14:36:45', '0');
INSERT INTO `os_storage` VALUES ('27', 'e068nxuobyh3ds3h75ak.png', '鲜花.png', 'image/png', '3720', 'http://127.0.0.1:8082/os/storage/fetch/e068nxuobyh3ds3h75ak.png', '2018-07-30 15:39:07', '0');
INSERT INTO `os_storage` VALUES ('28', 'rrowcdem32ncvoi8xlat.png', '娱乐.png', 'image/png', '4476', 'http://127.0.0.1:8082/os/storage/fetch/rrowcdem32ncvoi8xlat.png', '2018-07-30 15:39:07', '0');
INSERT INTO `os_storage` VALUES ('29', 'b8lhp31lnhmxxxokxvkb.png', '娱乐.png', 'image/png', '4476', 'http://127.0.0.1:8082/os/storage/fetch/b8lhp31lnhmxxxokxvkb.png', '2018-07-30 15:40:19', '0');
INSERT INTO `os_storage` VALUES ('30', '3ofpbj56hz9usdmp7mbo.png', '鲜花.png', 'image/png', '3720', 'http://127.0.0.1:8082/os/storage/fetch/3ofpbj56hz9usdmp7mbo.png', '2018-07-30 15:40:20', '0');
INSERT INTO `os_storage` VALUES ('31', 'p3et2vowznxotmghte5o.png', '母婴用品.png', 'image/png', '3647', 'http://127.0.0.1:8082/os/storage/fetch/p3et2vowznxotmghte5o.png', '2018-07-30 15:42:48', '0');
INSERT INTO `os_storage` VALUES ('32', 'xabsppmz2mw8fg6hqiia.png', '美食 (1).png', 'image/png', '3789', 'http://127.0.0.1:8082/os/storage/fetch/xabsppmz2mw8fg6hqiia.png', '2018-07-30 15:42:48', '0');
INSERT INTO `os_storage` VALUES ('33', 'tzbggi36tdord1dhpfk6.png', '美食 (1).png', 'image/png', '3789', 'http://127.0.0.1:8082/os/storage/fetch/tzbggi36tdord1dhpfk6.png', '2018-07-30 15:43:42', '0');
INSERT INTO `os_storage` VALUES ('34', 'ry9sf4h6wnxur0gchpad.png', 'ban1.png', 'image/png', '248025', 'http://127.0.0.1:8082/os/storage/fetch/ry9sf4h6wnxur0gchpad.png', '2018-07-30 15:43:53', '0');
INSERT INTO `os_storage` VALUES ('35', '3lzqm0yhh3zy7pj1yeqa.png', 'ban1.png', 'image/png', '248025', 'http://127.0.0.1:8082/os/storage/fetch/3lzqm0yhh3zy7pj1yeqa.png', '2018-07-30 15:44:01', '0');
INSERT INTO `os_storage` VALUES ('36', 'cn61onkrq1hgw852z10c.png', 'ban1.png', 'image/png', '248025', 'http://127.0.0.1:8082/os/storage/fetch/cn61onkrq1hgw852z10c.png', '2018-07-30 15:47:07', '0');
INSERT INTO `os_storage` VALUES ('37', 'w2y8of8utvjfof1lf2lt.png', '首页-哎呦小程序原型图.png', 'image/png', '1917098', 'http://127.0.0.1:8082/os/storage/fetch/w2y8of8utvjfof1lf2lt.png', '2018-07-30 17:08:10', '0');
INSERT INTO `os_storage` VALUES ('38', 'geh8mkl3ttx11soh6oh1.png', 'ban1.png', 'image/png', '248025', 'http://127.0.0.1:8082/os/storage/fetch/geh8mkl3ttx11soh6oh1.png', '2018-07-30 17:08:22', '0');
INSERT INTO `os_storage` VALUES ('39', 'ceaa9c5cu49jzk43r3pg.png', 'ban1.png', 'image/png', '248025', 'http://127.0.0.1:8082/os/storage/fetch/ceaa9c5cu49jzk43r3pg.png', '2018-07-30 17:51:25', '0');
INSERT INTO `os_storage` VALUES ('40', 'j250eua68v4pg3077pmx.png', 'ban1.png', 'image/png', '248025', 'http://127.0.0.1:8082/os/storage/fetch/j250eua68v4pg3077pmx.png', '2018-07-30 17:51:31', '0');
INSERT INTO `os_storage` VALUES ('41', '16kxqvjspf615vahavmc.png', 'ban1.png', 'image/png', '248025', 'http://127.0.0.1:8082/os/storage/fetch/16kxqvjspf615vahavmc.png', '2018-07-30 17:51:44', '0');
INSERT INTO `os_storage` VALUES ('42', 'cg1m40f1zbskxk1zy58y.png', '鲜花.png', 'image/png', '3720', 'http://127.0.0.1:8082/os/storage/fetch/cg1m40f1zbskxk1zy58y.png', '2018-07-31 10:12:22', '0');
INSERT INTO `os_storage` VALUES ('43', '6hhfkqit5g1k1x1lmgdg.png', 'ban1.png', 'image/png', '248025', 'http://127.0.0.1:8082/os/storage/fetch/6hhfkqit5g1k1x1lmgdg.png', '2018-07-31 10:12:29', '0');
INSERT INTO `os_storage` VALUES ('44', '0tl1xeitjzgfmh86k7ai.png', '生活服务.png', 'image/png', '3092', 'http://127.0.0.1:8082/os/storage/fetch/0tl1xeitjzgfmh86k7ai.png', '2018-07-31 10:17:31', '0');
INSERT INTO `os_storage` VALUES ('45', 'a40plt8m7d7r8mzp46is.png', '娱乐.png', 'image/png', '4476', 'http://127.0.0.1:8082/os/storage/fetch/a40plt8m7d7r8mzp46is.png', '2018-07-31 10:18:29', '0');
INSERT INTO `os_storage` VALUES ('46', 'f8w05hkob4h4l624ocwl.png', '娱乐.png', 'image/png', '4476', 'http://127.0.0.1:8082/os/storage/fetch/f8w05hkob4h4l624ocwl.png', '2018-07-31 10:59:24', '0');
INSERT INTO `os_storage` VALUES ('47', 'gs8qm04uxmitjm48divc.png', '服饰.png', 'image/png', '3364', 'http://127.0.0.1:8082/os/storage/fetch/gs8qm04uxmitjm48divc.png', '2018-07-31 10:59:29', '0');
INSERT INTO `os_storage` VALUES ('48', 'sush8ghqe9wg84s0nhik.png', '健身.png', 'image/png', '3174', 'http://127.0.0.1:8082/os/storage/fetch/sush8ghqe9wg84s0nhik.png', '2018-07-31 10:59:29', '0');
INSERT INTO `os_storage` VALUES ('49', 'pu7qhj4ricee2jipepx9.png', '丽人.png', 'image/png', '3903', 'http://127.0.0.1:8082/os/storage/fetch/pu7qhj4ricee2jipepx9.png', '2018-07-31 10:59:29', '0');
INSERT INTO `os_storage` VALUES ('50', 'qg85hb95yyq4brdbfsrv.png', '酒店 (1).png', 'image/png', '2746', 'http://127.0.0.1:8082/os/storage/fetch/qg85hb95yyq4brdbfsrv.png', '2018-07-31 10:59:29', '0');
INSERT INTO `os_storage` VALUES ('51', 'yg5zdmjy46uyc0qdcift.png', '娱乐.png', 'image/png', '4476', 'http://127.0.0.1:8082/os/storage/fetch/yg5zdmjy46uyc0qdcift.png', '2018-07-31 11:02:18', '0');
INSERT INTO `os_storage` VALUES ('52', 'dy7ksg5bomqt90uvc9oa.png', '酒店 (1).png', 'image/png', '2746', 'http://127.0.0.1:8082/os/storage/fetch/dy7ksg5bomqt90uvc9oa.png', '2018-07-31 11:02:21', '0');
INSERT INTO `os_storage` VALUES ('53', 'nqu28gv73qwxz2ank9by.png', '服饰.png', 'image/png', '3364', 'http://127.0.0.1:8082/os/storage/fetch/nqu28gv73qwxz2ank9by.png', '2018-07-31 11:02:21', '0');
INSERT INTO `os_storage` VALUES ('54', '0scqypaxxnve4hw41kvz.png', '健身.png', 'image/png', '3174', 'http://127.0.0.1:8082/os/storage/fetch/0scqypaxxnve4hw41kvz.png', '2018-07-31 11:02:21', '0');
INSERT INTO `os_storage` VALUES ('55', 'm5hm6b5gy740c0jtoee6.png', '娱乐.png', 'image/png', '4476', 'http://127.0.0.1:8082/os/storage/fetch/m5hm6b5gy740c0jtoee6.png', '2018-07-31 11:08:32', '0');
INSERT INTO `os_storage` VALUES ('56', 'ec5694jjl03jck8qkbgg.png', '健身.png', 'image/png', '3174', 'http://127.0.0.1:8082/os/storage/fetch/ec5694jjl03jck8qkbgg.png', '2018-07-31 11:08:36', '0');
INSERT INTO `os_storage` VALUES ('57', '7qigzm0udp0tm635z4yc.png', '服饰.png', 'image/png', '3364', 'http://127.0.0.1:8082/os/storage/fetch/7qigzm0udp0tm635z4yc.png', '2018-07-31 11:08:36', '0');
INSERT INTO `os_storage` VALUES ('58', 'c3ew4j4qlmyxw18ulsro.png', '酒店 (1).png', 'image/png', '2746', 'http://127.0.0.1:8082/os/storage/fetch/c3ew4j4qlmyxw18ulsro.png', '2018-07-31 11:08:36', '0');
INSERT INTO `os_storage` VALUES ('59', 'afjamsnd6eiuny5by6if.png', '丽人.png', 'image/png', '3903', 'http://127.0.0.1:8082/os/storage/fetch/afjamsnd6eiuny5by6if.png', '2018-07-31 11:08:36', '0');
INSERT INTO `os_storage` VALUES ('60', 'vfye61rl1hf193orvmix.png', '母婴用品.png', 'image/png', '3647', 'http://127.0.0.1:8082/os/storage/fetch/vfye61rl1hf193orvmix.png', '2018-07-31 11:30:31', '0');
INSERT INTO `os_storage` VALUES ('61', '23oegsop0j5mr0u8kywb.png', '服饰.png', 'image/png', '3364', 'http://127.0.0.1:8082/os/storage/fetch/23oegsop0j5mr0u8kywb.png', '2018-07-31 11:30:46', '0');
INSERT INTO `os_storage` VALUES ('62', 'u5l2640yfrcohy63emxu.png', '培训.png', 'image/png', '2903', 'http://127.0.0.1:8082/os/storage/fetch/u5l2640yfrcohy63emxu.png', '2018-07-31 11:30:46', '0');
INSERT INTO `os_storage` VALUES ('63', 'ewpn245xzte5sum5c4tp.png', '培训.png', 'image/png', '2903', 'http://127.0.0.1:8082/os/storage/fetch/ewpn245xzte5sum5c4tp.png', '2018-07-31 11:31:24', '0');
INSERT INTO `os_storage` VALUES ('64', 'sgirlcbu2spv5l54y9qy.png', '服饰.png', 'image/png', '3364', 'http://127.0.0.1:8082/os/storage/fetch/sgirlcbu2spv5l54y9qy.png', '2018-07-31 11:31:24', '0');
INSERT INTO `os_storage` VALUES ('65', 'hczlvkh77e1ytvjlwppi.png', '丽人.png', 'image/png', '3903', 'http://127.0.0.1:8082/os/storage/fetch/hczlvkh77e1ytvjlwppi.png', '2018-07-31 11:31:57', '0');
INSERT INTO `os_storage` VALUES ('66', 'y046qduj4imvobkwbebv.png', '美食 (1).png', 'image/png', '3789', 'http://127.0.0.1:8082/os/storage/fetch/y046qduj4imvobkwbebv.png', '2018-07-31 11:31:57', '0');
INSERT INTO `os_storage` VALUES ('67', 'pj9bh4yx95del36u8594.png', '母婴用品.png', 'image/png', '3647', 'http://127.0.0.1:8082/os/storage/fetch/pj9bh4yx95del36u8594.png', '2018-07-31 11:33:25', '0');
INSERT INTO `os_storage` VALUES ('68', 'nc7ib120qt2anf209rv0.png', '母婴用品.png', 'image/png', '3647', 'http://127.0.0.1:8082/os/storage/fetch/nc7ib120qt2anf209rv0.png', '2018-07-31 11:35:27', '0');
INSERT INTO `os_storage` VALUES ('69', 'x8joh37x2rskyy682tth.png', '鲜花.png', 'image/png', '3720', 'http://127.0.0.1:8082/os/storage/fetch/x8joh37x2rskyy682tth.png', '2018-07-31 11:36:15', '0');
INSERT INTO `os_storage` VALUES ('70', '1ufc3unitp9dlvlrmtkx.png', '娱乐.png', 'image/png', '4476', 'http://127.0.0.1:8082/os/storage/fetch/1ufc3unitp9dlvlrmtkx.png', '2018-07-31 14:31:46', '0');
INSERT INTO `os_storage` VALUES ('71', 'l6wltr0qtucmbk35g250.png', '母婴用品.png', 'image/png', '3647', 'http://127.0.0.1:8082/os/storage/fetch/l6wltr0qtucmbk35g250.png', '2018-07-31 14:32:26', '0');
INSERT INTO `os_storage` VALUES ('72', 'hqlcruxzqinyb1ahdba9.png', '美食 (1).png', 'image/png', '3789', 'http://127.0.0.1:8082/os/storage/fetch/hqlcruxzqinyb1ahdba9.png', '2018-07-31 14:32:26', '0');
INSERT INTO `os_storage` VALUES ('73', 'qwjslq7rgwxaexe8q4dv.png', '丽人.png', 'image/png', '3903', 'http://127.0.0.1:8082/os/storage/fetch/qwjslq7rgwxaexe8q4dv.png', '2018-07-31 14:32:26', '0');
INSERT INTO `os_storage` VALUES ('74', 'jc6e748r3sucodzmczmm.png', '娱乐.png', 'image/png', '4476', 'http://127.0.0.1:8082/os/storage/fetch/jc6e748r3sucodzmczmm.png', '2018-07-31 14:33:42', '0');
INSERT INTO `os_storage` VALUES ('75', '0j5rvwdjoe0m352r8p9r.png', '美食 (1).png', 'image/png', '3789', 'http://127.0.0.1:8082/os/storage/fetch/0j5rvwdjoe0m352r8p9r.png', '2018-07-31 14:34:08', '0');
INSERT INTO `os_storage` VALUES ('76', 'u1tfh30gvafq7qhdef7j.png', '娱乐.png', 'image/png', '4476', 'http://127.0.0.1:8082/os/storage/fetch/u1tfh30gvafq7qhdef7j.png', '2018-07-31 14:35:02', '0');
INSERT INTO `os_storage` VALUES ('77', '0zufbox5xd3n6lj8vdpb.png', '美食 (1).png', 'image/png', '3789', 'http://127.0.0.1:8082/os/storage/fetch/0zufbox5xd3n6lj8vdpb.png', '2018-07-31 14:35:19', '0');
INSERT INTO `os_storage` VALUES ('78', '2ote6483enjgbl1yho3z.png', '美食 (1).png', 'image/png', '3789', 'http://127.0.0.1:8082/os/storage/fetch/2ote6483enjgbl1yho3z.png', '2018-07-31 14:37:27', '0');
INSERT INTO `os_storage` VALUES ('79', 'vf6rlmnaysmweseaiqsc.png', '娱乐.png', 'image/png', '4476', 'http://127.0.0.1:8082/os/storage/fetch/vf6rlmnaysmweseaiqsc.png', '2018-07-31 14:38:07', '0');
INSERT INTO `os_storage` VALUES ('80', '79w3i0mbm4ycmvepnyrz.png', '母婴用品.png', 'image/png', '3647', 'http://127.0.0.1:8082/os/storage/fetch/79w3i0mbm4ycmvepnyrz.png', '2018-07-31 14:38:20', '0');
INSERT INTO `os_storage` VALUES ('81', '4f7323x0xqgoi8ldugbh.png', '娱乐.png', 'image/png', '4476', 'http://127.0.0.1:8082/os/storage/fetch/4f7323x0xqgoi8ldugbh.png', '2018-07-31 14:39:48', '0');
INSERT INTO `os_storage` VALUES ('82', 'mwtak5ku3t63krstding.png', '酒店 (1).png', 'image/png', '2746', 'http://127.0.0.1:8082/os/storage/fetch/mwtak5ku3t63krstding.png', '2018-07-31 14:39:57', '0');
INSERT INTO `os_storage` VALUES ('83', 'pspg1e9xi9ljxvbrf1vt.png', '丽人.png', 'image/png', '3903', 'http://127.0.0.1:8082/os/storage/fetch/pspg1e9xi9ljxvbrf1vt.png', '2018-07-31 14:39:57', '0');
INSERT INTO `os_storage` VALUES ('84', '3bpu4ko29gmob4avtdek.png', '培训.png', 'image/png', '2903', 'http://127.0.0.1:8082/os/storage/fetch/3bpu4ko29gmob4avtdek.png', '2018-07-31 14:40:48', '0');
INSERT INTO `os_storage` VALUES ('85', '1xfzkhb6nvkcmwc1mzw1.png', '鲜花.png', 'image/png', '3720', 'http://127.0.0.1:8082/os/storage/fetch/1xfzkhb6nvkcmwc1mzw1.png', '2018-07-31 14:40:48', '0');
INSERT INTO `os_storage` VALUES ('86', '8cvn0ooocfp7mjgiy494.png', '生活服务.png', 'image/png', '3092', 'http://127.0.0.1:8082/os/storage/fetch/8cvn0ooocfp7mjgiy494.png', '2018-07-31 14:40:48', '0');
INSERT INTO `os_storage` VALUES ('87', 'rxkhs76gcdrd5y4m6wle.png', '美食 (1).png', 'image/png', '3789', 'http://127.0.0.1:8082/os/storage/fetch/rxkhs76gcdrd5y4m6wle.png', '2018-07-31 14:40:48', '0');
INSERT INTO `os_storage` VALUES ('88', 'aigkjo7tm4y92190tdbx.png', '母婴用品.png', 'image/png', '3647', 'http://127.0.0.1:8082/os/storage/fetch/aigkjo7tm4y92190tdbx.png', '2018-07-31 14:40:48', '0');
INSERT INTO `os_storage` VALUES ('89', 'k6wah3epf0oy33xr50d7.png', '丽人.png', 'image/png', '3903', 'http://127.0.0.1:8082/os/storage/fetch/k6wah3epf0oy33xr50d7.png', '2018-07-31 14:40:48', '0');
INSERT INTO `os_storage` VALUES ('90', 't0xyzlikdu7ycbz19nsk.png', '娱乐.png', 'image/png', '4476', 'http://127.0.0.1:8082/os/storage/fetch/t0xyzlikdu7ycbz19nsk.png', '2018-07-31 14:40:48', '0');
INSERT INTO `os_storage` VALUES ('91', 'b27wo0q0ags90xrcj1ic.png', '丽人.png', 'image/png', '3903', 'http://127.0.0.1:8082/os/storage/fetch/b27wo0q0ags90xrcj1ic.png', '2018-07-31 14:41:01', '0');
INSERT INTO `os_storage` VALUES ('92', '5a86phalb3wh0k1em1ot.png', '娱乐.png', 'image/png', '4476', 'http://127.0.0.1:8082/os/storage/fetch/5a86phalb3wh0k1em1ot.png', '2018-07-31 14:41:44', '0');
INSERT INTO `os_storage` VALUES ('93', '7oy76lk05f7u0vz80bzr.png', '美食 (1).png', 'image/png', '3789', 'http://127.0.0.1:8082/os/storage/fetch/7oy76lk05f7u0vz80bzr.png', '2018-07-31 14:41:44', '0');
INSERT INTO `os_storage` VALUES ('94', 'wyqnpf8jog02io0kj24g.png', '鲜花.png', 'image/png', '3720', 'http://127.0.0.1:8082/os/storage/fetch/wyqnpf8jog02io0kj24g.png', '2018-07-31 14:41:44', '0');
INSERT INTO `os_storage` VALUES ('95', 'gzc0aijiwg7eijccm361.png', '母婴用品.png', 'image/png', '3647', 'http://127.0.0.1:8082/os/storage/fetch/gzc0aijiwg7eijccm361.png', '2018-07-31 14:41:44', '0');
INSERT INTO `os_storage` VALUES ('96', 'a3fozmpu3x8p1v8r1tfv.png', '培训.png', 'image/png', '2903', 'http://127.0.0.1:8082/os/storage/fetch/a3fozmpu3x8p1v8r1tfv.png', '2018-07-31 14:41:44', '0');
INSERT INTO `os_storage` VALUES ('97', 'lwg7mfsh0veqkvhvd8w2.png', '生活服务.png', 'image/png', '3092', 'http://127.0.0.1:8082/os/storage/fetch/lwg7mfsh0veqkvhvd8w2.png', '2018-07-31 14:41:44', '0');
INSERT INTO `os_storage` VALUES ('98', 'totmhj0fnpmpjr4kvdi6.png', '美食 (1).png', 'image/png', '3789', 'http://127.0.0.1:8082/os/storage/fetch/totmhj0fnpmpjr4kvdi6.png', '2018-07-31 14:42:14', '0');
INSERT INTO `os_storage` VALUES ('99', 'kd192d46aw8tzfsuybl0.png', '娱乐.png', 'image/png', '4476', 'http://127.0.0.1:8082/os/storage/fetch/kd192d46aw8tzfsuybl0.png', '2018-07-31 14:44:10', '0');
INSERT INTO `os_storage` VALUES ('100', 'id1toyr3eivkyy30kpq5.png', '鲜花.png', 'image/png', '3720', 'http://127.0.0.1:8082/os/storage/fetch/id1toyr3eivkyy30kpq5.png', '2018-07-31 14:44:11', '0');
INSERT INTO `os_storage` VALUES ('101', '2h3am82a96c5nthdxp8s.png', '娱乐.png', 'image/png', '4476', 'http://127.0.0.1:8082/os/storage/fetch/2h3am82a96c5nthdxp8s.png', '2018-07-31 14:46:17', '0');
INSERT INTO `os_storage` VALUES ('102', 'hz1t7pp3g9y4b3qnvdqf.png', '娱乐.png', 'image/png', '4476', 'http://127.0.0.1:8082/os/storage/fetch/hz1t7pp3g9y4b3qnvdqf.png', '2018-07-31 14:47:31', '0');
INSERT INTO `os_storage` VALUES ('103', 'k73u4c4znsokzbkpd6za.png', '娱乐.png', 'image/png', '4476', 'http://127.0.0.1:8082/os/storage/fetch/k73u4c4znsokzbkpd6za.png', '2018-07-31 14:48:11', '0');
INSERT INTO `os_storage` VALUES ('104', '9g6ixev74dyv2oa0dgob.png', '娱乐.png', 'image/png', '4476', 'http://127.0.0.1:8082/os/storage/fetch/9g6ixev74dyv2oa0dgob.png', '2018-07-31 14:48:48', '0');
INSERT INTO `os_storage` VALUES ('105', 'hee75vh8ukdbk1u08rup.png', '美食 (1).png', 'image/png', '3789', 'http://127.0.0.1:8082/os/storage/fetch/hee75vh8ukdbk1u08rup.png', '2018-07-31 14:51:20', '0');
INSERT INTO `os_storage` VALUES ('106', '14vwjz2l8m77r6x9b5ds.png', '娱乐.png', 'image/png', '4476', 'http://127.0.0.1:8082/os/storage/fetch/14vwjz2l8m77r6x9b5ds.png', '2018-07-31 15:07:46', '0');
INSERT INTO `os_storage` VALUES ('107', 'urcsrtqpc4irvys3deo5.png', '美食 (1).png', 'image/png', '3789', 'http://127.0.0.1:8082/os/storage/fetch/urcsrtqpc4irvys3deo5.png', '2018-07-31 15:07:52', '0');
INSERT INTO `os_storage` VALUES ('108', 'e7go5w57hkdxfb62gn7h.png', '丽人.png', 'image/png', '3903', 'http://127.0.0.1:8082/os/storage/fetch/e7go5w57hkdxfb62gn7h.png', '2018-07-31 15:07:52', '0');
INSERT INTO `os_storage` VALUES ('109', '6q0j213hhmn5qcesdbs9.png', '丽人.png', 'image/png', '3903', 'http://127.0.0.1:8082/os/storage/fetch/6q0j213hhmn5qcesdbs9.png', '2018-07-31 15:17:04', '0');
INSERT INTO `os_storage` VALUES ('110', 'gbdyk2y4f8tb11w6m776.png', '娱乐.png', 'image/png', '4476', 'http://127.0.0.1:8082/os/storage/fetch/gbdyk2y4f8tb11w6m776.png', '2018-07-31 15:29:58', '0');
INSERT INTO `os_storage` VALUES ('111', '6infe258a272xyp8pmf4.png', '美食 (1).png', 'image/png', '3789', 'http://127.0.0.1:8082/os/storage/fetch/6infe258a272xyp8pmf4.png', '2018-07-31 15:30:01', '0');
INSERT INTO `os_storage` VALUES ('112', 'yfk5ifkeefqtm76pasft.png', '娱乐.png', 'image/png', '4476', 'http://127.0.0.1:8082/os/storage/fetch/yfk5ifkeefqtm76pasft.png', '2018-07-31 15:31:30', '0');
INSERT INTO `os_storage` VALUES ('113', 'sjo3irot5z82cod218ih.png', '培训.png', 'image/png', '2903', 'http://127.0.0.1:8082/os/storage/fetch/sjo3irot5z82cod218ih.png', '2018-07-31 15:31:33', '0');
INSERT INTO `os_storage` VALUES ('114', 'u0frgvcsww0bx809xaqs.png', '酒店 (1).png', 'image/png', '2746', 'http://127.0.0.1:8082/os/storage/fetch/u0frgvcsww0bx809xaqs.png', '2018-07-31 15:31:36', '0');
INSERT INTO `os_storage` VALUES ('115', '92nc715k4dckxhcllefu.jpg', '14925822213780237.jpg', 'image/jpeg', '220261', 'http://127.0.0.1:8082/os/storage/fetch/92nc715k4dckxhcllefu.jpg', '2018-07-31 17:03:20', '0');
INSERT INTO `os_storage` VALUES ('116', '54nmo0563nda2c8zvu2y.jpg', '14919007135160213.jpg', 'image/jpeg', '130125', 'http://127.0.0.1:8082/os/storage/fetch/54nmo0563nda2c8zvu2y.jpg', '2018-07-31 17:04:55', '0');
INSERT INTO `os_storage` VALUES ('117', 'ord2r8g4zbgsq3re1nqo.jpg', '14924199099661697.jpg', 'image/jpeg', '278332', 'http://127.0.0.1:8082/os/storage/fetch/ord2r8g4zbgsq3re1nqo.jpg', '2018-07-31 17:05:40', '0');
INSERT INTO `os_storage` VALUES ('118', 'b0hixup34k9sab1orqbw.jpg', '14920623353130483.jpg', 'image/jpeg', '368349', 'http://127.0.0.1:8082/os/storage/fetch/b0hixup34k9sab1orqbw.jpg', '2018-07-31 17:05:56', '0');
INSERT INTO `os_storage` VALUES ('119', 'hisnry5v7d0jb2ojekss.jpg', '14932840600970609.jpg', 'image/jpeg', '227163', 'http://127.0.0.1:8082/os/storage/fetch/hisnry5v7d0jb2ojekss.jpg', '2018-07-31 17:06:43', '0');
INSERT INTO `os_storage` VALUES ('120', 'gu8ey3d8mx7h2sch1xuk.jpg', '14931121822100127.jpg', 'image/jpeg', '274243', 'http://127.0.0.1:8082/os/storage/fetch/gu8ey3d8mx7h2sch1xuk.jpg', '2018-07-31 17:06:54', '0');
INSERT INTO `os_storage` VALUES ('121', '2kvx041qydty8p5wdt2d.jpg', '14927748974441080.jpg', 'image/jpeg', '180843', 'http://127.0.0.1:8082/os/storage/fetch/2kvx041qydty8p5wdt2d.jpg', '2018-07-31 17:07:11', '0');
INSERT INTO `os_storage` VALUES ('122', 'czyahlr29voxbifvoipw.jpg', '14920623353130483.jpg', 'image/jpeg', '368349', 'http://127.0.0.1:8082/os/storage/fetch/czyahlr29voxbifvoipw.jpg', '2018-07-31 17:07:40', '0');
INSERT INTO `os_storage` VALUES ('123', 'n46k7e2bidlkgp5rvvda.png', 'ban1.png', 'image/png', '248025', 'http://127.0.0.1:8082/os/storage/fetch/n46k7e2bidlkgp5rvvda.png', '2018-07-31 17:09:59', '0');
INSERT INTO `os_storage` VALUES ('124', 'he5fvy5fg6h8guvlplv1.jpg', 'bff2e49136fcef1fd829f5036e07f116.jpg', 'image/jpeg', '240054', 'http://127.0.0.1:8082/os/storage/fetch/he5fvy5fg6h8guvlplv1.jpg', '2018-07-31 17:10:13', '0');
INSERT INTO `os_storage` VALUES ('125', 'fj5g0elpslp69pfky88z.jpg', '65091eebc48899298171c2eb6696fe27.jpg', 'image/jpeg', '179318', 'http://127.0.0.1:8082/os/storage/fetch/fj5g0elpslp69pfky88z.jpg', '2018-07-31 17:10:26', '0');
INSERT INTO `os_storage` VALUES ('126', 'ta4pmytf7ed5vhs0qss6.gif', 'f778738c-e4f8-4870-b634-56703b4acafe.gif', 'image/gif', '58215', 'http://127.0.0.1:8082/os/storage/fetch/ta4pmytf7ed5vhs0qss6.gif', '2018-07-31 17:15:04', '0');

-- ----------------------------
-- Table structure for `t_act`
-- ----------------------------
DROP TABLE IF EXISTS `t_act`;
CREATE TABLE `t_act` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(127) NOT NULL DEFAULT '' COMMENT '商铺名称',
  `pic` varchar(255) NOT NULL DEFAULT '' COMMENT '商铺照片',
  `act_date` varchar(127) NOT NULL DEFAULT '' COMMENT '活动开始时间',
  `content` varchar(255) NOT NULL DEFAULT '' COMMENT '活动内容',
  `address` varchar(127) NOT NULL DEFAULT '' COMMENT '商铺地址',
  `priority` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='活动专场表';

-- ----------------------------
-- Records of t_act
-- ----------------------------

-- ----------------------------
-- Table structure for `t_ad`
-- ----------------------------
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='广告表';

-- ----------------------------
-- Records of t_ad
-- ----------------------------
INSERT INTO `t_ad` VALUES ('1', 'name', 'link', 'url', '1', 'test', '0', '0', '2018-07-26 09:14:35', '1');
INSERT INTO `t_ad` VALUES ('2', 'test', 'http://www.baidu.com', 'http://127.0.0.1:8082/os/storage/fetch/he5fvy5fg6h8guvlplv1.jpg', '1', 'test', '1', '1', '2018-07-26 11:51:22', '0');
INSERT INTO `t_ad` VALUES ('3', 'test1', 'http://www.baidu.com', 'http://127.0.0.1:8082/os/storage/fetch/fj5g0elpslp69pfky88z.jpg', '1', 'test1', '1', '15', '2018-07-26 14:50:57', '0');
INSERT INTO `t_ad` VALUES ('4', '商城', 'http://www.baidu.com', 'http://127.0.0.1:8082/os/storage/fetch/h52alc9rv5y9oyuaq0pw.png', '1', '双方都', '1', '45', '2018-07-26 16:52:32', '1');

-- ----------------------------
-- Table structure for `t_admin`
-- ----------------------------
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='管理员表';

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES ('1', 'admin', 'admin', 'admin', '123456', '2018-07-25 14:35:20', '10.12', '0', '2018-07-25 14:35:32', '0');
INSERT INTO `t_admin` VALUES ('2', '2', '2', '2', '2', '2018-07-25 15:16:58', '2', '0', '2018-07-25 15:17:02', '0');
INSERT INTO `t_admin` VALUES ('3', '3', '3', '3', '3', '2018-07-25 15:17:10', '3', '0', '2018-07-25 15:17:13', '0');
INSERT INTO `t_admin` VALUES ('4', '4', '4', '4', '4', '2018-07-25 15:17:23', '4', '0', '2018-07-25 15:17:26', '0');
INSERT INTO `t_admin` VALUES ('5', '5', '5', '5', '5', '2018-07-25 15:17:42', '5', '0', '2018-07-25 15:17:46', '1');
INSERT INTO `t_admin` VALUES ('6', '6', '6', '6', '6', '2018-07-25 15:17:55', '6', '0', '2018-07-25 15:17:59', '1');
INSERT INTO `t_admin` VALUES ('7', '7', '7', '7', '7', '2018-07-25 15:18:18', '7', '0', '2018-07-25 15:19:08', '1');
INSERT INTO `t_admin` VALUES ('8', '8', '8', '8', '8', '2018-07-25 15:18:17', '8', '0', '2018-07-25 15:18:58', '1');
INSERT INTO `t_admin` VALUES ('9', '9', '9', '9', '9', '2018-07-25 15:18:46', '9', '0', '2018-07-25 15:19:06', '1');
INSERT INTO `t_admin` VALUES ('10', '10', '10', '10', '10', '2018-07-25 15:18:44', '10', '0', '2018-07-25 15:19:04', '1');
INSERT INTO `t_admin` VALUES ('11', '11', '11', '11', '11', '2018-07-25 15:18:42', '111', '0', '2018-07-25 15:19:01', '1');
INSERT INTO `t_admin` VALUES ('12', 'zhangsan', 'e10adc3949ba59abbe56e057f20f883e', '张三', '15083386045', null, null, '0', '2018-07-25 17:45:40', '0');
INSERT INTO `t_admin` VALUES ('13', 'zhangsan', 'e10adc3949ba59abbe56e057f20f883e', 'zhangsan', '15083386023', null, null, '0', '2018-07-25 17:47:37', '0');

-- ----------------------------
-- Table structure for `t_category`
-- ----------------------------
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='类目表';

-- ----------------------------
-- Records of t_category
-- ----------------------------
INSERT INTO `t_category` VALUES ('1', '2', '2', '1', '0', '2018-07-27 15:41:24', '1');
INSERT INTO `t_category` VALUES ('2', 'test', 'test', 'http://127.0.0.1:8082/os/storage/fetch/zshzy997xym8rsto46e9.png', '3', '2018-07-30 14:33:57', '0');
INSERT INTO `t_category` VALUES ('3', 'test', 'test', 'http://127.0.0.1:8082/os/storage/fetch/tdwtplefmt0eeqm6m7tg.png', '2', '2018-07-30 14:35:24', '0');

-- ----------------------------
-- Table structure for `t_goods`
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) DEFAULT '0' COMMENT '商品所属类目ID',
  `shop_id` int(11) DEFAULT '0' COMMENT '商品所属店铺ID',
  `sn` varchar(63) NOT NULL DEFAULT '' COMMENT '商品编号',
  `name` varchar(127) NOT NULL DEFAULT '' COMMENT '商品名称',
  `brief` varchar(255) DEFAULT '' COMMENT '商品简介',
  `content` text COMMENT '商品详细介绍，是富文本格式',
  `pic_url` varchar(255) DEFAULT NULL COMMENT '商品页面商品图片',
  `gallery` varchar(1023) DEFAULT NULL COMMENT '商品宣传图片列表',
  `keywords` varchar(255) DEFAULT '' COMMENT '商品关键字，采用逗号间隔',
  `is_on_sale` tinyint(1) DEFAULT '1' COMMENT '是否上架',
  `priority` tinyint(3) DEFAULT '0' COMMENT '排序',
  `retail_price` decimal(10,2) DEFAULT '0.00' COMMENT '零售价格',
  `counter_price` decimal(10,2) DEFAULT '0.00' COMMENT '会员价格',
  `discountType` tinyint(3) DEFAULT '0' COMMENT '优惠类型，1.满减 2.打折 3.自定义价格',
  `discountMoney` varchar(50) DEFAULT NULL COMMENT '优惠价格 满减/打折/自定义优惠价格',
  `during` tinyint(3) DEFAULT '1' COMMENT '使用期限，1.永久 2.自定义',
  `start_date` datetime DEFAULT NULL COMMENT '商品开始使用时间',
  `end_end` datetime DEFAULT NULL COMMENT '商品过期时间',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除 1.删除，0.未删除',
  PRIMARY KEY (`id`),
  KEY `goods_sn` (`sn`),
  KEY `cat_id` (`category_id`),
  KEY `shop_id` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品基本信息表';

-- ----------------------------
-- Records of t_goods
-- ----------------------------

-- ----------------------------
-- Table structure for `t_issue`
-- ----------------------------
DROP TABLE IF EXISTS `t_issue`;
CREATE TABLE `t_issue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question` varchar(255) DEFAULT NULL COMMENT '问题标题',
  `answer` varchar(255) DEFAULT NULL COMMENT '问题答案',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除 1.删除，0.未删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='常见问题表';

-- ----------------------------
-- Records of t_issue
-- ----------------------------

-- ----------------------------
-- Table structure for `t_keyword`
-- ----------------------------
DROP TABLE IF EXISTS `t_keyword`;
CREATE TABLE `t_keyword` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keyword` varchar(127) NOT NULL DEFAULT '' COMMENT '关键字',
  `priority` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='搜索关键字表';

-- ----------------------------
-- Records of t_keyword
-- ----------------------------

-- ----------------------------
-- Table structure for `t_order`
-- ----------------------------
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
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `shop_id` (`shop_id`),
  KEY `product_id` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('1', '1', '1', '1', '1', '1', '1', '1', '2018-07-27 15:42:56', '0');

-- ----------------------------
-- Table structure for `t_product`
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) DEFAULT '0' COMMENT '产品所属类目ID',
  `shop_id` int(11) DEFAULT '0' COMMENT '产品所属店铺ID',
  `sn` varchar(63) NOT NULL DEFAULT '' COMMENT '产品编号',
  `name` varchar(127) NOT NULL DEFAULT '' COMMENT '产品名称',
  `brief` varchar(255) DEFAULT '' COMMENT '产品简介',
  `info` text COMMENT '使用须知',
  `fen` int(11) DEFAULT '0' COMMENT '份数',
  `content` text COMMENT '产品详细介绍，是富文本格式',
  `pic_url` varchar(255) DEFAULT NULL COMMENT '产品页面产品图片',
  `gallery` varchar(1023) DEFAULT NULL COMMENT '产品宣传图片列表',
  `keywords` varchar(255) DEFAULT '' COMMENT '产品关键字，采用逗号间隔',
  `is_on_sale` tinyint(1) DEFAULT '1' COMMENT '是否上架',
  `priority` int(11) DEFAULT '0' COMMENT '排序',
  `retail_price` decimal(10,2) DEFAULT '0.00' COMMENT '零售价格',
  `counter_price` decimal(10,2) DEFAULT '0.00' COMMENT '会员价格',
  `during` tinyint(3) DEFAULT '1' COMMENT '使用期限，1.永久 2.自定义',
  `start_date` date DEFAULT NULL COMMENT '产品开始使用时间',
  `end_date` date DEFAULT NULL COMMENT '产品过期时间',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除 1.删除，0.未删除',
  PRIMARY KEY (`id`),
  KEY `product_sn` (`sn`),
  KEY `cat_id` (`category_id`),
  KEY `shop_id` (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='产品基本信息表';

-- ----------------------------
-- Records of t_product
-- ----------------------------
INSERT INTO `t_product` VALUES ('1', '1', '1', 'test', 'test', 'test', 'test', '0', 'test', 'test', '[]', 'test', '1', '0', '0.00', '0.00', '1', '2018-07-31', '2018-07-31', '2018-07-31 17:19:26', '0');

-- ----------------------------
-- Table structure for `t_seller`
-- ----------------------------
DROP TABLE IF EXISTS `t_seller`;
CREATE TABLE `t_seller` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `openid` varchar(255) DEFAULT NULL COMMENT '小程序标识',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `real_name` varchar(20) NOT NULL COMMENT '真实姓名',
  `phone` varchar(11) NOT NULL COMMENT '手机号',
  `avatar_url` varchar(255) NOT NULL DEFAULT '' COMMENT '用户头像图片',
  `status` tinyint(3) NOT NULL DEFAULT '1' COMMENT '1 可用, 0 禁用',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除,0.正常，1 已删除',
  PRIMARY KEY (`id`),
  KEY `openid` (`openid`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='商家表';

-- ----------------------------
-- Records of t_seller
-- ----------------------------
INSERT INTO `t_seller` VALUES ('1', '1', '1', '1', '15083386045', 'http://127.0.0.1:8082/os/storage/fetch/ta4pmytf7ed5vhs0qss6.gif', '1', '2018-07-27 15:40:41', '0');
INSERT INTO `t_seller` VALUES ('2', null, null, '1', '15083386023', '', '1', '2018-07-30 16:16:02', '0');
INSERT INTO `t_seller` VALUES ('3', null, null, '李四4', '15083386045', '', '1', '2018-07-30 16:16:39', '0');
INSERT INTO `t_seller` VALUES ('4', null, null, '管理员', '15083386023', '', '1', '2018-07-30 16:24:24', '0');
INSERT INTO `t_seller` VALUES ('11', null, null, '1', '15083386023', '', '1', '2018-07-30 17:01:59', '0');
INSERT INTO `t_seller` VALUES ('12', null, null, '李四4', '15083386045', '', '1', '2018-07-30 17:08:41', '0');
INSERT INTO `t_seller` VALUES ('13', null, null, '管理员', '15083386023', '', '1', '2018-07-31 10:13:11', '0');
INSERT INTO `t_seller` VALUES ('14', null, null, '管理员', '15083386023', '', '1', '2018-07-31 10:20:19', '0');
INSERT INTO `t_seller` VALUES ('15', null, null, '1', '15083386023', '', '1', '2018-07-31 10:59:45', '0');
INSERT INTO `t_seller` VALUES ('16', null, null, '李四4', '15083386023', '', '1', '2018-07-31 11:02:45', '0');
INSERT INTO `t_seller` VALUES ('17', null, null, '李四4', '15083386023', '', '1', '2018-07-31 11:03:37', '0');
INSERT INTO `t_seller` VALUES ('18', null, null, '李四4', '15083386023', '', '1', '2018-07-31 11:05:56', '0');
INSERT INTO `t_seller` VALUES ('19', null, null, '张三2', '15083386045', '', '1', '2018-07-31 11:09:08', '0');

-- ----------------------------
-- Table structure for `t_shop`
-- ----------------------------
DROP TABLE IF EXISTS `t_shop`;
CREATE TABLE `t_shop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '店铺名称',
  `info` varchar(255) NOT NULL COMMENT '店铺简介',
  `address` varchar(50) NOT NULL COMMENT '店铺地址',
  `longitude` double NOT NULL DEFAULT '0' COMMENT '经度',
  `latitude` double NOT NULL DEFAULT '0' COMMENT '纬度',
  `mobile` varchar(20) NOT NULL COMMENT '手机号',
  `shop_img` varchar(255) NOT NULL DEFAULT '' COMMENT '店铺门面图片地址',
  `pic_urls` varchar(1023) DEFAULT NULL COMMENT '图片地址列表',
  `priority` int(11) DEFAULT '0' COMMENT '排序',
  `status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '-1.不可用 0.审核中 1.可用',
  `advice` varchar(255) DEFAULT NULL COMMENT '超级管理员给店家的提醒，包括为什么审核不通过等',
  `owner_id` int(10) NOT NULL COMMENT '店铺是属于哪个店家的',
  `order_num` int(10) NOT NULL DEFAULT '0' COMMENT '客户订单量',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除,0.正常，1 已删除',
  `business` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='店铺表';

-- ----------------------------
-- Records of t_shop
-- ----------------------------
INSERT INTO `t_shop` VALUES ('1', '1', '1', '1', '0', '0', '1', 'http://127.0.0.1:8082/os/storage/fetch/2kvx041qydty8p5wdt2d.jpg', null, '0', '1', '1', '1', '0', '2018-07-27 15:41:08', '0', '15:00-24:00');
INSERT INTO `t_shop` VALUES ('2', 'test', '特特', '测试1', '1', '1', '特特', 'http://127.0.0.1:8082/os/storage/fetch/czyahlr29voxbifvoipw.jpg', null, '1', '1', null, '11', '0', '2018-07-30 17:01:59', '0', '15:00-24:00');
INSERT INTO `t_shop` VALUES ('3', 'test', '特', '测试', '2', '2', '特特', 'http://127.0.0.1:8082/os/storage/fetch/gu8ey3d8mx7h2sch1xuk.jpg', null, '2', '1', null, '12', '0', '2018-07-30 17:08:41', '0', '15:00-24:00');
INSERT INTO `t_shop` VALUES ('4', '测试', '测试', '南阳市仲景路', '1', '1', '测试', 'http://127.0.0.1:8082/os/storage/fetch/hisnry5v7d0jb2ojekss.jpg', '[\"http://127.0.0.1:8082/os/storage/fetch/6hhfkqit5g1k1x1lmgdg.png\"]', '1', '1', null, '13', '0', '2018-07-31 10:13:11', '0', '15:00-24:00');
INSERT INTO `t_shop` VALUES ('5', 'xiaoxuwen', '特', '测试', '1', '1', '测试', 'http://127.0.0.1:8082/os/storage/fetch/0tl1xeitjzgfmh86k7ai.png', '[\"http://127.0.0.1:8082/os/storage/fetch/a40plt8m7d7r8mzp46is.png\"]', '1', '1', null, '14', '0', '2018-07-31 10:20:19', '0', '15:00-24:00');
INSERT INTO `t_shop` VALUES ('6', 'xiaoxuwen', 'te', '测试', '1', '1', '特特', 'http://127.0.0.1:8082/os/storage/fetch/f8w05hkob4h4l624ocwl.png', '[\"http://127.0.0.1:8082/os/storage/fetch/qg85hb95yyq4brdbfsrv.png\"]', '1', '1', null, '15', '0', '2018-07-31 10:59:45', '0', '15:00-24:00');
INSERT INTO `t_shop` VALUES ('7', 'test', 'te', '测试', '2', '2', '特特', 'http://127.0.0.1:8082/os/storage/fetch/b0hixup34k9sab1orqbw.jpg', '[\"http://127.0.0.1:8082/os/storage/fetch/nqu28gv73qwxz2ank9by.png\"]', '2', '1', null, '16', '0', '2018-07-31 11:02:45', '0', '15:00-24:00');
INSERT INTO `t_shop` VALUES ('8', 'test', 'te', '测试', '2', '2', '特特', 'http://127.0.0.1:8082/os/storage/fetch/ord2r8g4zbgsq3re1nqo.jpg', '[\"http://127.0.0.1:8082/os/storage/fetch/nqu28gv73qwxz2ank9by.png\"]', '2', '1', null, '17', '0', '2018-07-31 11:03:37', '0', '15:00-24:00');
INSERT INTO `t_shop` VALUES ('9', 'test', 'te', '测试', '2', '2', '特特', 'http://127.0.0.1:8082/os/storage/fetch/54nmo0563nda2c8zvu2y.jpg', '[\"http://127.0.0.1:8082/os/storage/fetch/nqu28gv73qwxz2ank9by.png\"]', '2', '1', null, '18', '0', '2018-07-31 11:05:56', '0', '15:00-24:00');
INSERT INTO `t_shop` VALUES ('10', '测试', '特', '测试1', '4', '4', '特特', 'http://127.0.0.1:8082/os/storage/fetch/92nc715k4dckxhcllefu.jpg', '[\"http://127.0.0.1:8082/os/storage/fetch/u0frgvcsww0bx809xaqs.png\"]', '4', '1', null, '19', '0', '2018-07-31 11:09:08', '0', '15:00-24:00');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'test', 'test', 'test', '0', 'http://127.0.0.1:8082/os/storage/fetch/ta4pmytf7ed5vhs0qss6.gif', '123456', '1', '1', 'tes', 'test', '2018-07-27', '2018-07-27 10:09:52', '0');
