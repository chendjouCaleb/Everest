package org.everest.core.dic.service;

import org.everest.core.dic.Instance;
import org.everest.utils.Assert;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class InstanceValidator {
    public void checkDependenciesAvailability(List<Instance> instances){
        List<Class> candidateClasses = instances.stream().map(Instance::getRegisteredType).collect(Collectors.toList());
        instances.forEach(instance -> {
            instance.getDependencies().forEach(type -> {
                if(!candidateClasses.contains(type)){
                    throw new NoSuchElementException("La dépendance '" + type + "' de la classe '"
                            + instance.getRegisteredType() + "' n'a pas été enregistré comme dépendances");
                }
            });
        });
    }

    public void checkCircularDependencies(List<Instance> instances){
        instances.forEach(this::checkAutoDependency);
        instances.forEach(instance -> {
            instance.getDependencies().forEach(dependency -> {
                Instance instance1 = getInstance(dependency, instances);
                if (instance1.getDependencies().contains(instance.getRegisteredType())){
                    throw new IllegalArgumentException("Les instances de type '" + instance.getRegisteredType() + "' et '" +
                            instance1.getRegisteredType() + "' sont en dépendances circulaires");
                }
            });
        });
    }
    Instance getInstance(Class type, List<Instance> instances){
        return instances.stream().filter(instance ->
                instance.getRegisteredType().equals(type)
        ).findFirst().orElse(null);
    }
    public void checkAutoDependency(Instance instance){
        Assert.isFalse(instance.getDependencies().contains(instance.getRegisteredType()), "L'instance de type '" + instance.getRegisteredType() +
                "' est dépendante sur elle meme");
    }

}
