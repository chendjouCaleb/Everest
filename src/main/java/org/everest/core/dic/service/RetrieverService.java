package org.everest.core.dic.service;

import org.everest.core.dic.Instance;
import org.everest.core.dic.contract.IRetrieverService;
import org.everest.core.dic.enumeration.Scope;
import org.everest.core.dic.exception.ComponentException;
import org.everest.exception.InstanceNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RetrieverService implements IRetrieverService{
    @Override
    public Instance getInstance(String key, Map<String, Instance> instances) {
        if(instances.containsKey(key)){
            Instance instance = instances.get(key);
            if(instance.getScope().equals(Scope.NEW)){
                instance.setInstance(null);
            }
            return instance;
        }else {
            throw new InstanceNotFoundException("Instance with key '" + key + "' not found");
        }
    }

    @Override
    public Instance getInstance(Class clazz, Map<String, Instance> instances) {
        Instance instance = null;
        if(getInstanceWithClass(clazz, instances) != null){
            instance =  getInstanceWithClass(clazz, instances);
        }else if(getInstanceWithSuperClass(clazz, instances) != null){

            instance =  getInstanceWithSuperClass(clazz, instances);
        }else if(getInstanceWithInterface(clazz, instances) != null){
            instance =  getInstanceWithInterface(clazz, instances);
        }else {
            throw new InstanceNotFoundException("Nothing instance for a class'" + clazz.getName() + "' found");
        }
//        if(instance.getScope().equals(Scope.NEW)){
//            instance.setInstance(null);
//        }
        return instance;
    }

    private Instance getInstanceWithClass(Class className, Map<String, Instance> instances){
        Set<String> keys = instances.keySet();
        for (String key: keys){
            if (instances.get(key).getType().equals(className)){
                return instances.get(key);
            }
        }
        return null;
    }
    private Instance getInstanceWithSuperClass(Class ClassName, Map<String, Instance> instances){
        Set<String> keys = instances.keySet();
        for (String key: keys){
            if (instances.get(key).getType().getSuperclass().equals(ClassName)){
                return instances.get(key);
            }
        }
        return null;
    }
    private Instance getInstanceWithInterface(Class<?> className, Map<String, Instance> instances){

        Set<String> keys = instances.keySet();
        for (String key: keys){
            Instance instance = instances.get(key);
            List<Class> interfaces =  Arrays.asList(instance.getType().getInterfaces());
            if(interfaces.contains(className)){
                return instance;
            }
        }
        return null;
    }
}
