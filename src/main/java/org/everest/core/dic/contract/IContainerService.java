package org.everest.core.dic.contract;

import org.everest.core.dic.Container;
import org.everest.core.dic.Instance;
import org.everest.core.dic.service.RetrieverService;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IContainerService {
    void setInstanceKey(Instance instance, String key, Map<String, Instance> instances);
    Instance getInstance(Class<?> clazz, Map<String, Instance> instances);
    Instance getInstance(String name, Map<String, Instance> instances);
    Set<String> getDependencies(IRetrieverService retrieverService, Instance instance, Map<String, Instance> instances);

}
