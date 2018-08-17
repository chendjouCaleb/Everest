package org.everest.mvc.errorHandler;

import org.everest.mvc.binding.ObjectValidationException;
import org.everest.mvc.http.HttpStatus;
import org.everest.mvc.http.ResponseEntity;
import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.result.ActionResult;

public class ObjectValidationExceptionHandler implements IErrorHandler<ObjectValidationException> {
    @Override
    public Class getErrorType() {
        return ObjectValidationException.class;
    }

    @Override
    public ActionResult handleError(HttpContext httpContext, ObjectValidationException exception) {
        ValidationErrorModel model = new ValidationErrorModel(exception);

        return new ResponseEntity<>(model, HttpStatus.BAD_REQUEST);
    }
}
