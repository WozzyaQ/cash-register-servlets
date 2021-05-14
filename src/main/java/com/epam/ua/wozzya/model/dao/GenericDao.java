package com.epam.ua.wozzya.model.dao;

import com.epam.ua.wozzya.model.dao.entity.AbstractEntity;

import java.util.List;
import java.util.Optional;

public interface GenericDao<E extends AbstractEntity> {
    List<E> findAll() throws DaoException;

    Optional<E> findById(long id) throws DaoException;

    void create(E entity) throws DaoException;

    void update(E entity) throws DaoException;

    void delete(E entity) throws DaoException;
}
