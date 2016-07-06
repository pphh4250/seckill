package org.seckill.exception;

import org.seckill.dto.SeckillExecution;

/**
 * Created by Admin on 2016/6/26.
 */
public class SeckillCloseException extends SeckillException {
    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
