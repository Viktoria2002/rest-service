package com.rest.repository.mapper.impl;

import com.rest.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.rest.util.Constants.ColumnNames.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductResultSetMapperImplTest {
    @InjectMocks
    private ProductResultSetMapperImpl productResultSetMapper;

    @Mock
    private ResultSet resultSet;

    @Test
     void testMap() throws SQLException {
        when(resultSet.getLong(PR_ID)).thenReturn(1L);
        when(resultSet.getString(NAME)).thenReturn("Product");
        when(resultSet.getString(DESCRIPTION)).thenReturn("Description");
        when(resultSet.getBigDecimal(PRICE)).thenReturn(BigDecimal.TEN);

        Product product = productResultSetMapper.map(resultSet);

        assertEquals(1L, product.getId());
        assertEquals("Product", product.getName());
        assertEquals("Description", product.getDescription());
        assertEquals(BigDecimal.TEN, product.getPrice());
    }
}