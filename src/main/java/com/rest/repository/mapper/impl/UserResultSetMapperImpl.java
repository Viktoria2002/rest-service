package com.rest.repository.mapper.impl;

import com.rest.model.User;
import com.rest.repository.mapper.UserResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.rest.util.Constants.ColumnNames.*;

public class UserResultSetMapperImpl implements UserResultSetMapper {
    @Override
    public User map(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getLong(ID),
                resultSet.getString(LAST_NAME),
                resultSet.getString(FIRST_NAME),
                resultSet.getString(EMAIL),
                resultSet.getString(PASSWORD));
    }
}
