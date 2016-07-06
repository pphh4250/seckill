DELIMITER $$

CREATE PROCEDURE 'seckill'.'execute_seckill'
(in v_seckill_id bitint,in v_phone bigint,
in v_kill_time TIMESTAMP ,out r_result int)
  BEGIN
    DELARE insert_count int DEFAULT 0;
    START TRANSACTION;
    INSERT ignore into success_killed
    (seckill_id,user_phone,create_time)
    values (v_seckill_id,v_phone,v_kill_time);
    SELECT row_count() into insert_count;
    if(insert_count=0) then
      rollback;
      set r_result = -1;
    elseif(insert_count <0) then
      rollback;
      set r_result = -2;
    else
      update seckill
      set number = number -1
      where seckill_id = v_seckill_id
        and end_time > v_kill_time
        and start_time < v_kill_time
        and number > 0;
    SELECT row_count() into insert_count;
    if(insert_count == 0) then
      rollback;
      set r_result = 0;
    elseif (insert_count < 0) then
      rollback;
      set r_result = -2;
    else
      commit;
      set r_result =1;
    end if;
    end if;
end;
$$

DELIMITER ;

set @r_result = -3;

call execute_seckill(1003,13356564545,now(),@r_result);

select @r_result;

























