package com.rest.servlet;

import com.google.gson.Gson;
import com.rest.exception.RequestReaderException;
import com.rest.model.User;
import com.rest.servlet.dto.UserDto;
import com.rest.servlet.mapper.UserMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static com.rest.util.Constants.ExceptionMessages.READER_EXCEPTION_MESSAGE;

@WebServlet("/users/*")
public class UserServlet extends GeneralServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String pathInfo = request.getPathInfo();
        String jsonString;
        if (pathInfo == null || pathInfo.equals("/")) {
            List<User> users = userService.findAll();
            List<UserDto> userDtos = UserMapper.INSTANCE.toDto(users);
            jsonString = new Gson().toJson(userDtos);
        } else {
            Long id = Long.parseLong(pathInfo.substring(1));
            User user = userService.findById(id);
            UserDto userDto = UserMapper.INSTANCE.toDto(user);
            jsonString = new Gson().toJson(userDto);
        }
        showInfo(response, jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = new Gson().fromJson(request.getReader(), User.class);
            User savedUser = userService.save(user);
            UserDto userDto = UserMapper.INSTANCE.toDto(savedUser);
            showInfo(response, new Gson().toJson(userDto));
        } catch (IOException e) {
            throw new RequestReaderException(READER_EXCEPTION_MESSAGE + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.parseLong(request.getPathInfo().substring(1));
        boolean res = userService.deleteById(id);
        showInfo(response, new Gson().toJson(res));
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = new Gson().fromJson(request.getReader(), User.class);
            User updatedUser = userService.update(user);
            UserDto userDto = UserMapper.INSTANCE.toDto(updatedUser);
            showInfo(response, new Gson().toJson(userDto));
        } catch (IOException e) {
            throw new RequestReaderException(READER_EXCEPTION_MESSAGE + e.getMessage());
        }
    }
}
