package com.epam.ua.wozzya.model.db;


import com.epam.ua.wozzya.model.dao.AbstractDao;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {
    private static final Logger logger = Logger.getLogger(ConnectionManager.class);
    private Connection connection;

    public ConnectionManager() {
    }

    public void begin(boolean transaction, AbstractDao<?,?> ... daos) throws SQLException {
        if(connection == null) {
            connection = DBManager.getInstance().getConnection();
        }

        connection.setAutoCommit(!transaction);

        for(AbstractDao<?,?> dao : daos) {
            dao.setConnection(connection);
        }
    }


    public void end() throws SQLException {
        if(connection != null) {
            connection.setAutoCommit(true);
            DBManager.getInstance().release(connection);
        }
    }

    public void commit() throws SQLException {
        connection.commit();
    }

    public void rollback() throws SQLException {
        connection.rollback();
    }
}
