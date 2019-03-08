package Everest.Mvc.ResponseFormatter;

import Everest.Http.HttpContext;

import javax.annotation.Nonnull;

/**
 * Contains the information for the format of an Http Response setBody
 */
public class ResponseFormatContext {

    public ResponseFormatContext(HttpContext httpContext, Object object) {
        this.httpContext = httpContext;
        this.object = object;
    }

    /**
     * The {@link HttpContext} of the http request
     */
    private HttpContext httpContext;

    /**
     * The object to write in response setBody
     */
    private Object object;

    public HttpContext getHttpContext() {
        return httpContext;
    }

    public void setHttpContext(@Nonnull HttpContext httpContext) {
        this.httpContext = httpContext;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(@Nonnull Object object) {
        this.object = object;
    }
}
