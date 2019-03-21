package org.everest.core.dic.service;

import org.everest.core.dic.Instance;
import org.everest.core.dic.contract.IInstanceLifeCycle;
import org.everest.core.dic.enumeration.Scope;
import org.everest.core.dic.exception.ComponentCreationException;

public class InstanceLifeCycle implements IInstanceLifeCycle {

    @Override
    public Instance createInstance(Object obj,  Scope scope) {
        if (obj == null){
            throw new ComponentCreationException("Cannot add null object in Container");
        }

        Instance instance = new Instance();
        instance.setRegisteredType(obj.getClass());
        instance.setScope(scope);
        instance.setInstance(obj);

        return instance;
    }

    @Override
    public Instance destroyInstance(Instance instance) {
        return null;
    }
}
