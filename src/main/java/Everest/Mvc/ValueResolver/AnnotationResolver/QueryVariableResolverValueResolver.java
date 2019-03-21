package Everest.Mvc.ValueResolver.AnnotationResolver;

import Everest.Http.HttpContext;
import Everest.Http.HttpRequest;
import Everest.Mvc.ValueResolver.IAnnotationValueResolver;
import org.apache.commons.beanutils.ConvertUtils;
import org.everest.mvc.variableResolver.decorator.QueryVariable;

import java.lang.reflect.Parameter;
import java.util.List;

public class QueryVariableResolverValueResolver implements IAnnotationValueResolver<QueryVariable> {
    @Override
    public Object getVariable(HttpContext httpContext, Parameter parameter, QueryVariable annotation) {
        HttpRequest request = httpContext.getRequest();
        if(request.queryString() == null){
            return null;
        }
        List<String> stringVal = request.query().get(annotation.value());

        return ConvertUtils.convert(stringVal, parameter.getType());
    }
}
