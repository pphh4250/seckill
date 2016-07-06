package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SecKill;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * Created by Admin on 2016/5/16.
 */
public interface SecKillDao {

    int reduceNumber(@Param("seckillId") long seckillId,@Param("killTime") Date killTime);

    SecKill queryById(long seckillId);

    List<SecKill> queryAll(@Param("offset") int offset,@Param("limit") int limit);

    void killByProcedure(Map<String,Object> paramMap);

}
