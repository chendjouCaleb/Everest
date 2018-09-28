package org.everest.mvc.service;

import org.everest.mvc.http.MediaType;
import org.everest.mvc.httpContext.HttpContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JsonBodyHandler implements IRequestBodyHandler {
    private JSONService service;

    public JsonBodyHandler(JSONService service) {
        this.service = service;
    }

    @Override
    public Object getBody(HttpContext httpContext, Class<?> type) {
        String value = httpContext.getRequest().getBodyString();
        return  service.toObject(value, type);
    }

    @Override
    public Collection<String> getMediaTypes() {
        List<String> medias = new ArrayList<>();
        medias.add(MediaType.APPLICATION_JSON_VALUE);
        medias.add(MediaType.APPLICATION_JSON_UTF8_VALUE);
        return medias;
    }
}
