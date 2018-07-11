package org.everest.mvc.actionResultHandler;

import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.infrastructure.StaticContext;
import org.everest.mvc.result.RouteRedirection;
import org.everest.mvc.router.Router;
import org.everest.mvc.service.message.Message;

import java.util.ArrayList;
import java.util.List;

public class RedirectToRouteResponseHandler implements IResultHandler<RouteRedirection> {

    @Override
    public void handleResponse(HttpContext httpContext, RouteRedirection result) {
        Router router = StaticContext.context.getInstance(Router.class);
        result.getData().forEach((key, value) -> httpContext.getModel().addData(key, value));
        if(httpContext.getSession().getAttribute("flashMessage") == null){
            httpContext.getSession().setAttribute("flashMessage", new ArrayList<Message>());
        }
        List<Message> flashMessages = (List<Message>) httpContext.getSession().getAttribute("flashMessage");
        flashMessages.addAll(result.getFlashs());
        httpContext.getResponse().redirect(router.url(result.getTarget(), result.getParams()));
    }

    @Override
    public Class<?> getType() {
        return RouteRedirection.class;
    }
}
