package Everest.Mvc.ExceptionHandler;


import Everest.Mvc.Result.EntityResult;
import org.everest.decorator.Instance;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * Handle exception that dont have an {@link IExceptionHandler}.
 */
@Instance
public class DefaultExceptionHandler{

    /**
     * Provide a status Code if the exception class dont have a {@link ExceptionStatusCode} annotation.
     */
    private ExceptionStatusCodeGetter statusCodeGetter;

    public DefaultExceptionHandler(ExceptionStatusCodeGetter statusCodeGetter) {
        this.statusCodeGetter = statusCodeGetter;
    }


    public void handleException(ExceptionContext context) {
        ErrorResponseModel model = new ErrorResponseModel(context.getException());

        Class type = context.getException().getClass();
        Annotation statusCodeAnnotation = type.getAnnotation(ExceptionStatusCode.class);
        if(statusCodeAnnotation != null){
            int statusCode = statusCodeAnnotation.annotationType().getAnnotation(ExceptionStatusCode.class).value();
            model.setStatusCode(statusCode);
        }

        if(model.getStatusCode() == 0){
            try{
                model.setStatusCode(statusCodeGetter.get(context.getException().getClass()));
            }catch (NoSuchElementException e){
                model.setStatusCode(500);
            }
        }

        EntityResult<ErrorResponseModel> result = new EntityResult<>(model).setStatusCode(model.getStatusCode());
        context.setResult(result);
    }
}
