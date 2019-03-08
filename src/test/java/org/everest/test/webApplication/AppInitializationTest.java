package org.everest.test.webApplication;

import org.everest.core.event.EventEmitter;
import org.everest.mvc.infrastructure.WebApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AppInitializationTest {
    private WebApplication application;

    @BeforeEach
    public void initApp(){
        application = new WebApplication();

        application.init("org.everest.test.webApplication.AppInitializer");
    }

    @Test
    public void addControllerTest(){
        Assertions.assertTrue(!application.getContext().getControllers().isEmpty());
        Assertions.assertEquals(1,application.getContext().getControllers().size());
    }

    @Test
    public void addListenersTest(){
        Assertions.assertTrue(!application.getContext().getEventListeners().isEmpty());
        Assertions.assertEquals(4,application.getContext().getEventListeners().size());
    }

    @Test
    public void addErrorHandlersTest(){
        Assertions.assertTrue(!application.getContext().getErrorHandlers().isEmpty());
        Assertions.assertEquals(6,application.getContext().getErrorHandlers().size());
        System.out.println(application.getContext().getErrorHandlers());
    }

    @Test
    public void eventEmitterTest(){
        System.out.println(application.getContext().getContainer().getRetrieverService().getInstance(EventEmitter.class));
    }
}
