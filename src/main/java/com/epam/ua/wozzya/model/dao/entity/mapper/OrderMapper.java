package com.epam.ua.wozzya.model.dao.entity.mapper;

import com.epam.ua.wozzya.model.dao.entity.Order;
import com.epam.ua.wozzya.model.db.Fields;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements EntityMapper<Order>{

    @Override
    public Order mapRow(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getLong(Fields.ENTITY__ID));
        order.setCreator(rs.getLong(Fields.ORDER__CREATED_BY_WORKER));
        order.setClosed(rs.getBoolean(Fields.ORDER__IS_CLOSED));
        order.setDateClosed(rs.getDate(Fields.ORDER__DATE_CLOSED));
        order.setDateCreated(rs.getDate(Fields.ORDER__DATE_CREATED));
        order.setComment(rs.getString(Fields.ORDER__COMMENT));
        order.setLastEdited(rs.getDate(Fields.ORDER__LAST_EDITED));
        order.setOrderedBy(rs.getString(Fields.ORDER__ORDERED_BY));
        order.setTotal(rs.getBigDecimal(Fields.ORDER__TOTAL));
        return order;
    }
}
