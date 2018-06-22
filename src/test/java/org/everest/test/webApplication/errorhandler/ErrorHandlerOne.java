package org.everest.test.webApplication.errorhandler;


import org.everest.decorator.ErrorHandler;
import org.everest.mvc.errorHandler.IErrorHandler;
import org.everest.mvc.httpContext.HttpContext;

@ErrorHandler
public class ErrorHandlerOne implements IErrorHandler<Exception> {
    @Override
    public Class getErrorType() {
        return null;
    }

    @Override
    public void handleError(HttpContext httpContext, Exception exception) {

    }
}
