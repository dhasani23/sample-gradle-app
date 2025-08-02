package com.enterprise.core;

import sun.misc.Unsafe;
import java.lang.reflect.Field;
import java.security.ProtectionDomain;

// Add more comprehensive suppression warnings for Java 17
@SuppressWarnings({"deprecation", "removal", "sunapi", "restricted"})
public class CustomClassLoader extends ClassLoader {
    private static final Unsafe unsafe;
    
    static {
        try {
            // More robust handling for accessing Unsafe in Java 17
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (Exception e) {
            throw new RuntimeException("Cannot access Unsafe", e);
        }
    }
    
    public CustomClassLoader(ClassLoader parent) {
        super(parent);
    }
    
    public Class<?> defineCustomClass(String name, byte[] b, int off, int len) {
        return defineClass(name, b, off, len, (ProtectionDomain) null);
    }
    
    public Class<?> defineCustomClass(String name, byte[] b) {
        return defineClass(name, b, 0, b.length);
    }
    
    public void performUnsafeOperation() {
        // Using deprecated Unsafe API - add explicit suppression for Java 17
        try {
            long address = unsafe.allocateMemory(1024);
            unsafe.freeMemory(address);
        } catch (Exception e) {
            System.err.println("Unsafe operation failed: " + e.getMessage());
        }
    }
    
    // Remove deprecated finalize method - not compatible with Java 17 best practices
}