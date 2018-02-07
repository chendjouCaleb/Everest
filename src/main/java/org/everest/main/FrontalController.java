package org.everest.main;

import org.everest.main.component.http.Controller;
import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;
import dic.Container;
import filter.FilterManager;
import router.Route;
import router.Router;
import router.RouterUtils;

import java.io.IOException;

public class FrontalController {
    private Router router = (Router)Container.getService(Router.class);
    private FilterManager filterManager = (FilterManager)Container.getService(FilterManager.class);

    void addControllers(Class[] controllers){
        router.init(controllers);
    }

    void handleRequest(Request request, Response response) throws Exception {
        Route route = router.getCalledRoute(request);
        if(route == null){
            try {
                System.out.println("La route demandée n'existe pas!");
                String message = "Aucune route ne correspondont à l'URL: " + request.getPathInfo();
                response.getServletResponse().sendError(404, message);
            } catch (IOException e) {
                Utils.handleError(request, response, e);
            }
        }else {
            Controller controller = (Controller) Container.getService(route.getController());
            controller.setRequest(request);
            controller.setResponse(response);
            controller.setRouter(router);
            filterManager.handleFilter(route, request, response);
            controller.init();
            Object[] params = RouterUtils.params(route.getParameters(), route.getMethod());
            Utils.callRemote(controller, route.getMethod().getName(), params);

        }
    }

}
