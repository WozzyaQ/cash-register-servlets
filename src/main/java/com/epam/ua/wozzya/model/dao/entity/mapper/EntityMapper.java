package com.epam.ua.wozzya.model.dao.entity.mapper;

import com.epam.ua.wozzya.model.dao.entity.AbstractEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface EntityMapper<T extends AbstractEntity>{
    T mapRow(ResultSet rs) throws SQLException;
}
