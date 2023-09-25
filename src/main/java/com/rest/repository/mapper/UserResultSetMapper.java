package com.rest.repository.mapper;

import com.rest.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserResultSetMapper {
    User map(ResultSet resultSet) throws SQLException;
}
