package Everest.Mvc.ValueResolver.TypedResolver;

import Everest.Http.HttpContext;
import Everest.Mvc.ValueResolver.ITypedValueResolver;

import java.lang.reflect.Parameter;

public class HttpContextResolver implements ITypedValueResolver<HttpContext> {

    public HttpContext getValue(HttpContext httpContext, Parameter parameter) {
        return httpContext;
    }
}
