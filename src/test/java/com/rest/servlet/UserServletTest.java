package com.rest.servlet;

import com.rest.model.User;
import com.rest.service.UserService;
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

class UserServletTest {
    @InjectMocks
    private UserServlet userServlet;
    @Mock
    private UserService userService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    private List<User> users;
    private StringWriter stringWriter;
    private PrintWriter writer;

    @BeforeEach
    public void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);

        stringWriter = new StringWriter();
        writer = new PrintWriter(stringWriter);

        users = new ArrayList<>();
        User user1 = new User();
        user1.setId(1L);
        User user2 = new User();
        user2.setId(2L);
        users.add(user1);
        users.add(user2);

        when(response.getWriter()).thenReturn(writer);
    }

    @AfterEach
    public void cleanup() {
        writer.flush();
    }

    @Test
     void testDoGetWithNoId() {
        when(request.getPathInfo()).thenReturn(null);
        when(userService.findAll()).thenReturn(users);

        userServlet.doGet(request, response);

        String expectedJson = "[{\"id\":1},{\"id\":2}]";

        assertEquals(expectedJson, stringWriter.toString().trim());
        verify(userService, times(1)).findAll();
    }

    @Test
     void testDoGetWithId() {
        when(request.getPathInfo()).thenReturn("/1");
        when(userService.findById(anyLong())).thenReturn(users.get(0));

        userServlet.doGet(request, response);

        String expectedJson = "{\"id\":1}";

        assertEquals(expectedJson, stringWriter.toString().trim());
        verify(userService, times(1)).findById(anyLong());
    }


    @Test
     void testDoPost() throws IOException {
        String requestBody = "{\"id\":1}";
        BufferedReader reader = new BufferedReader(new StringReader(requestBody));
        when(request.getReader()).thenReturn(reader);

        User user = new User();
        user.setId(1L);
        when(userService.save(user)).thenReturn(user);

        userServlet.doPost(request, response);

        verify(userService, times(1)).save(any());
    }

    @Test
     void testDoPut() throws IOException {
        String requestBody = "{\"id\":1}";
        BufferedReader reader = new BufferedReader(new StringReader(requestBody));
        when(request.getReader()).thenReturn(reader);

        User user = new User();
        user.setId(1L);
        when(userService.update(user)).thenReturn(user);

        userServlet.doPut(request, response);

        verify(userService, times(1)).update(any());
    }

    @Test
     void testDoDelete() {
        when(request.getPathInfo()).thenReturn("/1");
        when(userService.deleteById(1L)).thenReturn(true);

        userServlet.doDelete(request, response);

        String expectedJson = "true";

        assertEquals(expectedJson, stringWriter.toString().trim());
        verify(userService, times(1)).deleteById(any());
    }
}
