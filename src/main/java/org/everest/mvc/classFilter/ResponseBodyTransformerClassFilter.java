package org.everest.mvc.classFilter;

import org.everest.core.dic.handler.ITypeFilter;
import org.everest.mvc.responseBody.IResponseBodyTransformer;
import org.everest.mvc.service.IRequestBodyHandler;

import java.util.Arrays;

public class ResponseBodyTransformerClassFilter implements ITypeFilter {
    @Override
    public boolean isAdmissible(Class type) {
        return Arrays.asList(type.getInterfaces()).contains(IResponseBodyTransformer.class);
    }
}
