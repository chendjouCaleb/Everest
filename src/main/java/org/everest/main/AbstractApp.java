package org.everest.main;

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
        List<Class> classes = getEventListenerClass();
        classes.addAll(getPrimaryEventListenerClass());
        return classes;
    }

    public AbstractApp(){
        System.out.println("App Configuration was initialized");
    }
}
