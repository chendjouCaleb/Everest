package Everest.Mvc.ValueResolver.TypedResolver;

import Everest.Http.HttpContext;
import Everest.Mvc.ValueResolver.ITypedValueResolver;
import org.everest.mvc.binding.BindingState;

import java.lang.reflect.Parameter;

public class BindingStateRevolver implements ITypedValueResolver<BindingState> {

    public BindingState getValue(HttpContext httpContext, Parameter parameter) {

        return httpContext.getBindingState();
    }


}
