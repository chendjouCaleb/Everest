package org.everest.mvc.router.variableResolver;


import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;
import org.everest.mvc.router.Route;
import org.everest.mvc.router.variableResolver.decorator.ResolvedBy;
import org.everest.mvc.router.variableResolver.resolver.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class RequestVariableResolver {
    private Map<Class<?>, IVariableResolver> variableResolvers = new HashMap<>();
    public RequestVariableResolver(){
        addVariableResolver(new PathVariableResolver());
        addVariableResolver(new HttpSessionResolver());
        addVariableResolver(new HttpRequestResolver());
        addVariableResolver(new HttpResponseResolver());
        addVariableResolver(new QueryVariableResolver());
    }

    public void addVariableResolver(IVariableResolver variableResolver){
        variableResolvers.put(variableResolver.getClass(), variableResolver);
    }

    public Object[] getVariables(Request request, Response response, Route route) {
        Method method = route.getMethod();
        Parameter[] parameters = method.getParameters();
        List<Object> variables = new ArrayList<>();

        for (Parameter parameter: parameters){
            Annotation[] annotations = parameter.getAnnotations();
            for (Annotation annotation: annotations){
                ResolvedBy resolver = annotation.annotationType().getAnnotation(ResolvedBy.class);
                IVariableResolver variableResolver = variableResolvers.get(resolver.value());

                Object value = variableResolver.getVariable(request, response, route, parameter, annotation);
                variables.add(value);
            }
        }

        return variables.toArray();
    }

    public Map<Class<?>, IVariableResolver> getVariableResolvers() {
        return variableResolvers;
    }

}
