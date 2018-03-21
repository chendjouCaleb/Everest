package org.everest.test.webApplication.errorhandler;


import org.everest.decorator.ErrorHandler;
import org.everest.main.component.errorHandler.IErrorHandler;
import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;

@ErrorHandler
public class ErrorHandlerTwo implements IErrorHandler<Throwable> {

    @Override
    public Class getErrorType() {
        return null;
    }

    @Override
    public void handleError(Request request, Response response, Throwable exception) {

    }
}
