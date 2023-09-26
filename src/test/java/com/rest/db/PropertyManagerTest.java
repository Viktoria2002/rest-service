package com.rest.db;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static com.rest.db.PropertyManager.loadProperties;
import static com.rest.util.Constants.ExceptionMessages.UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE;
import static org.junit.jupiter.api.Assertions.*;

class PropertyManagerTest {
    @Test
    public void testPropertyManagerConstructor() throws NoSuchMethodException, InstantiationException,
            IllegalAccessException {
        Constructor<PropertyManager> constructor = PropertyManager.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);

        try {
            constructor.newInstance();
            fail("Expected exception not thrown");
        } catch (InvocationTargetException e) {
            assertEquals(UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE, e.getCause().getMessage());
        }
    }

    @Test
    void testGetProperty() {
        PropertyManager.PROPERTIES.setProperty("test.property", "test value");

        String value = PropertyManager.getProperty("test.property");

        assertEquals("test value", value);
    }

    @Test
    void testLoadProperties() {
        String propertiesContent = "test.property=test value";
        InputStream inputStream = new ByteArrayInputStream(propertiesContent.getBytes());
        Thread.currentThread().setContextClassLoader(new TestClassLoader(inputStream));

        loadProperties();

        String value = PropertyManager.getProperty("test.property");

        assertEquals("test value", value);
    }

    private static class TestClassLoader extends ClassLoader {
        private final InputStream inputStream;

        public TestClassLoader(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        @Override
        public InputStream getResourceAsStream(String name) {
            return inputStream;
        }
    }
}
