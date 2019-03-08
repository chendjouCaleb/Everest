package org.everest.mvc.classFilter;

import org.everest.core.dic.handler.ITypeFilter;
import Everest.Mvc.ActionResultExecutor.IResultExecutor;

import java.util.Arrays;

public class ActionResultResultClassFilter implements ITypeFilter {
    @Override
    public boolean isAdmissible(Class type) {
        return Arrays.asList(type.getInterfaces()).contains(IResultExecutor.class);
    }
}
