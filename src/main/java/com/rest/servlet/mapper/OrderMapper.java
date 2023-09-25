package com.rest.servlet.mapper;

import com.rest.model.Order;
import com.rest.servlet.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(expression = "java(order.countTotalAmount().subtract((order.countTotalAmount().multiply(order.getDiscount())" +
            ".setScale(2, java.math.BigDecimal.ROUND_HALF_UP))))", target = "totalAmount")
    OrderDto toDto(Order order);

    List<OrderDto> toDto(List<Order> orders);
}
