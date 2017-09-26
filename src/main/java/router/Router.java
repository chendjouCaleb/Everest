package router;

import annotation.HttpController;
import annotation.Path;
import component.http.Controller;
import component.http.Request;
import component.http.Response;
import exception.NotFoundRouteException;
import filter.Client;
import filter.Filter;
import filter.FilterManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Router {
    private Class<?> controllers[];
    private Class filters[];
    private Response response;
    private Request request;
    private static Router instance=null;

    private Router(Class[] controllers){
        this.controllers = controllers;
        populate();
    }
    private Map<String, List<Route>> routes = new HashMap<String, List<Route>>();

    private void populate(){
        for(Class<?> controller : controllers){
            System.out.println("Controller: " + controller.getName());
            populateRoute(controller);
        }
    }

    private void refresh(HttpServletRequest request, HttpServletResponse response){
        this.request = new Request(request);
        this.response = new Response(response);
    }

    public void run(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws NotFoundRouteException {
        refresh(servletRequest, servletResponse);
        String url = request.getPathInfo();
        String method = request.getHttpMethod();
        Route route = getInvoked(url, method);
        if(route == null){
           throw new NotFoundRouteException("route non trové");
        }
        Controller controller = getInvokedController(route);
        invokeMethod(controller, route);
    }

    public static Router invoke(Class[] controllers,HttpServletRequest servletRequest, HttpServletResponse servletResponse){
        if(instance == null){
            instance = new Router(controllers);
            System.out.println("NOUVELLE INSTANCIATION");
        }
        instance.refresh(servletRequest, servletResponse);
        return instance;
    }



    private Controller getInvokedController(Route route){
        Controller controller = null;
        try {
             controller = (Controller)route.getController().getConstructor().newInstance();
             controller.setRequest(request);
             controller.setResponse(response);
             controller.init();
             controller.setRouter(instance);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return controller;
    }

    public void handleFilter(Controller controller, Route route, Object[] params){
        FilterManager filterManager = new FilterManager(controller, route.getMethod().getName(), params);
        List<Filter> filters = RouterUtils.getFilters(controller.getClass(), route.getMethod());
        for (Filter filter: filters){
            filterManager.addFilter(filter);
        }
        Client client = new Client();
        client.setFilterManager(filterManager);
        client.sendRequest(request, response);
    }

    private void invokeMethod(Controller controller, Route route){
        try {
            Object[] params = RouterUtils.params(route.getParameters(), route.getMethod());
            RouterUtils.getFilters(controller.getClass(), route.getMethod());
            //RouterUtils.callRemote(controller, route.getMethod().getName(), params);
            handleFilter(controller, route, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public String url(String name, String... params){
        Route route = findRoute(name);
        if(route != null){
            String url = request.getContextPath() + "/" + route.url(params);
            System.out.println("URL: " + url);
            return url;
        }
        return null;
    }

    private Route findRoute(String name){
        for (Map.Entry<String, List<Route>> listEntry: routes.entrySet()){
            for (Route route: listEntry.getValue()){
                if(route.getName().equals(name)){
                    return route;
                }
            }
        }
        return null;
    }

    /**
     *
     * @param url "url called by http Request"
     * @param method "method of http request"
     * @return "finded route"
     */
    private Route getInvoked(String url, String method){
        System.out.println("URL: " + url);
        System.out.println("METHODE HTTP: " + method);
        List<Route> get = routes.get(method);
            for (int i = 0; i < get.size(); i++) {
                Route route = get.get(i);
                if(route.matche(url)){
                    return route;
                }
            }
        return null;
    }

    private void populateRoute(Class<?> controller) {
        HttpController httpController = controller.getAnnotation(HttpController.class);
        String prefix = httpController.prefix().replaceAll("^/", "").replaceAll("/$", "");

        for(Method method: controller.getDeclaredMethods()){
            Path path = method.getAnnotation(Path.class);
            if(path != null){
                Route route = new Route();
                route.setMethod(method);
                System.out.println("METHOD: " + method.getName());
                route.setName(path.name());
                route.setController(controller);
                String routePath = path.route().replaceAll("^/", "").replaceAll("/$", "");
                String finalPath = prefix + "/" + routePath;
                route.setPath(finalPath);

                String httpMethod = path.httpMethod().toString();
                if(routes.get(httpMethod) == null){
                    routes.put(httpMethod, new ArrayList<Route>());
                }
                routes.get(httpMethod).add(route);
            }
        }
    }

}
