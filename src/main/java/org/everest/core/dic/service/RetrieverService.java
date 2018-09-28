package org.everest.core.dic.service;

import org.everest.core.dic.FactoryInstance;
import org.everest.core.dic.Instance;
import org.everest.core.dic.TypeInstance;
import org.everest.core.dic.contract.IRetrieverService;
import org.everest.core.dic.enumeration.Scope;
import org.everest.core.dic.handler.ITypeFilter;
import org.everest.core.dic.handler.InstanceAnnotationHandler;
import org.everest.exception.InstanceNotFoundException;
import org.everest.utils.ReflexionUtils;

import java.lang.annotation.Annotation;
import java.util.*;

public class RetrieverService implements IRetrieverService{

    private List<Instance> instances;

    public RetrieverService(List<Instance> instances) {
        this.instances = instances;
    }


    public Instance getInstance(String key) {
        for (Instance instance: instances){
            if(instance.getKey() != null && instance.getKey().equals(key)){
                return instance;
            }
        }
        throw new InstanceNotFoundException("Instance with key '" + key + "' not found");

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
        instances.forEach(instance -> objects.add(instance.getRegisteredType().cast(instance.getInstance())));
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

    @Override
    public List<Instance> getInstanceBySuperType(Class type) {
        List<Instance> instanceList = new ArrayList<>();
        instances.forEach(instance -> {
            Class superClass = instance.getInstance().getClass().getSuperclass();
            if(superClass != null && superClass.equals(type)){
                instanceList.add(instance);
            }
        });

        return instanceList;
    }

    @Override
    public List<Instance> getInstanceByInterface(Class type) {
        List<Instance> instanceList = new ArrayList<>();
        instances.forEach(instance -> {
            List<Class> classList = Arrays.asList(instance.getInstance().getClass().getInterfaces());
            if(classList.contains(type)){
                instanceList.add(instance);
            }
        });

        return instanceList;
    }

    @Override
    public List<Instance> getInstanceByAnnotation(Class<? extends Annotation> annotation) {
        List<Instance> instanceList = new ArrayList<>();
        instances.forEach(instance -> {
            if(ReflexionUtils.isAnnotatedBy(instance.getInstance().getClass(), annotation)){
                instanceList.add(instance);
            }
        });

        return instanceList;
    }

    @Override
    public List<Instance> getInstanceByTypeFilter(ITypeFilter typeFilter) {
        List<Instance> instanceList = new ArrayList<>();
        instances.forEach(instance -> {
            Class classe = instance.getInstance().getClass();
            if(typeFilter.isAdmissible(classe)){
                instanceList.add(instance);
            }
        });

        return instanceList;
    }

    @Override
    public <T> List<T> getObjectBySuperType(Class type) {

        return (List<T>) getObjects(getInstanceBySuperType(type));
    }

    @Override
    public <T> List<T> getObjectByInterface(Class<T> type) {
        return (List<T>) getObjects(getInstanceByInterface(type));
    }

    @Override
    public List<Object> getObjectByAnnotation(Class<? extends Annotation> annotation) {
        return getObjects(getInstanceByAnnotation(annotation));
    }

    @Override
    public List<Object> getObjectByTypeFilter(ITypeFilter typeFilter) {
        return getObjects(getInstanceByTypeFilter(typeFilter));
    }
}
