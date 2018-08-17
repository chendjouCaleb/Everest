package org.everest.context;


import org.everest.context.classHandler.ClassHandler;
import org.everest.context.classHandler.ComponentHandler;
import org.everest.context.classHandler.FactoryHandler;
import org.everest.context.classHandler.InstanceHandler;
import org.everest.core.dic.Container;
import org.everest.core.dic.Instance;
import org.everest.mvc.classHandler.ControllerHandler;
import org.everest.utils.ReflexionUtils;

import java.lang.annotation.Annotation;
import java.util.*;

public class ApplicationContext {
    private Container container;
    private Map<Class<?> , ClassHandler> classHandlers = new HashMap<>();
    public ApplicationContext(){
        container = new Container();
        container.addSingletonObject(this);
        addClassHandler(new ComponentHandler());
        addClassHandler(new InstanceHandler());
        addClassHandler(new FactoryHandler());
    }

    public void addByPackages(String... packageNames){
        container.addPackage(packageNames);
    }
    public void addClassHandler(ClassHandler classHandler){
        classHandlers.put(classHandler.getClass(), classHandler);
    }
    public void printComponent(){
        container.printAllComponent();
    }


    public int countDependencies(){
        return container.countInstances() - 1;
    }

    public <T> T getInstance(String key, Class<? extends T> clazz){
        return (T) container.getInstance(key).getInstance();
    }
    public Object getInstance(String key){
        return container.getInstance(key).getInstance();
    }
    public List<Object> findInstanceByAnnotation(Class<? extends Annotation> annotation){
        List<Object> instances = new ArrayList<>();
        for (Instance instance : container.getInstanceList()){
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

    public void addInstance(Object object){
        container.addSingletonObject(object);
    }

    public void addInstance(Object object, String name){
        container.addSingletonObject(object).setKey(name);
    }

    Map<Class<?>, ClassHandler> getClassHandlers() {
        return classHandlers;
    }

    public void initialize(){
        container.initialize();
    }

    public Container getContainer(){
        return container;
    }
}
