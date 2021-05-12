package com.epam.ua.wozzya.model.database;

import com.epam.ua.wozzya.util.ConnectionPropertiesConstants;
import com.epam.ua.wozzya.util.PropertyExtractor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SimpleConnectionSupplier implements SupplierWithSQLException<Connection> {

    private final String propertyPath;
    private String url;
    private String user;
    private String password;

    public SimpleConnectionSupplier(String propertyPath) {
        this.propertyPath = propertyPath;
    }

    @Override
    public Connection get() throws SQLException {
        return DriverManager.getConnection(getUrl(), getUser(), getPassword());
    }


    @Override
    public String getUrl() {
        if(url == null) {
            url = loadUrl();
        }
        return url;
    }

    private String loadUrl() {
        try{
            return PropertyExtractor.extractConnectionUrl(propertyPath);
        }catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getUser() {
        if(user == null) {
            user = loadUser();
        }
        return user;
    }

    //TODO custom exception
    private String loadUser() {
        try{
            return PropertyExtractor
                    .extractByKey(ConnectionPropertiesConstants.DB__USER, propertyPath);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getPassword() {
        if(password == null) {
            password = loadPassword();
        }
        return password;
    }

    private String loadPassword() {
        try{
            return PropertyExtractor
                    .extractByKey(ConnectionPropertiesConstants.DB__PASS, propertyPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
