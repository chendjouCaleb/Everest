package org.everest.main;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String clazz = sce.getServletContext().getInitParameter("app-class");

        System.out.println("App started\nInit Class: " + clazz);
        App.getApp().init(clazz);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
System.out.println("App was been stopped");
    }
}
