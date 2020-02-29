alter table curriculum_mid_course
  add teach int null comment '学时分配之讲课';

alter table curriculum_mid_course
  add experiment int null comment '学时分配之实验';

alter table curriculum_mid_course
  add practice int null comment '学分分配之实习';

alter table curriculum_mid_course
  add oac int null comment '学时分配之上机';

alter table curriculum_mid_course
  add semester varchar(32) null comment '执行学期';

alter table course drop column teach;

alter table course drop column experiment;

alter table course drop column practice;

alter table course drop column oac;

alter table course drop column semester;

