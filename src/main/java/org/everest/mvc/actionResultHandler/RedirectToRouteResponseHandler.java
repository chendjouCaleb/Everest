package org.everest.mvc.actionResultHandler;

import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.infrastructure.StaticContext;
import org.everest.mvc.result.RouteRedirection;
import org.everest.mvc.router.Router;

public class RedirectToRouteResponseHandler implements IResultHandler<RouteRedirection> {

    @Override
    public void handleResponse(HttpContext httpContext, RouteRedirection result) {
        Router router = StaticContext.context.getInstance(Router.class);
        httpContext.getResponse().redirect(router.url(result.getTarget(), result.getParams()));
    }

    @Override
    public Class<?> getType() {
        return RouteRedirection.class;
    }
}
