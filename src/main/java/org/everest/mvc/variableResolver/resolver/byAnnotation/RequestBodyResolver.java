package org.everest.mvc.variableResolver.resolver.byAnnotation;

import org.everest.mvc.binding.BindingState;
import org.everest.mvc.binding.IModelValidator;
import org.everest.mvc.binding.ObjectValidationException;
import Everest.Http.HttpContext;
import org.everest.mvc.service.RequestBodyHandler;
import org.everest.mvc.variableResolver.IVariableResolverByAnnotation;
import org.everest.mvc.variableResolver.decorator.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Parameter;
import java.util.Map;

public class RequestBodyResolver implements IVariableResolverByAnnotation<RequestBody> {
    private Logger logger = LoggerFactory.getLogger(RequestBodyResolver.class);
    private RequestBodyHandler requestBodyHandler;
    private IModelValidator modelValidator;

    public RequestBodyResolver(RequestBodyHandler requestBodyHandler, IModelValidator modelValidator) {
        this.requestBodyHandler = requestBodyHandler;
        this.modelValidator = modelValidator;
    }

    @Override
    public Object getVariable(HttpContext httpContext, Parameter parameter, RequestBody annotation) {

        Object obj = requestBodyHandler.getBody(httpContext, parameter.getType());


        Map<String, String> errors = modelValidator.validate(obj);
        BindingState state = new BindingState(obj);
        state.setFieldErrors(errors);

        if (annotation.validate() && state.getErrors().size() > 0) {
            throw new ObjectValidationException("L'object est invalide", state.getErrors());
        }
        httpContext.setBindingState(state);


        return obj;
    }

}
