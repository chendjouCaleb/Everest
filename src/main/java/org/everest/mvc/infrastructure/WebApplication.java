package org.everest.mvc.infrastructure;
import org.everest.context.ApplicationContext;
import org.everest.core.dic.decorator.AutoWired;
import org.everest.core.dic.decorator.Resolve;
import org.everest.core.event.EventEmitter;
import org.everest.decorator.Instance;
import org.everest.exception.ApplicationInitialisationException;
import org.everest.mvc.errorHandler.DefaultErrorHandler;
import org.everest.mvc.errorHandler.IErrorHandler;
import org.everest.mvc.httpContext.HttpContext;
import org.everest.utils.ReflexionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Instance
public class WebApplication {
    private Logger logger = LoggerFactory.getLogger(WebApplication.class);
    private static WebApplication app;
    private HttpContextBuilder httpContextBuilder = new HttpContextBuilder();
    private ApplicationContext context;

    public WebApplication(){
        context = new ApplicationContext();
        StaticContext.setContext(context);
    }

    public static WebApplication getApp() {
        if (app == null){
            app = new WebApplication();
        }
        return app;
    }

    public void run(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        HttpContext httpContext = httpContextBuilder.build(servletRequest, servletResponse);
        EventEmitter.getInstance().emit("OnApp.Request");
        logger.debug("New request: [ url = {}, method = {}]", httpContext.getRequest().getPathInfo(), httpContext.getRequest().getHttpMethod());
        MvcRunner mvcRunner = context.getContainer().getInstance(MvcRunner.class);
        mvcRunner.run(httpContext);
    }


    public void init(String appClassString){
        try {
            Class<?> appClass = Class.forName(appClassString);
            ApplicationInitializer initializer = (ApplicationInitializer) ReflexionUtils.instantiateClass(appClass);

            StaticContext.applicationInitializer = initializer;
            initializer.setApplicationContext(context);
            context.getContainer().addPackage(initializer.getBasePackages());
            context.getContainer().addInstance(initializer, "applicationInitializer");
            context.initialize();
            //logger.debug("There are {} Instance in httpContext", context.countDependencies());
            Class[] classes = ReflexionUtils.getClasses(initializer.getBasePackages()[0]);
            //Class[] classes = ReflexionUtils.getClasses("org.everest");
            logger.debug("---------------------------------");
            logger.debug("Application has {} classes", classes.length);
            logger.debug("---------------------------------");

            EventEmitter.getInstance().emit("OnApp.Start");
        } catch (ClassNotFoundException e) {
            throw new ApplicationInitialisationException("The App initiator Class'" +
                    appClassString + "' not found");
        }
    }

    public ApplicationContext getContext() { return context; }

    public ApplicationInitializer getInitializer() {
        return (ApplicationInitializer) context.getContainer().getInstance("applicationInitializer");
    }
}
