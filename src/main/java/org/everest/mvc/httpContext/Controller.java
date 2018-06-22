package org.everest.mvc.httpContext;

import org.everest.mvc.result.JSON;
import org.everest.mvc.result.Redirection;
import org.everest.mvc.result.Render;
import org.everest.mvc.result.RouteRedirection;

public abstract class Controller {
    final protected Render Render(String viewPath, Object... objects){

        return new Render(viewPathPrefix()+viewPath, objects);
    }

    final protected RouteRedirection RouteRedirection(String target, Object... params) {
        return new RouteRedirection(target, params);
    }

    final protected Redirection Redirection(String url){
        return new Redirection(url);
    }

    final protected JSON Json(Object models){
        return new JSON(models);
    }

    protected String viewPathPrefix(){
        return "";
    }
}
