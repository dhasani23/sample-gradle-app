package com.enterprise.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

@Service
@EnableCaching
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "enterprise.cache")
public class SpringBootCacheService {
    
    private String cacheType = "legacy";
    private int maxSize = 100;
    
    @PostConstruct
    public void init() {
        // javax.annotation usage - will need migration to jakarta.annotation in future Java versions
        System.out.println("Initializing cache service with type: " + cacheType);
    }
    
    @Cacheable("legacyData")
    public String getCachedData(String key) {
        // Replace deprecated Date constructor with modern time API
        Calendar cal = Calendar.getInstance();
        cal.set(2023, Calendar.DECEMBER, 25);
        
        // Or use Java 8+ time API
        LocalDateTime timestamp = LocalDateTime.of(2023, 12, 25, 0, 0);
        
        return "Cached data for " + key + " at " + timestamp.format(DateTimeFormatter.ISO_LOCAL_DATE);
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