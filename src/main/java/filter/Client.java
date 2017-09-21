package filter;

public class Client {
    FilterManager filterManager;

    public void setFilterManager(FilterManager filterManager){
        this.filterManager = filterManager;
    }

    public void sendRequest(String request, String response){
        filterManager.filterRequest(request, response);
    }
}
