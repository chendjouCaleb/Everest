package Everest.Mvc.ValueResolver;


import Everest.Http.HttpContext;

import java.lang.reflect.Parameter;

public interface ITypedValueResolver<T> {
    T getValue(HttpContext httpContext, Parameter parameter);
}
