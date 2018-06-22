package org.everest.mvc.infrastructure;

import org.everest.exception.ConflictException;
import org.everest.mvc.component.servlet.MultiPartConfig;
import org.everest.mvc.infrastructure.servlet.AppServlet;
import org.everest.mvc.infrastructure.servlet.ResourceServlet;
import org.everest.utils.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationContextListener implements ServletContextListener {
    private Logger logger = LoggerFactory.getLogger(ApplicationContextListener.class);
    private ApplicationInitializer initializer;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String clazz = sce.getServletContext().getInitParameter("app-class");
        logger.info("App started\nInit Class: " + clazz);
        initializer = initializeApplication(clazz);

        String[] staticResources = initializer.getStaticResourceFolders();
        addResourceServlets(sce, staticResources);
        addApplicationServlet(sce);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
System.out.println("App was been stopped");
    }

    private MultipartConfigElement multipartConfigElement(MultiPartConfig config){
        return new MultipartConfigElement(config.getLocation(), config.getMaxFileSize(), config.getMaxRequestSize(), config.getFileSizeThreshold());
    }

    private ApplicationInitializer initializeApplication(String className){
        WebApplication.getApp().init(className);
        return WebApplication.getApp().getInitializer();
    }

    private void addApplicationServlet(ServletContextEvent sce) {
        ServletRegistration.Dynamic dynamic = sce.getServletContext().addServlet("AppServlet",new AppServlet());
        dynamic.addMapping(initializer.getAppBaseUrl()+"*");
        dynamic.setMultipartConfig(multipartConfigElement(initializer.multiPartConfig()));
    }

    private void addResourceServlets(ServletContextEvent sce, String[] dirs){
        String duplicate = (String) ArrayUtils.findFirstDuplicate(dirs);
        if(duplicate != null){
            throw new ConflictException("Application have two same resource folders: [{" + duplicate + "}]");
        }

        for (String dir: dirs){
            String mapping = dir+ "*";
            sce.getServletContext().addServlet("ResourceServlet"+dir, new ResourceServlet()).addMapping(mapping);

            logger.info("Static resources: Folder=[{}], mapping = [{}]", dir, mapping);
        }
    }
}
