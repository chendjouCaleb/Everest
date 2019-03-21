package Everest.Mvc.ValueResolver.TypedResolver;

import Everest.Mvc.ValueResolver.ITypedValueResolver;
import org.everest.mvc.context.RouteData;
import Everest.Http.HttpContext;

import java.lang.reflect.Parameter;

public class RouteDataResolver implements ITypedValueResolver<RouteData> {

    public RouteData getValue(HttpContext httpContext, Parameter parameter) {
        return httpContext.getRouteData();
    }
}
