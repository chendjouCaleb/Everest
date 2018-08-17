package org.everest.test.webApplication.errorhandler;


import org.everest.decorator.ErrorHandler;
import org.everest.mvc.errorHandler.IErrorHandler;
import org.everest.mvc.http.ResponseEntity;
import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.result.ActionResult;

@ErrorHandler
public class ErrorHandlerOne implements IErrorHandler<Exception> {
    @Override
    public Class getErrorType() {
        return null;
    }

    @Override
    public ActionResult handleError(HttpContext httpContext, Exception exception) {
return null;
    }
}
