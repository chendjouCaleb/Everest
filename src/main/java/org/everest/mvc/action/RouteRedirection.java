package org.everest.mvc.action;

public class RouteRedirection extends Action{
    private String target;
    private Object[] params;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public RouteRedirection(String target, Object... params) {
        this.target = target;
        this.params = params;
    }
}
