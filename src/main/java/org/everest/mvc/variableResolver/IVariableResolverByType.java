package org.everest.mvc.variableResolver;


import Everest.Http.HttpContext;

import java.lang.reflect.Parameter;

public interface IVariableResolverByType<T> {
    Class<? extends T> getType();
    T getValue(HttpContext httpContext, Parameter parameter);
}
