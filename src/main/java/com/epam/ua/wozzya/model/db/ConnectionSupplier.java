package com.epam.ua.wozzya.model.db;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionSupplier {
    Connection getConnection() throws SQLException;
    void releaseConnection(Connection connection);
}
