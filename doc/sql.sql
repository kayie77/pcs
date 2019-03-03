--记录数据库修改语句
create tablespace pcs
datafile 'D:/oracle/oradata/orcl/pcs.dbf' 
size 5M 
autoextend on next 5M maxsize 3000M;

create user pcs identified by  pcs1qazxsw2 
default tablespace pcs temporary tablespace temp profile DEFAULT;

grant connect,resource,dba to pcs;



truncate table  OPER_TASKMAIN;
truncate table OPER_DATAREOPRTEDMAIN;
truncate table OPER_TASKMAIN;
truncate table DIC_DATACOLLECTPERSON;
truncate table DIC_AGRPROREPORT;


--------------------------------------------------------------------------------------------------
--采集指标
DROP TABLE IF EXISTS `dic_datacollectindex`;
CREATE TABLE `dic_datacollectindex` (
  `id` varchar(100) NOT NULL DEFAULT '',
  `indexcode` varchar(100) DEFAULT NULL,
  `indexname` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


insert into dic_datacollectindex values('1','CL','产量');
insert into dic_datacollectindex values('2','XL','销量');
insert into dic_datacollectindex values('3','PFJ','批发价');
insert into dic_datacollectindex values('4','SCJ','市场价');
insert into dic_datacollectindex values('5','SCZGJ','市场最高价');
insert into dic_datacollectindex values('6','SCZDJ','市场最低价');
insert into dic_datacollectindex values('7','SCPJJ','市场平均价');
insert into dic_datacollectindex values('8','SCDZJ','市场大宗价');

