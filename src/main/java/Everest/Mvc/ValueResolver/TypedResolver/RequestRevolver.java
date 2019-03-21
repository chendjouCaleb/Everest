package Everest.Mvc.ValueResolver.TypedResolver;

import Everest.Http.HttpContext;
import Everest.Http.HttpRequest;
import Everest.Mvc.ValueResolver.ITypedValueResolver;

import java.lang.reflect.Parameter;

public class RequestRevolver implements ITypedValueResolver<HttpRequest> {

    public HttpRequest getValue(HttpContext context, Parameter parameter) {
        return context.getRequest();
    }
}
