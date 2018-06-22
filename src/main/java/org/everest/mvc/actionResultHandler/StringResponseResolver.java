package org.everest.mvc.actionResultHandler;

import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.infrastructure.StaticContext;
import org.everest.mvc.router.Router;

import javax.servlet.ServletException;
import java.io.IOException;

public class StringResponseResolver implements IResultHandler<String> {
    String[] results;
    @Override
    public void handleResponse(HttpContext httpContext, String result) {
        results = result.split(":");
        String action = results[0];
        String param = results[1];

        if(action.equals("render")){
            try {
                httpContext.getResponse().render(httpContext.getRequest(), param);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (action.equals("redirect")){
            httpContext.getResponse().redirect(httpContext.getRequest().getContextPath() + param);
        }else if(action.equals("redirectTo")){
            Router router = StaticContext.context.getInstance(Router.class);
            httpContext.getResponse().redirect(router.url(param));
        }
    }

    @Override
    public Class<?> getType() {
        return String.class;
    }
}
