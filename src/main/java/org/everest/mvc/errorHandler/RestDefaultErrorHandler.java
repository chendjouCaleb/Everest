package org.everest.mvc.errorHandler;

import org.everest.mvc.http.HttpStatus;
import org.everest.mvc.http.ResponseEntity;
import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.result.ActionResult;
import org.everest.mvc.result.Render;

import java.io.PrintWriter;
import java.io.StringWriter;

public class RestDefaultErrorHandler implements IErrorHandler<Throwable> {
    @Override
    public Class getErrorType() {
        return null;
    }

    @Override
    public ResponseEntity<ErrorResponseModel> handleError(HttpContext httpContext, Throwable exception) {
        ErrorResponseModel model = new ErrorResponseModel(exception);
        model.setStatusCode(HttpStatus.BAD_GATEWAY.value());

        return new ResponseEntity<>(model, HttpStatus.BAD_GATEWAY);
    }
}
