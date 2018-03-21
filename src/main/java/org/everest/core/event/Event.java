package org.everest.core.event;

public class Event {
    private String name;
    private Object[] params;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Event(String name, Object... params) {
        this.name = name;
        this.params = params;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object... params) {
        this.params = params;
    }
}
