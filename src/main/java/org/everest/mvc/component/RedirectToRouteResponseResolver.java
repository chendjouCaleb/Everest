package org.everest.mvc.component;

import org.everest.main.StaticContext;
import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;
import org.everest.mvc.action.Render;
import org.everest.mvc.action.RouteRedirection;
import org.everest.mvc.router.Router;

import javax.servlet.ServletException;
import java.io.IOException;

public class RedirectToRouteResponseResolver implements IResponseResolver<RouteRedirection>{

    @Override
    public void handleResponse(Request request, Response response, RouteRedirection result) {
        Router router = StaticContext.context.getInstance(Router.class);
        response.redirect(router.url(result.getTarget(), result.getParams()));
    }

    @Override
    public Class<?> getType() {
        return RouteRedirection.class;
    }
}
