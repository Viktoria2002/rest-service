package com.rest.repository.impl;

import com.google.gson.Gson;
import com.rest.model.Product;
import com.rest.repository.AbstractTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryImplTest extends AbstractTest {
    @InjectMocks
    private ProductRepositoryImpl productRepository;

    @Test
     void testGetInstance() {
        ProductRepositoryImpl instance1 = ProductRepositoryImpl.getInstance();
        ProductRepositoryImpl instance2 = ProductRepositoryImpl.getInstance();

        assertSame(instance1, instance2);
    }

    @Test
    void testFindById() {
        Long productId = 1L;

        Product product = productRepository.findById(productId);

        assertNotNull(product);
        assertEquals(productId, product.getId());
    }

    @Test
    void testDeleteById() {
        boolean expectedResult = true;

        boolean result = productRepository.deleteById(4L);

        assertEquals(expectedResult, result);
    }

    @Test
    void testFindAll() {
        int expectedNumberOfProducts = 4;

        List<Product> products = productRepository.findAll();

        assertEquals(expectedNumberOfProducts, products.size());
    }

    @Test
    void testSave() {
        String jsonString = "{\n" +
                "  \"name\": \"productName\",\n" +
                "  \"description\": \"description\",\n" +
                "  \"price\": 100\n" +
                "}";
        Product product = new Gson().fromJson(jsonString, Product.class);

        Product savedProduct = productRepository.save(product);

        assertNotNull(savedProduct.getId());
    }

    @Test
    void testUpdate() {
        String productName = "productName";
        Product order = new Product(1L, productName, "description", new BigDecimal(100));

        Product savedProduct = productRepository.update(order);

        assertEquals(productName, savedProduct.getName());
    }
}
