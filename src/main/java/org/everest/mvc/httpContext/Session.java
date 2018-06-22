package org.everest.mvc.httpContext;

import javax.servlet.http.HttpSession;

/**
 * This is an wrapper for httpServletSession
 * @version 0.0.2
 * @author Chendjou deGrace
 */
public class Session {
    private HttpSession session;

    public Session(HttpSession session) {
        this.session = session;
    }

    @Deprecated
    public void set(String name, Object object){
        session.setAttribute(name, object);
    }
    public void setAttribute(String name, Object object){
        session.setAttribute(name, object);
    }

    public Object getAttr(String name, Object object){
        if(session.getAttribute(name) != null){
            return session.getAttribute(name);

        }
        set(name, object);
        return object;
    }

    @Deprecated
    public Object getAttr(String name){
        return session.getAttribute(name);
    }
    public Object getAttribute(String name){
        return session.getAttribute(name);
    }

    public Integer getIntegerAttr(String name){
        return Integer.valueOf(getAttribute(name).toString());
    }

    public void remove(String name){
        session.removeAttribute(name);
    }

    public HttpSession getSession() {
        return session;
    }
}
