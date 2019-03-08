package Everest.Mvc.ExceptionHandler;

import Everest.Http.HttpContext;
import Everest.Middleware.IMiddleware;
import Everest.Middleware.MiddlewareChain;

/**
 * The {@link Everest.Middleware.IMiddleware} that catch and handled a raised exceptions.
 */
public class ExceptionHandlerMiddleware implements IMiddleware {
    private ExceptionManager exceptionManager;

    public ExceptionHandlerMiddleware(ExceptionManager exceptionManager) {
        this.exceptionManager = exceptionManager;
    }


    public void execute(MiddlewareChain chain, HttpContext httpContext) {
        try{
            chain.executeNext(httpContext);
        }catch (Exception e){
            exceptionManager.handleError(e, httpContext);
        }
    }
}
