package Everest.Middleware;

import Everest.Http.HttpContext;

/**
 * The contract of all middleware class
 */
public interface IMiddleware {

    /**
     * The middleware method
     * @param chain Contains all other middleware
     * @param httpContext The context of an http request
     */
    void execute(MiddlewareChain chain, HttpContext httpContext);
}
