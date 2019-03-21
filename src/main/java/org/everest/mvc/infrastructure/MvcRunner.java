package org.everest.mvc.infrastructure;

import Everest.Mvc.ActionInvocation.ActionInvocationContext;
import Everest.Mvc.ActionInvocation.ActionInvocationResult;
import Everest.Mvc.ActionInvocation.ActionMethodInvoker;
import Everest.Mvc.ActionResultExecutor.ActionResultExecutor;
import Everest.Mvc.ValueResolver.MethodValueResolver;
import org.everest.core.dic.decorator.AutoWired;
import org.everest.core.dic.decorator.Resolve;
import org.everest.decorator.Instance;
import org.everest.mvc.binding.BindingState;
import org.everest.mvc.binding.IModelValidator;
import org.everest.mvc.binding.ObjectValidationException;
import org.everest.mvc.context.RouteData;
import org.everest.mvc.filter.FilterManager;
import Everest.Http.HttpContext;
import org.everest.mvc.model.ModelValidationException;

@Instance
public class MvcRunner {
    @AutoWired private RouteDispatcher routeDispatcher;
    @AutoWired private RouteLoader routeLoader;
    @AutoWired private MethodValueResolver variableResolver;
    @AutoWired private FilterManager filterManager;
    @Resolve private ActionMethodInvoker methodInvoker;
    @Resolve private ActionResultExecutor actionResultExecutor;
    @Resolve private IModelValidator modelValidator;

    public void run(HttpContext httpContext) {
            String pathInfo = httpContext.getRequest().path();
            RouteModel routeModel = routeDispatcher.getCalledRoute(routeLoader.getRoutes(), pathInfo, httpContext.getRequest().method());
            RouteData routeData = routeDispatcher.createRouteContext(routeModel, pathInfo);
            httpContext.setRouteModel(routeModel);
            httpContext.setRouteData(routeData);
            httpContext.setController(routeModel.getControllerModel().getObject());

            filterManager.handleFilter(httpContext);

            if (httpContext.getFilterChain().isFinished()) {
                Object[] params = variableResolver.getVariables(httpContext);

                BindingState state = modelValidator.validate(routeModel.getControllerModel().getObject(), routeModel.getMethod(), params);
                if(!state.isValid()){
                    throw new ObjectValidationException("Object invalide", state.getErrors());
                }

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

