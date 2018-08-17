package org.everest.mvc.httpContext;

import org.everest.mvc.result.*;

import java.util.Map;

public abstract class Controller {
    final protected Render Render(String viewPath, Object... objects){
        return new Render(viewPathPrefix()+viewPath, objects);
    }

    final protected View View(String viewPath){
        return new View(viewPath);
    }

    final protected RouteRedirection RouteRedirection(String target, Object... params) {
        return new RouteRedirection(target, params);
    }
    final protected RouteRedirection RouteRedirection(String target, Map<String, String> params) {
        return new RouteRedirection(target, params);
    }

    final protected Redirection Redirection(String url){
        return new Redirection(url);
    }
    final protected Redirection Redirection(String url, HttpStatus status){
        return (Redirection) new Redirection(url).statusCode(status.value());
    }

    final protected JSON Json(Object models){
        return new JSON(models);
    }

    protected String viewPathPrefix(){
        return "";
    }
}
