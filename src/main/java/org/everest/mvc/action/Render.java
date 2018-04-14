package org.everest.mvc.action;

public class Render extends Action{
    private String view;
    private Object[] models;

    public Render(String view, Object... models) {
        this.view = view;
        this.models = models;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public Object[] getModels() {
        return models;
    }

    public void setModels(Object[] models) {
        this.models = models;
    }
}
