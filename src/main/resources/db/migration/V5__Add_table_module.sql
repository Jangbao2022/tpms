--  专业表
create table module
(
  id           INT8 PRIMARY KEY AUTO_INCREMENT,
  `name`       VARCHAR(128) NOT NULL COMMENT '名称',
  pre_level_id INT8 COMMENT '前一级id',
  gmt_created  datetime,
  gmt_modified datetime

) DEFAULT CHARSET='utf8';
