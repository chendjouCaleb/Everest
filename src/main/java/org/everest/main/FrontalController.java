package org.everest.main;

import component.http.Controller;
import component.http.Request;
import component.http.Response;
import dic.Container;
import exception.RouteParamsException;
import filter.FilterManager;
import router.Route;
import router.Router;
import router.RouterUtils;

public class FrontalController {
    public Router router = (Router)Container.getService(Router.class);
    public FilterManager filterManager = (FilterManager)Container.getService(FilterManager.class);

    void addControllers(Class[] controllers){
        router.init(controllers);
    }

    void handleRequest(Request request, Response response){
        Route route = router.getCalledRoute(request);
        Controller controller = (Controller) Container.getService(route.getController());
        controller.setRequest(request);
        controller.setResponse(response);
        controller.setRouter(router);
        filterManager.handleFilter(route, request, response);
        controller.init();
        try {
            Object[] params = RouterUtils.params(route.getParameters(), route.getMethod());
            Utils.callRemote(controller, route.getMethod().getName(), params);
        } catch (RouteParamsException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
