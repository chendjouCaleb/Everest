package org.everest.mvc.httpContext;

import org.everest.mvc.model.Model;
import org.everest.mvc.router.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * This is an wrapper for httpServletRequest
 * @version 0.0.2
 * @author Chendjou deGrace
 */
public class Request {
    private Logger logger = LoggerFactory.getLogger(Request.class);
    private HttpServletRequest servletRequest;
    private String bodyString;
    private String contentType;
    private Map<String, String[]> parameters = new HashMap<>();
    public Request(HttpServletRequest request){
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.servletRequest = request;
        contentType = request.getContentType();
        parameters = servletRequest.getParameterMap();
        addRedirectAttribute();
        setBodyString();
    }

    public Map<String, String[]> getParameters() {
        return parameters;
    }

    public Request(){}

    public void addAttribute(String name, Object obj){
        servletRequest.setAttribute(name, obj);
    }

    public String getBodyString() {
        return bodyString;
    }

    public String getContentType(){
        return contentType;
    }
    private void setBodyString() {
        StringBuilder body = new StringBuilder();
        String line;
        try {
            BufferedReader reader = servletRequest.getReader();
            while ((line = reader.readLine()) != null){
                body.append(line);
            }
            bodyString = body.toString();
            logger.info("Body string: " + bodyString);
        }catch (Exception e){e.printStackTrace();}
    }

    /**
     * Check whether request is AJAX request
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

    private void addRedirectAttribute(){
        Map<String, Object> attributes = (Map<String, Object>) this.servletRequest.getSession().getAttribute("redirectAttributes");
        if(attributes != null ){
            attributes.forEach((key, value) -> getServletRequest().setAttribute(key, value));
        }
    }

}
