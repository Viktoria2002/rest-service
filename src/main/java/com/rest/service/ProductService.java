package com.rest.service;

import com.rest.model.Product;

public interface ProductService extends Service<Product, Long> {
    Product save(Product product);

    Product update(Product product);
}
