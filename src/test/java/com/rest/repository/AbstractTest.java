package com.rest.repository;

import com.rest.db.ConnectionManager;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.rest.util.Constants.TestDatabaseSetup.*;

public class AbstractTest {
    protected static PostgreSQLContainer<?> POSTGRESQL_CONTAINER;
    private static String jdbcUrl;
    private static String username;
    private static String password;

    @BeforeAll
    public static void setUp() throws NoSuchFieldException, IllegalAccessException {
        POSTGRESQL_CONTAINER = new PostgreSQLContainer<>(DOCKER_IMAGE_NAME)
                .withDatabaseName(DATABASE_NAME)
                .withUsername(USERNAME)
                .withPassword(PASSWORD);
        POSTGRESQL_CONTAINER.start();

        setDataSource();
    }

    @BeforeEach
    public void init() throws SQLException, IOException {
        initializeTestContainer(jdbcUrl, username, password, INIT_TEST_DB_PATH);
    }

    @AfterEach
    public void clear() throws SQLException, IOException {
        initializeTestContainer(jdbcUrl, username, password, CLEAR_TEST_DB_PATH);
    }

    @AfterAll
    public static void closeUp() {
        POSTGRESQL_CONTAINER.stop();
    }

    private static void setDataSource() throws NoSuchFieldException, IllegalAccessException {
        jdbcUrl = POSTGRESQL_CONTAINER.getJdbcUrl();
        username = POSTGRESQL_CONTAINER.getUsername();
        password = POSTGRESQL_CONTAINER.getPassword();

        Field privateField = ConnectionManager.class.getDeclaredField(DATA_SOURCE_FIELD);
        privateField.setAccessible(true);

        HikariDataSource dataSource = (HikariDataSource) privateField.get(ConnectionManager.class);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
    }

    private void initializeTestContainer(String jdbcUrl, String username, String password, String file) throws SQLException, IOException {
        String query = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                query += line;
                if (line.endsWith(";")) {
                    try (PreparedStatement statement = connection.prepareStatement(query)) {
                        statement.execute();
                    }
                    query = "";
                }
            }
        }
    }
}
