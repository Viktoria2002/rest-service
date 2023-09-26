package com.rest.db;

import com.rest.exception.LoadConfigFileException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.rest.util.Constants.DatabaseSetup.CONFIG_FILE;
import static com.rest.util.Constants.ExceptionMessages.LOAD_CONFIG_FILE_EXCEPTION_MESSAGE;
import static com.rest.util.Constants.ExceptionMessages.UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE;

public class PropertyManager {
    protected static final Properties PROPERTIES = new Properties();

    private PropertyManager() {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE);
    }

    static {
        loadProperties();
    }

    public static String getProperty(String name){
        return PROPERTIES.getProperty(name);
    }

    protected static void loadProperties() {
        try (InputStream inputStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(CONFIG_FILE)) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new LoadConfigFileException(LOAD_CONFIG_FILE_EXCEPTION_MESSAGE + e.getMessage());
        }
    }
}
