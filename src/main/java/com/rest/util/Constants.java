package com.rest.util;

import static com.rest.util.Constants.ExceptionMessages.UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE;

public final class Constants {
    private Constants() {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE);
    }

    public static final class SqlQueries {
        public static final String FIND_USER_BY_ID = "SELECT * FROM users WHERE id=?;";
        public static final String FIND_ALL_USERS = "SELECT * FROM users;";
        public static final String INSERT_USER = "INSERT INTO users (last_name, first_name, email, password) VALUES (?,?,?,?);";
        public static final String UPDATE_USER = "UPDATE users SET last_name=?, first_name=?, email=?, password=? WHERE id=?;";
        public static final String DELETE_USER = "DELETE FROM users WHERE id=?;";
        public static final String FIND_PRODUCT_BY_ID = "SELECT * FROM products WHERE pr_id=?;";
        public static final String FIND_ALL_PRODUCTS = "SELECT * FROM products;";
        public static final String INSERT_PRODUCT = "INSERT INTO products (name, description, price) VALUES (?,?,?);";
        public static final String UPDATE_PRODUCT = "UPDATE products SET name=?, description=?, price=? WHERE pr_id=?;";
        public static final String DELETE_PRODUCT = "DELETE FROM products WHERE pr_id=?;";
        public static final String FIND_ORDER_BY_ID = "SELECT * " +
                "FROM orders " +
                "JOIN users ON orders.user_id = users.id " +
                "JOIN product_order ON orders.id = product_order.order_id " +
                "JOIN products ON product_order.product_id = products.pr_id " +
                "WHERE orders.id=?;";
        public static final String FIND_ORDERS_BY_USER_ID = "SELECT * " +
                "FROM orders " +
                "JOIN users ON orders.user_id = users.id " +
                "JOIN product_order ON orders.id = product_order.order_id " +
                "JOIN products ON product_order.product_id = products.pr_id " +
                "WHERE orders.user_id=?;";
        public static final String FIND_ALL_ORDERS = "SELECT * " +
                "FROM orders " +
                "JOIN users ON orders.user_id = users.id " +
                "JOIN product_order ON orders.id = product_order.order_id " +
                "JOIN products ON product_order.product_id = products.pr_id;";
        public static final String INSERT_ORDER = "INSERT INTO orders (date, discount, shipping_address, user_id) VALUES (?,?,?,?);";
        public static final String INSERT_PRODUCT_ORDER = "INSERT INTO product_order (product_id, order_id) VALUES (?,?);";
        public static final String DELETE_PRODUCT_ORDER = "DELETE FROM product_order WHERE order_id=?;";
        public static final String DELETE_ORDER = "DELETE FROM orders WHERE id=?;";

        private SqlQueries() {
            throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE);
        }
    }

    public static final class ColumnNames {
        public static final String ID = "id";
        public static final String LAST_NAME = "last_name";
        public static final String FIRST_NAME = "first_name";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String PRICE = "price";
        public static final String DATE = "date";
        public static final String DISCOUNT = "discount";
        public static final String SHIPPING_ADDRESS = "shipping_address";
        public static final String USER_ID = "user_id";
        public static final String PR_ID = "pr_id";

        private ColumnNames() {
            throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE);
        }
    }

    public static final class ExceptionMessages {
        public static final String READER_EXCEPTION_MESSAGE = "Error reading from stream: ";
        public static final String WRITER_EXCEPTION_MESSAGE = "Error writing JSON string: ";
        public static final String SQL_EXCEPTION_MESSAGE = "Error working with database: ";
        public static final String UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE = "This is a utility class and cannot be instantiated";
        public static final String LOAD_CONFIG_FILE_EXCEPTION_MESSAGE = "Error loading configuration file: ";

        private ExceptionMessages() {
            throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE);
        }
    }

    public static final class DatabaseSetup {
        public static final String URL = "database.url";
        public static final String USERNAME = "database.username";
        public static final String PASSWORD = "database.password";
        public static final String DRIVER = "database.driver";
        public static final String CONFIG_FILE = "application.yml";

        private DatabaseSetup() {
            throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE);
        }
    }

    public static final class TestDatabaseSetup {
        public static final String DOCKER_IMAGE_NAME = "postgres:latest";
        public static final String DATABASE_NAME = "shop";
        public static final String USERNAME = "user";
        public static final String PASSWORD = "pass";
        public static final String DATA_SOURCE_FIELD = "dataSource";
        public static final String INIT_TEST_DB_PATH = "src/test/resources/db/inserts.sql";
        public static final String CLEAR_TEST_DB_PATH = "src/test/resources/db/clear.sql";

        private TestDatabaseSetup() {
            throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE);
        }
    }

    public static final class OutputProperties {
        public static final String CONTENT_TYPE = "application/json";
        public static final String ENCODING = "UTF-8";

        private OutputProperties() {
            throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE);
        }
    }
}
