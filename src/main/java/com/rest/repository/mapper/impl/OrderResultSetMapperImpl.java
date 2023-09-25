package com.rest.repository.mapper.impl;

import com.rest.model.Order;
import com.rest.model.Product;
import com.rest.model.User;
import com.rest.repository.mapper.OrderResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.rest.util.Constants.ColumnNames.*;

public class OrderResultSetMapperImpl implements OrderResultSetMapper {
    @Override
    public List<Order> map(ResultSet resultSet) throws SQLException {
        List<Order> orders = new ArrayList<>();
        Long currentOrderId = 0L;
        Order currentOrder = null;
        List<Product> currentProducts = null;
        while (resultSet.next()) {
            Long orderId = resultSet.getLong(ID);
            if (!orderId.equals(currentOrderId)) {
                if (currentOrder != null) {
                    currentOrder.setProducts(currentProducts);
                    orders.add(currentOrder);
                }

                currentOrder = new Order(resultSet.getLong(ID),
                        resultSet.getDate(DATE),
                        resultSet.getBigDecimal(DISCOUNT),
                        resultSet.getString(SHIPPING_ADDRESS),
                        new User(resultSet.getLong(USER_ID),
                                resultSet.getString(LAST_NAME),
                                resultSet.getString(FIRST_NAME),
                                resultSet.getString(EMAIL),
                                resultSet.getString(PASSWORD)));

                currentProducts = new ArrayList<>();
            }

            Product product1 = new Product(
                    resultSet.getLong(PR_ID),
                    resultSet.getString(NAME),
                    resultSet.getString(DESCRIPTION),
                    resultSet.getBigDecimal(PRICE));
            currentProducts.add(product1);

            currentOrderId = orderId;
        }
        if (currentOrder != null) {
            currentOrder.setProducts(currentProducts);
            orders.add(currentOrder);
        }
        return orders;
    }
}
