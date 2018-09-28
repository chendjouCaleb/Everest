package org.everest.mvc.actionResultHandler;

import org.everest.core.event.Event;
import org.everest.core.event.EventEmitter;
import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.infrastructure.StaticContext;


public class EventResponseHandler implements IResultHandler<Event> {
    private EventEmitter eventEmitter;

    public EventResponseHandler(EventEmitter eventEmitter) {
        this.eventEmitter = eventEmitter;
    }

    @Override
    public void handleResponse(HttpContext context, Event event) {
       eventEmitter.emit(event.getName(), event.getParams());
    }

    @Override
    public Class<?> getType() {
        return Event.class;
    }
}
