package org.everest.core.dic;

import org.everest.core.dic.contract.*;
import org.everest.core.dic.enumeration.Scope;
import org.everest.core.dic.service.*;

import java.util.*;

public class Container {

    private Map<String, Instance> instances = new HashMap<>();
    private IInstanceLifeCycle instanceLifeCycle = new InstanceLifeCycle();
    private IContainerService containerService = new ContainerService();
    private IDependencyResolver injectionService = new IDependencyResolverImpl();
    private IRetrieverService retrieverService = new RetrieverService();

    public void addInstance(Class clazz, String key, Scope scope){
        Object object = DicUtils.instanciateClass(clazz);
        addInstance(object, key, scope);
    }

    public void addInstance(Class clazz, Scope scope){
        addInstance(clazz, null, scope);
    }

    public void addInstance(Class clazz) {addInstance(clazz, Scope.SINGLETION);}

    public void addInstance(Object object, String key) { addInstance(object, key, Scope.SINGLETION);}
    public void addInstance(Object object){ addInstance(object, null); }
    private void addInstance(Object object, String key, Scope scope){
        Instance instance =  instanceLifeCycle.createInstance(object, scope);
        containerService.setInstanceKey(instance, key, instances);
        instances.put(instance.getKey(), instance);
    }

    public Instance getInstance(String key){
        return retrieverService.getInstance(key, instances);
    }

    public Instance getInstance(Class clazz){
        return retrieverService.getInstance(clazz, instances);
    }

    public void resolveDependencies(Instance instance){
        Set<String> dependencies = containerService.getDependencies(retrieverService, instance, instances);
        injectionService.resolveInjection(retrieverService, instance, instances, dependencies);
    }

    public void resolveAllDependencies(){
        Collection<Instance> instances = this.instances.values();
        for (Instance instance:instances){
            resolveDependencies(instance);
        }
    }

    public Map<String, Instance> getInstances() {
        return instances;
    }

    public int countInstances(){
        return instances.size();
    }
    public void printAllComponent(){
        instances.forEach((s, instance) -> System.out.println(instance.toString()));
    }
}
