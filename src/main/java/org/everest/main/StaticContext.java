package org.everest.main;

import org.everest.component.form.FormService;
import org.everest.context.ApplicationContext;
import org.everest.main.ApplicationInitializer;

public class StaticContext {
    public static ApplicationContext context = null;
    public static ApplicationInitializer applicationInitializer = null;

    public static void setContext(ApplicationContext ctx){
        context = ctx;
    }

    public static void addComponent(){
        context.addInstance(new FormService());
    }
}
