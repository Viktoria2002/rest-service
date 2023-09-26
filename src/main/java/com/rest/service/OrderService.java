package com.rest.service;

import com.rest.model.Order;
import com.rest.repository.dto.SimpleOrderDto;

public interface OrderService extends Service<Order, Long>{
    Order save(SimpleOrderDto order);
}
