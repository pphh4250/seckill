package org.seckill.dao.cache;

import junit.runner.BaseTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.SecKillDao;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.SecKill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Admin on 2016/7/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RedisDaoTest {
    private long id=1001;
    @Autowired
    private RedisDao redisDao;

    @Autowired
    private SecKillDao secKillDao;

    @Test
    public void testGetSeckill() throws Exception {
        SecKill secKill = redisDao.getSeckill(id);
        if (secKill == null){
            secKill = secKillDao.queryById(id);
            if (secKill != null){
                String result = redisDao.putSeckill(secKill);
                System.out.println(result);
                secKill = redisDao.getSeckill(id);
                System.out.println(secKill);
            }
        }
    }

}