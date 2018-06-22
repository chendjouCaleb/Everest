package org.everest.mvc.variableResolver;


import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.httpContext.Request;
import org.everest.mvc.httpContext.Response;
import org.everest.mvc.router.Route;

import java.lang.reflect.Parameter;

public interface IVariableResolverByType<T> {
    Class<? extends T> getType();
    T getValue(HttpContext httpContext, Parameter parameter);
}
