package com.enterprise.api;

import com.enterprise.service.LegacyDataService;
import com.enterprise.service.SpringBootCacheService;
import com.enterprise.core.SecurityManagerService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@RestController
@RequestMapping("/api/v1")
public class EnterpriseController {
    
    private final LegacyDataService dataService;
    private final SecurityManagerService securityService;
    private final SpringBootCacheService cacheService;
    
    public EnterpriseController(SpringBootCacheService cacheService) {
        this.dataService = new LegacyDataService();
        this.securityService = new SecurityManagerService();
        this.cacheService = cacheService;
    }
    
    @PostConstruct
    public void init() {
        // javax.annotation will cause issues in Java 17
        securityService.enableSecurity();
    }
    
    @PreDestroy
    public void cleanup() {
        securityService.disableSecurity();
    }
    
    @GetMapping("/process")
    public String processData(@RequestParam String input) {
        return dataService.processLegacyData(input);
    }
    
    @PostMapping("/unsafe")
    public String performUnsafeOperation() {
        try {
            dataService.performClassLoading();
            return "Unsafe operation completed";
        } catch (Exception e) {
            return "Unsafe operation failed: " + e.getMessage();
        }
    }
    
    @GetMapping("/cache/{key}")
    public String getCachedData(@PathVariable String key) {
        return cacheService.getCachedData(key);
    }
    
    @GetMapping("/config")
    public String getConfiguration() {
        return "Cache type: " + cacheService.getCacheType() + 
               ", Max size: " + cacheService.getMaxSize();
    }
}