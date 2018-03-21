package org.everest.core.dic;

import org.everest.core.dic.enumeration.Scope;

import java.util.ArrayList;
import java.util.List;

public class Instance {
    private Object instance;
    private String key;
    private Scope scope;
    private List<Class> dependencies = new ArrayList<>();
    private Class type;

    public List<Class> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<Class> dependencies) {
        this.dependencies = dependencies;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public Object getInstance() {
        return instance;
    }


    @Override
    public String toString() {
        return "Instance{" +
                "instance=" + instance +
                ", key='" + key + '\'' +
                ", scope=" + scope +
                '}';
    }

    public void setInstance(Object instance) {
        this.instance = instance;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }
}
