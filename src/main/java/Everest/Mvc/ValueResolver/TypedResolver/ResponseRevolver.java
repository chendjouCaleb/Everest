package Everest.Mvc.ValueResolver.TypedResolver;

import Everest.Http.HttpContext;
import Everest.Http.HttpResponse;
import Everest.Mvc.ValueResolver.ITypedValueResolver;

import java.lang.reflect.Parameter;

public class ResponseRevolver implements ITypedValueResolver<HttpResponse> {

    @Override
    public HttpResponse getValue(HttpContext httpContext, Parameter parameter) {
        return httpContext.getResponse();
    }
}
