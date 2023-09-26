package com.rest.service.impl;

import com.rest.model.Product;
import com.rest.repository.impl.ProductRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepositoryImpl productRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetInstance() {
        ProductServiceImpl instance1 = ProductServiceImpl.getInstance();
        ProductServiceImpl instance2 = ProductServiceImpl.getInstance();

        assertSame(instance1, instance2);
    }

    @Test
    public void testSaveProduct() {
        Product product = new Product();
        when(productRepository.save(product)).thenReturn(product);

        Product savedProduct = productService.save(product);

        assertEquals(product, savedProduct);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product();
        when(productRepository.update(product)).thenReturn(product);

        Product updatedProduct = productService.update(product);

        assertEquals(product, updatedProduct);
        verify(productRepository, times(1)).update(product);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Product product = new Product();
        when(productRepository.findById(id)).thenReturn(product);

        Product foundProduct = productService.findById(id);

        assertEquals(product, foundProduct);
        verify(productRepository, times(1)).findById(id);
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;
        when(productRepository.deleteById(id)).thenReturn(true);

        boolean result = productService.deleteById(id);

        assertEquals(true, result);
        verify(productRepository, times(1)).deleteById(id);
    }

    @Test
    public void testFindAll() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        when(productRepository.findAll()).thenReturn(productList);

        List<Product> foundProducts = productService.findAll();

        assertEquals(productList, foundProducts);
        verify(productRepository, times(1)).findAll();
    }
}