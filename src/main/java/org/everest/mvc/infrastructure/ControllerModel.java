package org.everest.mvc.infrastructure;

import org.everest.mvc.decorator.HttpMapping;

public class ControllerModel {
    private String mapping;
    private String name;
    private Object object;
    private HttpMapping httpMapping;

    public String getMapping() {
        return mapping;
    }

    public void setMapping(String mapping) {
        this.mapping = mapping;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public HttpMapping getHttpMapping() {
        return httpMapping;
    }

    public void setHttpMapping(HttpMapping httpMapping) {
        this.httpMapping = httpMapping;
    }
}
