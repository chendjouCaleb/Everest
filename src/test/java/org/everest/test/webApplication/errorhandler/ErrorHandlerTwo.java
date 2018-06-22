package org.everest.test.webApplication.errorhandler;


import org.everest.decorator.ErrorHandler;
import org.everest.mvc.errorHandler.IErrorHandler;
import org.everest.mvc.httpContext.HttpContext;

@ErrorHandler
public class ErrorHandlerTwo implements IErrorHandler<Throwable> {

    @Override
    public Class getErrorType() {
        return null;
    }

    @Override
    public void handleError(HttpContext httpContext, Throwable exception) {

    }
}
