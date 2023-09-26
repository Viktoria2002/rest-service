package com.rest.repository.impl;

import com.rest.db.ConnectionManager;
import com.rest.exception.DatabaseException;
import com.rest.model.Order;
import com.rest.repository.OrderRepository;
import com.rest.repository.dto.SimpleOrderDto;
import com.rest.repository.mapper.OrderResultSetMapper;
import com.rest.repository.mapper.impl.OrderResultSetMapperImpl;

import java.sql.*;
import java.util.List;

import static com.rest.util.Constants.ExceptionMessages.SQL_EXCEPTION_MESSAGE;
import static com.rest.util.Constants.SqlQueries.*;

public class OrderRepositoryImpl implements OrderRepository {
    private OrderResultSetMapper resultSetMapper;

    private OrderRepositoryImpl() {
        resultSetMapper = new OrderResultSetMapperImpl();
    }

    public static OrderRepositoryImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final OrderRepositoryImpl INSTANCE = new OrderRepositoryImpl();
    }

    @Override
    public Order findById(Long id) {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement statement = conn.prepareStatement(FIND_ORDER_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSetMapper.map(resultSet).get(0);
            }
        } catch (SQLException e) {
            throw new DatabaseException(SQL_EXCEPTION_MESSAGE + e.getMessage());
        }
    }

    @Override
    public List<Order> findByUserId(Long id) {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement statement = conn.prepareStatement(FIND_ORDERS_BY_USER_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSetMapper.map(resultSet);
            }
        } catch (SQLException e) {
            throw new DatabaseException(SQL_EXCEPTION_MESSAGE + e.getMessage());
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try (Connection conn = ConnectionManager.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement statement1 = conn.prepareStatement(DELETE_PRODUCT_ORDER);
                 PreparedStatement statement2 = conn.prepareStatement(DELETE_ORDER)) {
                statement1.setLong(1, id);
                statement2.setLong(1, id);
                statement1.executeUpdate();
                statement2.executeUpdate();
                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                throw new DatabaseException(SQL_EXCEPTION_MESSAGE + e.getMessage());
            }
        } catch (SQLException e) {
            throw new DatabaseException(SQL_EXCEPTION_MESSAGE + e.getMessage());
        }
    }

    @Override
    public List<Order> findAll() {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement statement = conn.prepareStatement(FIND_ALL_ORDERS)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSetMapper.map(resultSet);
            }
        } catch (SQLException e) {
            throw new DatabaseException(SQL_EXCEPTION_MESSAGE + e.getMessage());
        }
    }

    @Override
    public Order save(SimpleOrderDto order) {
        try (Connection conn = ConnectionManager.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement statement1 = conn.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement statement2 = conn.prepareStatement(INSERT_PRODUCT_ORDER)) {
                setParameters(order, statement1);
                statement1.executeUpdate();
                Long id = getSavedObjectId(statement1);
                for (Long productId : order.getProductIds()) {
                    setParameters(id, productId, statement2);
                    statement2.executeUpdate();
                }
                conn.commit();
                return findById(id);
            } catch (SQLException e) {
                conn.rollback();
                throw new DatabaseException(SQL_EXCEPTION_MESSAGE + e.getMessage());
            }
        } catch (SQLException e) {
            throw new DatabaseException(SQL_EXCEPTION_MESSAGE + e.getMessage());
        }
    }

    private void setParameters(SimpleOrderDto order, PreparedStatement statement) throws SQLException {
        statement.setDate(1, new Date(order.getDate().getTime()));
        statement.setBigDecimal(2, order.getDiscount());
        statement.setString(3, order.getShippingAddress());
        statement.setLong(4, order.getUserId());
    }

    private void setParameters(Long orderId, Long productId, PreparedStatement statement) throws SQLException {
        statement.setLong(1, productId);
        statement.setLong(2, orderId);
    }
}
