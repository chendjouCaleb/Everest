package org.everest.context;


import org.everest.context.classHandler.ClassHandler;
import org.everest.context.classHandler.ComponentHandler;
import org.everest.context.classHandler.FactoryHandler;
import org.everest.context.classHandler.InstanceHandler;
import org.everest.core.dic.Container;
import org.everest.core.dic.Instance;
import org.everest.utils.ReflexionUtils;

import java.lang.annotation.Annotation;
import java.util.*;

public class ApplicationContext {
    private Container container;
    private Map<Class<?> , ClassHandler> classHandlers = new HashMap<>();
    private DependencyService dependencyService = new DependencyService();
    public ApplicationContext(){
        container = new Container();
        addClassHandler(new ComponentHandler());
        addClassHandler(new InstanceHandler());
        addClassHandler(new FactoryHandler());
    }

    public void addByPackage(String packageName){
        dependencyService.addDependenciesByPackage(packageName, this);
    }
    public void addByPackages(String[] packageNames){

        dependencyService.addDependenciesByPackages(packageNames, this);
    }
    public void addClassHandler(ClassHandler classHandler){
        classHandlers.put(classHandler.getClass(), classHandler);
    }
    public void printComponent(){
        container.printAllComponent();
    }
    public void addInstance(Class<?> clazz, String key){
        container.addInstance(ReflexionUtils.instantiateClass(clazz), key);
    }
    public void addInstance(Class<?> clazz){
        container.addInstance(ReflexionUtils.instantiateClass(clazz));
    }

    public void addInstance(Object object, String key){
        container.addInstance(object, key);
    }

    public void addInstance(Object object){
        container.addInstance(object);
    }

    public int countDependencies(){
        return container.countInstances();
    }

    public <T> T getInstance(String key, Class<? extends T> clazz){
        return (T) container.getInstance(key).getInstance();
    }

    public List<Object> findInstanceByAnnotation(Class<? extends Annotation> annotation){
        List<Object> instances = new ArrayList<>();
        for (Instance instance : container.getInstances().values()){
            Annotation annotation1 = instance.getInstance().getClass().getAnnotation(annotation);
            if (annotation1 != null){
                instances.add(instance.getInstance());
            }
        }
        return instances;
    }

    public <T> T getInstance(Class<? extends T> clazz){
        return (T) container.getInstance(clazz).getInstance();
    }

    public void initialize(){
        container.resolveAllDependencies();
    }

    Map<Class<?>, ClassHandler> getClassHandlers() {
        return classHandlers;
    }
}
