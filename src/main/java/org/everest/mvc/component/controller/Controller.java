package org.everest.mvc.component.controller;

import org.everest.mvc.action.JSON;
import org.everest.mvc.action.Redirection;
import org.everest.mvc.action.Render;
import org.everest.mvc.action.RouteRedirection;

public abstract class Controller {
    protected Render Render(String viewPath, Object... objects){
        return new Render(viewPath, objects);
    }

    protected RouteRedirection RouteRedirection(String target, Object... params) {
        return new RouteRedirection(target, params);
    }

    protected Redirection Redirection(String url){
        return new Redirection(url);
    }

    protected JSON Json(Object models){
        return new JSON(models);
    }
}
