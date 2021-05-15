package com.epam.ua.wozzya.model.db;


import com.epam.ua.wozzya.model.dao.AbstractDao;
import com.epam.ua.wozzya.model.dao.GenericDao;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class ConnectionManager {
    private static final Logger logger = Logger.getLogger(ConnectionManager.class);
    private Connection connection;

    private static ConnectionSupplier supplier;

    public static void setSupplier(ConnectionSupplier supplier) {
        ConnectionManager.supplier = supplier;
    }

    public void begin(boolean transaction, GenericDao<?>... daos) throws SQLException {
        Objects.requireNonNull(supplier, "configure supplier first via ConnectionManager#setSupplier");

        if(connection == null) {
            connection = supplier.getConnection();
        }

        connection.setAutoCommit(!transaction);

        for(GenericDao<?> dao : daos) {
            ((AbstractDao)dao).setConnection(connection);
        }
    }


    public void end() throws SQLException {
        if(connection != null) {
            connection.setAutoCommit(true);
            supplier.releaseConnection(connection);
        }
    }

    public void commit() throws SQLException {
        connection.commit();
    }

    public void rollback() throws SQLException {
        connection.rollback();
    }
}
