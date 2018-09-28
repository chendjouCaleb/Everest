package org.everest.mvc.infrastructure;

import org.everest.core.event.EventEmitter;
import org.everest.decorator.Instance;

@Instance
public class EventEmitterConfiguration {

    @Instance
    public EventEmitter eventEmitter(){
        return EventEmitter.getInstance();
    }
}
