package org.everest.mvc.classFilter;

import Everest.Middleware.IMiddleware;
import org.everest.core.dic.handler.ITypeFilter;

import java.util.Arrays;

public class MiddlewareClassFilter implements ITypeFilter {
    public boolean isAdmissible(Class type) {
        return Arrays.asList(type.getInterfaces()).contains(IMiddleware.class);
    }
}
