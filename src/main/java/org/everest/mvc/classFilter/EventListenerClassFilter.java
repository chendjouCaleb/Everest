package org.everest.mvc.classFilter;

import org.everest.core.dic.handler.ITypeFilter;
import org.everest.decorator.Listener;
import org.everest.utils.ReflexionUtils;

public class EventListenerClassFilter implements ITypeFilter{
    @Override
    public boolean isAdmissible(Class type) {
        return ReflexionUtils.isAnnotatedBy(type, Listener.class);
    }
}
