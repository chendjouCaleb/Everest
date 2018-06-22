package org.everest.mvc.filter;

import org.everest.mvc.httpContext.Request;
import org.everest.mvc.httpContext.Response;

public class Client {
    FilterManager filterManager;

    public void setFilterManager(FilterManager filterManager){
        this.filterManager = filterManager;
    }

//    public void sendRequest(Request request, Response response){
//        filterManager.filterRequest(request, response);
//    }
}
