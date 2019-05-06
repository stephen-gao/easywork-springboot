package com.gao.cache.impl;

import com.gao.cache.ICache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author gs
 * @Date created time 2019/5/5 11:29
 * @Description
 */
@Component
public class RedisCacheImpl<HK, V> implements ICache<HK , V>{

    // 在构造器中获取redisTemplate实例, key(not hashKey) 默认使用String类型
    private RedisTemplate<String, Object> redisTemplate;
    // 在构造器中通过redisTemplate的工厂方法实例化操作对象
    private HashOperations<String, Object, Object> hashOperations;
    private ListOperations<String, Object> listOperations;
    private ZSetOperations<String, Object> zSetOperations;
    private SetOperations<String, Object> setOperations;
    private ValueOperations<String, Object> valueOperations;

    // 实例化操作对象后就可以直接调用方法操作Redis数据库
    @Autowired
    public RedisCacheImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
        this.listOperations = redisTemplate.opsForList();
        this.zSetOperations = redisTemplate.opsForZSet();
        this.setOperations = redisTemplate.opsForSet();
        this.valueOperations = redisTemplate.opsForValue();
    }


    @Override
    public void set(String key, V value, Long expireTime) {
//        String v = JSON.toJSONString(value);
        valueOperations.set(key, value ,expireTime,TimeUnit.SECONDS);
    }

    @Override
    public void setFiveMin(String key, V value) {
        set(key,value,FIVE_MIN_SECONDS);
    }

    @Override
    public void setTenMin(String key, V value) {
        set(key,value,TEN_MIN_SECONDS);
    }

    @Override
    public void setHalfHour(String key, V value) {
        set(key,value,HALF_HOUR_SECONDS);
    }

    @Override
    public void setOneHour(String key, V value) {
        set(key,value,ONE_HOUR_SECONDS);
    }

    @Override
    public V get(String key) {
        Object v = valueOperations.get(key);
        return (V)v;
    }

    @Override
    public void remove(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void expireHalfHour(String key) {
        redisTemplate.expire(key,HALF_HOUR_SECONDS, TimeUnit.SECONDS);
    }
}
