package org.everest.core.dic.contract;

public interface IDependencyResolver {
    void resolveInstance();
    void setParentToFactoryInstance();
}
