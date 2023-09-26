package com.rest.repository;

import com.rest.model.Order;
import com.rest.repository.dto.SimpleOrderDto;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findByUserId(Long id);

    Order save(SimpleOrderDto order);
}
