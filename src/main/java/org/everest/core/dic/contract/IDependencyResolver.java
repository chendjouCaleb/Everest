package org.everest.core.dic.contract;

import org.everest.core.dic.Instance;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IDependencyResolver {
    void resolveInjection(IRetrieverService retrieverService, Instance instance, Map<String, Instance> instances,Collection<String> dependencies);
    void resolveMethodInjection(Instance instance, List<String> dependencyKeys);
    void resolveConstructorInjection(Instance instance, List<String> dependencyKeys);
}
