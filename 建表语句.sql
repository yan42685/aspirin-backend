CREATE TABLE administrator
(
    `id`                  bigint(20)          NOT NULL COMMENT '主键',
    `create_time`         datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`         datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    username              varchar(255) unique not null comment '用户名',
    password              varchar(255)        not null comment '密码',
    `real_name`           varchar(255)        NOT NULL DEFAULT '' COMMENT '姓名',
    `gender`              tinyint             not null default 0 comment '性别',
    `phone_number`        varchar(255)        NOT NULL DEFAULT '' COMMENT '手机号',
    `contact_information` varchar(255)        NOT NULL DEFAULT '' COMMENT '其他联系方式, 比如qq或邮箱等',
    `nickname`            varchar(255)        NOT NULL DEFAULT '' COMMENT '昵称',
    `avatar_url`          varchar(255)        NOT NULL DEFAULT '' COMMENT '头像URL',
    `status`              tinyint             NOT NULL DEFAULT 0 COMMENT '用户账号状态',
    `role`                tinyint             not null comment '角色',
    `number`              varchar(255) unique comment '工号',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


CREATE TABLE teacher
(
    `id`                  bigint(20)          NOT NULL COMMENT '主键',
    `create_time`         datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`         datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    username              varchar(255) unique not null comment '用户名',
    password              varchar(255)        not null comment '密码',
    `real_name`           varchar(255)        NOT NULL DEFAULT '' COMMENT '姓名',
    `gender`              tinyint             not null default 0 comment '性别',
    `phone_number`        varchar(255)        NOT NULL DEFAULT '' COMMENT '手机号',
    `contact_information` varchar(255)        NOT NULL DEFAULT '' COMMENT '其他联系方式, 比如qq或邮箱等',
    `nickname`            varchar(255)        NOT NULL DEFAULT '' COMMENT '昵称',
    `avatar_url`          varchar(255)        NOT NULL DEFAULT '' COMMENT '头像URL',
    `status`              tinyint             NOT NULL DEFAULT 0 COMMENT '用户账号状态',
    `role`                tinyint             not null comment '角色',
    `number`              varchar(255) unique comment '教师编号',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE faculty
(
    `id`           bigint(20)          NOT NULL COMMENT '主键',
    `create_time`  datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`  datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `name`         varchar(255)        NOT NULL DEFAULT '' COMMENT '学院名',
    `phone_number` varchar(255)        NOT NULL DEFAULT '' COMMENT '工作联系电话',
    `avatar_url`   varchar(255)        NOT NULL DEFAULT '' COMMENT '学院图标URL',
    `number`       varchar(255) unique not null comment '学院编号',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE specialty
(
    `id`             bigint(20)          NOT NULL COMMENT '主键',
    `create_time`    datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `name`           varchar(255)        NOT NULL DEFAULT '' COMMENT '专业名',
    `faculty_number` varchar(255)        not null comment '所属学院编号',
    `number`         varchar(255) unique not null comment '专业编号',
    PRIMARY KEY (`id`),
    foreign key (faculty_number) references faculty (number) on update cascade on delete cascade
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE student
(
    `id`                  bigint(20)          NOT NULL COMMENT '主键',
    `create_time`         datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`         datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    username              varchar(255) unique not null comment '用户名',
    password              varchar(255)        not null comment '密码',
    `real_name`           varchar(255)        NOT NULL DEFAULT '' COMMENT '姓名',
    `gender`              tinyint             not null default 0 comment '性别',
    `phone_number`        varchar(255)        NOT NULL DEFAULT '' COMMENT '手机号',
    `contact_information` varchar(255)        NOT NULL DEFAULT '' COMMENT '其他联系方式, 比如qq或邮箱等',
    `nickname`            varchar(255)        NOT NULL DEFAULT '' COMMENT '昵称',
    `avatar_url`          varchar(255)        NOT NULL DEFAULT '' COMMENT '头像URL',
    `status`              tinyint             NOT NULL DEFAULT 0 COMMENT '用户账号状态',
    `role`                tinyint             not null comment '角色',
    `number`              varchar(255) unique comment '学号',
    `faculty_number`      varchar(255)        not null comment '学院编号',
    `specialty_number`    varchar(255)        not null comment '专业编号',
    `grade`               tinyint             not null comment '年级',
    admission_year        smallint            not null comment '入学年份',
    semester              smallint            not null comment '所处学期',
    PRIMARY KEY (`id`),
    foreign key (faculty_number) references faculty (number) on update cascade on delete cascade,
    foreign key (specialty_number) references specialty (number) on update cascade on delete cascade
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE bulletin
(
    `id`                   bigint(20)    NOT NULL COMMENT '主键',
    `create_time`          datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`          datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `title`                varchar(255)  not null comment '标题',
    `content`              varchar(9999) not null comment '内容',
    `administrator_number` varchar(255)  not null comment '发布公告的管理员工号',
    PRIMARY KEY (`id`),
    foreign key (administrator_number) references administrator (number) on update cascade on delete cascade
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE course
(
    `id`          bigint(20)          NOT NULL COMMENT '主键',
    `create_time` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    icon_url      varchar(255)        not null comment '课程图标',
    `name`        varchar(255)        not null comment '课程名',
    `number`      varchar(255) unique not null comment '课程编号',
    `type`        tinyint             not null comment '课程类型',
    description   varchar(255)        not null comment '课程简介',
    `period`      tinyint             not null comment '学时',
    credit        float(10, 2)        not null comment '学分',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


CREATE TABLE specialty_course
(
    `id`               bigint(20)          NOT NULL COMMENT '主键',
    `create_time`      datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`      datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `specialty_number` varchar(255)        not null comment '专业编号',
    `course_number`    varchar(255) unique not null comment '课程编号',
    `semester`         tinyint             not null comment '开课的学期',
    PRIMARY KEY (`id`),
    foreign key (specialty_number) references specialty (number) on update cascade on delete cascade,
    foreign key (course_number) references course (number) on update cascade on delete cascade
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE student_course
(
    `id`             bigint(20)          NOT NULL COMMENT '主键',
    `create_time`    datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `student_number` varchar(255)        not null comment '学生编号',
    `course_number`  varchar(255) unique not null comment '课程编号',
    PRIMARY KEY (`id`),
    foreign key (student_number) references student (number) on update cascade on delete cascade,
    foreign key (course_number) references course (number) on update cascade on delete cascade
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE grade
(
    `id`             bigint(20)   NOT NULL COMMENT '主键',
    `create_time`    datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `student_number` varchar(255) not null default '' comment '学生编号',
    `course_number`  varchar(255) not null default '' comment '课程编号',
    regular_scores   float(10, 2) not null default 0 comment '平时分',
    exam_scores      float(10, 2) not null default 0 comment '考试分',
    submitted        boolean      not null default false comment '打分是否提交',
    PRIMARY KEY (`id`),
    foreign key (student_number) references student (number) on update cascade on delete cascade,
    foreign key (course_number) references course (number) on update cascade on delete cascade
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


CREATE TABLE classroom
(
    `id`          bigint(20)          NOT NULL COMMENT '主键',
    `create_time` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `location`    varchar(255)        not null default '' comment '地点',
    number        varchar(255) unique not null default '' comment '编号',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


CREATE TABLE course_detail
(
    `id`               bigint(20)   NOT NULL COMMENT '主键',
    `create_time`      datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`      datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `teacher_number`   varchar(255) not null default '' comment '教师编号',
    `course_number`    varchar(255) not null default '' comment '课程编号',
    scheduling_time    tinyint      not null default 0 comment '第几节课',
    day_of_the_week    tinyint      not null default 0 comment '星期几',
    `classroom_number` varchar(255) not null default '' comment '教室编号',
    PRIMARY KEY (`id`),
    foreign key (teacher_number) references teacher (number) on update cascade on delete cascade,
    foreign key (course_number) references course (number) on update cascade on delete cascade,
    foreign key (classroom_number) references classroom (number) on update cascade on delete cascade
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


