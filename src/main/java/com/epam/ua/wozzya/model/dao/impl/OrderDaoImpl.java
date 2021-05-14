package com.epam.ua.wozzya.model.dao.impl;

import com.epam.ua.wozzya.model.dao.AbstractDao;
import com.epam.ua.wozzya.model.dao.DaoException;
import com.epam.ua.wozzya.model.dao.OrderDao;
import com.epam.ua.wozzya.model.dao.entity.Order;
import com.epam.ua.wozzya.model.dao.entity.mapper.OrderMapper;
import com.epam.ua.wozzya.model.db.Fields;
import com.epam.ua.wozzya.model.db.Tables;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl
        extends AbstractDao
        implements OrderDao {

    private static final Logger log = Logger.getLogger(OrderDaoImpl.class);

    private static final String SQL__FIND_ALL_ORDERS = "select * from " + Tables.ORDER_TABLE;

    private static final String SQL__FIND_BY_ID = "select * from " +
            Tables.ORDER_TABLE + " where " + Fields.ENTITY__ID + " = ?; ";

    private static final String SQL__CREATE_ORDER = "insert into " + Tables.ORDER_TABLE + " (" +
            Fields.ORDER__CREATED_BY_WORKER + ", " + Fields.ORDER__ORDERED_BY + ", " + Fields.ORDER__COMMENT + ")" +
            "values (?, ?, ?)";

    private static final String SQL__UPDATE_ORDER = "update " + Tables.ORDER_TABLE + " set " +
            Fields.ORDER__IS_CLOSED + " = ?, " +
            Fields.ORDER__DATE_CLOSED + " = ?, " +
            Fields.ORDER__COMMENT + " = ?, " +
            Fields.ORDER__ORDERED_BY + "= ?" +
            "where " + Tables.ORDER_TABLE + ".id = ?";

    private static final String SQL__DELETE_ORDER = "delete from " + Tables.ORDER_TABLE +
            "where " + Tables.ORDER_TABLE + ".id = ?";


    @Override
    public List<Order> findAll() throws DaoException {
        List<Order> orders = new ArrayList<>();

        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(SQL__FIND_ALL_ORDERS)) {
            OrderMapper mapper = new OrderMapper();
            while (rs.next()) {
                orders.add(mapper.mapRow(rs));
            }
        } catch (SQLException e) {
            log.error("exception near OrderDao#findAll");
            throw new DaoException(e);
        }

        return orders;
    }

    @Override
    public Optional<Order> findById(long id) throws DaoException {

        try (PreparedStatement st = connection.prepareStatement(SQL__FIND_BY_ID)) {
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            OrderMapper mapper = new OrderMapper();

            if (rs.next()) {
                return Optional.of(mapper.mapRow(rs));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            log.error("exception near OrderDao#findById");
            throw new DaoException(e);
        }
    }

    @Override
    public void create(Order entity) throws DaoException {
        ResultSet rs = null;
        try (PreparedStatement st = connection.prepareStatement(SQL__CREATE_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            st.setLong(1, entity.getCreatorId());
            st.setString(2, entity.getOrderedBy());
            st.setString(3, entity.getComment());


            ensureOneRowAffected(st.executeUpdate());

            rs = st.getGeneratedKeys();
            while (rs.next()) {
                entity.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            log.error("exception near OrderDao#create");
            throw new DaoException(e);
        } finally {
            closeResultSetUnsafe(rs);
        }
    }

    @Override
    public void update(Order entity) throws DaoException {
        try (PreparedStatement ps = connection.prepareStatement(SQL__UPDATE_ORDER)) {
            ps.setBoolean(1, entity.getClosed());
            ps.setDate(2, entity.getDateClosed());
            ps.setString(3, entity.getComment());
            ps.setString(4, entity.getOrderedBy());
            ps.setLong(5, entity.getId());

            ensureOneRowAffected(ps.executeUpdate());
        } catch (SQLException e) {
            log.error("exception near OrderDao#update");
        }
    }

    @Override
    public void delete(Order entity) throws DaoException {
        try (PreparedStatement ps = connection.prepareStatement(SQL__DELETE_ORDER)) {
            ps.setLong(1, entity.getId());

            ensureOneRowAffected(ps.executeUpdate());

        } catch (SQLException e) {
            log.error("exception near OrderDao#delete");
            throw new DaoException(e);
        }
    }
}
