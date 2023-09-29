package com.rest.repository;

import com.rest.db.ConnectionManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.rest.util.Constants.TestDatabaseSetup.*;

@ExtendWith(MockitoExtension.class)
public class AbstractTest {
    protected static PostgreSQLContainer<?> POSTGRESQL_CONTAINER;

    @BeforeAll
    public static void setUp() {
        POSTGRESQL_CONTAINER = new PostgreSQLContainer<>(DOCKER_IMAGE_NAME)
                .withDatabaseName(DATABASE_NAME)
                .withUsername(USERNAME)
                .withPassword(PASSWORD);
        POSTGRESQL_CONTAINER.start();

        setDataSource();
    }

    @BeforeEach
    public void init() throws SQLException, IOException {
        initializeTestContainer(INIT_TEST_DB_PATH);
    }

    @AfterEach
    public void clear() throws SQLException, IOException {
        initializeTestContainer(CLEAR_TEST_DB_PATH);
    }

    @AfterAll
    public static void closeUp() {
        POSTGRESQL_CONTAINER.stop();
    }

    private static void setDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(POSTGRESQL_CONTAINER.getJdbcUrl());
        config.setUsername(POSTGRESQL_CONTAINER.getUsername());
        config.setPassword(POSTGRESQL_CONTAINER.getPassword());
        ConnectionManager.setDataSource(new HikariDataSource(config));
    }

    private void initializeTestContainer(String file) throws SQLException, IOException {
        String query = "";
        try (Connection connection = ConnectionManager.getConnection();
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
