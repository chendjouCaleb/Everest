package org.everest.main.component.http;

import org.jtwig.web.servlet.JtwigRenderer;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Response {

    HttpServletResponse servletResponse;
    public Response(HttpServletResponse response){
        this.servletResponse = response;
    }

    public HttpServletResponse getServletResponse() {
        return servletResponse;
    }

    private static String TEMPLATE_PATH_PREFIX = "/WEB-INF/templates/";
    private final static String TWIG_FILE_EXTENSION   = ".twig";
    private static final JtwigRenderer renderer = JtwigRenderer.defaultRenderer();


    public void twigRender(Request request, String path) throws ServletException, IOException {

            renderer.dispatcherFor(TEMPLATE_PATH_PREFIX + path + TWIG_FILE_EXTENSION)
                    .with("router", this)
                    .render(request.getServletRequest(), servletResponse);

    }

    public void render(Request request, String path) throws ServletException, IOException {
        if(path.endsWith(".jsp")){
            try {
                request.getServletRequest().getServletContext().getRequestDispatcher(path)
                        .forward(request.getServletRequest(), servletResponse);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
                twigRender(request, path);
        }
    }

    public void redirect(String url){
        try {
            servletResponse.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCookie(String name, String value, int maxAge){
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        servletResponse.addCookie(cookie);
    }
}
