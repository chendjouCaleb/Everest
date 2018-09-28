package org.everest.core.dic.service;

import org.everest.core.dic.Instance;
import org.everest.core.dic.contract.IContainerService;
import org.everest.utils.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ContainerService implements IContainerService {


    public void setInstanceKey(List<? extends Instance> instances) {
        instances.forEach(instance -> {
            String key = StringUtils.lowerFist(instance.getRegisteredType().getSimpleName());
            if (!keyIsUsed(key, instances)) {
                instance.setKey(key);
            } else {
                instance.setKey(key + "#" + countInstanceByKey(key, instances));
            }
        });
    }

    boolean keyIsUsed(String key, List<? extends Instance> instances) {
        for (Instance instance : instances) {

            if (instance.getKey() != null && instance.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    int countInstanceByKey(String key, List<? extends Instance> instances) {
        int counter = 0;
        for (Instance instance : instances) {
            if (instance.getKey()!= null && instance.getKey().equals(key)) {
                counter++;
            }
        }
        return counter;
    }

    private String getKey(Class<?> className) {
        String name = className.getSimpleName();
        name = name.substring(0, 1).toLowerCase() + name.substring(1, name.length());
        return name;
    }

    private int countInstanceWithClass(Class className, Map<String, Instance> instances) {
        int count = 0;
        Collection<Instance> ins = instances.values();
        for (Instance instance : ins) {
            if (instance.getRegisteredType().equals(className)) {
                count++;
            }
        }
        return count;
    }
}
