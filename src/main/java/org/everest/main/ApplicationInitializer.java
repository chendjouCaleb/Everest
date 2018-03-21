package org.everest.main;

import org.everest.main.component.errorHandler.IErrorHandler;

import java.util.ArrayList;
import java.util.List;

public abstract class ApplicationInitializer {

    public abstract String[] getBasePackages();

    public abstract String getAppBaseUrl();
    List<Class> getPrimaryEventListenerClass(){
        ArrayList<Class> classes = new ArrayList<>();
        classes.add(LifeCycleListener.class);
        return classes;
    }
    public ApplicationInitializer(){
        System.out.println("App Configuration was initialized");
    }
}
