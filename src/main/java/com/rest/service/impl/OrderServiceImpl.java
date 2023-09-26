package com.rest.service.impl;

import com.rest.model.Order;
import com.rest.repository.OrderRepository;
import com.rest.repository.dto.SimpleOrderDto;
import com.rest.repository.impl.OrderRepositoryImpl;
import com.rest.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;

    private OrderServiceImpl() {
        orderRepository = OrderRepositoryImpl.getInstance();
    }

    public static OrderServiceImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final OrderServiceImpl INSTANCE = new OrderServiceImpl();
    }

    @Override
    public Order save(SimpleOrderDto order) {
        return orderRepository.save(order);
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        return orderRepository.deleteById(id);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
