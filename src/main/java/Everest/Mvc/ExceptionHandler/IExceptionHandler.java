package Everest.Mvc.ExceptionHandler;


import java.util.Collection;

public interface IExceptionHandler {
    Collection<Class<? extends Throwable>> getExceptionTypes();
    void handleException(ExceptionContext context);
}
