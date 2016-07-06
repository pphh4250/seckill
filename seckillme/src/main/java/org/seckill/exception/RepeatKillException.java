package org.seckill.exception;

import org.seckill.dto.SeckillExecution;

/**
 * Created by Admin on 2016/6/26.
 */
public class RepeatKillException  extends SeckillException{


    public RepeatKillException(String message) {

        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
