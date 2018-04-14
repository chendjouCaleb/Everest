package org.everest.mvc.router;

import annotation.HttpController;
import annotation.Path;
import org.everest.core.dic.decorator.AutoWired;
import org.everest.main.StaticContext;
import org.everest.mvc.router.variableResolver.RequestVariableResolver;

import java.lang.reflect.Method;
import java.util.*;

public class Router {
    private Map<String, Route> routes = new HashMap<>();

    @AutoWired
    private RequestVariableResolver variableResolver;
    public Router() {
        //variableResolver = new RequestVariableResolver();
    }

    public Route getCalledRoute(String pathInfo, String httpMethod) {
        return getInvokedRoute(pathInfo, httpMethod);
    }

    public String url(String name, Object... params) {
        Route route = findRoute(name);
        String url = null;
        if (route != null) {
            url = "/" + route.url(params);
        }
        return  StaticContext.applicationInitializer.getAppBaseUrl() + url;
    }

    public String htmlLink(String value, String name, Object... params) {
        String url = url(name, params);
        return  "<a href='" + url +"'> " + value + " </a>";
    }

    public String relativeUrl(String name, Object... params) {
        Route route = findRoute(name);
        String url = null;
        if (route != null) {
            url = "/" + route.url(params);
        }
        return url;
    }

    private Route findRoute(String name) {
        for (Route route : routes.values()) {
            if (route.getName().equals(name)) {
                return route;
            }
        }
        return null;
    }

    /**
     * @param url    "url called by http Request"
     * @param method "method of http request"
     * @return "finded route"
     */
    public Route getInvokedRoute(String url, String method) {
        String regex = toRegex(url);
        System.out.println("REGEX: " + regex);
        for (Route route : routes.values()) {
            if (route.matche(url) && route.getHttpMethod().equals(method)) {
                return route;
            }
        }
        return null;
    }

    public void init(Object[] controllers) {

        for (Object ctrl : controllers) {
            populateRoute(ctrl);
        }
        System.out.println("Controllers: " + controllers.length + "; Route: " + routes.size());
    }

    private void populateRoute(Object controller) {
        HttpController httpController = controller.getClass().getAnnotation(HttpController.class);

        String prefix = httpController.prefix().replaceAll("^/", "").replaceAll("/$", "");

        for (Method method : controller.getClass().getDeclaredMethods()) {
            Path path = method.getAnnotation(Path.class);
            if (path != null) {
                Route route = new Route();
                route.setMethod(method);
                route.setHttpMethod(path.httpMethod().toString());
                route.setName(path.name());
                route.setController(controller);
                String finalPath = getPath(path.route(), prefix);
                String regex = toRegex(finalPath);
                route.setPath(finalPath);
                route.setRegex(toRegex(regex));
                String httpMethod = path.httpMethod().toString();
                String key = httpMethod + route.getRegex();
                this.routes.put(key, route);

                System.out.println("New route add:  Key: " + key + " : " + route.getMethod().getName());
            }
        }
    }
    private String getPath(String path, String prefix){
        String routePath = path.replaceAll("^/", "").replaceAll("/$", "");
        String finalPath = prefix + "/" + routePath;
        return finalPath;
    }
    private String toRegex(String path){
        path = path.replaceAll("^/", "").replaceAll("/$", "");
        String parsedPath = path.replaceAll(":\\w+", "(\\\\w+)");
        parsedPath = parsedPath.replaceAll("[/]", "[/]?");
        return parsedPath;
    }

    public Map<String, Route> getRoutes() {
        return routes;
    }

    public void printRoute() {
        routes.values().forEach(System.out::println);
    }

    public int countRoutes() {
        return routes.size();
    }
}
