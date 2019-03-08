package org.everest.mvc.variableResolver;


import Everest.Http.HttpContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;

public interface IVariableResolverByAnnotation<T extends Annotation> {
    Object getVariable(HttpContext httpContext, Parameter parameter, T annotation);
}
