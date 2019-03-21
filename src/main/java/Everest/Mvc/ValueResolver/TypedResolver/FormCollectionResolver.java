package Everest.Mvc.ValueResolver.TypedResolver;

import Everest.Http.FormCollection;
import Everest.Http.HttpContext;
import Everest.Mvc.ValueResolver.ITypedValueResolver;

import java.lang.reflect.Parameter;

public class FormCollectionResolver implements ITypedValueResolver<FormCollection> {

    public FormCollection getValue(HttpContext httpContext, Parameter parameter) {
        return httpContext.getRequest().forms();
    }
}
