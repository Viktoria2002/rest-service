package com.rest.repository.mapper.impl;

import com.rest.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.rest.util.Constants.ColumnNames.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserResultSetMapperImplTest {
    @InjectMocks
    private UserResultSetMapperImpl mapper;

    @Mock
    private ResultSet resultSet;

    @Test
     void testMap() throws SQLException {
        when(resultSet.getLong(ID)).thenReturn(1L);
        when(resultSet.getString(LAST_NAME)).thenReturn("Doe");
        when(resultSet.getString(FIRST_NAME)).thenReturn("John");
        when(resultSet.getString(EMAIL)).thenReturn("john.doe@example.com");
        when(resultSet.getString(PASSWORD)).thenReturn("password");

        User user = mapper.map(resultSet);

        assertEquals(1L, user.getId());
        assertEquals("Doe", user.getLastName());
        assertEquals("John", user.getFirstName());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
    }
}
