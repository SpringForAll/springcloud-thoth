drop table if exists tbl_robot;

/*==============================================================*/
/* Table: tbl_robot                                             */
/*==============================================================*/
create table tbl_robot
(
   id                   bigint(20) not null auto_increment comment '机器人id',
   tenantId             bigint(20) comment '租户id',
   name                 varchar(64) comment '机器人名称',
   sex                  int(1) comment '性别：0 男 1 女',
   headPortrait        varchar(64) comment '头像',
   industryId           int(32) comment '行业领域id',
   accessWay            int(1) comment '接入方式',
   avaliable            int(1) comment '是否可用 0 关闭 1 开启 ，默认开启',
   gmtCreate            datetime comment '创建时间',
   gmtModified          datetime,
   primary key (id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='机器人';

drop table if exists tbl_industry;

/*==============================================================*/
/* Table: tbl_industry                                          */
/*==============================================================*/
create table tbl_industry
(
   id                   int(32) not null auto_increment,
   name                 varchar(64) comment '名称',
   primary key (id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='行业领域';


insert into tbl_industry(name) VALUES('游戏');
insert into tbl_industry(name) VALUES('电商');
insert into tbl_industry(name) VALUES('文化/体育');
insert into tbl_industry(name) VALUES('音乐/娱乐');
insert into tbl_industry(name) VALUES('酒店/餐饮');
insert into tbl_industry(name) VALUES('旅游');
insert into tbl_industry(name) VALUES('贸易/零售');

insert into tbl_industry(name) VALUES('法律/金融/咨询服务');
insert into tbl_industry(name) VALUES('教育');
insert into tbl_industry(name) VALUES('政府/非营利机构');
insert into tbl_industry(name) VALUES('医疗/保健');
insert into tbl_industry(name) VALUES('房地产/建筑');
insert into tbl_industry(name) VALUES('加工/制造');
insert into tbl_industry(name) VALUES('其他');

drop table if exists tbl_common_msg;

/*==============================================================*/
/* Table: tbl_common_msg                                        */
/*==============================================================*/
create table tbl_common_msg
(
   id                   bigint(20) not null auto_increment,
   robotId              int(32),
   message              varchar(32) comment '消息',
   type                 int(1) comment '0 欢迎语 1 未知问题答案',
   gmtCreate            datetime comment '创建时间 ',
   gmtModified          datetime comment '修改时间',
   primary key (id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='欢迎语';

drop table if exists tbl_robot_lib;

/*==============================================================*/
/* Table: tbl_robot_lib                                         */
/*==============================================================*/
create table tbl_robot_lib
(
   id                   bigint(20) not null auto_increment,
   robotId              bigint(20) comment '机器人id',
   libId                bigint(20) comment '库id'
   primary key (id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='机器人库';














