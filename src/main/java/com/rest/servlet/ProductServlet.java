package com.rest.servlet;

import com.google.gson.Gson;
import com.rest.exception.RequestReaderException;
import com.rest.model.Product;
import com.rest.servlet.dto.ProductDto;
import com.rest.servlet.mapper.ProductMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static com.rest.util.Constants.ExceptionMessages.READER_EXCEPTION_MESSAGE;

@WebServlet("/products/*")
public class ProductServlet extends GeneralServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String pathInfo = request.getPathInfo();
        String jsonString;
        if (pathInfo == null || pathInfo.equals("/")) {
            List<Product> products = productService.findAll();
            List<ProductDto> productDtos = ProductMapper.INSTANCE.toDto(products);
            jsonString = new Gson().toJson(productDtos);
        } else {
            Long id = Long.parseLong(pathInfo.substring(1));
            Product product = productService.findById(id);
            ProductDto productDto = ProductMapper.INSTANCE.toDto(product);
            jsonString = new Gson().toJson(productDto);
        }
        showInfo(response, jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            Product product = new Gson().fromJson(request.getReader(), Product.class);
            Product savedProduct = productService.save(product);
            ProductDto productDto = ProductMapper.INSTANCE.toDto(savedProduct);
            showInfo(response, new Gson().toJson(productDto));
        } catch (IOException e) {
            throw new RequestReaderException(READER_EXCEPTION_MESSAGE + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.parseLong(request.getPathInfo().substring(1));
        boolean res = productService.deleteById(id);
        showInfo(response, new Gson().toJson(res));
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        try {
            Product product = new Gson().fromJson(request.getReader(), Product.class);
            Product updatedProduct = productService.update(product);
            ProductDto productDto = ProductMapper.INSTANCE.toDto(updatedProduct);
            showInfo(response, new Gson().toJson(productDto));
        } catch (IOException e) {
            throw new RequestReaderException(READER_EXCEPTION_MESSAGE + e.getMessage());
        }
    }
}
