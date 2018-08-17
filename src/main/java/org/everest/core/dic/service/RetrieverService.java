package org.everest.core.dic.service;

import org.everest.core.dic.FactoryInstance;
import org.everest.core.dic.Instance;
import org.everest.core.dic.TypeInstance;
import org.everest.core.dic.contract.IRetrieverService;
import org.everest.core.dic.enumeration.Scope;
import org.everest.core.dic.handler.InstanceAnnotationHandler;
import org.everest.exception.InstanceNotFoundException;

import java.util.*;

public class RetrieverService implements IRetrieverService{

    private List<Instance> instances;

    public RetrieverService(List<Instance> instances) {
        this.instances = instances;
    }


    public Instance getInstance(String key) {
        return instances.stream().filter(instance -> instance.getKey().equals(key)).findFirst()
                .orElseThrow(() -> new InstanceNotFoundException("Instance with key '" + key + "' not found"));

    }


    @Override
    public Instance getInstance(Class clazz) {
        return instances.stream().filter(instance -> instance.getRegisteredType().equals(clazz)).findFirst()
                .orElseThrow(() -> new InstanceNotFoundException("Instance with registered type '" + clazz + "' not found"));

    }

    @Override
    public Instance getByConcreteType(Class clazz) {
        return instances.stream().filter(instance -> instance.getConcreteType().equals(clazz)).findFirst()
                .orElseThrow(() -> new InstanceNotFoundException("Instance with concrete type '" + clazz + "' not found"));
    }

    @Override
    public Instance getInstanceByRegisteredOrConcreteType(Class clazz) {
        return instances.stream()
                .filter(instance -> instance.getRegisteredType().equals(clazz) || instance.getConcreteType().equals(clazz)).findFirst()
                .orElseThrow(() -> new InstanceNotFoundException("Instance with registered or concrete type '" + clazz + "' not found"));
    }

    @Override
    public List<Instance> getInstances(List<Class> classes){
        List<Instance> founds = new ArrayList<>();
        for(Class type: classes){
            Instance instance = getInstance(type);
            if(instance != null){
                founds.add(instance);
            }
        }
        return founds;
    }

    @Override
    public List<Object> getObjects(List<Instance> instances){
        List<Object> objects = new ArrayList<>();
        instances.forEach(instance -> objects.add(instance.getInstance()));
        return objects;
    }

    @Override
    public List<Object> getObjectByRegisteredType(List<Class> instances) {
        List<Instance> instances1 = getInstances(instances);
        return getObjects(instances1);
    }

    @Override
    public List<FactoryInstance> getFactoryInstance() {
        List<FactoryInstance> factoryInstances = new ArrayList<>();
        instances.forEach(instance -> {
            if(instance.getClass().equals(FactoryInstance.class)){
                factoryInstances.add((FactoryInstance) instance);
            }
        });
        return factoryInstances;
    }

    @Override
    public List<TypeInstance> getTypesInstances() {
        List<TypeInstance> typeInstances = new ArrayList<>();
        instances.forEach(instance -> {
            if(instance.getClass().equals(TypeInstance.class)){
                typeInstances.add((TypeInstance) instance);
            }
        });
        return typeInstances;
    }

    @Override
    public List<Instance> getInstances() {
        return instances;
    }
}
