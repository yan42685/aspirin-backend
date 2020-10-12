CREATE TABLE administrator
(
    `id`                  bigint(20) not null COMMENT '主键',
    `create_time`         datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`         datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    username              varchar(255) unique comment '用户名',
    password              varchar(255) comment '密码',
    `real_name`           varchar(255) DEFAULT '' COMMENT '姓名',
    `gender`              tinyint      default 0 comment '性别',
    `phone_number`        varchar(255) DEFAULT '' COMMENT '手机号',
    `contact_information` varchar(255) DEFAULT '' COMMENT '其他联系方式, 比如qq或邮箱等',
    `nickname`            varchar(255) DEFAULT '' COMMENT '昵称',
    `avatar_url`          varchar(255) DEFAULT '' COMMENT '头像URL',
    `status`              tinyint      DEFAULT 0 COMMENT '用户账号状态',
    `role`                tinyint comment '角色',
    `number`              varchar(255) unique comment '工号',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


CREATE TABLE teacher
(
    `id`                  bigint(20) not null COMMENT '主键',
    `create_time`         datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`         datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    username              varchar(255) unique comment '用户名',
    password              varchar(255) comment '密码',
    `real_name`           varchar(255) DEFAULT '' COMMENT '姓名',
    `gender`              tinyint      default 0 comment '性别',
    `phone_number`        varchar(255) DEFAULT '' COMMENT '手机号',
    `contact_information` varchar(255) DEFAULT '' COMMENT '其他联系方式, 比如qq或邮箱等',
    `nickname`            varchar(255) DEFAULT '' COMMENT '昵称',
    `avatar_url`          varchar(255) DEFAULT '' COMMENT '头像URL',
    `status`              tinyint      DEFAULT 0 COMMENT '用户账号状态',
    `role`                tinyint comment '角色',
    `number`              varchar(255) unique comment '教师编号',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE faculty
(
    `id`           bigint(20) not null COMMENT '主键',
    `create_time`  datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`  datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `name`         varchar(255) DEFAULT '' COMMENT '学院名',
    `phone_number` varchar(255) DEFAULT '' COMMENT '工作联系电话',
    `avatar_url`   varchar(255) DEFAULT '' COMMENT '学院图标URL',
    `number`       varchar(255) unique comment '学院编号',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE specialty
(
    `id`             bigint(20) not null COMMENT '主键',
    `create_time`    datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `name`           varchar(255) DEFAULT '' COMMENT '专业名',
    `faculty_number` varchar(255) comment '所属学院编号',
    `number`         varchar(255) unique comment '专业编号',
    PRIMARY KEY (`id`),
    foreign key (faculty_number) references faculty (number) on update cascade on delete cascade
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE student
(
    `id`                  bigint(20) not null COMMENT '主键',
    `create_time`         datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`         datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    username              varchar(255) unique comment '用户名',
    password              varchar(255) comment '密码',
    `real_name`           varchar(255) DEFAULT '' COMMENT '姓名',
    `gender`              tinyint      default 0 comment '性别',
    `phone_number`        varchar(255) DEFAULT '' COMMENT '手机号',
    `contact_information` varchar(255) DEFAULT '' COMMENT '其他联系方式, 比如qq或邮箱等',
    `nickname`            varchar(255) DEFAULT '' COMMENT '昵称',
    `avatar_url`          varchar(255) DEFAULT '' COMMENT '头像URL',
    `status`              tinyint      DEFAULT 0 COMMENT '用户账号状态',
    `role`                tinyint comment '角色',
    `number`              varchar(255) unique comment '学号',
    `faculty_number`      varchar(255) comment '学院编号',
    `specialty_number`    varchar(255) comment '专业编号',
    admission_year        smallint comment '入学年份',
    semester              smallint comment '所处学期',
    PRIMARY KEY (`id`),
    foreign key (faculty_number) references faculty (number) on update cascade on delete cascade,
    foreign key (specialty_number) references specialty (number) on update cascade on delete cascade
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE bulletin
(
    `id`                   bigint(20) not null COMMENT '主键',
    `create_time`          datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`          datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `title`                varchar(255) comment '标题',
    `content`              varchar(9999) comment '内容',
    `administrator_number` varchar(255) comment '发布公告的管理员工号',
    PRIMARY KEY (`id`),
    foreign key (administrator_number) references administrator (number) on update cascade on delete cascade
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE course
(
    `id`          bigint(20) not null COMMENT '主键',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    icon_url      varchar(255) comment '课程图标',
    `name`        varchar(255) comment '课程名',
    `number`      varchar(255) unique comment '课程编号',
    `type`        tinyint comment '课程类型',
    description   varchar(255) comment '课程简介',
    `period`      tinyint comment '学时',
    credit        float(10, 2) comment '学分',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


CREATE TABLE specialty_course
(
    `id`               bigint(20) not null COMMENT '主键',
    `create_time`      datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`      datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `specialty_number` varchar(255) comment '专业编号',
    `course_number`    varchar(255) unique comment '课程编号',
    `semester`         tinyint comment '开课的学期',
    PRIMARY KEY (`id`),
    foreign key (specialty_number) references specialty (number) on update cascade on delete cascade,
    foreign key (course_number) references course (number) on update cascade on delete cascade
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE student_course_detail
(
    `id`             bigint(20) not null COMMENT '主键',
    `create_time`    datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `student_number` varchar(255) comment '学生编号',
    course_detail_id  bigint(20) unique comment '具体课程id',
    status tinyint(4) comment '选课状态',
    PRIMARY KEY (`id`),
    foreign key (student_number) references student (number) on update cascade on delete cascade,
    foreign key (course_detail_id) references course_detail (id) on update cascade on delete cascade
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE grade
(
    `id`             bigint(20) not null COMMENT '主键',
    `create_time`    datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `student_number` varchar(255) default '' comment '学生编号',
    `course_number`  varchar(255) default '' comment '课程编号',
    regular_scores   float(10, 2) default 0 comment '平时分',
    exam_scores      float(10, 2) default 0 comment '考试分',
    submitted        boolean      default false comment '打分是否提交',
    PRIMARY KEY (`id`),
    foreign key (student_number) references student (number) on update cascade on delete cascade,
    foreign key (course_number) references course (number) on update cascade on delete cascade
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


CREATE TABLE classroom
(
    `id`          bigint(20) not null COMMENT '主键',
    `create_time` datetime            DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime            DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `location`    varchar(255)        default '' comment '地点',
    number        varchar(255) unique default '' comment '编号',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


CREATE TABLE course_detail
(
    `id`               bigint(20) not null COMMENT '主键',
    `create_time`      datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`      datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `teacher_number`   varchar(255) default '' comment '教师编号',
    `course_number`    varchar(255) default '' comment '课程编号',
    scheduling_time    tinyint      default 0 comment '第几节课',
    day_of_the_week    tinyint      default 0 comment '星期几',
    `classroom_number` varchar(255) default '' comment '教室编号',
    PRIMARY KEY (`id`),
    foreign key (teacher_number) references teacher (number) on update cascade on delete cascade,
    foreign key (course_number) references course (number) on update cascade on delete cascade,
    foreign key (classroom_number) references classroom (number) on update cascade on delete cascade
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

INSERT INTO `administrator`
VALUES (1, '2020-09-22 11:11:49', '2020-09-22 11:11:49', 'admin1',
        '65465993155d91f4f016843ae6480dd0cc543d9264e3fe3c3afe1ac1a2e72400', '张三', 0, '13111112222', 'QQ: 356356', '清水',
        'www.agc.com', 0, 0, 'A00001');
INSERT INTO `administrator`
VALUES (2, '2020-09-22 11:13:44', '2020-09-22 11:13:44', 'admin2',
        '4f7edfdbb8b3bd1af2193475b167dc24f400a1898a627eb1b975a8abb4bbe146', '李四', 0, '13245454545', 'QQ: 1111', '枫叶',
        'www.abc.com', 0, 0, 'A00002');
