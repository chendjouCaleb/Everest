package org.everest.mvc.infrastructure;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.collections.MapUtils;
import org.everest.mvc.context.RouteContext;
import org.everest.mvc.controller.CommentController;
import org.everest.mvc.controller.UserController;
import org.everest.mvc.httpContext.HttpMethod;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RouteDispatcherTest {
    private RouteLoader routeLoader = new RouteLoader();
    RouteDispatcher dispatcher = new RouteDispatcher();
    private UserController controller = new UserController();
    private List<RouteModel> routeModels = new ArrayList<>();

    @Before
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
        RouteContext routeContext = dispatcher.createRouteContext(routeModel, "/user/1");
        assertEquals(1, routeContext.getParameterNames().size());
        assertEquals(1, routeContext.getParameters().size());
        assertEquals("1", routeContext.getParameters().get("id"));
    }
    @Test
    public void getRouteContext_With_Complex_url(){
        String urlMapping = "/user/{userId}/comment/{commentId}/response/{responseID}.{length}-{id}";
        String url = "/user/10/comment/20/response/30.25-id";
        RouteModel routeModel = dispatcher.getCalledRoute(routeModels, url, "GET");
        RouteContext routeContext = dispatcher.createRouteContext(routeModel, url);
        assertEquals(5, routeContext.getParameterNames().size());
        assertEquals(5, routeContext.getParameters().size());
        assertEquals("id", routeContext.getParameter("id"));
        assertEquals(new Integer(10), routeContext.getParameter("userId", Integer.class));
        assertEquals(new Integer(20), routeContext.getParameter("commentId", Integer.class));
        assertEquals(new Integer(30), routeContext.getParameter("responseID", Integer.class));
        assertEquals(new Integer(25), routeContext.getParameter("length", Integer.class));

    }

}