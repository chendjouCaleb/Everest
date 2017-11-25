package router;

import annotation.HttpController;
import annotation.Path;
import component.http.Controller;
import component.http.Request;
import component.http.Response;
import dic.Container;
import exception.NotFoundRouteException;
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
        if(route == null){
            try {
                throw new NotFoundRouteException("route non trouvée");
            } catch (NotFoundRouteException e) {
                e.printStackTrace();
            }
        }
        return route;
    }




    private Controller getInvokedController(Route route, Request request, Response response){
        Controller controller = null;
        try {
             controller = (Controller) Container.getService(route.getController());
             request.setRoute(route);
             controller.setRequest(request);
             controller.setResponse(response);
             controller.init();
             controller.setRouter((Router)Container.getService(Router.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return controller;
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
            }
        }
    }

}
