package com.enterprise.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.annotation.PostConstruct;
import java.util.Date;

@Service
@EnableCaching
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "enterprise.cache")
public class SpringBootCacheService {
    
    private String cacheType = "legacy";
    private int maxSize = 100;
    
    @PostConstruct
    public void init() {
        // javax.annotation usage - needs migration to jakarta.annotation in Java 17
        System.out.println("Initializing cache service with type: " + cacheType);
    }
    
    @Cacheable("legacyData")
    public String getCachedData(String key) {
        // Simulate expensive operation with deprecated Date constructor
        @SuppressWarnings("deprecation")
        Date timestamp = new Date(2023, 11, 25);
        
        return "Cached data for " + key + " at " + timestamp.toString();
    }
    
    // Spring Boot Configuration Properties - binding will change in newer versions
    public String getCacheType() {
        return cacheType;
    }
    
    public void setCacheType(String cacheType) {
        this.cacheType = cacheType;
    }
    
    public int getMaxSize() {
        return maxSize;
    }
    
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
}