package org.everest.mvc.infrastructure;
import org.everest.context.ApplicationContext;
import org.everest.core.event.EventEmitter;
import org.everest.exception.ApplicationInitialisationException;
import org.everest.utils.ReflexionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class WebApplication {
    private Logger logger = LoggerFactory.getLogger(WebApplication.class);
    private ApplicationContext context;

    public WebApplication(){
        context = new ApplicationContext();
    }


    public void init(String appClassString){
        try {
            Class<?> appClass = Class.forName(appClassString);
            MvcStartup initializer = (MvcStartup) ReflexionUtils.instantiateClass(appClass);

            initializer.setApplicationContext(context);
            context.getContainer().addPackage(initializer.getBasePackages());
            context.getContainer().addInstance(initializer, "mvcStartup");
            context.initialize();
            Class[] classes = ReflexionUtils.getClasses(initializer.getBasePackages()[0]);

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

    public MvcStartup getStartup() {
        return (MvcStartup) context.getContainer().getInstance("mvcStartup");
    }
}
