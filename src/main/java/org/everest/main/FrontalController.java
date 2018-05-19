package org.everest.main;

import org.everest.exception.RouteNotFoundException;
import org.everest.main.component.http.Controller;
import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;
import org.everest.mvc.filter.FilterManager;
import org.everest.mvc.component.responseResolver.ResponseHandler;
import org.everest.mvc.router.Route;
import org.everest.mvc.router.Router;
import org.everest.mvc.router.variableResolver.RequestVariableResolver;
import org.everest.mvc.service.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class FrontalController {
    private Router router = StaticContext.context.getInstance(Router.class);
    private FilterManager filterManager = StaticContext.context.getInstance(FilterManager.class);
    private RequestVariableResolver requestVariableResolver = new RequestVariableResolver();
    private ResponseHandler responseHandler = new ResponseHandler();
    private Logger logger = LoggerFactory.getLogger(FrontalController.class);
    void handleRequest(Request request, Response response) throws Exception {
        Route route = router.getCalledRoute(request.getPathInfo(), request.getHttpMethod());

        if(route == null){
            throw new RouteNotFoundException("Aucune route ne correspondont à l'URL: " + request.getPathInfo());
        }else {
            logger.info("Request route: " + route.toString());
            request.setRoute(route);
            handle(request, response, route);
        }
    }

    private void handle(Request request, Response response, Route route) throws Exception {
        Object ctrl = route.getController();
        request.setAttr("ctrl", router);
        request.setAttr("route", route);
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

        List<Message> flashMessages = (List<Message>) request.getSession().getAttr("flashMessage");
        flashMessages.addAll(request.getModel().getFlashs());
        if(!route.getMethod().getReturnType().equals(void.class) && result != null){
            responseHandler.handleResponse(request, response, result);
        }
    }

}
