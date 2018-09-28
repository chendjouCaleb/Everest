package org.everest.mvc.identity;

import org.everest.mvc.errorHandler.ErrorResponseModel;
import org.everest.mvc.errorHandler.IErrorHandler;
import org.everest.mvc.http.HttpStatus;
import org.everest.mvc.http.ResponseEntity;
import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.result.ActionResult;

public class NotAuthorizedExceptionHandler implements IErrorHandler<NotAuthorizedException> {

    public Class<NotAuthorizedException> getErrorType() {
        return NotAuthorizedException.class;
    }

    public ActionResult handleError(HttpContext httpContext, NotAuthorizedException exception) {
        ErrorResponseModel model = new ErrorResponseModel(exception);
        model.setUriResource(exception.getUriResource());
        return new ResponseEntity<>(model, HttpStatus.UNAUTHORIZED);
    }
}
