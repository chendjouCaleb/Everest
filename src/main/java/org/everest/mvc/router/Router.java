package org.everest.mvc.router;

import Everest.Mvc.ValueResolver.MethodValueResolver;
import org.everest.core.dic.decorator.AutoWired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Deprecated
public class Router {
    private Map<String, Route> routes = new HashMap<>();
    private Logger logger = LoggerFactory.getLogger(Router.class);

    @AutoWired
    private MethodValueResolver variableResolver;
    public Router() {
        //variableResolver = new MethodValueResolver();
    }

    public Route getCalledRoute(String pathInfo, String httpMethod) {
        return getInvokedRoute(pathInfo, httpMethod);
    }

    public String url(String name, Object... params) {
        Route route = findRoute(name);
        String url = null;
        if (route != null) {
            url = route.url(params);
        }
        return  url;
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
        if(name.contains(".") && name.contains("#")){
            return routes.get(name);
        }
        if(name.contains(".")){
            return routes.get(name+"#GET");
        }
        for (Route route : routes.values()) {
            if (route.getName().equals(name)) {
                return route;
            }
        }
        return null;
    }

    /**
     * @param url    "url called by http HttpRequest"
     * @param method "method of http request"
     * @return "finded route"
     */
    public Route getInvokedRoute(String url, String method) {
        String regex = toRegex(url);
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
        logger.debug("Controllers:  {} ; Routes: {}",controllers.length, routes.size());
    }

    private void populateRoute(Object controller) {
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
