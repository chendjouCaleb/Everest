package Everest.Mvc.ValueResolver.AnnotationResolver;

import Everest.Http.HttpContext;
import Everest.Mvc.ValueResolver.Annotations.RouteValue;
import Everest.Mvc.ValueResolver.IAnnotationValueResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Parameter;

/**
 * Value resolver for the {@link RouteValue} annotated parameters.
 */
public class RouteValueResolver implements IAnnotationValueResolver<RouteValue> {
    private Logger logger = LoggerFactory.getLogger(RouteValueResolver.class);
    public Object getVariable(HttpContext httpContext, Parameter parameter, RouteValue annotation) {
        String name = annotation.value();
        if(name.equals("")){
            name = parameter.getName();
        }
        return httpContext.getRouteData().getParameter(name, parameter.getType());
    }
}
