package com.gao.config;

import com.alibaba.fastjson.parser.ParserConfig;
import com.gao.utils.FastJsonRedisSerializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;
import java.time.Duration;

/**
 * @Author gs
 * @Date created time 2019/5/5 11:11
 * @Description
 */
@Configuration
@EnableCaching
public class RedisConfig<V> extends CachingConfigurerSupport{

    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {

        //初始化一个RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);
        //序列化方式
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);//JSONObject
        RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair.fromSerializer(fastJsonRedisSerializer);

        RedisCacheConfiguration defaultCacheConfig=RedisCacheConfiguration.defaultCacheConfig();
        //设置缓存过期时间
        defaultCacheConfig = defaultCacheConfig.entryTtl(Duration.ofMinutes(10));
        defaultCacheConfig = defaultCacheConfig.serializeValuesWith(pair);

        RedisCacheManager rcm = RedisCacheManager.builder(redisCacheWriter).cacheDefaults(defaultCacheConfig).transactionAware().build();
        ParserConfig.getGlobalInstance().addAccept("com.gao");
        ParserConfig.getGlobalInstance().addAccept("com.baomidou.mybatisplus.extension.plugins.pagination");
        return rcm;
    }


    @Bean
    public RedisTemplate<String,V> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,V> redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //使用fastjson序列化
        FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer(Object.class);
        //key序列化使用StringRedisSerializer
        redisTemplate.setKeySerializer(new StringRedisSerializer());//key序列化
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //value序列化使用fastJsonRedisSerializer
        redisTemplate.setValueSerializer(fastJsonRedisSerializer);  //value序列化
        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

}
