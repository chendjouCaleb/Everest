package org.everest.mvc.errorHandler;

import org.everest.core.dic.decorator.AutoWired;
import org.everest.mvc.actionResultHandler.ActionResultHandler;
import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.result.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ErrorEventManager {
    private Logger logger = LoggerFactory.getLogger(ErrorEventManager.class);
    @AutoWired ActionResultHandler resultHandler;
    DefaultErrorHandler defaultErrorHandler = new DefaultErrorHandler();
    RestDefaultErrorHandler restDefaultErrorHandler = new RestDefaultErrorHandler();
    Map<Class<? extends Throwable>, IErrorHandler> errorHandlers = new HashMap<>();

    public ErrorEventManager(){
        addErrorHandler(new ObjectValidationExceptionHandler());
        addErrorHandler(new NotAuthorizedAccessExceptionHandler());
    }
    public void addErrorHandler(IErrorHandler handler){
        errorHandlers.put(handler.getErrorType(), handler);
    }

    public void handleError(Throwable e, HttpContext context){
        logger.info("Error type: " + e.getClass());
        IErrorHandler errorHandler = getErrorHandler(e, context);
        logger.info("Error Handler: {}", errorHandler.getClass());
        ActionResult result = errorHandler.handleError(context, e);

        context.setActionResult(result);
        resultHandler.handleActionResult(context);
    }

    private IErrorHandler getErrorHandler(Throwable e, HttpContext context) {
        IErrorHandler errorHandler = errorHandlers.get(e.getClass());
        if(errorHandler == null) {
            if(context.isRestContext()){
                errorHandler = restDefaultErrorHandler;
            }else {
                errorHandler = defaultErrorHandler;
            }
        }
        return errorHandler;
    }

}
