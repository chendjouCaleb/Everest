package org.everest.mvc.infrastructure;

import org.everest.core.dic.decorator.AutoWired;
import org.everest.decorator.Instance;
import org.everest.mvc.FilterExecutionException;
import org.everest.mvc.actionResultHandler.ActionResultHandler;
import org.everest.mvc.context.RouteContext;
import org.everest.mvc.errorHandler.ErrorEventManager;
import org.everest.mvc.filter.FilterManager;
import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.variableResolver.RequestVariableResolver;
import org.everest.utils.Utils;

import java.lang.reflect.Method;

@Instance
public class MvcRunner {
    @AutoWired private RouteDispatcher routeDispatcher;
    @AutoWired private RouteLoader routeLoader;
    @AutoWired private RouteBuilder routeBuilder;
    @AutoWired private RequestVariableResolver variableResolver;
    @AutoWired private FilterManager filterManager;
    @AutoWired private ActionResultHandler actionResultHandler;
    @AutoWired private ErrorEventManager errorEventManager;

    public void run(HttpContext httpContext) {

        try {

            String pathInfo = httpContext.getRequest().getPathInfo();
            RouteModel routeModel = routeDispatcher.getCalledRoute(routeLoader.getRoutes(), pathInfo, httpContext.getRequest().getHttpMethod());
            RouteContext routeContext = routeDispatcher.createRouteContext(routeModel, pathInfo);
            httpContext.setRouteModel(routeModel);
            httpContext.setRouteContext(routeContext);
            httpContext.setController(routeModel.getControllerModel().getObject());
            httpContext.getRequest().addAttribute("ctrl", routeBuilder);
            httpContext.getRequest().addAttribute("Url", routeBuilder);
            httpContext.getRequest().addAttribute("route", httpContext.getRouteContext());
            httpContext.getRequest().addAttribute("routeData", httpContext.getRouteContext().getParameters());
            httpContext.getRequest().addAttribute("model", httpContext.getModel().getObjects());

            filterManager.handleFilter(httpContext);

            if (httpContext.getFilterChain().isFinished()) {
                Object[] params = variableResolver.getVariables(httpContext);
                ActionExecutionResult result = execute(routeModel.getControllerModel().getObject(),routeModel.getMethod(), params);
                if(result.isSuccess()){
                    httpContext.setActionResult(result.getObjectResult());
                    try {
                        actionResultHandler.handleActionResult(httpContext);
                    } catch (Exception e) {
                        throw new ActionExecutionException("Error was occurring during the execution of action: " + httpContext.getController().getClass().getSimpleName() + ":" + routeModel.getMethod().getName(), e);
                    }
                }else {
                    errorEventManager.handleError(result.getError(), httpContext);
                }

            } else {
                actionResultHandler.handleActionResult(httpContext);
            }

            httpContext.getModel().getSessionsObjects().forEach((key, value) -> {
                httpContext.getSession().setAttribute(key, value);
            });
        }
        catch (FilterExecutionException ex){
            errorEventManager.handleError(ex.getCause().getCause(), httpContext);
        }
        catch (Throwable e) {
            errorEventManager.handleError(e, httpContext);
        }
    }
    private ActionExecutionResult execute(Object controller, Method method, Object[] params){
        ActionExecutionResult result = new ActionExecutionResult();

        try {
            Object obj = Utils.callRemote(controller, method, params);
            result.setObjectResult(obj);
        } catch (Exception e) {
            result.setError(e.getCause());
        }
        return result;
    }
}

