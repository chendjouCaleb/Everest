package org.everest.core.dic.contract;

import org.everest.core.dic.FactoryInstance;
import org.everest.core.dic.Instance;
import org.everest.core.dic.TypeInstance;

import java.util.List;
import java.util.Map;

public interface IRetrieverService {
    Instance getInstance(String key);
    Instance getInstance(Class clazz);
    Instance getByConcreteType(Class clazz);
    Instance getInstanceByRegisteredOrConcreteType(Class clazz);

    List<Instance> getInstances(List<Class> classes);

    List<Object> getObjects(List<Instance> instances);
    List<Object> getObjectByRegisteredType(List<Class> instances);

    List<FactoryInstance> getFactoryInstance();
    List<TypeInstance> getTypesInstances();
    List<Instance> getInstances();
}
