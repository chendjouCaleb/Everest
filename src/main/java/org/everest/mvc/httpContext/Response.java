package org.everest.mvc.httpContext;

import org.everest.core.event.EventEmitter;
import org.everest.mvc.http.MediaType;
import org.everest.mvc.infrastructure.StaticContext;
import org.everest.mvc.renderer.JtwigView;
import org.everest.mvc.router.Router;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 * @author chendjou deGrace
 * @version 0.0.2
 * This is an wrapper for HttpServletResponse
 */
public class Response {

    private HttpServletResponse servletResponse;

    private String contentType;

    public Response(){
        contentType = MediaType.APPLICATION_JSON_VALUE;
    }



    public Response(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        this.servletResponse = response;
        contentType = MediaType.APPLICATION_JSON_VALUE;
    }

    public HttpServletResponse getServletResponse() {
        return servletResponse;
    }

    private final static String TWIG_FILE_EXTENSION = ".twig";


    public void twigRender(Request request, String path) throws ServletException, IOException {
        String filePath = StaticContext.applicationInitializer.getProjectBaseDir()
                + StaticContext.applicationInitializer.getWebAppPath()
                + StaticContext.applicationInitializer.getViewFolderPath()
                + path;
        new JtwigView().dispatcherFor(filePath + TWIG_FILE_EXTENSION).
                addAttribute("router", this).forward(request.getServletRequest(), servletResponse);
    }

    public void render(Request request, String path) throws ServletException, IOException {
        twigRender(request, path);
    }


    public void redirect(String url) {
        try {
            servletResponse.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCookie(String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        servletResponse.addCookie(cookie);
    }

    public void setStatusCode(int code){
        this.getServletResponse().setStatus(code);
    }

    public void getStatusCode(){
        this.getServletResponse().getStatus();
    }
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
