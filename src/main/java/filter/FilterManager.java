package filter;

import component.http.Controller;
import component.http.Request;
import component.http.Response;
import router.Route;
import router.RouterUtils;

import java.util.List;

public class FilterManager {
    private FilterChain filterChain;

    /*public FilterManager(Controller controller, String target, Object[] params){
        filterChain = new FilterChain();
        filterChain.setTarget(target);
        filterChain.setController(controller);
        filterChain.setTargetParams(params);
    }*/

    public void handleFilter(Route route, Request request, Response response){
        filterChain = new FilterChain();
        List<Filter> filters = RouterUtils.getFilters(route.getController(), route.getMethod());
        for (Filter filter: filters){
            filterChain.addFilter(filter);
        }
        Client client = new Client();
        client.setFilterManager(this);
        client.sendRequest(request, response);
    }

    public void addFilter(Filter filter){
        filterChain.addFilter(filter);
    }

    public void filterRequest(Request request, Response response){
        filterChain.execute(request, response);
    }

}
