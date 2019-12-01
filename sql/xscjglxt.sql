/*
Navicat MySQL Data Transfer

Source Server         : 阿里云
Source Server Version : 50728
Source Host           : 47.106.70.5:3306
Source Database       : xscjglxt

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2019-12-01 18:24:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` varchar(8) NOT NULL COMMENT '主键',
  `valid_flag` varchar(1) NOT NULL COMMENT '有效标识(0:无效;1:有效)',
  `create_time` varchar(20) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(20) DEFAULT NULL COMMENT '更新时间',
  `department_no` varchar(2) DEFAULT NULL COMMENT '学院编号',
  `department_name` varchar(50) DEFAULT NULL COMMENT '学院名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('2a6ea972', '1', '20191110002155207', '20191110002155207', '08', '医学院');
INSERT INTO `department` VALUES ('52f9210c', '1', '20191110002111587', '20191110002111587', '04', '环境与生物工程学院');
INSERT INTO `department` VALUES ('609983a8', '1', '20191110002137141', '20191110002137141', '07', '体育学院');
INSERT INTO `department` VALUES ('69187593', '1', '20191110002041474', '20191110002041474', '02', '机电工程学院');
INSERT INTO `department` VALUES ('8775b8d1', '1', '20191201171300788', '20191201171300788', '11', '外国语学院');
INSERT INTO `department` VALUES ('93850381', '1', '20191112181510620', '20191130162416266', '01', '信息工程学院');
INSERT INTO `department` VALUES ('9acfbb85', '1', '20191110002130281', '20191110002130282', '06', '基础教育学院');
INSERT INTO `department` VALUES ('ad59e44b', '1', '20191110002055479', '20191110002055479', '03', '土木工程学院');
INSERT INTO `department` VALUES ('b93c8bda', '1', '20191110002211615', '20191110002211615', '09', '文化与传播学院');
INSERT INTO `department` VALUES ('e6554943', '0', '20191110001913303', '20191112181042513', '01', '信息工程学院');
INSERT INTO `department` VALUES ('eaee3d2a', '1', '20191110002217008', '20191110002217008', '10', '管理学院');
INSERT INTO `department` VALUES ('f51fb5f4', '1', '20191110002119700', '20191110002119700', '05', '音乐学院');

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
  `id` varchar(8) NOT NULL COMMENT '主键',
  `valid_flag` varchar(1) NOT NULL COMMENT '有效标识(0:无效;1:有效)',
  `create_time` varchar(20) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(20) DEFAULT NULL COMMENT '更新时间',
  `department_id` varchar(8) DEFAULT NULL COMMENT '学院id',
  `major_id` varchar(8) DEFAULT NULL COMMENT '专业id',
  `grade_num` int(2) DEFAULT NULL COMMENT '班级编号',
  `grade_name` varchar(50) DEFAULT NULL COMMENT '班级名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grade
-- ----------------------------
INSERT INTO `grade` VALUES ('153f8613', '1', '20191117181300426', '20191117181300427', '69187593', 'dda79242', '1', '数控1班');
INSERT INTO `grade` VALUES ('17d408fa', '1', '20191117174123013', '20191117174123014', '93850381', '21639874', '1', '计科1班');
INSERT INTO `grade` VALUES ('c66b574a', '1', '20191117181243913', '20191118113038753', '93850381', '21639874', '2', '计科2班');
INSERT INTO `grade` VALUES ('cf0fa490', '1', '20191117181327403', '20191117181327403', 'ad59e44b', '0c651fc3', '1', '工造1班');
INSERT INTO `grade` VALUES ('e5bcedfb', '1', '20191117181419034', '20191117181419035', '93850381', '9d8b5065', '1', '电信1班');

-- ----------------------------
-- Table structure for major
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major` (
  `id` varchar(8) NOT NULL COMMENT '主键',
  `valid_flag` varchar(1) NOT NULL COMMENT '有效标识(0:无效;1:有效)',
  `create_time` varchar(20) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(20) DEFAULT NULL COMMENT '更新时间',
  `department_id` varchar(8) DEFAULT NULL COMMENT '学院id',
  `major_no` varchar(4) DEFAULT NULL COMMENT '专业编号',
  `major_name` varchar(50) DEFAULT NULL COMMENT '专业名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of major
-- ----------------------------
INSERT INTO `major` VALUES ('0c651fc3', '1', '20191117151847112', '20191117151847112', 'ad59e44b', '0827', '工程造价');
INSERT INTO `major` VALUES ('21639874', '1', '20191115104835973', '20191117181516192', '93850381', '0594', '计算机科学与技术');
INSERT INTO `major` VALUES ('9d8b5065', '1', '20191115105248974', '20191115105248974', '93850381', '0595', '电子信息工程');
INSERT INTO `major` VALUES ('dda79242', '1', '20191115181059750', '20191115181059751', '69187593', '0639', '数控技术');
INSERT INTO `major` VALUES ('f432ab5e', '1', '20191117151809184', '20191117151809185', '52f9210c', '0773', '食品质量与安全');
INSERT INTO `major` VALUES ('fcba7a6d', '1', '20191115181008389', '20191115181008390', '69187593', '0638', '机械工程');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` varchar(8) NOT NULL COMMENT '主键',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `valid_flag` varchar(1) NOT NULL COMMENT '有效标识(0:无效;1:有效)',
  `create_time` varchar(20) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('asdfghjk', 'teacher', '1', '20191110162939002', '20191110162939002');
INSERT INTO `role` VALUES ('qwertyui', 'admin', '1', '20191110162859001', '20191110162859001');
INSERT INTO `role` VALUES ('zxcvbnml', 'student', '1', '20191110163032022', '20191110163032022');

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `id` varchar(8) NOT NULL COMMENT '主键',
  `valid_flag` varchar(1) NOT NULL COMMENT '有效标识(0:无效;1:有效)',
  `create_time` varchar(20) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(20) DEFAULT NULL COMMENT '更新时间',
  `student_id` varchar(8) DEFAULT NULL COMMENT '学生用户id',
  `teach_subject_id` varchar(8) DEFAULT NULL COMMENT '授课表id',
  `normal_score` varchar(3) DEFAULT NULL COMMENT '平时成绩',
  `midterm_score` varchar(3) DEFAULT NULL COMMENT '期中成绩',
  `endterm_score` varchar(3) DEFAULT NULL COMMENT '期末成绩',
  `total_score` varchar(3) DEFAULT NULL COMMENT '总成绩',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='成绩表';

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES ('37ada42a', '1', '20191201170142362', '20191201170142363', 'a539bca0', '566079d1', '80', '60', '80', '76');
INSERT INTO `score` VALUES ('ee84db74', '1', '20191201170211073', '20191201170211073', '286ed59e', '566079d1', '90', '70', '87', '85');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` varchar(8) NOT NULL COMMENT '主键',
  `valid_flag` varchar(1) NOT NULL COMMENT '有效标识(0:无效;1:有效)',
  `create_time` varchar(20) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(20) DEFAULT NULL COMMENT '更新时间',
  `user_id` varchar(8) DEFAULT NULL COMMENT '用户表id',
  `sno` varchar(20) DEFAULT NULL COMMENT '学号',
  `birthday` varchar(8) DEFAULT NULL COMMENT '出生日期',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `grade_id` varchar(8) DEFAULT NULL COMMENT '班级id',
  `major_id` varchar(8) DEFAULT NULL COMMENT '专业id',
  `department_id` varchar(8) DEFAULT NULL COMMENT '学院id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生表';

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('286ed59e', '1', '20191201155916346', '20191201155916347', 'e067c1cb', '201611403202', null, '张学友', '17d408fa', '21639874', '93850381');
INSERT INTO `student` VALUES ('9c96d999', '1', '20191201155946449', '20191201155946449', '09d139ad', '201611403301', null, '刘诗诗', 'c66b574a', '21639874', '93850381');
INSERT INTO `student` VALUES ('a539bca0', '1', '20191201155858098', '20191201155858098', '41ff86df', '201611403201', null, '刘德华', '17d408fa', '21639874', '93850381');
INSERT INTO `student` VALUES ('cd98e369', '1', '20191201160032259', '20191201160032259', '458220a6', '201611505201', null, '陈冠希', '153f8613', 'dda79242', '69187593');
INSERT INTO `student` VALUES ('f26df276', '1', '20191201160011932', '20191201160011932', '4f40d1e1', '201611404201', null, '赵本山', 'e5bcedfb', '9d8b5065', '93850381');

-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject` (
  `id` varchar(8) NOT NULL COMMENT '主键',
  `valid_flag` varchar(1) NOT NULL COMMENT '有效标识(0:无效;1:有效)',
  `create_time` varchar(20) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(20) DEFAULT NULL COMMENT '更新时间',
  `subject_no` varchar(4) DEFAULT NULL COMMENT '学科编号',
  `subject_name` varchar(50) DEFAULT NULL COMMENT '课程名称',
  `credit` int(2) DEFAULT NULL COMMENT '学分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课程表';

-- ----------------------------
-- Records of subject
-- ----------------------------
INSERT INTO `subject` VALUES ('50db5755', '1', '20191125140935794', '20191125140935795', '0497', '大学英语', '3');
INSERT INTO `subject` VALUES ('a8da6769', '1', '20191125141038858', '20191125141038858', '0876', 'C++程序设计', '4');
INSERT INTO `subject` VALUES ('b1bb1588', '1', '20191120155254828', '20191120155432837', '0375', '高等数学', '5');
INSERT INTO `subject` VALUES ('cf5ce374', '1', '20191125141002836', '20191125141002836', '0874', '单片机应用', '3');
INSERT INTO `subject` VALUES ('e2ffc314', '1', '20191125141019865', '20191125141019865', '0875', '微机原理', '3');

-- ----------------------------
-- Table structure for teach_subject
-- ----------------------------
DROP TABLE IF EXISTS `teach_subject`;
CREATE TABLE `teach_subject` (
  `id` varchar(8) NOT NULL COMMENT '主键',
  `valid_flag` varchar(1) NOT NULL COMMENT '有效标识(0:无效;1:有效)',
  `create_time` varchar(20) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(20) DEFAULT NULL COMMENT '更新时间',
  `department_id` varchar(8) DEFAULT NULL COMMENT '学院id',
  `major_id` varchar(8) DEFAULT NULL COMMENT '专业id',
  `grade_id` varchar(8) DEFAULT NULL COMMENT '班级id',
  `subject_id` varchar(8) DEFAULT NULL COMMENT '课程id',
  `teacher_id` varchar(8) DEFAULT NULL COMMENT '教师id',
  `school_year` varchar(4) DEFAULT NULL COMMENT '学年',
  `school_term` varchar(1) DEFAULT NULL COMMENT '学期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='授课表';

-- ----------------------------
-- Records of teach_subject
-- ----------------------------
INSERT INTO `teach_subject` VALUES ('23da2b00', '1', '20191201160425269', '20191201160425270', '69187593', 'dda79242', '153f8613', 'a8da6769', '7edadc4e', '2019', '2');
INSERT INTO `teach_subject` VALUES ('241d3be0', '1', '20191201160216058', '20191201160216058', '93850381', '9d8b5065', 'e5bcedfb', 'cf5ce374', 'ad20161f', '2019', '1');
INSERT INTO `teach_subject` VALUES ('566079d1', '1', '20191201160154431', '20191201160154432', '93850381', '21639874', '17d408fa', 'b1bb1588', 'bf421501', '2019', '1');
INSERT INTO `teach_subject` VALUES ('901621aa', '1', '20191201171359002', '20191201171359002', '93850381', '21639874', '17d408fa', '50db5755', 'c5e6df0a', '2019', '2');
INSERT INTO `teach_subject` VALUES ('e97d01e3', '1', '20191201171418645', '20191201171418645', '93850381', '21639874', 'c66b574a', '50db5755', 'c5e6df0a', '2019', '2');
INSERT INTO `teach_subject` VALUES ('ea1499b7', '1', '20191201160233310', '20191201160233310', '93850381', '21639874', 'c66b574a', 'e2ffc314', '7edadc4e', '2019', '2');
INSERT INTO `teach_subject` VALUES ('f2bb9542', '1', '20191201171449353', '20191201171449353', '69187593', 'dda79242', '153f8613', '50db5755', 'c5e6df0a', '2019', '2');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` varchar(8) NOT NULL COMMENT '主键',
  `valid_flag` varchar(1) NOT NULL COMMENT '有效标识(0:无效;1:有效)',
  `create_time` varchar(20) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(20) DEFAULT NULL COMMENT '更新时间',
  `user_id` varchar(8) DEFAULT NULL COMMENT '用户id',
  `tno` varchar(20) DEFAULT NULL COMMENT '教师编号',
  `birthday` varchar(8) DEFAULT NULL COMMENT '生日',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `department_id` varchar(255) DEFAULT NULL COMMENT '学院id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师表';

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('7edadc4e', '1', '20191201160108483', '20191201160108484', 'f2a0d953', '0239', null, '黄春先', '93850381');
INSERT INTO `teacher` VALUES ('ad20161f', '1', '20191201160053491', '20191201160053491', 'f7e54faf', '0238', null, '陈伟', '93850381');
INSERT INTO `teacher` VALUES ('bf421501', '1', '20191201160127821', '20191201160127821', 'f3136bcf', '0335', null, '范德彪', '69187593');
INSERT INTO `teacher` VALUES ('c5e6df0a', '1', '20191201171328198', '20191201171328199', '606b04b4', '0447', null, '张四宝', '8775b8d1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(8) NOT NULL COMMENT '主键',
  `valid_flag` varchar(1) NOT NULL COMMENT '有效标识(0:无效;1:有效)',
  `create_time` varchar(20) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(20) DEFAULT NULL COMMENT '更新时间',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(200) DEFAULT NULL COMMENT '密码',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `sex` varchar(1) DEFAULT '1' COMMENT '性别(1:男;2:女)',
  `status` varchar(1) DEFAULT NULL COMMENT '身份标识(0:管理员;1:教师;2:学生)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('09d139ad', '1', '20191201155946449', '20191201155946449', '201611403301', '24656a2d17f5848e4ace035714c83da9', null, null, null, '2');
INSERT INTO `user` VALUES ('41ff86df', '1', '20191201155858101', '20191201155858102', '201611403201', 'b02ac5e0c19b7f6c44fb96ba3051c8b5', null, null, null, '2');
INSERT INTO `user` VALUES ('458220a6', '1', '20191201160032259', '20191201160032259', '201611505201', 'd7f2e31e012af2d258223bf2bcd88f60', null, null, null, '2');
INSERT INTO `user` VALUES ('45f10223', '1', '20191106181215912', '20191126161503092', 'admin', 'df655ad8d3229f3269fad2a8bab59b6c', null, null, null, '0');
INSERT INTO `user` VALUES ('4f40d1e1', '1', '20191201160011932', '20191201160011933', '201611404201', 'a83f70b8f78ebf4c36a1818dc9d71fbc', null, null, null, '2');
INSERT INTO `user` VALUES ('606b04b4', '1', '20191201171328202', '20191201171328202', '0447', 'ffca3a8802fb2c25558f8797b71d44af', null, null, null, '1');
INSERT INTO `user` VALUES ('e067c1cb', '1', '20191201155916347', '20191201155916347', '201611403202', '3102540447ee3cd7571271ec2f025cb6', null, null, null, '2');
INSERT INTO `user` VALUES ('f2a0d953', '1', '20191201160108484', '20191201160108484', '0239', '454f7c92c7b8e78ff228ba31792a47ee', null, null, null, '1');
INSERT INTO `user` VALUES ('f3136bcf', '1', '20191201160127821', '20191201160127821', '0335', 'd8b12aecd802d6ab4b30ed8f3f285a82', null, null, null, '1');
INSERT INTO `user` VALUES ('f7e54faf', '1', '20191201160053492', '20191201160053492', '0238', '8743617f4ec67ec3d2bc896174e54447', null, null, null, '1');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` varchar(8) NOT NULL COMMENT '主键',
  `valid_flag` varchar(1) NOT NULL COMMENT '有效标识(0:无效;1:有效)',
  `create_time` varchar(20) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(20) DEFAULT NULL COMMENT '更新时间',
  `user_id` varchar(8) NOT NULL COMMENT '用户id',
  `role_id` varchar(8) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('00000001', '1', '20191106181215912', '20191106181215912', '45f10223', 'qwertyui');
INSERT INTO `user_role` VALUES ('02e1078b', '1', '20191201160053564', '20191201160053564', 'f7e54faf', 'asdfghjk');
INSERT INTO `user_role` VALUES ('12f70cc6', '1', '20191201160127908', '20191201160127908', 'f3136bcf', 'asdfghjk');
INSERT INTO `user_role` VALUES ('19ad02bf', '1', '20191120223905073', '20191120223905073', '36615949', 'asdfghjk');
INSERT INTO `user_role` VALUES ('2a615550', '1', '20191201160032347', '20191201160032348', '458220a6', 'zxcvbnml');
INSERT INTO `user_role` VALUES ('3e0608cd', '1', '20191201155858195', '20191201155858195', '41ff86df', 'zxcvbnml');
INSERT INTO `user_role` VALUES ('43fa33de', '1', '20191119165042502', '20191119165042502', '216b40a4', 'zxcvbnml');
INSERT INTO `user_role` VALUES ('7b0ff8ed', '1', '20191120223738596', '20191120223738596', 'ed0faa87', 'asdfghjk');
INSERT INTO `user_role` VALUES ('91ded1c0', '1', '20191201155916432', '20191201155916432', 'e067c1cb', 'zxcvbnml');
INSERT INTO `user_role` VALUES ('a3c51c15', '1', '20191201160108566', '20191201160108566', 'f2a0d953', 'asdfghjk');
INSERT INTO `user_role` VALUES ('b4c4041c', '1', '20191201171328277', '20191201171328278', '606b04b4', 'asdfghjk');
INSERT INTO `user_role` VALUES ('bf98fafb', '1', '20191120223641960', '20191120223641965', '8a0dea80', 'asdfghjk');
INSERT INTO `user_role` VALUES ('c256d4c9', '1', '20191125141606536', '20191125141606536', 'a4b9a26b', 'asdfghjk');
INSERT INTO `user_role` VALUES ('c5b3716f', '1', '20191119180024628', '20191119180024629', '320c84e2', 'zxcvbnml');
INSERT INTO `user_role` VALUES ('d6bff8c9', '1', '20191119164238265', '20191119164238265', 'c1ef1fe1', 'zxcvbnml');
INSERT INTO `user_role` VALUES ('d97510a2', '1', '20191125141551248', '20191125141551248', '24b29a6f', 'asdfghjk');
INSERT INTO `user_role` VALUES ('dc940ed0', '1', '20191201155946532', '20191201155946533', '09d139ad', 'zxcvbnml');
INSERT INTO `user_role` VALUES ('de3246ed', '1', '20191119171012880', '20191119171012880', '4d271c6f', 'zxcvbnml');
INSERT INTO `user_role` VALUES ('fc6aa916', '1', '20191201160012014', '20191201160012014', '4f40d1e1', 'zxcvbnml');

-- ----------------------------
-- Table structure for wrap
-- ----------------------------
DROP TABLE IF EXISTS `wrap`;
CREATE TABLE `wrap` (
  `id` varchar(8) NOT NULL COMMENT '主键',
  `valid_flag` varchar(1) NOT NULL COMMENT '有效标识(0:无效;1:有效)',
  `create_time` varchar(20) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(20) DEFAULT NULL COMMENT '更新时间',
  `wrap_name` varchar(40) DEFAULT NULL COMMENT '轮播图名称',
  `wrap_desc` varchar(200) DEFAULT NULL COMMENT '轮播图介绍',
  `wrap_status` varchar(1) DEFAULT NULL COMMENT '轮播图启用状态',
  `img_url` varchar(200) DEFAULT NULL COMMENT '图片地址',
  `wrap_no` int(3) DEFAULT NULL COMMENT '序号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='轮播图';

-- ----------------------------
-- Records of wrap
-- ----------------------------
INSERT INTO `wrap` VALUES ('6b9ee5c7', '1', '20191130145910805', '20191130162632213', '图片2', '第二张图片', '1', '/uploads/2019113014591059图片2.jpg', '2');
INSERT INTO `wrap` VALUES ('ec9ad7fc', '1', '20191130144738212', '20191130160733919', '图片1', '第一张图片', '1', '/uploads/2019113014473847图片1.jpg', '1');
