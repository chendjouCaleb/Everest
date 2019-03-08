package org.everest.mvc.filter;

public class Client {
    FilterManager filterManager;

    public void setFilterManager(FilterManager filterManager){
        this.filterManager = filterManager;
    }

//    public void sendRequest(HttpRequest request, HttpResponse response){
//        filterManager.filterRequest(request, response);
//    }
}
