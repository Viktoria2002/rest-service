package com.rest.service.impl;

import com.rest.model.Order;
import com.rest.repository.dto.SimpleOrderDto;
import com.rest.repository.impl.OrderRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {
    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepositoryImpl orderRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetInstance() {
        OrderServiceImpl instance1 = OrderServiceImpl.getInstance();
        OrderServiceImpl instance2 = OrderServiceImpl.getInstance();

        assertSame(instance1, instance2);
    }

    @Test
    public void testSave() throws Exception {
        SimpleOrderDto orderDto = new SimpleOrderDto();
        Order savedOrder = new Order();
        when(orderRepository.save(orderDto)).thenReturn(savedOrder);

        Order result = orderService.save(orderDto);

        assertEquals(savedOrder, result);
        verify(orderRepository, times(1)).save(orderDto);
    }

    @Test
    void testFindById() {
        Long orderId = 1L;
        Order foundOrder = new Order();
        when(orderRepository.findById(orderId)).thenReturn(foundOrder);

        Order result = orderService.findById(orderId);

        assertEquals(foundOrder, result);
        verify(orderRepository, times(1)).findById(orderId);
    }

    @Test
    void testDeleteById() {
        Long orderId = 1L;
        when(orderRepository.deleteById(orderId)).thenReturn(true);

        boolean result = orderService.deleteById(orderId);

        assertTrue(result);
        verify(orderRepository, times(1)).deleteById(orderId);
    }

    @Test
    void testFindAll() {
        List<Order> orders = new ArrayList<>();
        when(orderRepository.findAll()).thenReturn(orders);

        List<Order> result = orderService.findAll();

        assertEquals(orders, result);
        verify(orderRepository, times(1)).findAll();
    }
}