package com.epam.ua.wozzya.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyExtractor {

    private PropertyExtractor() {

    }

    private static final String FORWARD_SLASH = "/";
    private static final String DOUBLE_COLUMN = ":";
    private static final String QUESTION_MARK = "?";
    private static final String EQUALS_SIGN = "=";
    private static final String AMPERSAND_SIGN = "&";

    public static String extractByKey(String key, String propertyPath) throws IOException{
        Properties prop = new Properties();
        try(InputStream is = new FileInputStream(propertyPath)) {
            prop.load(is);
            return prop.getProperty(key);
        }
    }

    public static String extractConnectionUrl(String propertyPath) throws IOException{
        Properties prop = new Properties();
        try (InputStream is = new FileInputStream(propertyPath)) {
            prop.load(is);

            StringBuilder sb = new StringBuilder();
            sb.append(prop.getProperty(ConnectionPropertiesConstants.DB__CONNECTOR))
                    .append(DOUBLE_COLUMN)
                    .append(FORWARD_SLASH + FORWARD_SLASH)
                    .append(prop.getProperty(ConnectionPropertiesConstants.DB__HOST))
                    .append(DOUBLE_COLUMN)
                    .append(prop.getProperty(ConnectionPropertiesConstants.DB__PORT))
                    .append(FORWARD_SLASH)
                    .append(prop.getProperty(ConnectionPropertiesConstants.DB__NAME));

            sb.append(QUESTION_MARK);

            for (ConnectionPropertiesConstants.ConnectionParameters parameter :
                    ConnectionPropertiesConstants.ConnectionParameters.values()) {
                String parameterValue = prop.getProperty(parameter.getPropAlias());
                if(parameterValue != null) {
                    sb.append(parameter.getUrlKey())
                            .append(EQUALS_SIGN)
                            .append(parameterValue)
                            .append(AMPERSAND_SIGN);
                }
            }

            sb.delete(sb.lastIndexOf(AMPERSAND_SIGN), sb.length());
            return sb.toString();
        }
    }
}
