package org.everest.mvc.httpContext;

import org.everest.mvc.filter.FilterChain;
import org.everest.mvc.model.BindingState;
import org.everest.mvc.model.Model;
import org.everest.mvc.router.Route;

public class HttpContext {
    private Object controller;
    private Request request;
    private Response response;
    private Session session;
    private FilterChain filterChain;
    private Model model;
    private Route route;
    private Object actionResult;
    private BindingState bindingState;
    private Object requestBody;

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
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

    public Route getRoute() {
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
}
