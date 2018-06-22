package org.everest.mvc.infrastructure;

import org.everest.component.form.FormService;
import org.everest.context.ApplicationContext;

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
