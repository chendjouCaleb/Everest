package org.everest.mvc.router.variableResolver;


import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;
import org.everest.mvc.router.Route;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;

public interface IVariableResolverByType<T> {
    Class<? extends T> getType();
    T getValue(Request request, Response response, Route route, Parameter parameter);
}
