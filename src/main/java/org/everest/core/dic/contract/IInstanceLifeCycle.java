package org.everest.core.dic.contract;

import org.everest.core.dic.Instance;
import org.everest.core.dic.enumeration.Scope;

public interface IInstanceLifeCycle {
    Instance createInstance(Object obj,  Scope scope);
    Instance destroyInstance(Instance instance);
}
