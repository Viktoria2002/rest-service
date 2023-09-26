package com.rest.service.impl;

import com.rest.model.Product;
import com.rest.repository.ProductRepository;
import com.rest.repository.impl.ProductRepositoryImpl;
import com.rest.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    private ProductServiceImpl() {
        productRepository = ProductRepositoryImpl.getInstance();
    }

    public static ProductServiceImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final ProductServiceImpl INSTANCE = new ProductServiceImpl();
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        return productRepository.update(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        return productRepository.deleteById(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
