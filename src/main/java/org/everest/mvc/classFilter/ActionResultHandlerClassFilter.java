package org.everest.mvc.classFilter;

import org.everest.core.dic.handler.ITypeFilter;
import org.everest.mvc.actionResultHandler.ActionResultHandler;
import org.everest.mvc.actionResultHandler.IResultHandler;
import org.everest.mvc.filter.IFilter;

import java.util.Arrays;

public class ActionResultHandlerClassFilter implements ITypeFilter {
    @Override
    public boolean isAdmissible(Class type) {
        return Arrays.asList(type.getInterfaces()).contains(IResultHandler.class);
    }
}
