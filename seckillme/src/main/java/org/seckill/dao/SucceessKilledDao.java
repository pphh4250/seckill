package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;

/**
 * Created by Admin on 2016/5/16.
 */
public interface SucceessKilledDao {

    int insertSuccessKilled( @Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
    SuccessKilled queryByIdWithSeckill( @Param("seckillId") long seckillId,@Param("userPhone") long userPhone);
}
