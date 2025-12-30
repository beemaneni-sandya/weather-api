package com.example.Weather_API.service;

import com.example.Weather_API.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.Duration;

@Service
public class WeatherService {

    private final WebClient.Builder webClientBuilder;
    private final RedisTemplate<String, WeatherResponse> redisTemplate;

    @Value("${weather.api.url}")
    private String apiUrl;

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    public WeatherService(WebClient.Builder webClientBuilder, RedisTemplate<String, WeatherResponse> redisTemplate) {
        this.webClientBuilder = webClientBuilder;
        this.redisTemplate = redisTemplate;
    }

    public WeatherResponse getWeather(String city) {
        String cacheKey = "weather:" + city.toLowerCase();

        // Step 1: Try Redis cache with error handling
        try {
            WeatherResponse cached = redisTemplate.opsForValue().get(cacheKey);
            if (cached != null) {
                System.out.println("✅ Cache hit for city: " + city);
                return cached;
            }
        } catch (Exception e) {
            System.err.println("⚠️ Redis cache error: " + e.getMessage());
        }

        System.out.println("🌦 Fetching weather from API for city: " + city);

        // Step 2: Build OpenWeatherMap API URL
        String requestUrl = String.format(
                "%s?q=%s&appid=%s&units=metric",
                apiUrl, city, apiKey
        );

        try {
            // Step 3: Make API request
            WeatherResponse response = webClientBuilder.build()
                    .get()
                    .uri(requestUrl)
                    .retrieve()
                    .bodyToMono(WeatherResponse.class)
                    .block(Duration.ofSeconds(10));

            if (response == null) {
                throw new RuntimeException("Received null response from Weather API");
            }

            // Step 4: Try to cache response
            try {
                redisTemplate.opsForValue().set(cacheKey, response, Duration.ofHours(12));
                System.out.println("☁️ Cached new weather data for: " + city);
            } catch (Exception e) {
                System.err.println("⚠️ Failed to cache data: " + e.getMessage());
            }

            return response;

        } catch (WebClientResponseException e) {
            System.err.println("❌ API Error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
            throw new RuntimeException("Error fetching weather: " + e.getStatusCode());
        } catch (Exception e) {
            System.err.println("❌ Unexpected error fetching weather: " + e.getMessage());
            throw new RuntimeException("Unexpected error fetching weather data", e);
        }
    }
}