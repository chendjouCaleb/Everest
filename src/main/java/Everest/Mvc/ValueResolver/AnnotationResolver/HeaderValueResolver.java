package Everest.Mvc.ValueResolver.AnnotationResolver;

import Everest.Http.HttpContext;
import Everest.Mvc.ValueResolver.Annotations.HeaderValue;
import Everest.Mvc.ValueResolver.IAnnotationValueResolver;
import org.apache.commons.beanutils.ConvertUtils;

import java.lang.reflect.Parameter;

public class HeaderValueResolver implements IAnnotationValueResolver<HeaderValue> {

    public Object getVariable(HttpContext httpContext, Parameter parameter, HeaderValue annotation) {
        String name = annotation.value();
        if(name.equals("")){
            name = parameter.getName();
        }

        String stringVal = httpContext.getRequest().headers().get(name);

        return ConvertUtils.convert(stringVal, parameter.getType());
    }
}
