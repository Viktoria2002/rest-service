package com.rest.repository.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SimpleOrderDto {
    private Long id;
    private Date date;
    private BigDecimal discount;
    private String shippingAddress;
    private Long userId;
    private List<Long> productIds;

    public SimpleOrderDto() {
    }

    public SimpleOrderDto(Date date, BigDecimal discount, String shippingAddress, Long userId, List<Long> productIds) {
        this.date = date;
        this.discount = discount;
        this.shippingAddress = shippingAddress;
        this.userId = userId;
        this.productIds = productIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }
}
