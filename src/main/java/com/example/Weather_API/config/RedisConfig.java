package com.example.Weather_API.config;

import com.example.Weather_API.model.WeatherResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, WeatherResponse> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, WeatherResponse> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        
        // Use String serializer for keys
        template.setKeySerializer(new StringRedisSerializer());
        
        // Use JSON serializer for values
        Jackson2JsonRedisSerializer<WeatherResponse> serializer = new Jackson2JsonRedisSerializer<>(WeatherResponse.class);
        serializer.setObjectMapper(new ObjectMapper());
        template.setValueSerializer(serializer);
        
        return template;
    }

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}