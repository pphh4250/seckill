package org.seckill.service.impl;

import com.mchange.v1.util.MapUtils;
import org.seckill.dao.SecKillDao;
import org.seckill.dao.SucceessKilledDao;
import org.seckill.dao.cache.RedisDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.SecKill;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStateEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Admin on 2016/6/26.
 */

@Service
public class SeckillServiceImpl implements SeckillService {

    @Resource
    private SecKillDao secKillDao;
    @Autowired
    private RedisDao redisDao;

    @Resource
    private SucceessKilledDao succeessKilledDao;

    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    private final  String slat="sadflasuolwe980sdflkjvxcz;kjafj;qa';kczxklkjsdaf";

    public List<SecKill> getSeckillList() {
        return secKillDao.queryAll(0, 4);
    }

    public SecKill getById(long seckillId) {
        return secKillDao.queryById(seckillId);
    }

    public Exposer exportSeckillUrl(long seckillId) {

        SecKill secKill =  redisDao.getSeckill(seckillId);
        if (secKill == null){
            secKill = secKillDao.queryById(seckillId);
            if (secKill == null){
                return new Exposer(false,seckillId);
            }else{
                redisDao.putSeckill(secKill);
            }

        }



        Date startTime = secKill.getStartTime();

        Date endTime = secKill.getEndTime();

        Date nowTime = new Date();

        if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
            return new Exposer(false,seckillId,nowTime.getTime(),startTime.getTime(),endTime.getTime());
        }

        String md5 = getMD5(seckillId);


        return new Exposer(true,md5,seckillId);
    }


    private String getMD5(long seckillId){
        String base = seckillId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    @Transactional
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws RepeatKillException, SeckillCloseException, SeckillException {

        if (md5 == null || ! md5.equals(getMD5(seckillId))){

            throw new SeckillException("md5 fail");
        }

        Date nowTime = new Date();

        int updateCount = secKillDao.reduceNumber(seckillId , nowTime);

        try {

            if (updateCount <= 0){
                throw new SeckillCloseException("seckill is closed");
            }else{
                int insertCount = succeessKilledDao.insertSuccessKilled(seckillId,userPhone);

                if (insertCount <= 0 ){
                    throw new RepeatKillException("seckill repeated");
                }else
                {
                    logger.info("param is {},{}",seckillId,userPhone);

                    SuccessKilled successKilled = succeessKilledDao.queryByIdWithSeckill(seckillId,userPhone);

                    logger.info("get this obj = {}",successKilled);

                    //SuccessKilled successKilled =  succeessKilledDao.queryByIdWithSeckill(1000, 13500009299L);

                    return new SeckillExecution(seckillId, SeckillStateEnum.SUCCEESS,successKilled);
                   // return null;
                }
            }


        }catch (SeckillCloseException e1){
            throw e1;
        }catch (RepeatKillException e2) {
            throw e2;
        }
        catch (Exception e){
            logger.error(e.getMessage(),e);
            throw new SeckillException("seckill inner error :"+ e.getMessage());
        }



    }

    public SeckillExecution executeSeckillProdedure(long seckillId, long userPhone, String md5) {
        if (md5 == null || ! md5.equals(getMD5(seckillId))){
            return new SeckillExecution(seckillId,SeckillStateEnum.DATA_REWRITE);
        }

        Date killTime = new Date();

        Map<String ,Object> map = new HashMap<String,Object>();
        map.put("seckillId",seckillId);
        map.put("phhone",userPhone);
        map.put("killTime",killTime);
        map.put("result", null);
        try{
            secKillDao.killByProcedure(map);
           int result = org.apache.commons.collections.MapUtils.getInteger(map,"result",-2);
            if (result == 1){
                SuccessKilled successKilled = succeessKilledDao.queryByIdWithSeckill(seckillId,userPhone);
                return new SeckillExecution(seckillId,SeckillStateEnum.SUCCEESS,successKilled);
            }else{
                return new SeckillExecution(seckillId,SeckillStateEnum.stateOf(result));
            }


        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new SeckillExecution(seckillId,SeckillStateEnum.INNER_ERROR);
        }


    }


}
