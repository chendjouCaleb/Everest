package org.everest.main;
import org.everest.main.component.http.DefaultErrorHandler;
import org.everest.main.component.http.ErrorHandler;
import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;
import dic.Container;
import event.EventEmitter;
import event.Listener;
import exception.AppException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

class App {

    private static App app;

    private FrontalController frontalController;

    private List<ErrorHandler> errorHandlers = new ArrayList<>();

    private DefaultErrorHandler defaultErrorHandler = new DefaultErrorHandler();

    static App getApp() {
        if (app == null){
            app = new App();
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


    public void init(String AppClassString){
        frontalController= (FrontalController) Container.getService(FrontalController.class);
        try {
            Class appClass = Class.forName(AppClassString);
            System.out.println("App Class main " + appClass);
            AbstractApp abstractApp = (AbstractApp) Container.getService(appClass);
            initContainer(abstractApp);
            frontalController.addControllers(abstractApp.getControllers());

            errorHandlers.addAll(abstractApp.getErrorHandlers());
            System.out.println("Error handlers registered: " + errorHandlers.size());
            EventEmitter.getInstance().emit("OnApp.Start");
            System.out.println("App initialisation is finished. There are " + abstractApp.getControllers().length + " Controllers");
        } catch (ClassNotFoundException e) {
            try {
                throw new AppException("App initiator Class not found");
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }
    }

    private void initContainer(AbstractApp app){
        for (Class clazz: app.getAppEventListeners()){
            EventEmitter.getInstance().addListener((Listener)Container.getService(clazz));
        }
        Container.getContainer().addServiceInstance(EventEmitter.getInstance());
    }

    public List<ErrorHandler> getErrorHandlers() {
        return errorHandlers;
    }

    public void setErrorHandlers(List<ErrorHandler> errorHandlers) {
        this.errorHandlers = errorHandlers;
    }

    public DefaultErrorHandler getDefaultErrorHandler() {
        return defaultErrorHandler;
    }

    public void setDefaultErrorHandler(DefaultErrorHandler defaultErrorHandler) {
        this.defaultErrorHandler = defaultErrorHandler;
    }
}
