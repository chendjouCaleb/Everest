package org.everest.mvc.actionResultHandler;

import org.everest.core.event.Event;
import org.everest.core.event.EventEmitter;
import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.infrastructure.StaticContext;


public class EventResponseHandler implements IResultHandler<Event> {
    @Override
    public void handleResponse(HttpContext context, Event event) {
        EventEmitter eventEmitter = StaticContext.context.getInstance(EventEmitter.class);
       eventEmitter.emit(event.getName(), event.getParams());
    }

    @Override
    public Class<?> getType() {
        return Event.class;
    }
}
