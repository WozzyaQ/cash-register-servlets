package com.epam.ua.wozzya.web.listener;

import com.epam.ua.wozzya.model.db.ConnectionManager;
import com.epam.ua.wozzya.model.db.ConnectionSupplier;
import com.epam.ua.wozzya.model.db.MySqlConnectionPoolSupplier;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    private static final Logger log = Logger.getLogger(ContextListener.class);
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("initializing context");
        initConnectionSupplier(sce);
    }

    @SuppressWarnings("unchecked")
    private void initConnectionSupplier(ServletContextEvent sce) {
        String managerName = sce.getServletContext().getInitParameter("dbmanager");
        Class<ConnectionSupplier> supplierClass;
        try {
            supplierClass = (Class<ConnectionSupplier>) Class.forName(managerName);

            ConnectionSupplier supplier = supplierClass.newInstance();
            ConnectionManager.setSupplier(supplier);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            log.warn("error near ContextListener#initConnectionSupplier");
            ConnectionManager.setSupplier(new MySqlConnectionPoolSupplier());
            log.warn("using default supplier");
        }
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("destroying context");
    }
}
