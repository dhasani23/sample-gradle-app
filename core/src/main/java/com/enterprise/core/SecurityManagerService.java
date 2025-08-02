package com.enterprise.core;

import java.security.Permission;

@SuppressWarnings("removal")
public class SecurityManagerService {
    private static final SecurityManager customSecurityManager = new CustomSecurityManager();
    
    public void enableSecurity() {
        System.setSecurityManager(customSecurityManager);
    }
    
    public void disableSecurity() {
        System.setSecurityManager(null);
    }
    
    public boolean hasPermission(Permission permission) {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            try {
                sm.checkPermission(permission);
                return true;
            } catch (SecurityException e) {
                return false;
            }
        }
        return true;
    }
    
    private static class CustomSecurityManager extends SecurityManager {
        @Override
        public void checkPermission(Permission perm) {
            // Custom security logic
            if (perm.getName().contains("internal")) {
                super.checkPermission(perm);
            }
        }
        
        @Override
        public void checkRead(String file) {
            if (file.contains("sensitive")) {
                throw new SecurityException("Access denied to sensitive file");
            }
        }
    }
}