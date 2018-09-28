package org.everest.test.webApplication;

import org.everest.core.event.EventEmitter;
import org.everest.mvc.infrastructure.WebApplication;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AppInitializationTest {
    private WebApplication application;
    @Before
    public void initApp(){
        application = new WebApplication();

        application.init("org.everest.test.webApplication.AppInitializer");
    }

    @Test
    public void addControllerTest(){
        Assert.assertTrue(!application.getContext().getControllers().isEmpty());
        Assert.assertEquals(1,application.getContext().getControllers().size());
    }

    @Test
    public void addListenersTest(){
        Assert.assertTrue(!application.getContext().getEventListeners().isEmpty());
        Assert.assertEquals(4,application.getContext().getEventListeners().size());
    }

    @Test
    public void addErrorHandlersTest(){
        Assert.assertTrue(!application.getContext().getErrorHandlers().isEmpty());
        Assert.assertEquals(6,application.getContext().getErrorHandlers().size());
        System.out.println(application.getContext().getErrorHandlers());
    }

    @Test
    public void eventEmitterTest(){
        System.out.println(application.getContext().getContainer().getRetrieverService().getInstance(EventEmitter.class));
    }
}
