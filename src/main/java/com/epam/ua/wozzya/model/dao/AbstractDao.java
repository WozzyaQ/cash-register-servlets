package com.epam.ua.wozzya.model.dao;

import com.epam.ua.wozzya.model.dao.entity.AbstractEntity;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<I, E extends AbstractEntity> {
    protected Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public abstract List<E> findAll() throws DaoException;
    public abstract Optional<E> findById(I id) throws DaoException;
    public abstract boolean create(E entity) throws DaoException;
    public abstract boolean update(E entity) throws DaoException;
    public abstract boolean delete(E entity) throws DaoException;
}
