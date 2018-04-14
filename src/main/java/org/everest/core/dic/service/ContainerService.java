package org.everest.core.dic.service;

import org.everest.core.dic.Instance;
import org.everest.core.dic.contract.IContainerService;
import org.everest.core.dic.contract.IRetrieverService;
import org.everest.core.dic.decorator.AutoWired;
import org.everest.core.dic.exception.ComponentCreationException;
import org.everest.exception.InstanceNotFoundException;

import java.lang.reflect.Field;
import java.util.*;

public class ContainerService implements IContainerService {

    @Override
    public void setInstanceKey(Instance instance, String key, Map<String, Instance> instances) {
        if (key != null) {
            if (instances.containsKey(key)) {
                String message = "Error during the adding the component of class '" +
                        instance.getInstance().getClass() + "'. " +
                        "The key '" + key + "' is already used by another component : '"+
                        instances.get(key).getType() + "'.";

                throw new ComponentCreationException(message);
            }
            instance.setKey(key);
        } else {

            Class className = instance.getInstance().getClass();

            if (countInstanceWithClass(className, instances) == 0) {
                instance.setKey(getKey(className));
            } else {
                instance.setKey(getKey(className) + "#" + countInstanceWithClass(className, instances));
            }
            //System.out.println("Instance: " + instance.getType() + "; Key: " + instance.getKey());
        }

    }

    private String getKey(Class<?> className) {
        String name = className.getSimpleName();
        name = name.substring(0, 1).toLowerCase() + name.substring(1, name.length());
        return name;
    }

    @Override
    public Instance getInstance(Class<?> clazz, Map<String, Instance> instances) {
        return null;
    }

    @Override
    public Instance getInstance(String name, Map<String, Instance> instances) {
        return null;
    }

    @Override
    public Set<String> getDependencies(IRetrieverService retrieverService, Instance instance, Map<String, Instance> instances) {
        Set<String> dependencies = new HashSet<>();

        Field[] fields = instance.getType().getDeclaredFields();

        for (Field field: fields){
            field.setAccessible(true);
            AutoWired autoWired = field.getAnnotation(AutoWired.class);
            if(autoWired != null){
                if(autoWired.qualifier().equals("")){
                    try{
                        Instance dependency = retrieverService.getInstance(field.getType(), instances);
                        dependencies.add(dependency.getKey());
                    }catch (InstanceNotFoundException e){
                        System.out.println("Error during the resolving the field " + field.getName() +
                                " of " + instance.getType());
                        throw new InstanceNotFoundException(e);
                    }

                }else {
                    dependencies.add(autoWired.qualifier());
                }
            }
        }
        return dependencies;
    }

    private int countInstanceWithClass(Class className, Map<String, Instance> instances) {
        int count = 0;
        Collection<Instance> ins = instances.values();
        for (Instance instance : ins) {
            if (instance.getType().equals(className)) {
                count++;
            }
        }
        return count;
    }
}
