package org.everest.mvc.infrastructure;

import org.everest.mvc.controller.UserController;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class RouteBuilderTest {
    private Logger logger = LoggerFactory.getLogger(RouteBuilderTest.class);
    private RouteLoader routeLoader = new RouteLoader();
    private UserController controller = new UserController();
    private RouteBuilder routeBuilder = new RouteBuilder();
    private Map<String, String> params = new HashMap<>();
    @Before
    public void setUp() throws Exception {
        List list = new ArrayList();
        list.add(controller);
        routeLoader.load(list);
        routeBuilder.setRouteModels(routeLoader.getRoutes());
        params.put("id", "10");
    }

    @Test
    public void url() throws Exception {
       routeLoader.getRoutes().forEach(routeModel -> logger.info(routeModel.getName()));

        String url = routeBuilder.url("User.GetById", params);
        assertEquals("/user/10", url);
    }
    @Test
    public void url_Without_verbs(){
        assertEquals("/user", routeBuilder.url("User.options"));
    }

    @Test
    public void url_With_Tab_params(){
        assertEquals("/user/10", routeBuilder.url("User.GetById", new String[]{"10"}));
    }
    @Test
    public void url_With_Tab_Object(){
        assertEquals("/user/10", routeBuilder.url("User.GetById", 10));
    }

    @Test
    public void url_Without_params(){
        assertEquals("/user", routeBuilder.url("User.Add"));
    }

}