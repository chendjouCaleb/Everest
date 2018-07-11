package org.everest.mvc.renderer;

import org.everest.mvc.httpContext.HttpContext;

public abstract class ViewRenderer {
    public abstract void render(String viewName, HttpContext httpContext);
    private String prefix;
    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
