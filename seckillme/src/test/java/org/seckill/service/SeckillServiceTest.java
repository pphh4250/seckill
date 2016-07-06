package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.SecKill;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.*;

/**
 * Created by Admin on 2016/6/26.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
                        "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private SeckillService seckillService;


    @Test
    public void testGetSeckillList() throws Exception {
        List<SecKill> list = seckillService.getSeckillList();
        logger.info("list={}" ,list);
    }

    @Test
    public void testGetById() throws Exception {
        long id = 1000;
        SecKill seckill = seckillService.getById(id);
        logger.info("seckill = {}",seckill);
    }

    @Test
    public void testExportSeckillUrl() throws Exception {
        long id = 1000;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("expose = {}",exposer);
    }

    @Test
    public void testExecuteSeckill() throws Exception {
        long id = 1000;
        long phone = 13510009299L;
        String md5 = "6f6112cd300b71555a01c2b74cf730e6";
        SeckillExecution seckillExecution =  seckillService.executeSeckill(id, phone, md5);

        logger.info("result = {}",seckillExecution );
    }

    @Test
    public void executerSeckillProcedure(){
        long seckillId = 1000;
        long   phone =13544342234L;
        Exposer exposer =  seckillService.exportSeckillUrl(seckillId);

        if (exposer.isExposed()){
            String md5 = exposer.getMd5();
            SeckillExecution execution = seckillService.executeSeckillProdedure(seckillId,phone,md5);
            logger.info(execution.getStateInfo());
        }

    }
























}