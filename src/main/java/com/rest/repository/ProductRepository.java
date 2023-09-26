package com.rest.repository;

import com.rest.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Product save(Product product);

    Product update(Product product);
}
