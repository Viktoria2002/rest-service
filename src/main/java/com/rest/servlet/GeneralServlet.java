package com.rest.servlet;

import com.rest.exception.ResponseWriterException;
import com.rest.service.OrderService;
import com.rest.service.ProductService;
import com.rest.service.UserService;
import com.rest.service.impl.OrderServiceImpl;
import com.rest.service.impl.ProductServiceImpl;
import com.rest.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.rest.util.Constants.ExceptionMessages.WRITER_EXCEPTION_MESSAGE;
import static com.rest.util.Constants.OutputProperties.CONTENT_TYPE;
import static com.rest.util.Constants.OutputProperties.ENCODING;

public class GeneralServlet extends HttpServlet {
    protected UserService userService;
    protected ProductService productService;
    protected OrderService orderService;

    @Override
    public void init() {
        userService = UserServiceImpl.getInstance();
        productService = ProductServiceImpl.getInstance();
        orderService = OrderServiceImpl.getInstance();
    }

    protected void showInfo(HttpServletResponse response, String jsonString) {
        try {
            response.setContentType(CONTENT_TYPE);
            response.setCharacterEncoding(ENCODING);
            response.getWriter().write(jsonString);
        } catch (IOException e) {
            throw new ResponseWriterException(WRITER_EXCEPTION_MESSAGE + e.getMessage());
        }
    }
}
