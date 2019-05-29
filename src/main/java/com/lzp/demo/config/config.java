package com.lzp.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Author LiZePing
 * @date2019/5/28 8:28
 */
@Configuration
public class config {

    @Autowired
    private RedisTemplate redisTemplate;
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){
         redisTemplate.setKeySerializer(new StringRedisSerializer());
         redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
         return redisTemplate;
    }
}
