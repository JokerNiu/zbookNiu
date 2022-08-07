package com.wdzggroup.rbzy.erp.InsureContro.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    @Resource
    private RedisTemplate<String,String> redisTemplate;
    public void set(String key,String value ){
        //更改在redis里面查看key编码问题
        RedisSerializer redisSerializer =new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        ValueOperations<String,String> vo = redisTemplate.opsForValue();
        vo.set(key, value);

    }
    public String get(String key) {
        ValueOperations<String,String> vo = redisTemplate.opsForValue();
        return vo.get(key);
    }

    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }
    /*指定key失效时间*/
    public void expire(String key,long time){
        redisTemplate.expire(key,time, TimeUnit.MINUTES);

    }
    /*获取key失效时间*/
    public long getExpire(String key){
        Long expire = redisTemplate.getExpire(key);
        return expire;
    }
    /*判断key是否存在*/
    public boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }
}
