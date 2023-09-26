package com.rest.servlet;

import com.rest.service.impl.OrderServiceImpl;
import com.rest.service.impl.ProductServiceImpl;
import com.rest.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.PrintWriter;

import static com.rest.util.Constants.OutputProperties.CONTENT_TYPE;
import static com.rest.util.Constants.OutputProperties.ENCODING;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GeneralServletTest {
    @InjectMocks
    private GeneralServlet servlet;

    @Mock
    private PrintWriter printWriter;

    @Mock
    private HttpServletResponse response;

    @Captor
    private ArgumentCaptor<String> jsonStringCaptor;

    private GeneralServlet generalServlet;

    @BeforeEach
     void setUp() {
        MockitoAnnotations.initMocks(this);
        generalServlet = new GeneralServlet();
    }

    @Test
     void testInit_InitializesServices() {
        generalServlet.init();

        assertNotNull(generalServlet.userService);
        assertNotNull(generalServlet.productService);
        assertNotNull(generalServlet.orderService);

        assertTrue(generalServlet.userService instanceof UserServiceImpl);
        assertTrue(generalServlet.productService instanceof ProductServiceImpl);
        assertTrue(generalServlet.orderService instanceof OrderServiceImpl);
    }

    @Test
     void testShowInfo() throws IOException {
        String jsonString = "test json string";
        when(response.getWriter()).thenReturn(printWriter);

        servlet.showInfo(response, jsonString);

        verify(response).setContentType(CONTENT_TYPE);
        verify(response).setCharacterEncoding(ENCODING);
        verify(response.getWriter()).write(jsonStringCaptor.capture());

        assertEquals(jsonString, jsonStringCaptor.getValue());
    }
}
