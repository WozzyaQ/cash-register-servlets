package com.epam.ua.wozzya.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyExtractor {

    private PropertyExtractor() {
    }

    public static String extractByKey(String key, String propertyPath) throws IOException{
        Properties prop = new Properties();
        try(InputStream is = new FileInputStream(propertyPath)) {
            prop.load(is);
            return prop.getProperty(key);
        }
    }
}
