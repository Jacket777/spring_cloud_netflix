package com.msb.zuul.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

@Component
public class RedisOperator {
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 获取剩余时间
     * @param isRedisLimitKey
     * @return
     */
    public long ttl(String isRedisLimitKey){
        return redisTemplate.getExpire(isRedisLimitKey);
    }

    /**
     * 增加次数
     * @param ipRedisKey
     * @param delta
     * @return
     */
    public long increment(String ipRedisKey, int delta) {
        return redisTemplate.opsForValue().increment(ipRedisKey,delta);
    }

    /**
     * 设置过期时间
     * @param key
     * @param timeout
     */
    public void expire(String key, Integer timeout) {
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 对key 设置超时时间
     * @param key
     * @param value
     * @param timeout
     */
    public void set(String key,String value,Integer timeout){
        redisTemplate.opsForValue().set(key,value,timeout,TimeUnit.SECONDS);

    }
}
