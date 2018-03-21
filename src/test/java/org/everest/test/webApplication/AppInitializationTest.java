package org.everest.test.webApplication;

import org.everest.main.WebApplication;
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
        Assert.assertTrue(!application.getControllers().isEmpty());
        Assert.assertEquals(4,application.getControllers().size());
    }

    @Test
    public void addListenersTest(){
        Assert.assertTrue(!application.getListeners().isEmpty());
        Assert.assertEquals(4,application.getListeners().size());
    }

    @Test
    public void addErrorHandlersTest(){
        Assert.assertTrue(!application.getErrorHandlers().isEmpty());
        Assert.assertEquals(2,application.getErrorHandlers().size());
    }
}
