package org.everest.mvc.infrastructure;

import org.everest.mvc.controller.UserController;
import org.everest.mvc.decorator.HttpMapping;
import Everest.Http.HttpMethod;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RouteLoaderTest {
    private RouteLoader routeLoader = new RouteLoader();
    private UserController controller = new UserController();

    @Test
    public void Load(){
        List list = new ArrayList();
        list.add(controller);
        routeLoader.load(list);
        assertEquals(7, routeLoader.getRoutes().size());
        assertEquals(1, routeLoader.getControllers().size());
    }

    @Test
    public void loadController() throws Exception {
        HttpMapping httpMapping = controller.getClass().getAnnotation(HttpMapping.class);
        ControllerModel model = routeLoader.loadController(controller);
        assertEquals("/user", model.getMapping());
        assertEquals("User", model.getName());
        assertEquals(httpMapping, model.getHttpMapping());
        assertEquals(controller, model.getObject());
    }

    @Test
    public void loadRoute() throws Exception {
        Method method = controller.getClass().getMethod("Get", Integer.class);
        ControllerModel controllerModel = routeLoader.loadController(controller);
        RouteModel routeModel = routeLoader.loadRoute(controllerModel, method);

        assertEquals("user/{id}", routeModel.getMapping());
        assertEquals(controllerModel, routeModel.getControllerModel());
        assertEquals(method, routeModel.getMethod());
        assertEquals(HttpMethod.GET, routeModel.getVerbs());
        assertEquals("User.Get#GET", routeModel.getName());
    }

}