package org.everest.main;
import annotation.HttpController;
import filter.FilterManager;
import org.everest.context.ApplicationContext;
import org.everest.context.classHandler.ConverterHandler;
import org.everest.context.classHandler.RepositoryHandler;
import org.everest.decorator.ErrorHandler;
import org.everest.exception.ApplicationInitialisationException;
import org.everest.main.component.classHandler.ControllerHandler;
import org.everest.main.component.classHandler.ErrorClassHandler;
import org.everest.main.component.classHandler.ListenerHandler;
import org.everest.main.component.classHandler.RequestFilterHandler;
import org.everest.main.component.errorHandler.DefaultErrorHandler;
import org.everest.main.component.errorHandler.IErrorHandler;
import org.everest.main.component.http.*;
import org.everest.core.event.EventEmitter;
import org.everest.main.form.Form;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import org.everest.mvc.router.Router;
import org.everest.mvc.router.variableResolver.RequestVariableResolver;
import org.everest.mvc.router.variableResolver.VariableResolverHandler;
import org.everest.mvc.service.JSONService;
import org.everest.utils.ReflexionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebApplication {
    private Logger logger = LoggerFactory.getLogger(WebApplication.class);
    private static WebApplication app;

    private FrontalController frontalController;
    private ApplicationContext context;
    private List<IErrorHandler> errorHandlers = new ArrayList<>();
    private List<Object> controllers = new ArrayList<>();
    private List<Object> listeners = new ArrayList<>();
    private DefaultErrorHandler defaultErrorHandler = new DefaultErrorHandler();
    private Router router;

    public WebApplication(){
        context = new ApplicationContext();
        StaticContext.setContext(context);
        StaticContext.addComponent();
        addClassHandler();

    }

    static WebApplication getApp() {
        if (app == null){
            app = new WebApplication();
        }
        return app;
    }

    void run(HttpServletRequest servletRequest, HttpServletResponse servletResponse){
        Request request = new Request(servletRequest);
        Response response = new Response(servletResponse);
        EventEmitter.getInstance().emit("OnApp.Request");
        try{
            frontalController.handleRequest(request, response);
        }catch (Throwable e){
            Utils.handleError(request, response, e);
        }
    }

    private void addClassHandler(){
        context.addClassHandler(new ControllerHandler());
        context.addClassHandler(new ListenerHandler());
        context.addClassHandler(new ErrorClassHandler());
        context.addClassHandler(new RepositoryHandler());
        context.addClassHandler(new RequestFilterHandler());
        context.addClassHandler(new VariableResolverHandler());
        context.addClassHandler(new ConverterHandler());

    }

    private void addServiceInstance(){
        context.addInstance(new FilterManager());
        context.addInstance(new RequestVariableResolver());
        router = new Router();
        context.addInstance(router);
        frontalController = new FrontalController();
        context.addInstance(frontalController);
        context.addInstance(EventEmitter.getInstance());
        context.addInstance(new Form());
        context.addInstance(new JSONService());
        context.initialize();

    }

    private void addEventListeners(){
        EventEmitter emitter = context.getInstance(EventEmitter.class);
        listeners = context.findInstanceByAnnotation(org.everest.decorator.Listener.class);
        emitter.addListeners(listeners);
    }

    private void addControllers(){
        controllers = context.findInstanceByAnnotation(HttpController.class);
        FrontalController frontalController = context.getInstance(FrontalController.class);
    }

    private void addErrorHandlers(){
        List<Object> errorHandlers = context.findInstanceByAnnotation(ErrorHandler.class);
        errorHandlers.forEach(o -> this.errorHandlers.add((IErrorHandler) o));
    }


    public void init(String appClassString){
        //frontalController= context.getInstance(FrontalController.class);
        try {
            Class<?> appClass = Class.forName(appClassString);
            context.addInstance(appClass);
            ApplicationInitializer initializer = context.getInstance(ApplicationInitializer.class);
            StaticContext.applicationInitializer = initializer;
            context.addByPackages(initializer.getBasePackages());
            addServiceInstance();
            context.initialize();
            addEventListeners();
            addControllers();
            addErrorHandlers();
            context.initialize();
            router.init(controllers.toArray());
            logger.debug("There are {} Instance in context", context.countDependencies());
            Class[] classes = ReflexionUtils.getClasses(initializer.getBasePackages()[0]);
            //Class[] classes = ReflexionUtils.getClasses("org.everest");
            logger.debug("---------------------------------");
            logger.debug("Application has {} classes", classes.length);
            logger.debug("---------------------------------");

            logger.debug("Error handlers registered: {}", errorHandlers.size());
            EventEmitter.getInstance().emit("OnApp.Start");
        } catch (ClassNotFoundException e) {
            throw new ApplicationInitialisationException("The App initiator Class'" +
                    appClassString + "' not found");
        }
    }

    public List<IErrorHandler> getErrorHandlers() {
        return errorHandlers;
    }

    public DefaultErrorHandler getDefaultErrorHandler() {
        return defaultErrorHandler;
    }

    public void setDefaultErrorHandler(DefaultErrorHandler defaultErrorHandler) {
        this.defaultErrorHandler = defaultErrorHandler;
    }

    public ApplicationContext getContext() { return context; }

    public List<Object> getListeners() {return listeners; }

    public Collection<Object> getControllers() {  return controllers; }
}
