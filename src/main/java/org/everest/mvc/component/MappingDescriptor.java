package org.everest.mvc.component;

import org.everest.mvc.decorator.HttpMapping;
import Everest.Http.HttpMethod;

public class MappingDescriptor {
    String value;
    String name;
    HttpMethod verb;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MappingDescriptor(){

    }
    public MappingDescriptor(HttpMapping httpMapping){
        value = httpMapping.value();
        verb = httpMapping.verbs();
        name = httpMapping.name();
    }

    public HttpMethod getVerb() {
        return verb;
    }

    public void setVerb(HttpMethod verb) {
        this.verb = verb;
    }
}
