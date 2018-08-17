package org.everest.core.dic.contract;

import org.everest.core.dic.Instance;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IContainerService {
    void setInstanceKey(List<? extends Instance> instances);

}
