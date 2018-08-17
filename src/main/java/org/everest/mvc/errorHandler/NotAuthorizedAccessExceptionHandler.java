package org.everest.mvc.errorHandler;

import org.everest.exception.NotAuthorizedAccessException;
import org.everest.mvc.http.HttpStatus;
import org.everest.mvc.http.ResponseEntity;
import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.result.ActionResult;

public class NotAuthorizedAccessExceptionHandler implements IErrorHandler<NotAuthorizedAccessException> {
    @Override
    public Class getErrorType() {
        return NotAuthorizedAccessException.class;
    }

    @Override
    public ActionResult handleError(HttpContext httpContext, NotAuthorizedAccessException exception) {
        ErrorResponseModel model = new ErrorResponseModel(exception);

        return new ResponseEntity<>(model, HttpStatus.UNAUTHORIZED);
    }
}
