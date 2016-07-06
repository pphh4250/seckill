CREATE database seckill;

use seckill;

 CREATE TABLE seckill(
  seckill_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品库存ID',
  name VARCHAR(120) NOT NULL COMMENT '商品名称',
  number INT NOT NULL COMMENT '库存量',
  start_time TIMESTAMP NOT NULL COMMENT '开始时间',
  end_time TIMESTAMP NOT NULL DEFAULT current_timestamp  COMMENT '结束时间',
  create_time TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  PRIMARY KEY (seckill_id),
  KEY idx_start_time(start_time),
  KEY idx_end_time(end_time),
  KEY idx_create_time(create_time)
)engine = innoDB auto_increment=1000 DEFAULT charset=utf8 comment='秒杀库存表';


  insert into seckill(name,number,start_time,end_time)
  values('1000元秒杀iphone6',100,'2016-01-01 00:00:00','2016-01-03 00:00:00'),
('100元秒杀iphone7',400,'2016-01-01 00:00:00','2016-01-03 00:00:00'),
('4000元秒杀iphone8',1500,'2016-01-01 00:00:00','2016-01-03 00:00:00'),
('500元秒杀iphone9',1600,'2016-01-01 00:00:00','2016-01-03 00:00:00');

CREATE TABLE success_killed(
 seckill_id   BIGINT NOT NULL  COMMENT '秒杀商品ID',
   user_phone  BIGINT NOT NULL COMMENT '用户手机号码',
   state  TINYINT NOT NULL DEFAULT -1 COMMENT '状态',
  create_time  TIMESTAMP NOT NULL COMMENT '创建时间',
  PRIMARY KEY (seckill_id,user_phone),
  KEY idx_create_time(create_time)

)ENGINE =innodb DEFAULT CHARSET =utf8 COMMENT ='订单明细表';

  mysql -uroot -p