package org.everest.mvc.infrastructure;

import org.everest.exception.RouteNotFoundException;
import org.everest.mvc.filter.FilterChain;
import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.filter.FilterManager;
import org.everest.mvc.actionResultHandler.ActionResultHandler;
import org.everest.mvc.router.Route;
import org.everest.mvc.router.Router;
import org.everest.mvc.variableResolver.RequestVariableResolver;
import org.everest.mvc.service.message.Message;
import org.everest.mvc.variableResolver.VariableResolverException;
import org.everest.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class FrontalController {
    private Router router = StaticContext.context.getInstance(Router.class);
    private FilterManager filterManager = StaticContext.context.getInstance(FilterManager.class);
    private RequestVariableResolver requestVariableResolver = StaticContext.context.getInstance(RequestVariableResolver.class);
    private ActionResultHandler responseHandler = StaticContext.context.getInstance(ActionResultHandler.class);
    private Logger logger = LoggerFactory.getLogger(FrontalController.class);

    void handleRequest(HttpContext httpContext) throws Exception {
        Route route = router.getCalledRoute(httpContext.getRequest().getPathInfo(), httpContext.getRequest().getHttpMethod());

        if(route == null){
            throw new RouteNotFoundException("Aucune route ne correspondont à l'URL: " + httpContext.getRequest().getPathInfo());
        }else {
            logger.info("Request route: " + route.toString());
            httpContext.getRequest().setRoute(route);
            httpContext.setRoute(route);
            handle(httpContext);
        }
    }

    private void handle(HttpContext httpContext){
        Object ctrl = httpContext.getController();
        httpContext.getRequest().addAttribute("ctrl", router);
        httpContext.getRequest().addAttribute("route", httpContext.getRoute());
        httpContext.getRequest().addAttribute("model", httpContext.getModel().getObjects());


        filterManager.handleFilter(httpContext);

        if(httpContext.getFilterChain().isFinished()){
            Route route = httpContext.getRoute();
            try {
                Object[] params = requestVariableResolver.getVariables(httpContext);
                Object result = Utils.callRemote(ctrl, route.getMethod(), params);
                httpContext.setActionResult(result);
                if(!route.getMethod().getReturnType().equals(void.class) && result != null){
                    responseHandler.handleActionResult(httpContext);
                }
            }catch (Exception e){
                throw new ActionExecutionException("Error was occuring during the execution of action: " + route.getMethod().getName(), e);
            }


        }else {
            responseHandler.handleActionResult(httpContext);
        }

        List<Message> flashMessages = (List<Message>) httpContext.getSession().getAttribute("flashMessage");
        flashMessages.addAll(httpContext.getModel().getFlashs());

        httpContext.getModel().getSessionsObjects().forEach((key, value) -> {
            httpContext.getSession().setAttribute(key, value);
        });
    }

}
