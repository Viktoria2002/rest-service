package com.rest.util;

import static com.rest.util.Constants.ExceptionMessages.UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE;

public final class Constants {
    private Constants() {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE);
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
        public static final String UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE = "This is a utility class and cannot be instantiated";

        private ExceptionMessages() {
            throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE);
        }
    }
}
