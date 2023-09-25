package com.rest.servlet.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderDto {
    private Long id;
    private Date date;
    private BigDecimal totalAmount;
    private String shippingAddress;
    private List<ProductDto> products;
    private UserDto user;

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
