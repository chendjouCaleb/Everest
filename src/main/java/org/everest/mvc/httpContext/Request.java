package org.everest.mvc.httpContext;

import org.everest.mvc.model.Model;
import org.everest.mvc.router.Route;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * This is an wrapper for httpServletRequest
 * @version 0.0.2
 * @author Chendjou deGrace
 */
public class Request {
    private HttpServletRequest servletRequest;
    private Session session;
    private Route route;
    private Model model = new Model();
    public Request(HttpServletRequest request){
        this.servletRequest = request;
    }

    @Deprecated()
    public void setAttr(String name, Object obj){
        servletRequest.setAttribute(name, obj);
    }
    public void addAttribute(String name, Object obj){
        servletRequest.setAttribute(name, obj);
    }

    public Object getRouteParams(int index){
        return this.getRoute().getParameters()[index];
    }
    public int getRouteIntParams(int index){
        return Integer.valueOf(this.getRoute().getParameters()[index]);
    }
    /**
     * Check whether request is ajax request
     * @return
     */
    public boolean isXHR(){
        return ((servletRequest.getHeader("X-Requested-With") != null) && "XMLHttpRequest".equals(servletRequest.getHeader("X-Requested-With")));
    }
    public String getPathInfo(){
        return servletRequest.getPathInfo();
    }

    public String getHttpMethod(){
        return servletRequest.getMethod();
    }
    public String getContextPath(){return  servletRequest.getContextPath() + servletRequest.getServletPath();}

    public String getParam(String name){
        return servletRequest.getParameter(name);
    }
    public String[] getParams(String name){
        return servletRequest.getParameterValues(name);
    }
    public String getParam(String name, boolean nullable){
        String param = getParam(name);
        if(nullable && param.trim().length() == 0){return null;}
        return param;
    }
    
    public Integer getIntegerParam(String name){
        Integer number = null;
        try{
            number = Integer.valueOf(getParam(name));
        }catch (Exception ignored){}
        return number;
    }
    /**
     * Check if request is for à specified type
     * @param method is the httpMethod
     * @return
     */
    public boolean isMethod(String method){
        return servletRequest.getMethod().equalsIgnoreCase(method);
    }

    /**
     *
     * @return the httpServletRequest for the request
     */

    public String getIpAdress(){
        String ipAddress = null;
        ipAddress = servletRequest.getHeader("X-FORWARDED-FOR");
        if(ipAddress == null){
            ipAddress = servletRequest.getRemoteAddr();
        }
        return ipAddress;
    }

    public String getCookieValue(String nom){
        Cookie[] cookies = servletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if(cookie != null && nom.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public String getUserAgent(){
        return servletRequest.getHeader("user-agent");
    }
    public HttpServletRequest getServletRequest() {
        return servletRequest;
    }

    @Deprecated
    public Session getSession() {
        return session;
    }

    public void setSession(Session sess) {
        session = sess;
    }

    @Deprecated
    public Route getRoute() { return route; }
    public void setRoute(Route route) {this.route = route; }

    @Deprecated
    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
