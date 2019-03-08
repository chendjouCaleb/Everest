package org.everest.mvc.infrastructure;

import org.everest.mvc.component.MappingDescriptor;
import Everest.Http.HttpMethod;

import java.lang.reflect.Method;

public class RouteModel {
    private String mapping;
    private String name;
    private HttpMethod verbs;
    private ControllerModel controllerModel;
    private Method method;
    private MappingDescriptor descriptor;

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



    public ControllerModel getControllerModel() {
        return controllerModel;
    }

    public void setControllerModel(ControllerModel controllerModel) {
        this.controllerModel = controllerModel;
    }

    public MappingDescriptor getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(MappingDescriptor descriptor) {
        this.descriptor = descriptor;
    }

    public HttpMethod getVerbs() {
        return verbs;
    }

    public void setVerbs(HttpMethod verbs) {
        this.verbs = verbs;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
