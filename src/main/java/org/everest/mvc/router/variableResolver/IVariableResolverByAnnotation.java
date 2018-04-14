package org.everest.mvc.router.variableResolver;


import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;
import org.everest.mvc.router.Route;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;

public interface IVariableResolverByAnnotation<T extends Annotation> {
    Object getVariable(Request request, Response response, Route route, Parameter parameter, T annotation);
}
