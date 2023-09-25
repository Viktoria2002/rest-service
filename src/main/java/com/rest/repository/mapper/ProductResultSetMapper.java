package com.rest.repository.mapper;

import com.rest.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ProductResultSetMapper {
    Product map(ResultSet resultSet) throws SQLException;
}
