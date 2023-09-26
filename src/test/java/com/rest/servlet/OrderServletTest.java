package com.rest.servlet;

import com.rest.model.Order;
import com.rest.model.Product;
import com.rest.repository.dto.SimpleOrderDto;
import com.rest.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderServletTest {
    @InjectMocks
    private OrderServlet orderServlet;
    @Mock
    private OrderService orderService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    private List<Order> orders;
    private StringWriter stringWriter;
    private PrintWriter writer;

    @BeforeEach
     void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);

        stringWriter = new StringWriter();
        writer = new PrintWriter(stringWriter);

        List<Product> products1 = new ArrayList<>();
        List<Product> products2 = new ArrayList<>();
        Product product1 = new Product();
        product1.setPrice(new BigDecimal(20));
        Product product2 = new Product();
        product2.setPrice(new BigDecimal(40));
        products1.add(product1);
        products2.add(product2);

        orders = new ArrayList<>();
        Order order1 = new Order();
        order1.setId(1L);
        order1.setProducts(products1);
        order1.setDiscount(new BigDecimal(0.1));
        orders.add(order1);
        Order order2 = new Order();
        order2.setId(2L);
        order2.setProducts(products2);
        order2.setDiscount(new BigDecimal(0.2));
        orders.add(order2);

        when(response.getWriter()).thenReturn(writer);
    }

    @AfterEach
     void cleanup() {
        writer.flush();
    }

    @Test
     void testDoGetWithNoId() {
        when(request.getPathInfo()).thenReturn(null);
        when(orderService.findAll()).thenReturn(orders);

        orderServlet.doGet(request, response);

        String expectedJson = "[{\"id\":1,\"totalAmount\":18.00,\"products\":[{\"price\":20}]},{\"id\":2,\"totalAmount\":32.00,\"products\":[{\"price\":40}]}]";

        assertEquals(expectedJson, stringWriter.toString().trim());
        verify(orderService, times(1)).findAll();
    }

    @Test
     void testDoGetWithId() {
        when(request.getPathInfo()).thenReturn("/1");
        when(orderService.findById(anyLong())).thenReturn(orders.get(0));

        orderServlet.doGet(request, response);

        String expectedJson = "{\"id\":1,\"totalAmount\":18.00,\"products\":[{\"price\":20}]}";

        assertEquals(expectedJson, stringWriter.toString().trim());
        verify(orderService, times(1)).findById(anyLong());
    }


    @Test
     void testDoPost() throws IOException {
        String requestBody = "{\"id\":1}";
        BufferedReader reader = new BufferedReader(new StringReader(requestBody));
        when(request.getReader()).thenReturn(reader);

        SimpleOrderDto orderDto = new SimpleOrderDto();
        Order order = new Order();
        order.setId(1L);
        when(orderService.save(orderDto)).thenReturn(order);

        orderServlet.doPost(request, response);

        verify(orderService, times(1)).save(any());
    }

    @Test
     void testDoDelete() {
        when(request.getPathInfo()).thenReturn("/1");
        when(orderService.deleteById(1L)).thenReturn(true);

        orderServlet.doDelete(request, response);

        String expectedJson = "true";

        assertEquals(expectedJson, stringWriter.toString().trim());
        verify(orderService, times(1)).deleteById(any());
    }
}
