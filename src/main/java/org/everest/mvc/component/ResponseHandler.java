package org.everest.mvc.component;

import org.everest.core.event.Event;
import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    private Map<Class<?>, IResponseResolver<?>> resolvers = new HashMap<>();
    public ResponseHandler(){
        addResponseResolvers(String.class, new StringResponseResolver());
        addResponseResolvers(Event.class, new EventResponseResolver());
    }
    public void addResponseResolvers(Class<?> type, IResponseResolver<?> resolver){
        resolvers.put(type, resolver);
    }

    public void handleResponse(Request request, Response response, Object result){
        Class type = result.getClass();
        IResponseResolver resolver = resolvers.get(type);
        resolver.handleResponse(request, response, result);
    }

}

