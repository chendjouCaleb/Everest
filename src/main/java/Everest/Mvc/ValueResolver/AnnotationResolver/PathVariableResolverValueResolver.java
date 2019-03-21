package Everest.Mvc.ValueResolver.AnnotationResolver;

import Everest.Mvc.ValueResolver.IAnnotationValueResolver;
import org.apache.commons.beanutils.ConvertUtils;
import Everest.Http.HttpContext;
import org.everest.mvc.variableResolver.decorator.PathVariable;

import java.lang.reflect.Parameter;

public class PathVariableResolverValueResolver implements IAnnotationValueResolver<PathVariable> {
    @Override
    public Object getVariable(HttpContext httpContext, Parameter parameter, PathVariable path) {
        return httpContext.getRouteData().getParameter(path.value(), parameter.getType());

    }
}
