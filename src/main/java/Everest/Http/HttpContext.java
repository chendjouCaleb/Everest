package Everest.Http;

import org.everest.mvc.context.RouteData;
import org.everest.mvc.filter.FilterChain;
import org.everest.mvc.infrastructure.RouteModel;
import org.everest.mvc.binding.BindingState;
import org.everest.mvc.model.Model;
import org.everest.mvc.router.Route;

import java.lang.reflect.Method;

/**
 * Encapsulates all HTTP-specific information about an individual HTTP request.
 */
public abstract class HttpContext {
    private Object controller;

    /**
     * Gets the {@link HttpRequest }object for this request.
     */
    protected HttpRequest request;

    /**
     * Gets the {@link HttpResponse } object for this request.
     */
    protected HttpResponse response;

    /**
     * Gets information about the underlying connection for this request.
     */
    private ConnectionInfo connectionInfo;

    /**
     * Gets or sets a key/value collection that can be used to share data within the scope of this request.
     */
    private ItemCollection items = new ItemCollection();

    /**
     * Additional options for the HttpContext
     */
    private HttpContextOptions options = new HttpContextOptions();
    private Session session;
    private FilterChain filterChain;
    private Model model;
    private Route route;
    private Object actionResult;
    private BindingState bindingState;
    private Object requestBody;
    private RouteModel routeModel;
    private RouteData routeData;
    private boolean restContext;

    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }

    public HttpResponse getResponse() {
        return response;
    }

    public void setResponse(HttpResponse response) {
        this.response = response;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @Deprecated public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Object getController() {
        if(controller == null){
            return route.getController();
        }
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public FilterChain getFilterChain() {
        return filterChain;
    }

    public void setFilterChain(FilterChain filterChain) {
        this.filterChain = filterChain;
    }

    public Object getActionResult() {
        return actionResult;
    }

    public void setActionResult(Object actionResult) {
        this.actionResult = actionResult;
    }

    public BindingState getBindingState() {
        return bindingState;
    }

    public void setBindingState(BindingState bindingState) {
        this.bindingState = bindingState;
    }

    public Object getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(Object requestBody) {
        this.requestBody = requestBody;
    }

    public RouteModel getRouteModel() {
        return routeModel;
    }

    public void setRouteModel(RouteModel routeModel) {
        this.routeModel = routeModel;
    }

    public RouteData getRouteData() {
        return routeData;
    }

    public void setRouteData(RouteData routeData) {
        this.routeData = routeData;
    }

    public Method getMethod(){
        if(route != null)
            return route.getMethod();
        return routeModel.getMethod();
    }

    public boolean isRestContext() {
        return restContext;
    }

    public void setRestContext(boolean restContext) {
        this.restContext = restContext;
    }

    public HttpContextOptions getOptions() {
        return options;
    }

    public void setOptions(HttpContextOptions options) {
        this.options = options;
    }

    public ConnectionInfo getConnectionInfo() {
        return connectionInfo;
    }

    public ItemCollection getItems() {
        return items;
    }
}
