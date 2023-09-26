package com.rest.servlet;

import com.rest.model.Product;
import com.rest.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ProductServletTest {
    @InjectMocks
    private ProductServlet productServlet;
    @Mock
    private ProductService productService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    private List<Product> products;
    private StringWriter stringWriter;
    private PrintWriter writer;

    @BeforeEach
     void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);

        stringWriter = new StringWriter();
        writer = new PrintWriter(stringWriter);

        products = new ArrayList<>();
        Product product1 = new Product();
        product1.setId(1L);
        Product product2 = new Product();
        product2.setId(2L);
        products.add(product1);
        products.add(product2);

        when(response.getWriter()).thenReturn(writer);
    }

    @AfterEach
     void cleanup() {
        writer.flush();
    }

    @Test
     void testDoGetWithNoId() {
        when(request.getPathInfo()).thenReturn(null);
        when(productService.findAll()).thenReturn(products);

        productServlet.doGet(request, response);

        String expectedJson = "[{\"id\":1},{\"id\":2}]";

        assertEquals(expectedJson, stringWriter.toString().trim());
        verify(productService, times(1)).findAll();
    }

    @Test
     void testDoGetWithId() {
        when(request.getPathInfo()).thenReturn("/1");
        when(productService.findById(anyLong())).thenReturn(products.get(0));

        productServlet.doGet(request, response);

        String expectedJson = "{\"id\":1}";

        assertEquals(expectedJson, stringWriter.toString().trim());
        verify(productService, times(1)).findById(anyLong());
    }


    @Test
     void testDoPost() throws IOException {
        String requestBody = "{\"id\":1}";
        BufferedReader reader = new BufferedReader(new StringReader(requestBody));
        when(request.getReader()).thenReturn(reader);

        Product product = new Product();
        product.setId(1L);
        when(productService.save(product)).thenReturn(product);

        productServlet.doPost(request, response);

        verify(productService, times(1)).save(any());
    }

    @Test
     void testDoPut() throws IOException {
        String requestBody = "{\"id\":1}";
        BufferedReader reader = new BufferedReader(new StringReader(requestBody));
        when(request.getReader()).thenReturn(reader);

        Product product = new Product();
        product.setId(1L);
        when(productService.update(product)).thenReturn(product);

        productServlet.doPut(request, response);

        verify(productService, times(1)).update(any());
    }

    @Test
     void testDoDelete() {
        when(request.getPathInfo()).thenReturn("/1");
        when(productService.deleteById(1L)).thenReturn(true);

        productServlet.doDelete(request, response);

        String expectedJson = "true";

        assertEquals(expectedJson, stringWriter.toString().trim());
        verify(productService, times(1)).deleteById(any());
    }
}