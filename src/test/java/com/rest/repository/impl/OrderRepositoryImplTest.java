package com.rest.repository.impl;

import com.google.gson.Gson;
import com.rest.model.Order;
import com.rest.repository.AbstractTest;
import com.rest.repository.dto.SimpleOrderDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

@ExtendWith(MockitoExtension.class)
class OrderRepositoryImplTest extends AbstractTest {
    @InjectMocks
    private OrderRepositoryImpl orderRepository;

    @Test
     void testGetInstance() {
        OrderRepositoryImpl instance1 = OrderRepositoryImpl.getInstance();
        OrderRepositoryImpl instance2 = OrderRepositoryImpl.getInstance();

        assertSame(instance1, instance2);
    }

    @Test
     void testFindById() {
        Long orderId = 1L;

        Order order = orderRepository.findById(orderId);

        assertNotNull(order);
        assertEquals(orderId, order.getId());
    }

    @Test
     void testFindByUserId() {
        Long userId = 2L;
        int expectedOrdersOfUser = 2;

        List<Order> order = orderRepository.findByUserId(userId);

        assertEquals(expectedOrdersOfUser, order.size());
    }

    @Test
     void testFindAll() {
        int expectedNumberOfOrders = 3;

        List<Order> orders = orderRepository.findAll();

        assertEquals(expectedNumberOfOrders, orders.size());
    }

    @Test
     void testSave() {
        String jsonString = "{\n" +
                "  \"date\": \"2023-11-11\",\n" +
                "  \"discount\": 0.1,\n" +
                "  \"shippingAddress\": \"addr\",\n" +
                "  \"userId\": 1,\n" +
                "  \"productIds\": [2]\n" +
                "}";
        SimpleOrderDto order = new Gson().fromJson(jsonString, SimpleOrderDto.class);

        Order savedOrder = orderRepository.save(order);

        assertNotNull(savedOrder.getId());
    }

    @Test
     void testDeleteById() {
        boolean expectedResult = true;

        boolean result = orderRepository.deleteById(1L);

        assertEquals(expectedResult, result);
    }
}
