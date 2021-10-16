package ru.geekbrains.trainingproject.market.config.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories
public class RedisConnectionConfig {
    @Bean
    public JedisConnectionFactory jedisConnectionRedisServiceFactory(int port, String hostName, String password) {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(hostName, port);
        redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

//    @Bean
//    public JedisConnectionFactory jedisConnectionForMemurai() {
//        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(cartHostname, cartPort);
//        return new JedisConnectionFactory();
//    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(int port, String hostName, String password) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(jedisConnectionRedisServiceFactory(port, hostName, password));
        return template;
    }

//    @Bean
//    public RedisTemplate<String, Object> memuraiTemplete(){
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//        template.setConnectionFactory(jedisConnectionForMemurai());
//        return template;
//    }
}

