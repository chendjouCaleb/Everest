package org.everest.mvc.service;

import org.everest.mvc.http.MediaType;
import org.everest.mvc.httpContext.HttpContext;
import org.everest.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class RequestBodyHandler {
    private Logger logger = LoggerFactory.getLogger(RequestBodyHandler.class);
    private Map<String, IRequestBodyHandler> handlers = new HashMap<>();

    public RequestBodyHandler() {
        IRequestBodyHandler handler = new DefaultRequestBodyHandler();
        IRequestBodyHandler jsonHandler = new JsonBodyHandler();
        addHandler(MediaType.APPLICATION_FORM_URLENCODED_VALUE, handler);
        addHandler(MediaType.MULTIPART_FORM_DATA_VALUE, handler);
        addHandler(MediaType.APPLICATION_JSON_VALUE, jsonHandler);
        addHandler(MediaType.APPLICATION_JSON_UTF8_VALUE, jsonHandler);

    }

    public Map<String, IRequestBodyHandler> getHandlers() {
        return handlers;
    }

    public void addHandler(String mediaType, IRequestBodyHandler handler) {
        this.handlers.put(mediaType, handler);
    }

    public <T> T getBody(HttpContext httpContext, Class<T> type){
        String contentType = httpContext.getRequest().getContentType();
        IRequestBodyHandler handler = handlers.get(contentType);
        Assert.notNull(handler, "Aucun extracteur trouvé pour le média: " + contentType);
        logger.info("Media Type: " + contentType);
        logger.info("Handler: " + handler.getClass().getSimpleName());
        Object body = handler.getBody(httpContext, type);
        logger.info("Request body: {}", body.toString());
        return (T) body;
    }
}
