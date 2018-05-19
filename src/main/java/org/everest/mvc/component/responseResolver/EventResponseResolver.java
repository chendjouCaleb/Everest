package org.everest.mvc.component.responseResolver;

import org.everest.core.event.Event;
import org.everest.core.event.EventEmitter;
import org.everest.main.StaticContext;
import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;


public class EventResponseResolver implements IResponseResolver<Event>{
    @Override
    public void handleResponse(Request request, Response response, Event event) {
        EventEmitter eventEmitter = StaticContext.context.getInstance(EventEmitter.class);
       eventEmitter.emit(event.getName(), event.getParams());
    }

    @Override
    public Class<?> getType() {
        return Event.class;
    }
}
