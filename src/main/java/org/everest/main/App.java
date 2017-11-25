package org.everest.main;
import component.http.Request;
import component.http.Response;
import dic.Container;
import event.EventEmitter;
import event.Listener;
import exception.AppException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class App {

    private static App app;

    private FrontalController frontalController;

    static App getApp(String appClass) {
        if (app == null){
            app = new App();
            app.init(appClass);
        }

        return app;
    }

    void run(HttpServletRequest servletRequest, HttpServletResponse servletResponse){
        Request request = new Request(servletRequest);
        Response response = new Response(servletResponse);
        EventEmitter.getInstance().emit("OnApp.Request");
        frontalController.handleRequest(request, response);

    }

    private void init(String AppClassString){
        frontalController= (FrontalController) Container.getService(FrontalController.class);
        try {
            Class appClass = Class.forName(AppClassString);
            System.out.println("App Class main " + appClass);
            AbstractApp abstractApp = (AbstractApp) Container.getService(appClass);
            initContainer(abstractApp);
            frontalController.addControllers(abstractApp.getControllers());
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

}
