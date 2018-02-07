package filter;

import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;

public class Client {
    FilterManager filterManager;

    public void setFilterManager(FilterManager filterManager){
        this.filterManager = filterManager;
    }

    public void sendRequest(Request request, Response response){
        filterManager.filterRequest(request, response);
    }
}
