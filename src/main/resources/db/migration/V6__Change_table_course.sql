alter table course
  add english_name varchar(128) not null comment '英文名称';

alter table course
  add course_quality int null comment '课程性质';

alter table course
  add assessment_method int not null comment '考核方式';

alter table course
  add teach int null comment '学时分配之讲课';

alter table course
  add experiment int null comment '学时分配之实验';

alter table course
  add practice int null comment '学分分配之实习';

alter table course
  add oac int null comment '学时分配之上机';

alter table course
  add semester varchar(32) null comment '执行学期';

alter table course
  add module_id INT8 not null comment '对应模块id';

