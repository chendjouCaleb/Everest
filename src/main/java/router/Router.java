package router;

import annotation.HttpController;
import annotation.Path;
import exception.NotFoundRouteException;
import filter.Client;
import filter.Filter;
import filter.FilterManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Router {
    private Class<?> controllers[];
    public Router(Class<?>[] controllers){
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

    public void run(String url, String method) throws Exception {
        Route route = getInvoked(url, method);
        if(route == null){
           throw new NotFoundRouteException("route non trové");
        }
        Controller controller = getInvokedController(route);
        invokeMethod(controller, route);

    }



    public Controller getInvokedController(Route route){
        Controller controller = null;
        try {
             controller = (Controller)route.getController().getConstructor().newInstance();
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
        List<Filter> filters = RouterUtils.getFilters(route.getMethod());
        for (Filter filter: filters){
            filterManager.addFilter(filter);
        }
        Client client = new Client();
        client.setFilterManager(filterManager);
        client.sendRequest("REQUEST", "RESPONSE");
    }

    public void invokeMethod(Controller controller, Route route){
        try {
            Object[] params = RouterUtils.params(route.getParameters(), route.getMethod());
            RouterUtils.getFilters(route.getMethod());
            //RouterUtils.callRemote(controller, route.getMethod().getName(), params);
            handleFilter(controller, route, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public String url(String name, String... params){
        Route route = findRoute(name);
        if(route != null){
            return route.url(params);
        }
        return null;
    }

    public Route findRoute(String name){
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
            Route route = new Route();
            route.setMethod(method);
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
