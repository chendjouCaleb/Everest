package org.everest.mvc.classFilter;

import org.everest.core.dic.handler.ITypeFilter;
import org.everest.mvc.errorHandler.IErrorHandler;

import java.util.Arrays;

public class ErrorManagerClassFilter implements ITypeFilter {
    @Override
    public boolean isAdmissible(Class type) {
        return Arrays.asList(type.getInterfaces()).contains(IErrorHandler.class);
    }
}
