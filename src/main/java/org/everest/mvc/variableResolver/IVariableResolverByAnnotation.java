package org.everest.mvc.variableResolver;


import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.httpContext.Request;
import org.everest.mvc.httpContext.Response;
import org.everest.mvc.router.Route;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;

public interface IVariableResolverByAnnotation<T extends Annotation> {
    Object getVariable(HttpContext httpContext, Parameter parameter, T annotation);
}
