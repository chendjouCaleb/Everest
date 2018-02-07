package org.everest.main;

import org.everest.main.component.http.ErrorHandler;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractApp {
    public abstract Class[] getControllers();
    public Object[] getServiceInstances(){
        return null;
    }

    public List<Class> getEventListenerClass(){
        return null;
    }
    List<Class> getPrimaryEventListenerClass(){
        ArrayList<Class> classes = new ArrayList<>();
        classes.add(LifeCycleListener.class);

        return classes;
    }

    public List<Class> getAppEventListeners(){
        List<Class> classes = getPrimaryEventListenerClass();
        if(getEventListenerClass() != null){
            classes.addAll(getEventListenerClass());
        }
        return classes;
    }

    public abstract List<ErrorHandler>getErrorHandlers();

    public AbstractApp(){
        System.out.println("App Configuration was initialized");
    }
}
