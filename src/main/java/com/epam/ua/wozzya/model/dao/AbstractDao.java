package com.epam.ua.wozzya.model.dao;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public abstract class AbstractDao {
    protected Connection connection;
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void ensureOneRowAffected(int actualRowsAffected) throws DaoException {
        if(actualRowsAffected != 1) {
            throw new DaoException("affected more than 1 row");
        }
    }

    public void closeResultSetUnsafe(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                //DO NOTHING
            }
        }
    }
}
