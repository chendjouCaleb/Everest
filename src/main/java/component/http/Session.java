package component.http;

import javax.servlet.http.HttpSession;
import java.util.Objects;

public class Session {
    HttpSession session;
    Session(Request request){

        session = request.getServletRequest().getSession();
        request.setSession(this);
    }

    public void set(String name, Object object){
        session.setAttribute(name, object);
    }
    public void setAttribute(String name, Object object){
        session.setAttribute(name, object);
    }

    public Object get(String name){
        return session.getAttribute(name);
    }

    public void remove(String name){
        session.removeAttribute(name);
    }

    public HttpSession getSession() {
        return session;
    }
}
