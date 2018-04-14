package org.everest.main;

import org.everest.exception.RouteNotFoundException;
import org.everest.main.component.http.Controller;
import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;
import filter.FilterManager;
import org.everest.main.StaticContext;
import org.everest.mvc.component.ResponseHandler;
import org.everest.mvc.router.Route;
import org.everest.mvc.router.Router;
import org.everest.mvc.router.RouterUtils;
import org.everest.mvc.router.variableResolver.RequestVariableResolver;


public class FrontalController {
    private Router router = StaticContext.context.getInstance(Router.class);
    private FilterManager filterManager = StaticContext.context.getInstance(FilterManager.class);
    private RequestVariableResolver requestVariableResolver = new RequestVariableResolver();
    private ResponseHandler responseHandler = new ResponseHandler();

    void handleRequest(Request request, Response response) throws Exception {
        Route route = router.getCalledRoute(request.getPathInfo(), request.getHttpMethod());

        if(route == null){
            throw new RouteNotFoundException("Aucune route ne correspondont à l'URL: " + request.getPathInfo());
        }else {
            System.out.println("ROUTE: " + route.toString());
            request.setRoute(route);
            handle(request, response, route);
        }
    }

    private void handle(Request request, Response response, Route route) throws Exception {
        Object ctrl = route.getController();
        request.setAttr("ctrl", router);
        if(ctrl instanceof Controller){
            Controller controller = (Controller) ctrl;
            controller.setRequest(request);
            controller.setResponse(response);
            controller.setRouter(router);
        }

        filterManager.handleFilter(route, request, response);
        //controller.init();
        //Object[] params = RouterUtils.params(route.getParameters(), route.getMethod());
        Object[] params = requestVariableResolver.getVariables(request, response, route);
        Object result = Utils.callRemote(ctrl, route.getMethod(), params);
        if(!route.getMethod().getReturnType().equals(void.class) && result != null){
            responseHandler.handleResponse(request, response, result);
        }
    }

}
