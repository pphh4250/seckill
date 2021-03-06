package org.seckill.web;

import com.sun.org.apache.xpath.internal.operations.Mod;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.dto.SeckillResult;
import org.seckill.entity.SecKill;
import org.seckill.enums.SeckillStateEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.service.SeckillService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Admin on 2016/6/28.
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(Model model){
        List<SecKill> list =  seckillService.getSeckillList();
        model.addAttribute("list",list);
        return "list";

    }


    @RequestMapping(value = "/{seckillId}/detail",method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model){
        if (seckillId == null){
            return "redirect:/seckill/list";

        }
        SecKill secKill =  seckillService.getById(seckillId);
        if (secKill == null){
            return "forward:/seckill/list";

        }

        model.addAttribute("seckill",secKill);
        return "detail";


    }

    @RequestMapping(value = "/{seckillId}/exposer",method = RequestMethod.POST,
                    produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Exposer> exporser(@PathVariable("seckillId") Long seckillId){

        SeckillResult<Exposer> result;

        try {
            Exposer exposer =  seckillService.exportSeckillUrl(seckillId);

            result = new SeckillResult<Exposer>(true,exposer);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            result = new SeckillResult<Exposer>(false,e.getMessage());
        }


        return result;
    }


    @RequestMapping(value = "/{seckillId}/{md5}/execution",
                    method = RequestMethod.POST,
                    produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public  SeckillResult<SeckillExecution> execute( @PathVariable("seckillId") Long seckillId,
                                                     @PathVariable("md5") String md5,
                                                     @CookieValue(value = "killPhone",required = false)Long phone){

        if (phone == null){
            return new SeckillResult<SeckillExecution>(false,"not regist");
        }

        SeckillResult<SeckillExecution> result;

        try {
            SeckillExecution seckillExecution = seckillService.executeSeckillProdedure(seckillId, phone, md5);

            return  new SeckillResult<SeckillExecution>(true,seckillExecution);
        }catch (RepeatKillException e){
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStateEnum.REPEAT_KILL);

            logger.error(e.getMessage(),e);
            return new SeckillResult<SeckillExecution>(true,seckillExecution);
        }catch (SeckillCloseException e){
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStateEnum.END);

            logger.error(e.getMessage(),e);
            return new SeckillResult<SeckillExecution>(true,seckillExecution);

        }catch (Exception e){
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStateEnum.INNER_ERROR);

            logger.error(e.getMessage(),e);
            return new SeckillResult<SeckillExecution>(true,seckillExecution);
        }

        //return result;

    }


    @RequestMapping(value =  "/time/now",method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> time(){
        Date now = new Date();
        return new SeckillResult<Long>(true,now.getTime());

    }




















}
