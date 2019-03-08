package org.everest.mvc.service;

import Everest.Http.HttpContext;

import java.util.Collection;

public interface IRequestBodyHandler {
    Object getBody(HttpContext httpContext, Class<?> type);
    Collection<String> getMediaTypes();
}
