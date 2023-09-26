package com.rest.listener;

import com.rest.db.ConnectionManager;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class DBConnectionListener implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionManager.closeConnectionPool();
    }
}