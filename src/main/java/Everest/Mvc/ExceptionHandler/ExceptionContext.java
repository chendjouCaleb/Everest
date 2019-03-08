package Everest.Mvc.ExceptionHandler;

import Everest.Http.HttpContext;

import javax.annotation.Nonnull;

/**
 * Contains the information for handle a raised exception
 */
public class ExceptionContext {

    /**
     * The raised exception.
     */
    private Throwable exception;


    /**
     * The {@link HttpContext} oh the http request.
     */
    private HttpContext httpContext;

    /**
     * The result of the handling operation.
     */
    private Object result;

    public ExceptionContext(@Nonnull Throwable exception, @Nonnull HttpContext httpContext) {
        this.exception = exception;
        this.httpContext = httpContext;
    }

    @Nonnull
    public Throwable getException() {
        return exception;
    }


    public void setException(@Nonnull Throwable exception) {
        this.exception = exception;
    }

    public HttpContext getHttpContext() {
        return httpContext;
    }

    public void setHttpContext(@Nonnull HttpContext httpContext) {
        this.httpContext = httpContext;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(@Nonnull Object result) {
        this.result = result;
    }
}
