package com.rest.servlet;

import com.google.gson.Gson;
import com.rest.exception.RequestReaderException;
import com.rest.model.Order;
import com.rest.repository.dto.SimpleOrderDto;
import com.rest.servlet.dto.OrderDto;
import com.rest.servlet.mapper.OrderMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static com.rest.util.Constants.ExceptionMessages.READER_EXCEPTION_MESSAGE;

@WebServlet("/orders/*")
public class OrderServlet extends GeneralServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String pathInfo = request.getPathInfo();
        String jsonString;
        if (pathInfo == null || pathInfo.equals("/")) {
            List<Order> orders = orderService.findAll();
            List<OrderDto> orderDtos = OrderMapper.INSTANCE.toDto(orders);
            jsonString = new Gson().toJson(orderDtos);
        } else {
            Long id = Long.parseLong(pathInfo.substring(1));
            Order order = orderService.findById(id);
            OrderDto orderDto = OrderMapper.INSTANCE.toDto(order);
            jsonString = new Gson().toJson(orderDto);
        }
        showInfo(response, jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            SimpleOrderDto order = new Gson().fromJson(request.getReader(), SimpleOrderDto.class);
            Order savedOrder = orderService.save(order);
            OrderDto orderDto = OrderMapper.INSTANCE.toDto(savedOrder);
            showInfo(response, new Gson().toJson(orderDto));
        } catch (IOException e) {
            throw new RequestReaderException(READER_EXCEPTION_MESSAGE + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.parseLong(request.getPathInfo().substring(1));
        boolean res = orderService.deleteById(id);
        showInfo(response, new Gson().toJson(res));
    }
}
