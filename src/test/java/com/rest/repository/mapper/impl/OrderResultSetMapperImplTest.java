package com.rest.repository.mapper.impl;

import com.rest.model.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.rest.util.Constants.ColumnNames.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderResultSetMapperImplTest {
    @InjectMocks
    private OrderResultSetMapperImpl mapper;

    @Mock
    private ResultSet resultSet;

    @Test
     void testMap() throws SQLException {
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getLong(ID)).thenReturn(1L, 2L);
        when(resultSet.getDate(DATE)).thenReturn(new Date(2022));
        when(resultSet.getBigDecimal(DISCOUNT)).thenReturn(BigDecimal.ZERO);
        when(resultSet.getString(SHIPPING_ADDRESS)).thenReturn("Address");
        when(resultSet.getLong(USER_ID)).thenReturn(1L);
        when(resultSet.getString(LAST_NAME)).thenReturn("LastName");
        when(resultSet.getString(FIRST_NAME)).thenReturn("FirstName");
        when(resultSet.getString(EMAIL)).thenReturn("email@example.com");
        when(resultSet.getString(PASSWORD)).thenReturn("password");
        when(resultSet.getLong(PR_ID)).thenReturn(1L, 2L);
        when(resultSet.getString(NAME)).thenReturn("Product1", "Product2");
        when(resultSet.getString(DESCRIPTION)).thenReturn("Description1", "Description2");
        when(resultSet.getBigDecimal(PRICE)).thenReturn(BigDecimal.TEN, BigDecimal.valueOf(20));

        List<Order> orders = mapper.map(resultSet);

        assertEquals(2, orders.size());

        Order order2 = orders.get(1);
        assertEquals(2L, order2.getId());
    }
}