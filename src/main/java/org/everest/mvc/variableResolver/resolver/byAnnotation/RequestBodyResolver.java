package org.everest.mvc.variableResolver.resolver.byAnnotation;

import org.everest.mvc.binding.BindingState;
import org.everest.mvc.binding.IModelValidator;
import org.everest.mvc.binding.ObjectValidationException;
import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.infrastructure.StaticContext;
import org.everest.mvc.service.RequestBodyHandler;
import org.everest.mvc.variableResolver.IVariableResolverByAnnotation;
import org.everest.mvc.variableResolver.decorator.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Parameter;
import java.util.Map;

public class RequestBodyResolver implements IVariableResolverByAnnotation<RequestBody> {
    private Logger logger = LoggerFactory.getLogger(RequestBodyResolver.class);

    @Override
    public Object getVariable(HttpContext httpContext, Parameter parameter, RequestBody annotation) {
        RequestBodyHandler handler = StaticContext.context.getInstance(RequestBodyHandler.class);
        IModelValidator modelValidator = StaticContext.context.getInstance(IModelValidator.class);

Object obj = handler.getBody(httpContext, parameter.getType());
        logger.info("Request body type: {}", obj.getClass());
        logger.info("Request body: {}", obj.toString());
        if(!annotation.value().equals("")){
            httpContext.getModel().addData(annotation.value(), obj);
        }else {
            httpContext.getModel().addData(annotation.value(), obj.getClass().getSimpleName().toLowerCase());
        }
        httpContext.setRequestBody(obj);

        Map<String, String> errors = modelValidator.validate(obj);
        BindingState state = new BindingState(obj);
        state.setFieldErrors(errors);
        httpContext.getModel().addData("errors", errors);
        httpContext.getModel().addData("state", state);

        if(annotation.validate() && state.getErrors().size() > 0){
                throw new ObjectValidationException("L'object est invalide", state.getErrors());
            }
        httpContext.setBindingState(state);


        return obj;
    }

}
