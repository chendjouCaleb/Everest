package Everest.Mvc.ValueResolver;


import Everest.Http.HttpContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;

public interface IAnnotationValueResolver<T extends Annotation> {
    Object getVariable(HttpContext httpContext, Parameter parameter, T annotation);
}
