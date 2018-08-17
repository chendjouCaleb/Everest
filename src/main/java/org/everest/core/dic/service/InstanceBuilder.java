package org.everest.core.dic.service;

import org.everest.core.dic.FactoryInstance;
import org.everest.core.dic.Instance;
import org.everest.core.dic.TypeInstance;
import org.everest.utils.Assert;
import org.everest.utils.ReflexionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InstanceBuilder {
    private DependencyInspector dependencyInspector = new DependencyInspector();

    public List<TypeInstance> createTypeInstances(List<Class> types) {
        List<TypeInstance> typeInstances = new ArrayList<>();
        types.forEach(type -> {
            Assert.isFalse(type.isInterface() || type.isAnnotation(), type.getName() + " is not admissible for instantiation");
            TypeInstance typeInstance = createInstance(type);
            typeInstances.add(typeInstance);
        });
        return typeInstances;
    }

    public List<FactoryInstance> createFactoryInstances(List<Method> methods) {
        List<FactoryInstance> factoryInstances = new ArrayList<>();
        for (Method method : methods) {
            FactoryInstance factoryInstance = createInstance(method);
            factoryInstances.add(factoryInstance);
        }
        return factoryInstances;
    }

    public TypeInstance createInstance(Class type) {
        TypeInstance typeInstance = new TypeInstance();
        typeInstance.setConcreteType(type);

        Assert.isTrue(type.getDeclaredConstructors().length == 1, "Le type '" + type.getName() +
                "' ne doit avoir qu'un seul constructeur");
        typeInstance.setConstructorDependencies(dependencyInspector.getDependentTypesByConstructor(type.getDeclaredConstructors()[0]));
        typeInstance.setFieldDependencies(dependencyInspector.getDependentTypesByFields(type));

        if (type.getInterfaces().length > 0) {
            Assert.isTrue(type.getInterfaces().length == 1, "Le type '" + type.getName() +
                    "' ne doit implémenter qu'une seule interface");
            typeInstance.setRegisteredType(type.getInterfaces()[0]);
        } else {
            typeInstance.setRegisteredType(type);
        }

        return typeInstance;
    }

    public TypeInstance createInstanceByObject(Object object) {
        TypeInstance instance = new TypeInstance();
        instance.setRegisteredType(object.getClass());
        instance.setConcreteType(object.getClass());
        instance.setInstance(object);
        return instance;
    }

    public FactoryInstance createInstance(Method method) {
        FactoryInstance instance = new FactoryInstance();
        instance.setMethod(method);
        instance.setRegisteredType(method.getReturnType());
        instance.setMethodDependencies(dependencyInspector.getDependentTypesByMethod(method));

        return instance;
    }
}
