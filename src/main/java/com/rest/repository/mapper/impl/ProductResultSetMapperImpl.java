package com.rest.repository.mapper.impl;

import com.rest.model.Product;
import com.rest.repository.mapper.ProductResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.rest.util.Constants.ColumnNames.*;
import static com.rest.util.Constants.ColumnNames.PRICE;

public class ProductResultSetMapperImpl implements ProductResultSetMapper {
    @Override
    public Product map(ResultSet resultSet) throws SQLException {
        return new Product(resultSet.getLong(PR_ID),
                resultSet.getString(NAME),
                resultSet.getString(DESCRIPTION),
                resultSet.getBigDecimal(PRICE));
    }
}
