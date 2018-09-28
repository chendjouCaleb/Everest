package org.everest.core.dic;

import org.everest.core.dic.enumeration.Scope;
import org.everest.core.dic.exception.InstanceException;

import java.util.ArrayList;
import java.util.List;

public class Instance {
    private Object instance;
    private String key;
    private Scope scope = Scope.SINGLETON;
    private Class registeredType;
    private Class concreteType;

    public List<Class> getDependencies() {
        return new ArrayList<>();
    }

    public Class getRegisteredType() {
        return registeredType;
    }

    public void setRegisteredType(Class registeredType) {
        this.registeredType = registeredType;
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
                ", registeredType=" + registeredType +
                ", concreteType=" + concreteType +
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

    public Class getConcreteType() {
        return concreteType;
    }

    public void setConcreteType(Class concreteType) {
        if(concreteType.isInterface()){
            throw new InstanceException("The type '" + concreteType.getName() + "' is not a instantiable class");
        }
        this.concreteType = concreteType;
    }
}
