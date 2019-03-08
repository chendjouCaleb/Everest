package org.everest.mvc.classFilter;

import org.everest.core.dic.handler.ITypeFilter;
import Everest.Mvc.ExceptionHandler.IExceptionHandler;

import java.util.Arrays;

public class ExceptionHandlerClassFilter implements ITypeFilter {
    @Override
    public boolean isAdmissible(Class type) {
        return Arrays.asList(type.getInterfaces()).contains(IExceptionHandler.class);
    }
}
