package org.everest.mvc.renderer;

import org.everest.mvc.httpContext.HttpContext;

public interface IViewRenderer {
    void render(String viewName, HttpContext httpContext);
}
