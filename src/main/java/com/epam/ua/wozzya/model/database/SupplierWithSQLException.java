package com.epam.ua.wozzya.model.database;

import java.sql.SQLException;

public interface SupplierWithSQLException <T>{
    T get() throws SQLException;
    String getUrl();
    String getUser();
    String getPassword();
}
