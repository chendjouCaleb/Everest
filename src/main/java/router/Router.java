package router;

import annotation.HttpController;
import annotation.Path;
import org.everest.main.component.http.Controller;
import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;

import java.lang.reflect.Method;
import java.util.*;

public class Router {
    private Map<String, List<Route>> routes = new HashMap<>();
    private Request request;

    public Router(){

    }
    public Route getCalledRoute(Request request){
        this.request = request;
        String url = request.getPathInfo();
        String method = request.getHttpMethod();
        Route route = getInvokedRoute(url, method);
        return route;
    }



    public String url(String name, Object... params){
        Route route = findRoute(name);
        String url = null;
        if(route != null){
            url = request.getContextPath() + "/" + route.url(params);
        }
        return url;
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
    private Route getInvokedRoute(String url, String method){
        for (Route route : routes.get(method)) {
            if (route.matche(url)) {
                return route;
            }
        }
        return null;
    }

    public void init(Class[] controllers){
        for (Class ctrl: controllers){
            populateRoute(ctrl);
        }
    }
    private void populateRoute(Class<?> controller) {
        HttpController httpController = controller.getAnnotation(HttpController.class);
        if(httpController == null){
            return;
        }
        String prefix = httpController.prefix().replaceAll("^/", "").replaceAll("/$", "");

        for(Method method: controller.getDeclaredMethods()){
            Path path = method.getAnnotation(Path.class);
            if(path != null){
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
                System.out.println("New route add: " + route.getPath() + " : " + route.getMethod().getName());
            }
        }
    }

}
