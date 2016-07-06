package org.seckill.service;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.SecKill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;

import java.util.List;

/**
 * Created by Admin on 2016/6/26.
 */
public interface SeckillService {

    List<SecKill> getSeckillList();

    SecKill getById(long seckillId);

    Exposer exportSeckillUrl(long seckillId);

    SeckillExecution executeSeckill(long seckillId,long userPhone,String md5) throws RepeatKillException , SeckillCloseException, SeckillException;

    SeckillExecution executeSeckillProdedure(long seckillId,long userPhone,String md5);

}
