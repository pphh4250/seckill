package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SecKill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Admin on 2016/5/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SecKillDaoTest {

    @Resource
    private SecKillDao seckillDao;
    @Test
    public void testReduceNumber() throws Exception {
        long id = 1000;
        Date killTime = new Date();
        seckillDao.reduceNumber(id,killTime);
    }

    @Test
    public void testQueryById() throws Exception {
        long id = 1000;
        SecKill seckill = seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }

    @Test
    public void testQueryAll() throws Exception {
        List<SecKill> secKills = seckillDao.queryAll(2,3);
        for (SecKill secKill : secKills)
        {
            System.out.println(secKill);
        }
    }
}