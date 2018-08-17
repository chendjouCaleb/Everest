package org.everest.mvc.actionResultHandler;

import org.everest.decorator.Instance;
import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.result.ActionResult;

import java.util.HashMap;
import java.util.Map;

@Instance
public class ActionResultHandler {
    private Map<Class<?>, IResultHandler<?>> handlers = new HashMap<>();
    public ActionResultHandler(){
        addActionResultHandler(new StringResponseResolver());
        addActionResultHandler(new EventResponseHandler());
        addActionResultHandler(new RenderResponseHandler());
        addActionResultHandler(new RedirectionResponseHandler());
        addActionResultHandler(new RedirectToRouteResponseHandler());
        addActionResultHandler(new JSONResponseHandler());
        addActionResultHandler(new ViewResultHandler());
        addActionResultHandler(new ResponseEntityResultHandler());
        addActionResultHandler(new HttpStatusCodeResponseHandler());

    }
    public void addActionResultHandler(IResultHandler<?> handler){
        handlers.put(handler.getType(), handler);
    }

    public void handleActionResult(HttpContext httpContext){
        Class type = httpContext.getActionResult().getClass();
        IResultHandler resolver = handlers.get(type);
        handleStatusCode(httpContext);
        resolver.handleResponse(httpContext, httpContext.getActionResult());
    }

    private void handleStatusCode(HttpContext context){
        if (context.getActionResult().getClass().isAssignableFrom(ActionResult.class)){
            context.getResponse().setStatusCode(((ActionResult)context.getActionResult()).getStatusCode());

        }
    }

}

