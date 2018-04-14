package org.everest.mvc.router.variableResolver;


import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;
import org.everest.mvc.router.Route;
import org.everest.mvc.router.variableResolver.decorator.ResolvedBy;
import org.everest.mvc.router.variableResolver.resolver.byAnnotation.*;
import org.everest.mvc.router.variableResolver.resolver.byType.ModelRevolver;
import org.everest.mvc.router.variableResolver.resolver.byType.RequestRevolver;
import org.everest.mvc.router.variableResolver.resolver.byType.ResponseRevolver;
import org.everest.mvc.router.variableResolver.resolver.byType.SessionRevolver;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class RequestVariableResolver {
    private Map<Class<?>, IVariableResolverByAnnotation> variableResolvers = new HashMap<>();
    private Map<Class<?>, IVariableResolverByType> variableResolversByType = new HashMap<>();
    public RequestVariableResolver(){
        addVariableResolverByAnnotation(new PathVariableResolver());
        addVariableResolverByAnnotation(new HttpSessionResolver());
        addVariableResolverByAnnotation(new HttpRequestResolver());
        addVariableResolverByAnnotation(new HttpResponseResolver());
        addVariableResolverByAnnotation(new QueryVariableResolver());
        addVariableResolverByAnnotation(new ComponentResolver());
        addVariableResolverByAnnotation(new HttpRequestVariableResolver());
        addVariableResolverByAnnotation(new FormHandlerVariableResolver());

        addVariableResolverByType(new RequestRevolver());
        addVariableResolverByType(new ResponseRevolver());
        addVariableResolverByType(new SessionRevolver());
        addVariableResolverByType(new ModelRevolver());

    }

    public void addVariableResolverByAnnotation(IVariableResolverByAnnotation variableResolver){
        variableResolvers.put(variableResolver.getClass(), variableResolver);
    }

    public void addVariableResolverByType(IVariableResolverByType variableResolver){
        variableResolversByType.put(variableResolver.getType(), variableResolver);
    }

    public Object[] getVariables(Request request, Response response, Route route) {
        Method method = route.getMethod();
        Parameter[] parameters = method.getParameters();
        List<Object> variables = new ArrayList<>();

        for (Parameter parameter: parameters){
            boolean handled = false;
            Annotation[] annotations = parameter.getAnnotations();
            for (Annotation annotation: annotations){
                ResolvedBy resolver = annotation.annotationType().getAnnotation(ResolvedBy.class);
                if (resolver != null){
                    IVariableResolverByAnnotation variableResolver = variableResolvers.get(resolver.value());
                    Object value = variableResolver.getVariable(request, response, route, parameter, annotation);
                    variables.add(value);
                    handled = true;
                }
            }
            if (!handled){
                IVariableResolverByType variableResolverByType = variableResolversByType.get(parameter.getType());
                Object value = variableResolverByType.getValue(request, response, route, parameter);
                variables.add(value);
            }
        }

        return variables.toArray();
    }

    public Map<Class<?>, IVariableResolverByAnnotation> getVariableResolvers() {
        return variableResolvers;
    }

}
