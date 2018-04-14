package org.everest.mvc.action;

public class JSON extends Action{
    private Object model;

    public JSON(Object models) {
        this.model = models;
    }

    public Object getModel() {
        return model;
    }

    public void setModel(Object model) {
        this.model = model;
    }
}
