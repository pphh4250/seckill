CREATE database seckill;

use seckill;

 CREATE TABLE seckill(
  seckill_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '��Ʒ���ID',
  name VARCHAR(120) NOT NULL COMMENT '��Ʒ����',
  number INT NOT NULL COMMENT '�����',
  start_time TIMESTAMP NOT NULL COMMENT '��ʼʱ��',
  end_time TIMESTAMP NOT NULL DEFAULT current_timestamp  COMMENT '����ʱ��',
  create_time TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '����ʱ��',
  PRIMARY KEY (seckill_id),
  KEY idx_start_time(start_time),
  KEY idx_end_time(end_time),
  KEY idx_create_time(create_time)
)engine = innoDB auto_increment=1000 DEFAULT charset=utf8 comment='��ɱ����';


  insert into seckill(name,number,start_time,end_time)
  values('1000Ԫ��ɱiphone6',100,'2016-01-01 00:00:00','2016-01-03 00:00:00'),
('100Ԫ��ɱiphone7',400,'2016-01-01 00:00:00','2016-01-03 00:00:00'),
('4000Ԫ��ɱiphone8',1500,'2016-01-01 00:00:00','2016-01-03 00:00:00'),
('500Ԫ��ɱiphone9',1600,'2016-01-01 00:00:00','2016-01-03 00:00:00');

CREATE TABLE success_killed(
 seckill_id   BIGINT NOT NULL  COMMENT '��ɱ��ƷID',
   user_phone  BIGINT NOT NULL COMMENT '�û��ֻ�����',
   state  TINYINT NOT NULL DEFAULT -1 COMMENT '״̬',
  create_time  TIMESTAMP NOT NULL COMMENT '����ʱ��',
  PRIMARY KEY (seckill_id,user_phone),
  KEY idx_create_time(create_time)

)ENGINE =innodb DEFAULT CHARSET =utf8 COMMENT ='������ϸ��';

  mysql -uroot -p