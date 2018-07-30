package org.everest.mvc.infrastructure;

import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.httpContext.Request;
import org.everest.mvc.httpContext.Response;
import org.everest.mvc.httpContext.Session;
import org.everest.mvc.model.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpContextBuilder {
    public HttpContext build(HttpServletRequest servletRequest, HttpServletResponse servletResponse){
        HttpContext httpContext = new HttpContext();
        Request request = new Request(servletRequest);
        Response response = new Response(servletResponse);
        Session session = new Session(servletRequest.getSession());

        httpContext.setRequest(request);
        httpContext.setResponse(response);
        httpContext.setSession(session);
        httpContext.setModel(new Model());

        return httpContext;
    }
}
