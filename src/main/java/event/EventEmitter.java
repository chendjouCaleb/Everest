package event;

import org.everest.main.Utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventEmitter {
    private static EventEmitter instance;
    private Listener actualListener;
    private Method actualMethod;
    private List<Listener> listeners = new ArrayList<>();

    public void addListener(Listener listener){
        if(!this.listeners.contains(listener)){
            System.out.println(listener.getClass() + " has been added in listeners list");
            this.listeners.add(listener);
        }else{
            System.out.println(listener.getClass() + " is already added in listeners list");
        }
    }

    public void addListeners(List<Listener> listeners){
        for (Listener listener:listeners){
            addListener(listener);
        }
    }
    private EventEmitter(){
    }


    public static EventEmitter getInstance() {
        if(instance == null){
            instance = new EventEmitter();
        }
        return instance;
    }

    public void emit(String event, Object... args){
    for (Map.Entry<Listener, Method> entry: getCalledListeners(event).entrySet())
        try {
            Utils.callRemote(entry.getKey(), entry.getValue().getName(), args);
        } catch (Exception e) {
            try {
                throw new EventException("Error was occurring when : " + entry.getValue().getName() + "has been called for "+ event +
                " event");
            } catch (EventException e1) {
                e1.printStackTrace();
            }
        }
    }
    public Map<Listener, Method> getCalledListeners(String event){
        Map<Listener, Method> listeners = new HashMap<>();
        for (Listener listener: this.listeners){
            OnListen onListen = listener.getClass().getAnnotation(OnListen.class);
            if(onListen != null){
                Method[] methods = listener.getClass().getMethods();
                for (Method method: methods){
                    OnListen methodListen = method.getAnnotation(OnListen.class);
                    if(methodListen != null && event.equals(onListen.value()+"."+methodListen.value()) ){
                        listeners.put(listener, method);
                    }
                }
            }
        }
        return listeners;
    }

    /*public void addCalledListenerMethods(Map<Listener, Method> listeners, Class listener, String event){
        Method[] methods = listener.getMethods();
        for (Method method: methods){
            OnListen onListen = method.getAnnotation(OnListen.class);
            if(onListen != null && onListen.value()){

            }
        }
    }*/

    /*public void setCalledListener(String event){
        actualListener = null;
        actualMethod = null;
        String calledEvent = "";
        for (Listener listener:listeners){
            OnListen classListen = listener.getClass().getAnnotation(OnListen.class);
            if(classListen != null){
                calledEvent = classListen.value() + ".";
            }

            for (Method method: listener.getClass().getDeclaredMethods()){
                OnListen methodListen = method.getAnnotation(OnListen.class);
                if(methodListen != null){
                    calledEvent += methodListen.value();
                }

                if(calledEvent.equals(event)){
                    actualListener = (listener.getClass().cast(listener));
                    actualMethod =  method;
                }
            }
        }
    }*/
}
