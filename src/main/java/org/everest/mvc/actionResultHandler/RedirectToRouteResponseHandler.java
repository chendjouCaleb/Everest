package org.everest.mvc.actionResultHandler;

import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.infrastructure.RouteBuilder;
import org.everest.mvc.infrastructure.StaticContext;
import org.everest.mvc.result.RouteRedirection;
import org.everest.mvc.router.Router;
import org.everest.mvc.service.message.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RedirectToRouteResponseHandler implements IResultHandler<RouteRedirection> {

    @Override
    public void handleResponse(HttpContext httpContext, RouteRedirection result) {
        RouteBuilder routeBuilder = StaticContext.context.getInstance(RouteBuilder.class);
        result.getData().forEach((key, value) -> httpContext.getModel().addData(key, value));
        httpContext.getSession().setAttribute("redirectAttributes", result.getData());
        if(httpContext.getSession().getAttribute("flashMessage") == null){
            httpContext.getSession().setAttribute("flashMessage", new ArrayList<Message>());
        }
        List<Message> flashMessages = (List<Message>) httpContext.getSession().getAttribute("flashMessage");
        flashMessages.addAll(result.getFlashs());
        if(result.getParameters().size() == 0){
            httpContext.getResponse().redirect(routeBuilder.url(result.getTarget(), (String[]) result.getParams()));
        }else {
            httpContext.getResponse().redirect(routeBuilder.url(result.getTarget(), result.getParameters()));
        }

    }

    @Override
    public Class<?> getType() {
        return RouteRedirection.class;
    }


}
