package org.everest.core.dic;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class FactoryInstance extends Instance {
    private Method method;
    private Instance parentInstance;
    private List<Class>  methodDependencies = new ArrayList<>();

    @Override
    public List<Class> getDependencies() {
        //System.out.println("Dependencies count: " + super.getDependencies());
        List<Class> dependencies = new ArrayList<>();
        dependencies.add(parentInstance.getRegisteredType());
        dependencies.addAll(methodDependencies);
        return dependencies;
    }
    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Instance getParentInstance() {
        return parentInstance;
    }

    public void setParentInstance(Instance parentInstance) {
        this.parentInstance = parentInstance;
    }

    public List<Class> getMethodDependencies() {
        return methodDependencies;
    }

    public void setMethodDependencies(List<Class> methodDependencies) {
        this.methodDependencies = methodDependencies;
    }


}
