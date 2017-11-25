package component.http;


import org.jtwig.web.servlet.JtwigRenderer;
import router.Route;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * This is wrapper for httpSerletRequest
 * @version 0.0.1
 * @author Chendjou
 */
public class Request {
    HttpServletRequest servetRequest;
    private Session session;
    private Route route;
    public Request(HttpServletRequest request){
        this.servetRequest = request;
        session = new Session(this);

        this.session = session;
    }

    public void setAttr(String name, Object obj){
        servetRequest.setAttribute(name, obj);
    }


    /**
     * Check whether request is ajax request
     * @return
     */
    public boolean isXHR(){
        return ((servetRequest.getHeader("X-Requested-With") != null) && "XMLHttpRequest".equals(servetRequest.getHeader("X-Requested-With")));
    }
    public String getPathInfo(){
        return servetRequest.getPathInfo();
    }

    public String getHttpMethod(){
        return servetRequest.getMethod();
    }
    public String getContextPath(){return  servetRequest.getContextPath() + servetRequest.getServletPath();}

    public String getParam(String name){
        return servetRequest.getParameter(name);
    }
    /**
     * Check if request is for à specified type
     * @param method is the httpMethod
     * @return
     */
    public boolean isMethod(String method){
        return servetRequest.getMethod().equalsIgnoreCase(method);
    }

    /**
     *
     * @return the httpServletRequest for the request
     */
    public HttpServletRequest getServletRequest() {
        return servetRequest;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Route getRoute() { return route; }
    public void setRoute(Route route) {this.route = route; }
}
