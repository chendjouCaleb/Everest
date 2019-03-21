package Everest.Mvc.ValueResolver.AnnotationResolver;

import Everest.Http.HttpContext;
import Everest.Mvc.ValueResolver.Annotations.QueryValue;
import Everest.Mvc.ValueResolver.IAnnotationValueResolver;
import org.apache.commons.beanutils.ConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Parameter;
import java.util.List;

public class QueryValueResolver implements IAnnotationValueResolver<QueryValue> {
    private Logger logger = LoggerFactory.getLogger(QueryValueResolver.class);
    public Object getVariable(HttpContext httpContext, Parameter parameter, QueryValue annotation) {
        String name = annotation.value();
        if(name.equals("")){
            name = parameter.getName();
        }

        List<String> stringVal = httpContext.getRequest().query().get(name);


        logger.info("Query value: {} = {}", name, stringVal);

        return ConvertUtils.convert(stringVal, parameter.getType());
    }
}