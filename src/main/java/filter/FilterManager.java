package filter;

import component.http.Controller;
import component.http.Request;
import component.http.Response;

public class FilterManager {
    FilterChain filterChain;

    public FilterManager(Controller controller, String target, Object[] params){
        filterChain = new FilterChain();
        filterChain.setTarget(target);
        filterChain.setController(controller);
        filterChain.setTargetParams(params);
    }

    public void addFilter(Filter filter){
        filterChain.addFilter(filter);
    }

    public void filterRequest(Request request, Response response){
        filterChain.execute(request, response);
    }

}
