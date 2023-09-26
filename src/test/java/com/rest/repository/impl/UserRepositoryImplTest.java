package com.rest.repository.impl;

import com.google.gson.Gson;
import com.rest.model.User;
import com.rest.repository.AbstractTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

@ExtendWith(MockitoExtension.class)
class UserRepositoryImplTest extends AbstractTest {
    @InjectMocks
    private UserRepositoryImpl userRepository;

    @Test
     void testGetInstance() {
        UserRepositoryImpl instance1 = UserRepositoryImpl.getInstance();
        UserRepositoryImpl instance2 = UserRepositoryImpl.getInstance();

        assertSame(instance1, instance2);
    }

    @Test
    void testFindById() {
        Long userId = 1L;

        User user = userRepository.findById(userId);

        assertNotNull(user);
        assertEquals(userId, user.getId());
    }

    @Test
    void testDeleteById() {
        boolean expectedResult = true;

        boolean result = userRepository.deleteById(2L);

        assertEquals(expectedResult, result);
    }

    @Test
    void testFindAll() {
        int expectedNumberOfUsers = 3;

        List<User> users = userRepository.findAll();

        assertEquals(expectedNumberOfUsers, users.size());
    }

    @Test
    void testSave() {
        String jsonString = "{\n" +
                "  \"lastName\": \"LN4\",\n" +
                "  \"firstName\": \"FN4\",\n" +
                "  \"email\": \"email4\",\n" +
                "  \"password\": \"pass4\"\n" +
                "}";
        User user = new Gson().fromJson(jsonString, User.class);

        User savedUser = userRepository.save(user);

        assertNotNull(savedUser.getId());
    }

    @Test
    void testUpdate() {
        String lastName = "lastName";
        User user = new User(1L, lastName, "FN3", "email3", "pass3");

        User savedUser = userRepository.update(user);

        assertEquals(lastName, savedUser.getLastName());
    }
}
