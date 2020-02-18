--  专业表
create table major
(
  id           INT8 PRIMARY KEY AUTO_INCREMENT,
  `name`       VARCHAR(128) NOT NULL COMMENT '名称',
  gmt_created  datetime,
  gmt_modified datetime

) DEFAULT CHARSET='utf8';


--  用户
create table user
(
  id           INT8 PRIMARY KEY AUTO_INCREMENT,
  `name`       VARCHAR(128) NOT NULL COMMENT '姓名',
  `power`      int          NOT NULL COMMENT '权限',
  account      varchar(10)  not null comment '账号',
  password     varchar(20)  not null comment '密码',
  intake       datetime COMMENT '入学时间',
  major_id     INT8         NOT NULL COMMENT '专业id',
  gmt_created  datetime,
  gmt_modified datetime,
  FOREIGN KEY (major_id) REFERENCES major (id)
) DEFAULT CHARSET='utf8';

create unique index user_account_index
on user (account);

--  课程
create table course
(
  id           INT8 PRIMARY KEY AUTO_INCREMENT,
  `name`       VARCHAR(128) NOT NULL COMMENT '名称',
  credit       int          NOT NULL COMMENT '学分',
  class_hour   int          NOT NULL COMMENT '学时',
  `type`       int          NOT NULL COMMENT '必/选修',
  teacher_id   INT8         NOT NULL COMMENT '讲师id',
  gmt_created  datetime,
  gmt_modified datetime,
  FOREIGN KEY (teacher_id) REFERENCES user (id)
) DEFAULT CHARSET='utf8';

--  培养方案
create table curriculum_mid_course
(
  id                         INT8 PRIMARY KEY AUTO_INCREMENT,
  `name`                     VARCHAR(128) NOT NULL COMMENT '名称',
  `current`                  int          NOT NULL COMMENT '是否当前方案',
  grade                      varchar(20)  not null comment '年级',
  need_obligatory_class_hour int          NOT NULL COMMENT '所需必修学时',
  need_obligatory_credit     int          NOT NULL COMMENT '所需必修学分',
  need_elective_class_hour   int          NOT NULL COMMENT '所需选修学时',
  need_elective_credit       int          NOT NULL COMMENT '所需选修学分',
  major_id                   INT8         NOT NULL COMMENT '专业id',
  gmt_created                datetime,
  gmt_modified               datetime,
  FOREIGN KEY (major_id) REFERENCES major (id)

) DEFAULT CHARSET='utf8';

--  课程-培养方案中间表
create table curriculum
(
  id            INT8 PRIMARY KEY AUTO_INCREMENT,
  course_id     INT8 COMMENT '课程id',
  curriculum_id INT8 COMMENT '培养方案id',
  gmt_created   datetime,
  gmt_modified  datetime,
  FOREIGN KEY (course_id) REFERENCES course (id),
  FOREIGN KEY (curriculum_id) REFERENCES curriculum (id)
) DEFAULT CHARSET='utf8';
