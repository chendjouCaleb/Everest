package Everest.Servlet.Http;

import org.everest.mvc.component.MultiPartConfig;
import org.everest.mvc.infrastructure.MvcStartup;
import org.everest.mvc.infrastructure.WebApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationContextListener implements ServletContextListener {
    private Logger logger = LoggerFactory.getLogger(ApplicationContextListener.class);
    private MvcStartup initializer;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String clazz = sce.getServletContext().getInitParameter("app-class");
        logger.info("Application started. Startup class: " + clazz);

        WebApplication application = new WebApplication();
        application.init(clazz);

        sce.getServletContext().setAttribute("applicationContext", application.getContext());
        addApplicationServlet(sce);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
System.out.println("App was been stopped");
    }

    private MultipartConfigElement multipartConfigElement(MultiPartConfig config){
        return new MultipartConfigElement(config.getLocation(), config.getMaxFileSize(), config.getMaxRequestSize(), config.getFileSizeThreshold());
    }

    private void addApplicationServlet(ServletContextEvent sce) {
        ServletRegistration.Dynamic dynamic = sce.getServletContext().addServlet("AppServlet",new AppServlet());
        dynamic.addMapping("/*");
        //dynamic.setMultipartConfig(multipartConfigElement(initializer.multiPartConfig()));
    }
}
