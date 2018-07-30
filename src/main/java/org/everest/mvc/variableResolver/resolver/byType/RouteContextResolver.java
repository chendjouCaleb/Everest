package org.everest.mvc.variableResolver.resolver.byType;

import org.everest.mvc.context.RouteContext;
import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.variableResolver.IVariableResolverByType;

import java.lang.reflect.Parameter;

public class RouteContextResolver implements IVariableResolverByType<RouteContext> {

    public Class<RouteContext> getType() {
        return RouteContext.class;
    }

    public RouteContext getValue(HttpContext httpContext, Parameter parameter) {
        return httpContext.getRouteContext();

    }
}
