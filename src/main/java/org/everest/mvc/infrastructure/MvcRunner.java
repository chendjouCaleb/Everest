package org.everest.mvc.infrastructure;

import Everest.Mvc.ActionInvocation.ActionInvocationContext;
import Everest.Mvc.ActionInvocation.ActionInvocationResult;
import Everest.Mvc.ActionInvocation.ActionMethodInvoker;
import Everest.Mvc.ActionResultExecutor.ActionResultExecutor;
import org.everest.core.dic.decorator.AutoWired;
import org.everest.core.dic.decorator.Resolve;
import org.everest.decorator.Instance;
import org.everest.mvc.context.RouteContext;
import org.everest.mvc.filter.FilterManager;
import Everest.Http.HttpContext;
import org.everest.mvc.variableResolver.RequestVariableResolver;

@Instance
public class MvcRunner {
    @AutoWired private RouteDispatcher routeDispatcher;
    @AutoWired private RouteLoader routeLoader;
    @AutoWired private RequestVariableResolver variableResolver;
    @AutoWired private FilterManager filterManager;
    @Resolve private ActionMethodInvoker methodInvoker;
    @Resolve private ActionResultExecutor actionResultExecutor;

    public void run(HttpContext httpContext) {
            String pathInfo = httpContext.getRequest().path();
            RouteModel routeModel = routeDispatcher.getCalledRoute(routeLoader.getRoutes(), pathInfo, httpContext.getRequest().method());
            RouteContext routeContext = routeDispatcher.createRouteContext(routeModel, pathInfo);
            httpContext.setRouteModel(routeModel);
            httpContext.setRouteContext(routeContext);
            httpContext.setController(routeModel.getControllerModel().getObject());

            filterManager.handleFilter(httpContext);

            if (httpContext.getFilterChain().isFinished()) {
                Object[] params = variableResolver.getVariables(httpContext);

                ActionInvocationContext invocationContext = new ActionInvocationContext(routeModel.getControllerModel().getObject(),
                        routeModel.getMethod(), params);
                ActionInvocationResult result = methodInvoker.invoke(invocationContext);

                httpContext.setActionResult(result.getObjectResult());

                actionResultExecutor.execute(httpContext, result);

            } else {
                ActionInvocationResult result = new ActionInvocationResult();
                result.setObjectResult(httpContext.getActionResult());
                actionResultExecutor.execute(httpContext, result);
            }
    }
}

