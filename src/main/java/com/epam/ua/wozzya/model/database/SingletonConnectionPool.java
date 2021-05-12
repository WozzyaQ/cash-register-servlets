package com.epam.ua.wozzya.model.database;


import java.sql.Connection;
import java.sql.SQLException;

import java.util.concurrent.LinkedBlockingDeque;

public class SingletonConnectionPool extends AbstractConnectionPool implements ConnectionPool{

    private static final String propertyPath = "src/main/resources/config.properties";
    private volatile static ConnectionPool instance;
    private static SupplierWithSQLException<Connection> connectionSupplier;
    private static final int INITIAL_POOL_SIZE = 10;

    private static LinkedBlockingDeque<Connection> pool;

    private SingletonConnectionPool() {
        super();
        initPool();
    }

    private static void initPool() {
        connectionSupplier = new SimpleConnectionSupplier(propertyPath);

        pool = new LinkedBlockingDeque<>(INITIAL_POOL_SIZE);

        for (int i = 0; i < INITIAL_POOL_SIZE; ++i) {
            try{

                pool.add(new PooledConnectionProxy(connectionSupplier.get()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            synchronized(SingletonConnectionPool.class) {
                if (instance == null) {
                    instance = new SingletonConnectionPool();
                }
            }
        }
        return instance;
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;

        try {
            connection = pool.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        return pool.offer(connection);
    }

    @Override
    public String getUrl() {
        return connectionSupplier.getUrl();
    }

    @Override
    public String getUser() {
        return connectionSupplier.getUser();
    }

    @Override
    public String getPassword() {
        return connectionSupplier.getPassword();
    }
}
