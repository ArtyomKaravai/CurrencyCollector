package com.example.currencycollector.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;


@Configuration
@EnableCaching
@EnableScheduling
public class MainConfiguration {

    public static final String EXCHANGE_RATE_CACHE = "rateCache";

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(EXCHANGE_RATE_CACHE);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
