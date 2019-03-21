package org.everest.mvc.infrastructure;

import org.everest.mvc.context.RouteData;
import org.everest.mvc.controller.CommentController;
import org.everest.mvc.controller.UserController;
import Everest.Http.HttpMethod;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RouteDispatcherTest {
    private RouteLoader routeLoader = new RouteLoader();
    RouteDispatcher dispatcher = new RouteDispatcher();
    private UserController controller = new UserController();
    private List<RouteModel> routeModels = new ArrayList<>();

    @BeforeEach
    public void setUp() throws Exception {

        List<Object> list = new ArrayList<>();
        list.add(controller);
        list.add(new CommentController());
        routeLoader.load(list);
        routeModels = routeLoader.getRoutes();
    }

    @Test
    public void getCalledRoute_without_params() {
        RouteModel routeModel = dispatcher.getCalledRoute(routeModels, "/user", "POST");
        assertEquals(controller, routeModel.getControllerModel().getObject());
        assertEquals(HttpMethod.POST, routeModel.getVerbs());
        assertEquals("User.Add#POST", routeModel.getName());
    }

    @Test
    public void getCalledRoute_with_params() {
        RouteModel routeModel = dispatcher.getCalledRoute(routeModels, "/user/1", "GET");
        assertEquals(controller, routeModel.getControllerModel().getObject());
        assertEquals(HttpMethod.GET, routeModel.getVerbs());
        assertEquals("User.Get#GET", routeModel.getName());
    }

    @Test
    public void getRouteContext(){
        RouteModel routeModel = dispatcher.getCalledRoute(routeModels, "/user/1", "GET");
        RouteData routeData = dispatcher.createRouteContext(routeModel, "/user/1");
        assertEquals(1, routeData.getParameterNames().size());
        assertEquals(1, routeData.getParameters().size());
        assertEquals("1", routeData.getParameters().get("id"));
    }
    @Test
    public void getRouteContext_With_Complex_url(){
        String urlMapping = "/user/{userId}/comment/{commentId}/response/{responseID}.{length}-{id}";
        String url = "/user/10/comment/20/response/30.25-id";
        RouteModel routeModel = dispatcher.getCalledRoute(routeModels, url, "GET");
        RouteData routeData = dispatcher.createRouteContext(routeModel, url);
        assertEquals(5, routeData.getParameterNames().size());
        assertEquals(5, routeData.getParameters().size());
        assertEquals("id", routeData.getParameter("id"));
        assertEquals(new Integer(10), routeData.getParameter("userId", Integer.class));
        assertEquals(new Integer(20), routeData.getParameter("commentId", Integer.class));
        assertEquals(new Integer(30), routeData.getParameter("responseID", Integer.class));
        assertEquals(new Integer(25), routeData.getParameter("length", Integer.class));

    }

}