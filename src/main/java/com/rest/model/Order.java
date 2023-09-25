package com.rest.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Order {
    private Long id;
    private Date date;
    private BigDecimal discount;
    private String shippingAddress;
    private List<Product> products;
    private User user;

    public Order(Long id, Date date, BigDecimal discount, String shippingAddress, User user) {
        this.id = id;
        this.date = date;
        this.discount = discount;
        this.shippingAddress = shippingAddress;
        this.user = user;
    }

    public Order() {

    }

    public BigDecimal countTotalAmount() {
        return products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
