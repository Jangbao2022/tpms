drop table curriculum;
drop table curriculum_mid_course;

--  培养方案
create table curriculum
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
create table curriculum_mid_course
(
  id            INT8 PRIMARY KEY AUTO_INCREMENT,
  course_id     INT8 COMMENT '课程id',
  curriculum_id INT8 COMMENT '培养方案id',
  gmt_created   datetime,
  gmt_modified  datetime,
  FOREIGN KEY (course_id) REFERENCES course (id),
  FOREIGN KEY (curriculum_id) REFERENCES curriculum (id)
) DEFAULT CHARSET='utf8';