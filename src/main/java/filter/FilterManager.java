package filter;

import router.Controller;

import java.lang.reflect.Method;
import java.util.Objects;

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

    public void filterRequest(String request, String response){
        filterChain.execute(request, response);
    }

}
