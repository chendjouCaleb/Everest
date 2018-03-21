package org.everest.core.event;

import org.everest.core.OnListen;
import org.everest.main.Utils;

import java.lang.reflect.Method;
import java.util.*;

public class EventEmitter {
    private static EventEmitter instance;
    private List<Object> listeners = new ArrayList<>();

    public void addListener(Object listener) {
        if (!this.listeners.contains(listener)) {
            //System.out.println(listener.getClass() + " has been added in listeners list");
            this.listeners.add(listener);
        } else {
            //System.out.println(listener.getClass() + " is already added in listeners list");
        }
    }

    public void addListeners(Collection<Object> listeners) {
        for (Object listener : listeners) {
            addListener(listener);
        }
    }

    private EventEmitter() {
    }


    public static EventEmitter getInstance() {
        if (instance == null) {
            instance = new EventEmitter();
        }
        return instance;
    }

    public void emit(String event, Object... args) {
        System.out.println("New event Was been emitted: " + event);
        for (Map.Entry<Object, Method> entry : getCalledListeners(event).entrySet())
            try {
                System.out.println("Listener execution: " + entry.getKey() + " : " + entry.getValue().getName());
                Utils.callRemote(entry.getKey(), entry.getValue().getName(), args);
            } catch (Exception e) {
                e.printStackTrace();
                throw new EventException("Error was occurring when : " + entry.getValue().getName() + " has been called for " + event);

            }
    }

    private Map<Object, Method> getCalledListeners(String event) {
        Map<Object, Method> listeners = new HashMap<>();
        for (Object listener : this.listeners) {
            OnListen onListen = listener.getClass().getAnnotation(OnListen.class);
            if (onListen != null) {
                Method[] methods = listener.getClass().getMethods();
                for (Method method : methods) {
                    OnListen methodListen = method.getAnnotation(OnListen.class);
                    if (methodListen != null && event.equals(onListen.value() + "." + methodListen.value())) {
                        listeners.put(listener, method);
                    }
                }
            }
        }
        return listeners;
    }
}
