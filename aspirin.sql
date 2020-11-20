/*
 Navicat Premium Data Transfer

 Source Server         : aspirin
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : alexyan.cn:3306
 Source Schema         : aspirin

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 20/11/2020 14:08:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for administrator
-- ----------------------------
DROP TABLE IF EXISTS `administrator`;
CREATE TABLE `administrator`
(
    `id`                  bigint(20)                                                    NOT NULL COMMENT '主键',
    `create_time`         datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `update_time`         datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    `username`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
    `password`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
    `real_name`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '姓名',
    `gender`              tinyint(4)                                                    NULL DEFAULT 0 COMMENT '性别',
    `phone_number`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '手机号',
    `contact_information` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '其他联系方式, 比如qq或邮箱等',
    `nickname`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '昵称',
    `avatar_url`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '头像URL',
    `status`              tinyint(4)                                                    NULL DEFAULT 0 COMMENT '用户账号状态',
    `role`                tinyint(4)                                                    NULL DEFAULT NULL COMMENT '角色',
    `number`              varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工号',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `username` (`username`) USING BTREE,
    UNIQUE INDEX `number` (`number`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of administrator
-- ----------------------------
INSERT INTO `administrator`
VALUES (1, '2020-09-22 11:11:49', '2020-11-18 15:51:00', 'a001',
        'd46a801dedaa19c8140c0820ae5046cb255eea1e6f4724a1b8bc7db200598c43', '张三', 0, '13111112222', 'QQ: 356356', '清水',
        'http://qiniu-cdn.alexyan.cn/administrator/admin1/avatar/defaultAvatar.jpg', 0, 0, 'a001');
INSERT INTO `administrator`
VALUES (2, '2020-09-22 11:13:44', '2020-11-18 15:51:13', 'a002',
        '50235dd7949c721870bf83b2f3fcd5b262dd205066826f230655ddf3293fdebf', '李四', 0, '13245454545', 'QQ: 1111', '枫叶',
        'http://qiniu-cdn.alexyan.cn/administrator/admin1/avatar/defaultAvatar.jpg', 0, 0, 'a002');
INSERT INTO `administrator`
VALUES (3, '2020-11-18 15:40:37', '2020-11-18 15:52:22', 'a1',
        '01b825b0d35b4b241e2ded8e9c183f27d6226f79039bbe3b0b57eee065c8af40', '哈哈', 0, '1233445', 'QQ: 1111', '哈哈哈',
        'cdn.alexyan.cn/administrator/admin1/avatar/defaultAvatar.jpg', 0, 0, 'a1');

-- ----------------------------
-- Table structure for bulletin
-- ----------------------------
DROP TABLE IF EXISTS `bulletin`;
CREATE TABLE `bulletin`
(
    `id`                   bigint(20)                                                     NOT NULL COMMENT '主键',
    `create_time`          datetime(0)                                                    NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `update_time`          datetime(0)                                                    NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    `title`                varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '标题',
    `content`              varchar(9999) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '内容',
    `administrator_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '发布公告的管理员工号',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `administrator_number` (`administrator_number`) USING BTREE,
    CONSTRAINT `bulletin_ibfk_1` FOREIGN KEY (`administrator_number`) REFERENCES `administrator` (`number`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bulletin
-- ----------------------------
INSERT INTO `bulletin`
VALUES (123, '2020-10-02 15:29:33', '2020-10-02 17:41:18', 'testTitle1', 'testContent1', 'a001');
INSERT INTO `bulletin`
VALUES (124, '2020-10-12 23:06:25', '2020-10-12 23:06:29', 'testTitle2', 'testContent2', 'a001');
INSERT INTO `bulletin`
VALUES (125, '2020-10-12 23:07:07', '2020-10-12 23:07:42', 'testTitle3', 'testContent3', 'a002');

-- ----------------------------
-- Table structure for classroom
-- ----------------------------
DROP TABLE IF EXISTS `classroom`;
CREATE TABLE `classroom`
(
    `id`          bigint(20)                                                    NOT NULL COMMENT '主键',
    `create_time` datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `update_time` datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    `location`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '地点',
    `number`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '编号',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `number` (`number`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of classroom
-- ----------------------------
INSERT INTO `classroom`
VALUES (1, '2020-10-02 22:37:17', '2020-10-02 22:37:20', '教3_101', '001');
INSERT INTO `classroom`
VALUES (2, '2020-10-02 22:38:03', '2020-10-02 22:38:06', '教3_102', '002');
INSERT INTO `classroom`
VALUES (3, '2020-10-02 22:38:29', '2020-10-02 22:38:31', '教3_202', '005');
INSERT INTO `classroom`
VALUES (4, '2020-10-02 22:38:55', '2020-10-02 22:38:57', '教3_302', '007');
INSERT INTO `classroom`
VALUES (5, '2020-10-02 22:39:19', '2020-10-02 22:39:21', '教3_405', '009');
INSERT INTO `classroom`
VALUES (6, '2020-10-02 22:39:49', '2020-10-02 22:39:52', '教3_601', '012');
INSERT INTO `classroom`
VALUES (7, '2020-10-02 22:40:13', '2020-10-02 22:40:13', '教4_105', '101');
INSERT INTO `classroom`
VALUES (8, '2020-10-02 22:40:44', '2020-10-02 22:40:46', '教4_201', '112');
INSERT INTO `classroom`
VALUES (9, '2020-10-02 22:41:06', '2020-10-02 22:41:08', '教4_302', '115');
INSERT INTO `classroom`
VALUES (10, '2020-10-02 22:41:27', '2020-10-02 22:41:29', '教4_401', '117');
INSERT INTO `classroom`
VALUES (11, '2020-10-02 22:42:35', '2020-10-02 22:42:38', '教4_402', '118');
INSERT INTO `classroom`
VALUES (12, '2020-10-02 22:43:01', '2020-10-02 22:43:04', '教2_107', '212');
INSERT INTO `classroom`
VALUES (13, '2020-10-02 22:43:24', '2020-10-02 22:43:27', '教2_201', '221');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`
(
    `id`          bigint(20)                                                    NOT NULL COMMENT '主键',
    `create_time` datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `update_time` datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    `icon_url`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '课程图标',
    `name`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '课程名',
    `number`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '课程编号',
    `type`        tinyint(4)                                                    NULL DEFAULT NULL COMMENT '课程类型',
    `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '课程简介',
    `period`      tinyint(4)                                                    NULL DEFAULT NULL COMMENT '学时',
    `credit`      float(10, 2)                                                  NULL DEFAULT NULL COMMENT '学分',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `number` (`number`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course`
VALUES (1, '2020-10-02 21:28:42', '2020-10-09 14:56:47', 'http://qiniu-cdn.alexyan.cn/course/001/icon/1高等数学.jpg',
        '高等数学', '001', 0, '秃头', 64, 3.00);
INSERT INTO `course`
VALUES (2, '2020-10-02 21:31:43', '2020-10-09 14:56:47', 'http://qiniu-cdn.alexyan.cn/course/002/icon/2离散数学.jpg',
        '离散数学', '002', 1, '秃头', 54, 4.00);
INSERT INTO `course`
VALUES (3, '2020-10-02 21:33:19', '2020-10-09 14:56:47', 'http://qiniu-cdn.alexyan.cn/course/003/icon/3大学物理.jpg',
        '大学物理', '003', 0, '秃头', 48, 5.00);
INSERT INTO `course`
VALUES (4, '2020-10-02 21:34:06', '2020-10-09 14:56:47', 'http://qiniu-cdn.alexyan.cn/course/004/icon/4Python.jpg',
        'Python', '004', 3, '选择性秃头', 36, 2.00);
INSERT INTO `course`
VALUES (5, '2020-10-02 21:35:04', '2020-10-07 23:33:56', 'http://qiniu-cdn.alexyan.cn/course/005/icon/5汇编语言.jpg',
        '汇编语言', '005', 3, '选择性秃头', 48, 3.00);
INSERT INTO `course`
VALUES (6, '2020-10-02 21:35:58', '2020-10-09 14:56:47', 'http://qiniu-cdn.alexyan.cn/course/006/icon/6Windows.jpg',
        'Windows', '006', 3, '选择性秃头', 36, 2.00);
INSERT INTO `course`
VALUES (7, '2020-10-02 21:37:15', '2020-10-09 14:56:47', 'http://qiniu-cdn.alexyan.cn/course/007/icon/7Linux.jpg',
        'Linux', '007', 3, '选择性秃头', 56, 3.00);
INSERT INTO `course`
VALUES (8, '2020-10-02 21:37:53', '2020-10-09 14:56:47', 'http://qiniu-cdn.alexyan.cn/course/008/icon/8汉语言文学.jpg',
        '汉语言文学', '008', 2, '间接性秃头', 32, 2.00);
INSERT INTO `course`
VALUES (9, '2020-10-02 21:39:39', '2020-10-09 14:56:47', 'http://qiniu-cdn.alexyan.cn/course/009/icon/9茶艺.png', '茶艺',
        '009', 2, '间接性秃头', 24, 2.00);
INSERT INTO `course`
VALUES (10, '2020-10-02 21:42:01', '2020-11-18 17:38:33', 'http://qiniu-cdn.alexyan.cn/course/010/icon/10大数据技术.jpg',
        '大数据技术', '010', 2, '间接性秃头', 24, 1.00);
INSERT INTO `course`
VALUES (11, '2020-10-02 21:43:37', '2020-10-09 14:56:47', 'http://qiniu-cdn.alexyan.cn/course/011/icon/11职场学.jpg',
        '职场学', '011', 2, '间接性秃头', 36, 2.00);
INSERT INTO `course`
VALUES (12, '2020-10-02 21:44:28', '2020-10-09 14:56:47', 'http://qiniu-cdn.alexyan.cn/course/012/icon/12丝绸之路.jpg',
        '丝绸之路', '012', 2, '间接性秃头', 36, 2.00);
INSERT INTO `course`
VALUES (13, '2020-10-15 10:45:00', '2020-10-15 10:46:01', 'http://qiniu-cdn.alexyan.cn/course/013/icon/13线性代数.jpg',
        '线性代数', '013', 2, '秃头', 36, 3.00);
INSERT INTO `course`
VALUES (14, '2020-10-15 10:46:22', '2020-10-15 10:47:15', 'http://qiniu-cdn.alexyan.cn/course/014/icon/14C语言.jpg',
        'C语言', '014', 1, '秃头', 48, 2.00);
INSERT INTO `course`
VALUES (15, '2020-10-15 10:47:22', '2020-10-15 10:48:12', 'http://qiniu-cdn.alexyan.cn/course/015/icon/15C++.jpg',
        'C++', '015', 1, '秃头', 36, 2.00);
INSERT INTO `course`
VALUES (16, '2020-10-15 10:48:19', '2020-11-18 17:44:29',
        'http://qiniu-cdn.alexyan.cn/course/016/icon/16Hadoop大数据技术.jpg', 'Hadoop大数据技术', '016', 2, '选择性秃头', 36, 3.00);
INSERT INTO `course`
VALUES (17, '2020-10-15 10:49:27', '2020-10-15 10:49:37', 'http://qiniu-cdn.alexyan.cn/course/017/icon/17计算机组成原理.jpg',
        '计算机组成原理', '017', 2, '间接性秃头', 48, 3.00);
INSERT INTO `course`
VALUES (18, '2020-10-15 10:50:42', '2020-10-15 10:50:45', 'http://qiniu-cdn.alexyan.cn/course/018/icon/18数据结构.jpg',
        '数据结构', '018', 2, '秃头', 54, 4.00);

-- ----------------------------
-- Table structure for course_detail
-- ----------------------------
DROP TABLE IF EXISTS `course_detail`;
CREATE TABLE `course_detail`
(
    `id`               bigint(20)                                                    NOT NULL COMMENT '主键',
    `create_time`      datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `update_time`      datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    `teacher_number`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '教师编号',
    `course_number`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '课程编号',
    `scheduling_time`  tinyint(4)                                                    NULL DEFAULT 0 COMMENT '第几节课',
    `day_of_the_week`  tinyint(4)                                                    NULL DEFAULT 0 COMMENT '星期几',
    `classroom_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '教室编号',
    `semester`         tinyint(4)                                                    NULL DEFAULT NULL COMMENT '学期',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `teacher_number` (`teacher_number`) USING BTREE,
    INDEX `course_number` (`course_number`) USING BTREE,
    INDEX `classroom_number` (`classroom_number`) USING BTREE,
    CONSTRAINT `course_detail_ibfk_1` FOREIGN KEY (`teacher_number`) REFERENCES `teacher` (`number`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `course_detail_ibfk_2` FOREIGN KEY (`course_number`) REFERENCES `course` (`number`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `course_detail_ibfk_3` FOREIGN KEY (`classroom_number`) REFERENCES `classroom` (`number`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_detail
-- ----------------------------
INSERT INTO `course_detail`
VALUES (1, '2020-10-12 23:11:31', '2020-10-12 23:11:34', '00000', '001', 1, 1, '001', 2);
INSERT INTO `course_detail`
VALUES (666, '2020-10-13 01:10:39', '2020-10-13 01:10:39', '00000', '001', 1, 3, '001', 1);

-- ----------------------------
-- Table structure for faculty
-- ----------------------------
DROP TABLE IF EXISTS `faculty`;
CREATE TABLE `faculty`
(
    `id`           bigint(20)                                                    NOT NULL COMMENT '主键',
    `create_time`  datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `update_time`  datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    `name`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '学院名',
    `phone_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '工作联系电话',
    `avatar_url`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '学院图标URL',
    `number`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学院编号',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `number` (`number`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of faculty
-- ----------------------------
INSERT INTO `faculty`
VALUES (1, '2020-09-29 09:05:19', '2020-11-11 19:51:29', '计算机与信息工程学院', '19484733432', '', '计算机与信息工程学院');
INSERT INTO `faculty`
VALUES (2, '2020-10-02 22:02:27', '2020-11-11 19:51:37', '文学院', '18736849924', '', '文学院');
INSERT INTO `faculty`
VALUES (3, '2020-10-02 22:04:03', '2020-11-11 19:51:42', '数统学院', '18836649294', '', '数统学院');
INSERT INTO `faculty`
VALUES (4, '2020-10-02 22:05:47', '2020-11-11 19:51:46', '物电学院', '17365759202', '', '物电学院');
INSERT INTO `faculty`
VALUES (5, '2020-10-02 22:06:22', '2020-11-11 19:51:51', '生命科学学院', '17736284902', '', '生命科学学院');

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade`
(
    `id`               bigint(20)                                                    NOT NULL COMMENT '主键',
    `create_time`      datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `update_time`      datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    `student_number`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '学生编号',
    `course_detail_id` bigint(20)                                                    NULL DEFAULT NULL COMMENT '课程详情ID',
    `regular_scores`   float(10, 2)                                                  NULL DEFAULT 0.00 COMMENT '平时分',
    `exam_scores`      float(10, 2)                                                  NULL DEFAULT 0.00 COMMENT '考试分',
    `submitted`        tinyint(1)                                                    NULL DEFAULT 0 COMMENT '打分是否提交',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `student_number` (`student_number`) USING BTREE,
    INDEX `grade_ibfk_2` (`course_detail_id`) USING BTREE,
    CONSTRAINT `grade_ibfk_1` FOREIGN KEY (`student_number`) REFERENCES `student` (`number`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `grade_ibfk_2` FOREIGN KEY (`course_detail_id`) REFERENCES `course_detail` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grade
-- ----------------------------
INSERT INTO `grade`
VALUES (3, '2020-10-09 09:06:21', '2020-10-14 22:41:47', '1', 1, 78.00, 87.00, 0);
INSERT INTO `grade`
VALUES (7, '2020-10-09 09:12:07', '2020-10-13 11:26:48', '201822111910102', 1, 90.00, 95.00, 0);
INSERT INTO `grade`
VALUES (8, '2020-10-09 09:12:35', '2020-10-13 13:23:02', '201822111910103', 666, 96.00, 98.00, 0);

-- ----------------------------
-- Table structure for specialty
-- ----------------------------
DROP TABLE IF EXISTS `specialty`;
CREATE TABLE `specialty`
(
    `id`             bigint(20)                                                    NOT NULL COMMENT '主键',
    `create_time`    datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `update_time`    datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    `name`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '专业名',
    `faculty_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属学院编号',
    `number`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '专业编号',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `number` (`number`) USING BTREE,
    INDEX `faculty_number` (`faculty_number`) USING BTREE,
    CONSTRAINT `specialty_ibfk_1` FOREIGN KEY (`faculty_number`) REFERENCES `faculty` (`number`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of specialty
-- ----------------------------
INSERT INTO `specialty`
VALUES (123, '2020-09-29 09:05:21', '2020-11-13 01:16:39', '软件工程', '计算机与信息工程学院', '软件工程');
INSERT INTO `specialty`
VALUES (124, '2020-10-02 22:10:40', '2020-11-13 01:16:43', '大数据', '计算机与信息工程学院', '大数据');
INSERT INTO `specialty`
VALUES (125, '2020-10-02 22:11:11', '2020-11-13 01:16:47', '物联网', '计算机与信息工程学院', '物联网');
INSERT INTO `specialty`
VALUES (126, '2020-10-02 22:11:41', '2020-11-13 01:16:51', '通信工程', '计算机与信息工程学院', '通信工程');
INSERT INTO `specialty`
VALUES (201, '2020-10-02 22:12:24', '2020-11-13 01:16:57', '汉语言文学', '文学院', '汉语言文学');
INSERT INTO `specialty`
VALUES (202, '2020-10-02 22:14:14', '2020-11-13 01:17:01', '语言学', '文学院', '语言学');
INSERT INTO `specialty`
VALUES (203, '2020-10-02 22:15:27', '2020-11-13 01:17:05', '世界文学', '文学院', '世界文学');
INSERT INTO `specialty`
VALUES (311, '2020-10-02 22:16:08', '2020-11-13 01:17:10', '数学', '数统学院', '数学');
INSERT INTO `specialty`
VALUES (312, '2020-10-02 22:17:50', '2020-11-13 01:17:14', '应用数学', '数统学院', '应用数学');
INSERT INTO `specialty`
VALUES (313, '2020-10-02 22:21:36', '2020-11-13 01:17:19', '统计学', '数统学院', '统计学');
INSERT INTO `specialty`
VALUES (401, '2020-10-02 22:22:24', '2020-11-13 01:17:23', '微电子', '物电学院', '微电子');
INSERT INTO `specialty`
VALUES (402, '2020-10-02 22:24:10', '2020-11-13 01:17:26', '物理学', '物电学院', '物理学');
INSERT INTO `specialty`
VALUES (403, '2020-10-02 22:25:31', '2020-11-13 01:17:31', '电子信息工程', '物电学院', '电子信息工程');
INSERT INTO `specialty`
VALUES (531, '2020-10-02 22:26:26', '2020-11-13 01:17:35', '微生物学', '生命科学学院', '微生物学');
INSERT INTO `specialty`
VALUES (532, '2020-10-02 22:26:59', '2020-11-13 01:17:39', '遗传学', '生命科学学院', '遗传学');
INSERT INTO `specialty`
VALUES (533, '2020-10-02 22:28:25', '2020-11-13 01:17:43', '生物化学', '生命科学学院', '生物化学');

-- ----------------------------
-- Table structure for specialty_course
-- ----------------------------
DROP TABLE IF EXISTS `specialty_course`;
CREATE TABLE `specialty_course`
(
    `id`               bigint(20)                                                    NOT NULL COMMENT '主键',
    `create_time`      datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `update_time`      datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    `specialty_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '专业编号',
    `course_number`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '课程编号',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `specialty_number` (`specialty_number`) USING BTREE,
    INDEX `specialty_course_ibfk_2` (`course_number`) USING BTREE,
    CONSTRAINT `specialty_course_ibfk_1` FOREIGN KEY (`specialty_number`) REFERENCES `specialty` (`number`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `specialty_course_ibfk_2` FOREIGN KEY (`course_number`) REFERENCES `course` (`number`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of specialty_course
-- ----------------------------
INSERT INTO `specialty_course`
VALUES (1, '2020-10-07 22:48:41', '2020-10-08 02:42:22', '软件工程', '001');
INSERT INTO `specialty_course`
VALUES (2, '2020-10-07 22:48:47', '2020-10-08 02:42:55', '软件工程', '002');
INSERT INTO `specialty_course`
VALUES (3, '2020-10-12 22:51:19', '2020-10-12 22:51:22', '软件工程', '003');
INSERT INTO `specialty_course`
VALUES (4, '2020-10-12 22:53:07', '2020-10-12 23:00:10', '软件工程', '004');
INSERT INTO `specialty_course`
VALUES (5, '2020-10-12 22:59:53', '2020-10-12 22:59:56', '软件工程', '005');
INSERT INTO `specialty_course`
VALUES (6, '2020-10-12 23:00:15', '2020-10-12 23:00:18', '软件工程', '006');
INSERT INTO `specialty_course`
VALUES (7, '2020-10-12 23:00:31', '2020-10-12 23:00:34', '软件工程', '007');
INSERT INTO `specialty_course`
VALUES (8, '2020-10-12 23:00:47', '2020-10-12 23:00:49', '软件工程', '011');
INSERT INTO `specialty_course`
VALUES (9, '2020-10-12 23:01:29', '2020-10-12 23:01:32', '大数据', '008');
INSERT INTO `specialty_course`
VALUES (10, '2020-10-12 23:03:06', '2020-10-12 23:03:09', '大数据', '009');
INSERT INTO `specialty_course`
VALUES (11, '2020-10-12 23:03:45', '2020-10-12 23:03:48', '物联网', '010');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`
(
    `id`                  bigint(20)                                                    NOT NULL COMMENT '主键',
    `create_time`         datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `update_time`         datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    `username`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
    `password`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
    `real_name`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '姓名',
    `gender`              tinyint(4)                                                    NULL DEFAULT 0 COMMENT '性别',
    `phone_number`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '手机号',
    `contact_information` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '其他联系方式, 比如qq或邮箱等',
    `nickname`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '昵称',
    `avatar_url`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '头像URL',
    `status`              tinyint(4)                                                    NULL DEFAULT 0 COMMENT '用户账号状态',
    `role`                tinyint(4)                                                    NULL DEFAULT NULL COMMENT '角色',
    `number`              varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学号',
    `faculty_number`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学院编号',
    `specialty_number`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '专业编号',
    `admission_year`      smallint(6)                                                   NULL DEFAULT NULL COMMENT '入学年份',
    `semester`            smallint(6)                                                   NULL DEFAULT NULL COMMENT '所处学期',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `username` (`username`) USING BTREE,
    UNIQUE INDEX `number` (`number`) USING BTREE,
    INDEX `faculty_number` (`faculty_number`) USING BTREE,
    INDEX `specialty_number` (`specialty_number`) USING BTREE,
    CONSTRAINT `student_ibfk_1` FOREIGN KEY (`faculty_number`) REFERENCES `faculty` (`number`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `student_ibfk_2` FOREIGN KEY (`specialty_number`) REFERENCES `specialty` (`number`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student`
VALUES (1, '2020-09-29 09:08:46', '2020-11-13 08:26:21', 'student',
        '5ee6fd533cce1ca74bedf50546bdc9787367697458697eef79c27f58ef64097e', '林冰', 1, '12341424512', 'QQ: 948478227',
        '咯咯咯', 'http://qiniu-cdn.alexyan.cn/administrator/admin1/avatar/defaultAvatar.jpg', 0, 2, '1', '计算机与信息工程学院',
        '软件工程', 2018, 1);
INSERT INTO `student`
VALUES (2, '2020-10-02 21:01:44', '2020-10-09 14:04:27', 'kakaka', '123456', '陈晨', 2, '17378849992', 'QQ:894665786',
        '嗷嗷嗷', 'http://qiniu-cdn.alexyan.cn/administrator/admin1/avatar/defaultAvatar.jpg', 0, 2, '201822111910102',
        '计算机与信息工程学院', '大数据', 2018, 1);
INSERT INTO `student`
VALUES (3, '2020-10-02 21:16:09', '2020-10-09 14:04:27', 'yiyiyi', '123456', '李冰', 1, '14637884780', 'QQ:765432298',
        '啦啦啦', 'http://qiniu-cdn.alexyan.cn/administrator/admin1/avatar/defaultAvatar.jpg', 0, 2, '201822111910103',
        '计算机与信息工程学院', '物联网', 2018, 1);
INSERT INTO `student`
VALUES (4, '2020-10-02 21:18:09', '2020-10-09 14:04:27', 'yayaya', '123456', '李白', 1, '17264859305', 'QQ:837664624',
        '哈哈哈', 'http://qiniu-cdn.alexyan.cn/administrator/admin1/avatar/defaultAvatar.jpg', 0, 2, '201722111910104',
        '计算机与信息工程学院', '软件工程', 2017, 1);
INSERT INTO `student`
VALUES (5, '2020-10-02 21:46:44', '2020-10-09 14:04:27', 'lalala', '123456', '杜甫', 1, '16278499521', 'QQ:826431834',
        '嘿嘿嘿', 'http://qiniu-cdn.alexyan.cn/administrator/admin1/avatar/defaultAvatar.jpg', 0, 2, '201822111920105',
        '文学院', '汉语言文学', 2018, 1);
INSERT INTO `student`
VALUES (6, '2020-10-02 21:51:34', '2020-10-09 14:04:27', 'hahaha', '123456', '张亮', 2, '18265645824', 'QQ:273765895',
        '嘻嘻嘻', 'http://qiniu-cdn.alexyan.cn/administrator/admin1/avatar/defaultAvatar.jpg', 0, 2, '201822111910206',
        '文学院', '语言学', 2018, 1);
INSERT INTO `student`
VALUES (7, '2020-10-02 21:53:33', '2020-10-09 14:04:27', 'kukuku', '123456', '刘备', 1, '18266485932', 'QQ:826648596',
        '呵呵呵', 'http://qiniu-cdn.alexyan.cn/administrator/admin1/avatar/defaultAvatar.jpg', 0, 2, '201822111910107',
        '数统学院', '数学', 2018, 1);
INSERT INTO `student`
VALUES (8, '2020-10-02 21:55:40', '2020-10-09 14:04:27', 'quququ', '123456', '关羽', 2, '17653849967', 'QQ:465368956',
        '咕咕咕', 'http://qiniu-cdn.alexyan.cn/administrator/admin1/avatar/defaultAvatar.jpg', 0, 2, '201922111910120',
        '数统学院', '应用数学', 2019, 1);
INSERT INTO `student`
VALUES (9, '2020-10-02 21:57:06', '2020-10-09 14:04:27', 'ououou', '123456', '张飞', 1, '17365589265', 'QQ:183655895',
        '叽叽叽', 'http://qiniu-cdn.alexyan.cn/administrator/admin1/avatar/defaultAvatar.jpg', 0, 2, '201922111910122',
        '生命科学学院', '遗传学', 2019, 1);
INSERT INTO `student`
VALUES (10, '2020-10-02 22:31:07', '2020-10-09 14:04:27', 'mimimi', '123456', '孙尚香', 2, '18376459323', 'QQ:837546599',
        '呱呱呱', 'http://qiniu-cdn.alexyan.cn/administrator/admin1/avatar/defaultAvatar.jpg', 0, 2, '201922111910130',
        '生命科学学院', '微生物学', 2019, 1);
INSERT INTO `student`
VALUES (11, '2020-10-02 22:33:41', '2020-10-09 14:04:27', 'ananan', '123456', '王丽', 2, '16543567890', 'QQ:754322578',
        '汪汪汪', 'http://qiniu-cdn.alexyan.cn/administrator/admin1/avatar/defaultAvatar.jpg', 0, 2, '201922111910140',
        '物电学院', '微电子', 2019, 1);
INSERT INTO `student`
VALUES (12, '2020-10-02 22:35:24', '2020-10-09 14:04:27', 'enenen', '123456', '诸葛亮', 2, '15784325788', 'QQ:764322689',
        '喵喵喵', 'http://qiniu-cdn.alexyan.cn/administrator/admin1/avatar/defaultAvatar.jpg', 0, 2, '201722111910142',
        '物电学院', '电子信息工程', 2017, 1);
INSERT INTO `student`
VALUES (13, '2020-11-16 17:08:52', '2020-11-18 17:28:51', '666', '123456', '墨墨', 2, '', '', '', '', 0, 2, '666',
        '计算机与信息工程学院', '软件工程', 2018, 5);
INSERT INTO `student`
VALUES (1328311535178305537, '2020-11-16 20:18:43', '2020-11-16 20:18:43', '777',
        '64cbd6be3e00847c2df37b9e244d6c0855e5a29ea40b23a4ccd59fc6660a4c78', '清清', 1, '', '', '',
        'http://qiniu-cdn.alexyan.cn/administrator/admin1/avatar/defaultAvatar.jpg', 0, 2, '777', '文学院', '语言学', NULL,
        NULL);
INSERT INTO `student`
VALUES (1328312671037771777, '2020-11-16 20:23:14', '2020-11-16 20:23:14', '222',
        'd23f76a3ddf4a17507496c674748a62ef0f820ca2e77a6ada44c9a2d20573a61', '孤鹤', 1, '', '', '',
        'http://qiniu-cdn.alexyan.cn/administrator/admin1/avatar/defaultAvatar.jpg', 0, 2, '222', '文学院', '汉语言文学', 2018,
        5);
INSERT INTO `student`
VALUES (1328670671841742849, '2020-11-17 20:05:48', '2020-11-17 20:05:48', '333',
        'ba0c74ebb87427999a0300c01e282725c9f332cdf8decd151d28b5bf732f9351', 'ning mo', 1, '', '', '',
        'http://qiniu-cdn.alexyan.cn/administrator/admin1/avatar/defaultAvatar.jpg', 0, 2, '333', '计算机与信息工程学院', '软件工程',
        2000, NULL);
INSERT INTO `student`
VALUES (1328986653185454082, '2020-11-18 17:01:24', '2020-11-18 17:01:24', '1234',
        'ec2350ac58f39d5168855e0b03975e91f3b4e6dd299734a05a0d81d7c3b35349', '1觉得是分开', 1, '', '', '',
        'http://qiniu-cdn.alexyan.cn/administrator/admin1/avatar/defaultAvatar.jpg', 0, 2, '1234', '计算机与信息工程学院', '大数据',
        2018, 5);

-- ----------------------------
-- Table structure for student_course_detail
-- ----------------------------
DROP TABLE IF EXISTS `student_course_detail`;
CREATE TABLE `student_course_detail`
(
    `id`               bigint(20)                                                    NOT NULL COMMENT '主键',
    `create_time`      datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `update_time`      datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    `student_number`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学生编号',
    `course_detail_id` bigint(20)                                                    NULL DEFAULT NULL COMMENT '课程详情id',
    `status`           tinyint(4)                                                    NULL DEFAULT NULL COMMENT '选课状态',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `student_number` (`student_number`) USING BTREE,
    INDEX `student_course_detail_ibfk_2` (`course_detail_id`) USING BTREE,
    CONSTRAINT `student_course_detail_ibfk_1` FOREIGN KEY (`student_number`) REFERENCES `student` (`number`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `student_course_detail_ibfk_2` FOREIGN KEY (`course_detail_id`) REFERENCES `course_detail` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_course_detail
-- ----------------------------
INSERT INTO `student_course_detail`
VALUES (1, '2020-10-12 23:23:45', '2020-10-12 23:23:48', '1', NULL, 0);
INSERT INTO `student_course_detail`
VALUES (2, '2020-10-12 23:24:26', '2020-10-12 23:24:29', '1', NULL, 0);
INSERT INTO `student_course_detail`
VALUES (3, '2020-10-12 23:24:47', '2020-10-12 23:25:09', '1', NULL, 1);
INSERT INTO `student_course_detail`
VALUES (4, '2020-10-12 23:25:12', '2020-10-12 23:25:15', '1', NULL, 1);
INSERT INTO `student_course_detail`
VALUES (5, '2020-10-12 23:25:27', '2020-10-12 23:25:30', '1', NULL, 1);
INSERT INTO `student_course_detail`
VALUES (6, '2020-10-12 23:25:47', '2020-10-12 23:25:50', '1', NULL, 1);
INSERT INTO `student_course_detail`
VALUES (7, '2020-10-12 23:26:03', '2020-10-13 01:08:06', '1', NULL, 0);
INSERT INTO `student_course_detail`
VALUES (8, '2020-10-12 23:26:20', '2020-10-14 18:01:15', '1', 1, 0);
INSERT INTO `student_course_detail`
VALUES (9, '2020-10-12 23:26:39', '2020-10-13 01:08:12', '1', NULL, 1);
INSERT INTO `student_course_detail`
VALUES (10, '2020-10-12 23:26:55', '2020-10-14 18:00:02', '1', 1, 1);
INSERT INTO `student_course_detail`
VALUES (1315714289741651969, '2020-10-13 02:01:45', '2020-10-13 02:01:45', '1', 666, 1);
INSERT INTO `student_course_detail`
VALUES (1315714361334226945, '2020-10-13 02:02:02', '2020-10-13 02:02:02', '1', 666, 0);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`
(
    `id`                  bigint(20)                                                    NOT NULL COMMENT '主键',
    `create_time`         datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `update_time`         datetime(0)                                                   NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    `username`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
    `password`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
    `real_name`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '姓名',
    `gender`              tinyint(4)                                                    NULL DEFAULT 0 COMMENT '性别',
    `phone_number`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '手机号',
    `contact_information` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '其他联系方式, 比如qq或邮箱等',
    `nickname`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '昵称',
    `avatar_url`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '头像URL',
    `status`              tinyint(4)                                                    NULL DEFAULT 0 COMMENT '用户账号状态',
    `role`                tinyint(4)                                                    NULL DEFAULT NULL COMMENT '角色',
    `number`              varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '教师编号',
    `faculty_number`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学院编号',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `username` (`username`) USING BTREE,
    UNIQUE INDEX `number` (`number`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher`
VALUES (12349, '2020-10-02 17:09:31', '2020-11-15 13:48:17', 'teacher',
        '0aa5d5b268bf0adbd4869ad9cb11e252481e6b9dc230431a4a4778f18ac71e0d', '张三', 1, '', 'qq678987', '小火苗',
        'http://qiniu-cdn.alexyan.cn/administrator/admin1/avatar/defaultAvatar.jpg', 0, 1, '00000', '文学院');
INSERT INTO `teacher`
VALUES (21314, '2020-09-29 17:58:35', '2020-10-09 14:05:15', 'abc', '111111', '秦风', 0, '12324', '23235@gmail.com',
        '秦风风风', 'http://qiniu-cdn.alexyan.cn/administrator/admin1/avatar/defaultAvatar.jpg', 0, 1, '10003', NULL);
INSERT INTO `teacher`
VALUES (76440, '2020-10-02 21:07:13', '2020-10-09 14:05:15', 'ppoo', '111111', '肖辰', 0, '9865433', 'qq345678', '不开心',
        'http://qiniu-cdn.alexyan.cn/administrator/admin1/avatar/defaultAvatar.jpg', 0, 1, '10004', NULL);
INSERT INTO `teacher`
VALUES (76543, '2020-09-28 16:48:17', '2020-10-09 14:05:15', '3153fff44',
        '7c217c3364e8c618c294ace1e379c36418c4aa9e05d79921f31cfbd58a0cad74', '李牧', 0, '00000', 'qq12355', '木木',
        'http://qiniu-cdn.alexyan.cn/administrator/admin1/avatar/defaultAvatar.jpg', 0, 1, '10056', NULL);
INSERT INTO `teacher`
VALUES (87545, '2020-10-02 20:49:14', '2020-10-09 14:05:15', 'kkii', '111111', '成量', 0, '87545678', 'qq54678', '小狐狸',
        'http://qiniu-cdn.alexyan.cn/administrator/admin1/avatar/defaultAvatar.jpg', 0, 1, '10013', NULL);
INSERT INTO `teacher`
VALUES (324566, '2020-10-02 21:22:10', '2020-10-09 14:05:15', 'hhll7', '111111', '狄仁杰', 0, '167953368', 'qq4678993',
        '小红帽', 'http://qiniu-cdn.alexyan.cn/administrator/admin1/avatar/defaultAvatar.jpg', 0, 1, '10020', NULL);
INSERT INTO `teacher`
VALUES (442345, '2020-10-02 20:43:20', '2020-10-09 14:05:15', 'Frank', '111111', '李四', 0, '8775544', 'QQ87654', '刘姥姥',
        'http://qiniu-cdn.alexyan.cn/administrator/admin1/avatar/defaultAvatar.jpg', 0, 1, '10021', NULL);
INSERT INTO `teacher`
VALUES (444228, '2020-10-02 21:08:56', '2020-10-09 14:05:15', 'ttrr1', '111111', '苦乐', 0, '12884775', 'wx665478', '胡萝卜',
        'http://qiniu-cdn.alexyan.cn/administrator/admin1/avatar/defaultAvatar.jpg', 0, 1, '10022', NULL);
INSERT INTO `teacher`
VALUES (467899, '2020-10-02 20:55:20', '2020-10-09 14:05:15', 'kuku', '111111', '孙小伟', 0, '897654', 'QQ88654', '大可爱',
        'http://qiniu-cdn.alexyan.cn/administrator/admin1/avatar/defaultAvatar.jpg', 0, 1, '10027', NULL);
INSERT INTO `teacher`
VALUES (876554, '2020-10-02 20:43:35', '2020-10-09 14:05:15', 'Lily', '111111', '王五', 0, '5433567', 'qq654433', '王小二',
        'http://qiniu-cdn.alexyan.cn/administrator/admin1/avatar/defaultAvatar.jpg', 0, 1, '10030', NULL);
INSERT INTO `teacher`
VALUES (876747, '2020-10-02 20:51:17', '2020-10-09 14:05:15', 'jijia', '111111', '张两', 0, '8654545', 'wx76454', '小石头',
        'http://qiniu-cdn.alexyan.cn/administrator/admin1/avatar/defaultAvatar.jpg', 0, 1, '10035', NULL);
INSERT INTO `teacher`
VALUES (6543675, '2020-10-02 21:20:11', '2020-10-09 14:05:15', 'yuyu', '111111', '黄范', 0, '16524785', 'qq782764', '黄瓜',
        'http://qiniu-cdn.alexyan.cn/administrator/admin1/avatar/defaultAvatar.jpg', 0, 1, '10040', NULL);
INSERT INTO `teacher`
VALUES (1327936448046514177, '2020-11-15 19:28:15', '2020-11-15 19:28:15', '666',
        '0b322fcb0e1693e4063291c28029bab6360f04a8b9ff1c4df8ffe90f9087c29b', 'ning mo', 1, '', '', '',
        'http://qiniu-cdn.alexyan.cn/administrator/admin1/avatar/defaultAvatar.jpg', 0, 1, '666', '计算机与信息工程学院');
INSERT INTO `teacher`
VALUES (1328313031747915778, '2020-11-16 20:24:40', '2020-11-16 20:24:40', '111',
        '5289f8fb7adb081f9dac58835bbd88816487637ca3e1f8ef7b24932e4ee17488', '哈哈', 1, '', '', '',
        'http://qiniu-cdn.alexyan.cn/administrator/admin1/avatar/defaultAvatar.jpg', 0, 1, '111', '计算机与信息工程学院');
INSERT INTO `teacher`
VALUES (1329028773556449281, '2020-11-18 19:48:46', '2020-11-18 19:48:46', '1222',
        'f767da39f0a70e2c5f9922642bb1fa9614f720e037d184332e295bf44203ee95', 'tec', 1, '', '', '',
        'http://qiniu-cdn.alexyan.cn/administrator/admin1/avatar/defaultAvatar.jpg', 0, 1, '1222', '文学院');

SET FOREIGN_KEY_CHECKS = 1;
