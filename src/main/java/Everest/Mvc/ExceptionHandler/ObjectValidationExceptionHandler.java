package Everest.Mvc.ExceptionHandler;

import Everest.Http.StatusCode;
import Everest.Mvc.Result.EntityResult;
import org.everest.mvc.binding.ObjectValidationException;
import org.everest.mvc.http.ResponseEntity;
import Everest.Http.HttpContext;
import org.everest.mvc.model.ModelValidationException;
import org.everest.mvc.result.ActionResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ObjectValidationExceptionHandler implements IExceptionHandler {

    @Override
    public Collection<Class<? extends Throwable>> getExceptionTypes() {
        ArrayList<Class<? extends Throwable>> list = new ArrayList<>();
        list.add(ObjectValidationException.class);
        return list;
    }

    @Override
    public void handleException(ExceptionContext context) {
        ValidationErrorModel model = new ValidationErrorModel((ObjectValidationException) context.getException());


        context.setResult(new EntityResult<>(model).setStatusCode(StatusCode.BAD_REQUEST));
    }
}
