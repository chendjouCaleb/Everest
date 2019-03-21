package org.everest.mvc.service;

import Everest.Core.Exception.InputOutputException;
import Everest.Http.MediaType;
import Everest.Http.HttpContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JsonBodyHandler implements IRequestBodyHandler {
    private JsonConverter service;

    public JsonBodyHandler(JsonConverter service) {
        this.service = service;
    }

    @Override
    public Object getBody(HttpContext httpContext, Class<?> type) {
        BufferedReader reader = httpContext.getRequest().reader();
        StringBuilder builder = new StringBuilder();
        String line;
            try {
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                reader.close();
            } catch (IOException e) {
                throw new InputOutputException(e);
            }

        String value = builder.toString();
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
