package com.rest.service.impl;

import com.rest.model.User;
import com.rest.repository.impl.UserRepositoryImpl;
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
class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepositoryImpl userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetInstance() {
        UserServiceImpl instance1 = UserServiceImpl.getInstance();
        UserServiceImpl instance2 = UserServiceImpl.getInstance();

        assertSame(instance1, instance2);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        User user = new User();
        when(userRepository.findById(id)).thenReturn(user);

        User foundUser = userService.findById(id);

        assertEquals(user, foundUser);
        verify(userRepository, times(1)).findById(id);
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;
        when(userRepository.deleteById(id)).thenReturn(true);

        boolean result = userService.deleteById(id);

        assertEquals(true, result);
        verify(userRepository, times(1)).deleteById(id);
    }

    @Test
    public void testFindAll() {
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        when(userRepository.findAll()).thenReturn(userList);

        List<User> foundUsers = userService.findAll();

        assertEquals(userList, foundUsers);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.save(user);

        assertEquals(user, savedUser);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        when(userRepository.update(user)).thenReturn(user);

        User updatedUser = userService.update(user);

        assertEquals(user, updatedUser);
        verify(userRepository, times(1)).update(user);
    }
}
