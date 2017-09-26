package filter;

import component.http.Request;
import component.http.Response;

public class Client {
    FilterManager filterManager;

    public void setFilterManager(FilterManager filterManager){
        this.filterManager = filterManager;
    }

    public void sendRequest(Request request, Response response){
        filterManager.filterRequest(request, response);
    }
}
