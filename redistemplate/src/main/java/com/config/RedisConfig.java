package com.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class RedisConfig {

    @Value(value = "${spring.redis.host}")
    private String host;

    @Value(value = "${spring.redis.port}")
    private int port;

    @Value(value = "${spring.redis.password}")
    private String password;

    @Value(value = "${spring.redis.timeout}")
    private int timeout;

    @Value(value = "${spring.redis.pool.max-active}")
    private int maxActive;

    @Value(value = "${spring.redis.pool.max-wait}")
    private int maxWait;

    @Value(value = "${spring.redis.pool.max-idle}")
    private int maxIdle;

    @Value(value = "${spring.redis.pool.min-idle}")
    private int minIdle;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        System.out.println(host);
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(host);
        factory.setPort(port);
        factory.setPassword(password);
        factory.setTimeout(timeout);
        factory.getPoolConfig().setMaxIdle(maxIdle);
        factory.getPoolConfig().setMinIdle(minIdle);
        factory.getPoolConfig().setMaxTotal(maxActive);
        factory.getPoolConfig().setMaxWaitMillis(maxWait);
        return factory;
    }


    @Bean
    public RedisTemplate<String, Serializable> redisCacheTemplate(JedisConnectionFactory factory) {
        RedisTemplate<String, Serializable> template = new RedisTemplate<String, Serializable>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setDefaultExpiration(10);
        return cacheManager;
    }


}
