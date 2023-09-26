package com.rest.db;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.SQLException;

import static com.rest.util.Constants.ExceptionMessages.UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE;
import static org.junit.jupiter.api.Assertions.*;

class ConnectionManagerTest {
    private static Connection connection;

    @BeforeAll
    public static void setUp() throws SQLException {
        connection = ConnectionManager.getConnection();
    }

    @AfterAll
    public static void tearDown() throws SQLException {
        connection.close();
        ConnectionManager.closeConnectionPool();
    }

    @Test
    public void testGetConnection() {
        assertNotNull(connection);
    }

    @Test
    public void testCloseConnectionPool() throws SQLException {
        assertFalse(connection.isClosed());
    }

    @Test
    public void testConnectionManagerConstructor() throws NoSuchMethodException, InstantiationException,
            IllegalAccessException {
        Constructor<ConnectionManager> constructor = ConnectionManager.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        try {
            constructor.newInstance();
            fail("Expected exception not thrown");
        } catch (InvocationTargetException e) {
            assertEquals(UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE, e.getCause().getMessage());
        }
    }
}
