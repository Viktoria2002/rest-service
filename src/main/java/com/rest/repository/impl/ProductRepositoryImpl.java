package com.rest.repository.impl;

import com.rest.db.ConnectionManager;
import com.rest.exception.DatabaseException;
import com.rest.model.Product;
import com.rest.repository.ProductRepository;
import com.rest.repository.mapper.ProductResultSetMapper;
import com.rest.repository.mapper.impl.ProductResultSetMapperImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.rest.util.Constants.ExceptionMessages.SQL_EXCEPTION_MESSAGE;
import static com.rest.util.Constants.SqlQueries.*;

public class ProductRepositoryImpl implements ProductRepository {
    private ProductResultSetMapper resultSetMapper;

    private ProductRepositoryImpl() {
        resultSetMapper = new ProductResultSetMapperImpl();
    }

    public static ProductRepositoryImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final ProductRepositoryImpl INSTANCE = new ProductRepositoryImpl();
    }

    @Override
    public Product findById(Long id) {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement statement = conn.prepareStatement(FIND_PRODUCT_BY_ID)) {
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
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement statement = conn.prepareStatement(DELETE_PRODUCT)){
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DatabaseException(SQL_EXCEPTION_MESSAGE + e.getMessage());
        }
    }

    @Override
    public List<Product> findAll() {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement statement = conn.prepareStatement(FIND_ALL_PRODUCTS)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Product> products = new ArrayList<>();
                while (resultSet.next()) {
                    products.add(resultSetMapper.map(resultSet));
                }
                return products;
            }
        } catch (SQLException e) {
            throw new DatabaseException(SQL_EXCEPTION_MESSAGE + e.getMessage());
        }
    }

    @Override
    public Product save(Product product) {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement statement = conn.prepareStatement(INSERT_PRODUCT, Statement.RETURN_GENERATED_KEYS)) {
            setParameters(product, statement);
            statement.executeUpdate();
            Long id = getSavedObjectId(statement);
            return findById(id);
        } catch (SQLException e) {
            throw new DatabaseException(SQL_EXCEPTION_MESSAGE + e.getMessage());
        }
    }

    @Override
    public Product update(Product product) {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement statement = conn.prepareStatement(UPDATE_PRODUCT)) {
            setParameters(product, statement);
            statement.executeUpdate();
            return findById(product.getId());
        } catch (SQLException e) {
            throw new DatabaseException(SQL_EXCEPTION_MESSAGE + e.getMessage());
        }
    }

    private void setParameters(Product product, PreparedStatement statement) throws SQLException {
        statement.setString(1, product.getName());
        statement.setString(2, product.getDescription());
        statement.setBigDecimal(3, product.getPrice());
        if (product.getId() != null) {
            statement.setLong(4, product.getId());
        }
    }
}
