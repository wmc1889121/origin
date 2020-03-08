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
  create_time BIGINT not null,
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
  begin BIGINT not null,
  end BIGINT not null
) engine = Innodb;

DROP TABLE IF EXISTS tm_comment;
CREATE TABLE tm_comment (
  id BIGINT primary key auto_increment,
  user_id BIGINT NOT NULL comment '被评论用户id',
  comment_by BIGINT NOT NULL comment '发表评论用户id',
  stars TINYINT default 0,
	remark VARCHAR(255) NOT NULL comment '评论内容',
  create_time BIGINT not null
) engine = Innodb;





DROP TABLE IF EXISTS tm_order;
CREATE TABLE tm_order (
  id BIGINT primary key,
	state VARCHAR(20) NOT NULL,
  amount BIGINT DEFAULT 0,
	pay_time BIGINT,
  create_time BIGINT not null
);