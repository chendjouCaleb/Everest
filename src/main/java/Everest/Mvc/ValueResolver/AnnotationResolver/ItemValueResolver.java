package Everest.Mvc.ValueResolver.AnnotationResolver;

import Everest.Core.Exception.InvalidOperationException;
import Everest.Http.HttpContext;
import Everest.Mvc.ValueResolver.Annotations.ItemValue;
import Everest.Mvc.ValueResolver.IAnnotationValueResolver;

import java.lang.reflect.Parameter;

public class ItemValueResolver implements IAnnotationValueResolver<ItemValue> {
    @Override
    public Object getVariable(HttpContext httpContext, Parameter parameter, ItemValue annotation) {
        String name = annotation.value();
        if(name.equals("")){
            name = parameter.getName();
        }

        Object obj = httpContext.getItems().get(name);
        return obj;
    }
}
