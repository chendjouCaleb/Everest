package Everest.Mvc.ValueResolver;


import Everest.Core.Annotations;
import org.everest.decorator.Instance;
import Everest.Http.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

@Instance
public class MethodValueResolver {
    private Logger logger = LoggerFactory.getLogger(MethodValueResolver.class);
    private ValueResolverProvider resolverProvider;

    public MethodValueResolver(ValueResolverProvider resolverProvider) {
        this.resolverProvider = resolverProvider;
    }

    public Object[] getVariables(Method method, HttpContext httpContext){
        Parameter[] parameters = method.getParameters();
        List<Object> variables = new ArrayList<>();

        for (Parameter parameter: parameters){
            List<Annotation> annotations = Annotations.annotatedAnnotations(parameter, ValueResolver.class);
            if(annotations.size() > 1){
                throw new ValueResolverException("The parameter " + parameter.getName() + " has multiple annotation resolver");
            }
            if(annotations.size() == 1){
                Annotation targetAnnotation = annotations.get(0);
                variables.add(resolveByAnnotation(parameter, targetAnnotation, httpContext));
            }else{
                variables.add(resolveByType(parameter, httpContext));
            }
        }

        return variables.toArray();
    }

    public Object[] getVariables(HttpContext httpContext) {
        Method method = httpContext.getMethod();
        return getVariables(method, httpContext);
    }

    private Object resolveByAnnotation(Parameter parameter, Annotation annotation, HttpContext httpContext){
        try {
            IAnnotationValueResolver variableResolver = resolverProvider.getAnnotationResolver(annotation.annotationType());
            return variableResolver.getVariable(httpContext, parameter, annotation);
        }catch (NoSuchElementException e){
            throw new ValueResolverException("Aucun injecteur de valeur pour l'annotation: " + annotation, e);
        }

    }

    private Object resolveByType(Parameter parameter, HttpContext httpContext){
        try{
            ITypedValueResolver variableResolverByType = resolverProvider.getTypedResolver(parameter.getType());
            return variableResolverByType.getValue(httpContext, parameter);
        }catch (NoSuchElementException e){
            throw new ValueResolverException("Aucun injecteur de valeur pour le type: " + parameter.getType(), e);
        }

    }

}
