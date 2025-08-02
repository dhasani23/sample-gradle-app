package com.enterprise.service;

import com.enterprise.core.CustomClassLoader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.util.Date;

public class LegacyDataService {
    private final CustomClassLoader classLoader;
    
    public LegacyDataService() {
        this.classLoader = new CustomClassLoader(getClass().getClassLoader());
    }
    
    @SuppressWarnings("deprecation")
    public String processLegacyData(String input) {
        // Using deprecated Date constructor
        Date timestamp = new Date(2023, 11, 25);
        
        try {
            // JAXB will be removed from JDK in Java 11+
            JAXBContext context = JAXBContext.newInstance(String.class);
            return input + " processed at " + timestamp.toString();
        } catch (JAXBException e) {
            throw new RuntimeException("JAXB processing failed", e);
        }
    }
    
    public void performClassLoading() {
        classLoader.performUnsafeOperation();
    }
    
    @Override
    protected void finalize() throws Throwable {
        // Another deprecated finalize usage
        super.finalize();
    }
}