package org.everest.core.dic.contract;

import org.everest.core.dic.Instance;

import java.util.Map;

public interface IRetrieverService {
    Instance getInstance(String key, Map<String, Instance> instances);
    Instance getInstance(Class clazz, Map<String, Instance> instances);
}
