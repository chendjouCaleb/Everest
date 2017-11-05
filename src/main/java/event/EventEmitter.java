package event;

import main.Utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class EventEmitter {
    private static EventEmitter instance;
    private Listener actualListener;
    private Method actualMethod;
    private List<Listener> listeners = new ArrayList<>();


    private EventEmitter(Class... listeners){
        for (Class listenerClass:listeners){
            Listener listener = null;
            try {
                listener = (Listener) listenerClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    throw new EventException("Erreur lors de l'instanciation de la classe listener: ");
                } catch (EventException e1) {
                    e1.printStackTrace();
                }
            }
            if(!this.listeners.contains(listener)){
                this.listeners.add(listener);
            }
        }
    }


    public static EventEmitter getInstance(Class... listeners) {
        if(instance == null){
            instance = new EventEmitter(listeners);
        }
        return instance;
    }

    public void emit(String event, Object... args){
        setCalledListener(event);

        if(this.actualMethod != null){
            try {
                Utils.callRemote(actualListener, actualMethod.getName(), args);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            try {
                throw new EventException("Aucun écouteur pour l'évènement: " + event);
            } catch (EventException e) {
                e.printStackTrace();
            }
        }
    }

    public void setCalledListener(String event){
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
    }
}
