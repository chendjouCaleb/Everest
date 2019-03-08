package org.everest.mvc.service;

import org.everest.mvc.binding.IModelBinder;
import Everest.Http.MediaType;
import Everest.Http.HttpContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class DefaultRequestBodyHandler implements IRequestBodyHandler{

    private IModelBinder modelBinder;

    public DefaultRequestBodyHandler(IModelBinder modelBinder) {
        this.modelBinder = modelBinder;
    }

    @Override
    public Object getBody(HttpContext httpContext, Class<?> type) {
        Map<String, ?> map = httpContext.getRequest().forms();
        return modelBinder.bind(type,map );
    }

    @Override
    public Collection<String> getMediaTypes() {
        Collection<String> medias = new ArrayList<>();
        medias.add(MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        medias.add(MediaType.MULTIPART_FORM_DATA_VALUE);
        return medias;
    }
}
