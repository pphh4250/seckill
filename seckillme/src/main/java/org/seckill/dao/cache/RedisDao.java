package org.seckill.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.seckill.entity.SecKill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Locale;

/**
 * Created by Admin on 2016/7/1.
 */
public class RedisDao {
    private JedisPool jedisPool;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private RuntimeSchema<SecKill> schema = RuntimeSchema.createFrom(SecKill.class);
    public RedisDao(String ip , int port){
        jedisPool = new JedisPool(ip,port);

    }
    public SecKill getSeckill(long seckillId){
        try{
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "seckill:" + seckillId;

                byte[] bytes = jedis.get(key.getBytes());
                if (bytes != null){
                    SecKill secKill = schema.newMessage();
                    ProtostuffIOUtil.mergeFrom(bytes,secKill,schema);
                    return secKill;
                }
            }finally {
                jedis.close();
            }

        } catch (Exception e){
            logger.error(e.getMessage(),e);
            //DENIED Redis is running in protected mode because protected mode is enabled, no bind address was specified, no authentication password is requested to clients. In this mode connections are only accepted from the loopback interface. If you want to connect from external computers to Redis you may adopt one of the following solutions: 1) Just disable protected mode sending the command 'CONFIG SET protected-mode no' from the loopback interface by connecting to Redis from the same host the server is running, however MAKE SURE Redis is not publicly accessible from internet if you do so. Use CONFIG REWRITE to make this change permanent. 2) Alternatively you can just disable the protected mode by editing the Redis configuration file, and setting the protected mode option to 'no', and then restarting the server. 3) If you started the server manually just for testing, restart it with the '--protected-mode no' option. 4) Setup a bind address or an authentication password. NOTE: You only need to do one of the above things in order for the server to start accepting connections from the outside.
        }
        return  null;
    }

    public String putSeckill(SecKill seckill){
        try{
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "seckill:"+seckill.getSeckillId();
                byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                int timeout = 60*60;
                String resutl = jedis.setex(key.getBytes(),timeout,bytes);
                return resutl;
            }finally {
                jedis.close();
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }

        return  null;
    }
}

































