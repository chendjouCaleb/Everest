package org.everest.main.component.http;

import javax.servlet.http.HttpSession;

public class Session {
    private HttpSession session;
    Session(Request request){
        session = request.getServletRequest().getSession();

    }

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


    public Object getAttr(String name){
        return session.getAttribute(name);
    }
    public Object get(String name){
        return session.getAttribute(name);
    }
    public Integer getIntegerAttr(String name){
        return Integer.valueOf(get(name).toString());
    }

    public void remove(String name){
        session.removeAttribute(name);
    }

    public HttpSession getSession() {
        return session;
    }
}
