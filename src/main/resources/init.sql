DROP TABLE IF EXISTS tm_user;
CREATE TABLE tm_user (
  id BIGINT primary key auto_increment,
	open_id VARCHAR(20) NOT NULL comment '微信open_id',
	name VARCHAR(20),
  sex tinyint default 2 comment '0-女, 1-男, 2-未知',
	birthday VARCHAR(20) comment 'yyyy-MM-dd',
  mobile BIGINT,
  email VARCHAR(50),
  ability VARCHAR(30) comment '我的技能',
  location_id BIGINT comment '所在地id',
  location_name VARCHAR(30) comment '所在地名',
  stars tinyint default 0 comment '评价星级',
  certificate VARCHAR(30) comment '技能证书',
  self_description VARCHAR(2000) comment '自我介绍',
  create_time BIGINT NOT NULL,
  modify_time BIGINT,
  is_deleted boolean default 0
) engine = Innodb;

DROP TABLE IF EXISTS tm_education;
CREATE TABLE tm_education (
  id BIGINT primary key auto_increment,
  user_id BIGINT NOT NULL,
	college VARCHAR(20) NOT NULL comment '学校',
	major VARCHAR(20) NOT NULL comment '专业',
	degree VARCHAR(20) NOT NULL comment '学历',
  begin BIGINT NOT NULL,
  end BIGINT NOT NULL
) engine = Innodb;

DROP TABLE IF EXISTS tm_comment;
CREATE TABLE tm_comment (
  id BIGINT primary key auto_increment,
  user_id BIGINT NOT NULL comment '被评论用户id',
  comment_by BIGINT NOT NULL comment '发表评论用户id',
  stars TINYINT default 0,
	remark VARCHAR(255) NOT NULL comment '评论内容',
  create_time BIGINT NOT NULL
) engine = Innodb;

DROP TABLE IF EXISTS tm_favorite;
CREATE TABLE tm_favorite (
  id BIGINT primary key auto_increment,
  user_id BIGINT NOT NULL comment '用户id',
  requirement_id BIGINT NOT NULL comment '需求id',
  create_time BIGINT NOT NULL
) engine = Innodb;

DROP TABLE IF EXISTS tm_requirement_ticket;
CREATE TABLE tm_requirement_ticket (
  id BIGINT primary key auto_increment,
  user_id BIGINT NOT NULL comment '用户id',
  state tinyint default 0 comment '0-PUBLISHED, 1-BE_APPLIED, 2-PROCESSED, 3-COMPLETE',
	name VARCHAR(60) NOT NULL comment '需求名',
  reward BIGINT NOT NULL comment '需求报酬',
  location_id BIGINT comment '所在地id',
  location_name VARCHAR(30) comment '所在地名',
  requirement_id BIGINT NOT NULL comment '需求id',
  description VARCHAR(2000) comment '相信描述',
  create_time BIGINT NOT NULL,
  modify_time BIGINT
) engine = Innodb;

DROP TABLE IF EXISTS tm_order;
CREATE TABLE tm_order (
  id BIGINT primary key auto_increment,
  user_id BIGINT NOT NULL comment '用户id',
  requirement_id BIGINT NOT NULL comment '需求id',
  state tinyint default 0 comment '0-INIT, 1-ACCEPTED, 2-REJECTED',
	name VARCHAR(60) NOT NULL comment '订单名',
  amount BIGINT NOT NULL comment '需求报酬',
  create_time BIGINT NOT NULL,
  modify_time BIGINT
) engine = Innodb;

DROP TABLE IF EXISTS tm_location;
CREATE TABLE tm_location (
  id BIGINT primary key auto_increment,
  parent_id BIGINT comment '上级地点id',
  level tinyint default 0 comment '0-国家级, 1-省级, 2-地级市',
	name VARCHAR(30) NOT NULL comment '地点名'
) engine = Innodb;



=================================================== state machine ==============================================

create table tm_sm_configurer (
	id bigint primary key auto_increment,
	tenant_id bigint,
	pid bigint,
	type tinyint,
	role varchar(20),
	src varchar(20),
	event varchar(20),
	target varchar(20),
	action varchar(20),
	guard varchar(20)
) engine=INNODB;

create table tm_order (
	id bigint primary key,
	tenant_id bigint,
	state varchar(20),
	pay_order_id bigint,
	pay_channel varchar(20),
	pay_time datetime,
	delivery_id bigint,
	delivery_time datetime,
	refund_order_id bigint,
	refund_time datetime,
	create_time datetime
) engine=INNODB;




