package org.everest.mvc.variableResolver.resolver.byType;

import Everest.Http.HttpContext;
import org.everest.mvc.router.Route;
import org.everest.mvc.variableResolver.IVariableResolverByType;

import java.lang.reflect.Parameter;

public class RouteRevolver implements IVariableResolverByType<Route> {
    @Override
    public Class<Route> getType() {
        return Route.class;
    }

    @Override
    public Route getValue(HttpContext httpContext, Parameter parameter) {

        return httpContext.getRoute();
    }
}
