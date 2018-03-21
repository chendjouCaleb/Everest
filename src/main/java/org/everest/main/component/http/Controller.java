package org.everest.main.component.http;

import org.everest.main.Utils;
import org.everest.mvc.router.Router;


public abstract class Controller {
    protected Response response;
    protected Request request;
    protected Session session;
    protected Router router;


    public void init() {
    }


    public String url(String name, Object... params) {
        return router.url(name, params);
    }

    @Deprecated
    public String generateUrl(String controller, String name, String... params) {
        return url(name, params);
    }


    @Deprecated
    public void render(String path) {
        request.setAttr("router", router);
        try {
            response.render(request, path);
        } catch (Exception e) {
            Utils.handleError(request, response, e);
        }
    }

    public void setRouter(Router router) {
        this.router = router;
    }

    @Deprecated()
    public void redirectTo(String name, Object... params) {
        String url = router.url(name, params);
        response.redirect(url);
    }




    @Deprecated
    public void redirect(String url) {
        response.redirect(request.getContextPath() + url);
    }


    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
        request.setAttr("ctrl", this);
        setSession(request);
    }

    public Session getSession() {
        return session;
    }

    public Router getRouter() {
        return router;
    }

    private void setSession(Request request) {
        this.session = new Session(request);
    }
}
