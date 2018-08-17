package org.everest.mvc.variableResolver;


import org.everest.decorator.Component;
import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.variableResolver.decorator.ResolvedBy;
import org.everest.mvc.variableResolver.resolver.byAnnotation.*;
import org.everest.mvc.variableResolver.resolver.byType.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

@Component
public class RequestVariableResolver {
    private Map<Class<?>, IVariableResolverByAnnotation> variableResolvers = new HashMap<>();
    private Map<Class<?>, IVariableResolverByType> variableResolversByType = new HashMap<>();
    private Logger logger = LoggerFactory.getLogger(RequestVariableResolver.class);
    public RequestVariableResolver(){
        addVariableResolverByAnnotation(new PathVariableResolver());
        addVariableResolverByAnnotation(new QueryVariableResolver());
        addVariableResolverByAnnotation(new ComponentResolver());
        addVariableResolverByAnnotation(new HttpRequestVariableResolver());
        addVariableResolverByAnnotation(new SessionAttributeResolver());
        addVariableResolverByAnnotation(new ModelAttributeVariableResolver());
        addVariableResolverByAnnotation(new RequestBodyResolver());

        addVariableResolverByType(new RequestRevolver());
        addVariableResolverByType(new ResponseRevolver());
        addVariableResolverByType(new SessionRevolver());
        addVariableResolverByType(new ModelRevolver());
        addVariableResolverByType(new RouteRevolver());
        addVariableResolverByType(new HttpContextResolver());
        addVariableResolverByType(new BindingStateRevolver());
        addVariableResolverByType(new IdentityAccountResolver());
        addVariableResolverByType(new RouteContextResolver());

    }

    public void addVariableResolverByAnnotation(IVariableResolverByAnnotation variableResolver){
        variableResolvers.put(variableResolver.getClass(), variableResolver);
    }

    public void addVariableResolverByType(IVariableResolverByType variableResolver){
        variableResolversByType.put(variableResolver.getType(), variableResolver);
    }
    public Object[] getVariables(Method method, HttpContext httpContext){
        Parameter[] parameters = method.getParameters();
        List<Object> variables = new ArrayList<>();

        for (Parameter parameter: parameters){
            boolean handled = false;
            Annotation[] annotations = parameter.getAnnotations();
            for (Annotation annotation: annotations){
                logger.info("Resolver Annotation test: {}", annotation.getClass().getSimpleName());
                ResolvedBy resolver = annotation.annotationType().getAnnotation(ResolvedBy.class);
                if (resolver != null){
                    logger.info("Resolver mapped: {}", resolver.getClass().getSimpleName());
                    IVariableResolverByAnnotation variableResolver = variableResolvers.get(resolver.value());

                    Object value = variableResolver.getVariable(httpContext, parameter, annotation);
                    variables.add(value);
                    handled = true;
                }
            }
            if (!handled){
                IVariableResolverByType variableResolverByType = variableResolversByType.get(parameter.getType());
                if (variableResolverByType == null){
                    throw new VariableResolverException("Aucun injecteur de valeur pour le type: " + parameter.getType());
                }
                Object value = variableResolverByType.getValue(httpContext, parameter);
                variables.add(value);
            }
        }

        return variables.toArray();
    }

    public Object[] getVariables(HttpContext httpContext) {
        Method method = httpContext.getMethod();
        return getVariables(method, httpContext);
    }

    public Map<Class<?>, IVariableResolverByAnnotation> getVariableResolvers() {
        return variableResolvers;
    }

}
