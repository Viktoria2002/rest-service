package com.rest.repository.mapper;

import com.rest.model.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface OrderResultSetMapper {
    List<Order> map(ResultSet resultSet) throws SQLException;
}
