package org.everest.mvc.classFilter;

import org.everest.core.dic.handler.ITypeFilter;
import org.everest.mvc.filter.Filter;

public class RequestFilterClass implements ITypeFilter {
    @Override
    public boolean isAdmissible(Class type) {
        return type.getSuperclass() != null && type.getSuperclass().equals(Filter.class);
    }
}
