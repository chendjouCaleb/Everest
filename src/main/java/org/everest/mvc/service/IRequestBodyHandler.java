package org.everest.mvc.service;

import org.everest.mvc.httpContext.HttpContext;

import java.util.Collection;

public interface IRequestBodyHandler {
    Object getBody(HttpContext httpContext, Class<?> type);
    Collection<String> getMediaTypes();
}
