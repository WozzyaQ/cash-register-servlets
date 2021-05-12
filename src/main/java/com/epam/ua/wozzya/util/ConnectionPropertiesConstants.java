package com.epam.ua.wozzya.util;

public class ConnectionPropertiesConstants {
    private ConnectionPropertiesConstants() {

    }
    public static final String DB__CONNECTOR = "db.connector";
    public static final String DB__PORT = "db.port";
    public static final String DB__HOST = "db.host";
    public static final String DB__USER = "db.user";
    public static final String DB__PASS = "db.pass";
    public static final String DB__NAME = "db.name";

    public enum ConnectionParameters {
        SERVER_TIMEZONE("db.serverTimezone"),
        ENCODING("db.encoding");

        final String propAlias;
        final String urlKey;
        ConnectionParameters(String propAlias) {
            this.propAlias = propAlias;
            this.urlKey = propAlias.substring(3);
        }

        public String getPropAlias() {
            return propAlias;
        }

        public String getUrlKey() {
            return urlKey;
        }
    }
}
