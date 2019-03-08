package org.everest.mvc.service;

import org.everest.decorator.Instance;
import Everest.Http.MediaType;
import Everest.Http.HttpContext;
import org.everest.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@Instance
public class RequestBodyHandler {
    private Logger logger = LoggerFactory.getLogger(RequestBodyHandler.class);
    private Map<String, IRequestBodyHandler> handlers = new HashMap<>();

    public RequestBodyHandler() {
    }

    public Map<String, IRequestBodyHandler> getHandlers() {
        return handlers;
    }

    public void addHandler(IRequestBodyHandler handler) {
        handler.getMediaTypes().forEach(media -> handlers.put(media, handler));
    }

    public <T> T getBody(HttpContext httpContext, Class<T> type) {
        String contentType = httpContext.getRequest().contentType();
        IRequestBodyHandler handler = handlers.get(contentType);
        if(httpContext.getRequest().method().equals("GET")){
            handler = handlers.get(MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        }
        Assert.notNull(handler, "Aucun extracteur trouvé pour le média: " + contentType);
        logger.info("Media Type: " + contentType);
        logger.info("Handler: " + handler.getClass().getSimpleName());
        Object body = handler.getBody(httpContext, type);
        logger.info("HttpRequest setBody: {}", body.toString());
        return (T) body;
    }
}
