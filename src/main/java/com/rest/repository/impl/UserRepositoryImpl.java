package com.rest.repository.impl;

import com.rest.db.ConnectionManager;
import com.rest.exception.DatabaseException;
import com.rest.model.Order;
import com.rest.model.User;
import com.rest.repository.OrderRepository;
import com.rest.repository.UserRepository;
import com.rest.repository.mapper.UserResultSetMapper;
import com.rest.repository.mapper.impl.UserResultSetMapperImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.rest.util.Constants.ExceptionMessages.SQL_EXCEPTION_MESSAGE;
import static com.rest.util.Constants.SqlQueries.*;

public class UserRepositoryImpl implements UserRepository {
    private UserResultSetMapper resultSetMapper;
    private OrderRepository orderRepository;

    private UserRepositoryImpl() {
        resultSetMapper = new UserResultSetMapperImpl();
        orderRepository = OrderRepositoryImpl.getInstance();
    }

    public static UserRepositoryImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final UserRepositoryImpl INSTANCE = new UserRepositoryImpl();
    }

    @Override
    public User findById(Long id) {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement statement = conn.prepareStatement(FIND_USER_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() ? resultSetMapper.map(resultSet) : null;
            }
        } catch (SQLException e) {
            throw new DatabaseException(SQL_EXCEPTION_MESSAGE + e.getMessage());
        }
    }

    @Override
    public boolean deleteById(Long id) {
        List<Order> orders = orderRepository.findByUserId(id);
        orders.forEach(order -> orderRepository.deleteById(order.getId()));
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement statement = conn.prepareStatement(DELETE_USER)){
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DatabaseException(SQL_EXCEPTION_MESSAGE + e.getMessage());
        }
    }

    @Override
    public List<User> findAll() {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement statement = conn.prepareStatement(FIND_ALL_USERS)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                List<User> users = new ArrayList<>();
                while (resultSet.next()) {
                    users.add(resultSetMapper.map(resultSet));
                }
                return users;
            }
        } catch (SQLException e) {
            throw new DatabaseException(SQL_EXCEPTION_MESSAGE + e.getMessage());
        }
    }

    @Override
    public User save(User user) {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement statement = conn.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
            setParameters(user, statement);
            statement.executeUpdate();
            Long id = getSavedObjectId(statement);
            return findById(id);
        } catch (SQLException e) {
            throw new DatabaseException(SQL_EXCEPTION_MESSAGE + e.getMessage());
        }
    }

    @Override
    public User update(User user) {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement statement = conn.prepareStatement(UPDATE_USER)) {
            setParameters(user, statement);
            statement.executeUpdate();
            return findById(user.getId());
        } catch (SQLException e) {
            throw new DatabaseException(SQL_EXCEPTION_MESSAGE + e.getMessage());
        }
    }

    private void setParameters(User user, PreparedStatement statement) throws SQLException {
        statement.setString(1, user.getLastName());
        statement.setString(2, user.getFirstName());
        statement.setString(3, user.getEmail());
        statement.setString(4, user.getPassword());
        if (user.getId() != null) {
            statement.setLong(5, user.getId());
        }
    }
}
