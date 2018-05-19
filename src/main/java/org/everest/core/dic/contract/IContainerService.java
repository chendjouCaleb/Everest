package org.everest.core.dic.contract;

import org.everest.core.dic.Instance;

import java.util.Map;
import java.util.Set;

public interface IContainerService {
    void setInstanceKey(Instance instance, String key, Map<String, Instance> instances);
    Instance getInstance(Class<?> clazz, Map<String, Instance> instances);
    Instance getInstance(String name, Map<String, Instance> instances);
    Set<String> getDependencies(IRetrieverService retrieverService, Instance instance, Map<String, Instance> instances);

}
