package org.everest.test.webApplication;

import org.everest.mvc.infrastructure.WebApplication;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.everest.mvc.router.Route;
import org.everest.mvc.router.Router;

public class RouterTest {
    private WebApplication application;
    @Before
    public void initApp(){
        application = new WebApplication();
        application.init("org.everest.test.webApplication.AppInitializer");
    }

    @Test
    public void addROuterTest(){
        Router router = application.getContext().getInstance(Router.class);
        Assert.assertEquals(5, router.countRoutes());
    }

    @Test
    public void getInvokedRouteGETTest(){
        Router router = application.getContext().getInstance(Router.class);
        Route home = router.getCalledRoute("/home", "GET");
        Assert.assertEquals(home.getName(), "home");

        Route init = router.getCalledRoute("/app", "GET");
        Assert.assertEquals(init.getName(), "default");
    }
    @Test
    public void getInvokedRoutePOSTTest(){
        Router router = application.getContext().getInstance(Router.class);
        Route edit = router.getCalledRoute("/edit", "POST");
        Assert.assertEquals(edit.getName(), "edit");
    }

    @Test
    public void getInvokedGETRouteWithParamTest(){
        Router router = application.getContext().getInstance(Router.class);
        Route edit = router.getCalledRoute("/edit/5", "GET");
        System.out.println("URL" + router.htmlLink("édition","edit"));
        Assert.assertEquals(edit.getName(), "editIdGET");
    }

    @Test
    public void getInvokedPOSTRouteWithParamTest(){
        Router router = application.getContext().getInstance(Router.class);
        Route edit = router.getCalledRoute("/edit/5", "POST");
        Assert.assertEquals(edit.getName(), "editId");
    }
}
