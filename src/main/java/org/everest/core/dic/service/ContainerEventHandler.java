package org.everest.core.dic.service;

import org.everest.core.dic.Container;
import org.everest.core.dic.Instance;
import org.everest.core.dic.decorator.AfterContainerInitilized;
import org.everest.core.dic.exception.ContainerInitializationException;
import org.everest.utils.ReflexionUtils;
import org.everest.utils.Utils;

import javax.validation.constraints.Null;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ContainerEventHandler {
    private Container container;

    public ContainerEventHandler(Container container) {
        this.container = container;
    }

    public void executeAfterContainerInitialized(){
        for (Instance instance: container.getInstanceList() ){
            List<Method> methods = ReflexionUtils.findMethodsByAnnotation(instance.getRegisteredType(), AfterContainerInitilized.class);
            for (Method method: methods){
                try {
                    Utils.callRemote(instance.getInstance(), method, container);
                } catch (Exception e) {
                    throw new ContainerInitializationException("Erreur durant l'execution de la méthode " + method.getName(), e);
                }
            }
        }
    }
}
