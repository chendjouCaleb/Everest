package org.everest.mvc.component;

import org.everest.main.StaticContext;
import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;
import org.everest.mvc.router.Router;

import javax.servlet.ServletException;
import java.io.IOException;

public class StringResponseResolver implements IResponseResolver<String>{
    String[] results;
    @Override
    public void handleResponse(Request request, Response response, String result) {
        results = result.split(":");
        String action = results[0];
        String param = results[1];

        if(action.equals("render")){
            try {
                response.render(request, param);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (action.equals("redirect")){
            response.redirect(request.getContextPath() + param);
        }else if(action.equals("redirectTo")){
            Router router = StaticContext.context.getInstance(Router.class);
            response.redirect(router.url(param));
        }
    }
}
