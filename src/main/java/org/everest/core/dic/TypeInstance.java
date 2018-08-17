package org.everest.core.dic;

import org.everest.core.dic.Instance;
import org.everest.core.dic.exception.InstanceException;

import java.util.ArrayList;
import java.util.List;

public class TypeInstance extends Instance {
    private List<Class> fieldDependencies = new ArrayList<>();
    private List<Class> constructorDependencies = new ArrayList<>();




    public List<Class> getFieldDependencies() {
        return fieldDependencies;
    }

    public void setFieldDependencies(List<Class> fieldDependencies) {
        this.fieldDependencies = fieldDependencies;
    }

    public List<Class> getConstructorDependencies() {
        return constructorDependencies;
    }

    public void setConstructorDependencies(List<Class> constructorDependencies) {
        this.constructorDependencies = constructorDependencies;
    }

    @Override
    public List<Class> getDependencies() {
        List<Class> dependencies = new ArrayList<>();
        dependencies.addAll(fieldDependencies);
        dependencies.addAll(constructorDependencies);
        return dependencies;
    }
}
