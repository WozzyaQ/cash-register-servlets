package com.epam.ua.wozzya.model.db;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class MySqlConnectionPoolSupplier implements ConnectionSupplier{


    public MySqlConnectionPoolSupplier() {
        // reflection conscious
    }

    public Connection getConnection() throws SQLException {
        Connection con = null;
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");

            DataSource ds = (DataSource) envContext.lookup("jdbc/projectdb");
            con = ds.getConnection();
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
        return con;
    }

    public void releaseConnection(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}