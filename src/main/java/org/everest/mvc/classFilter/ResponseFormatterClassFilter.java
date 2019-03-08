package org.everest.mvc.classFilter;

import org.everest.core.dic.handler.ITypeFilter;
import Everest.Mvc.ResponseFormatter.IResponseFormatter;

import java.util.Arrays;

public class ResponseFormatterClassFilter implements ITypeFilter {
    @Override
    public boolean isAdmissible(Class type) {
        return Arrays.asList(type.getInterfaces()).contains(IResponseFormatter.class);
    }
}
