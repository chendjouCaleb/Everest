package org.everest.mvc.httpContext;

import org.everest.core.event.EventEmitter;
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

    public Response(HttpServletResponse response) {
        this.servletResponse = response;
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
        EventEmitter eventEmitter = StaticContext.context.getInstance(EventEmitter.class);
        eventEmitter.emit("response.render", request, path);
        twigRender(request, path);
    }

    public String redirectToRoute(String name, Object... params) {
        Router router = StaticContext.context.getInstance(Router.class);
        return "redirect:" + router.relativeUrl(name, params);
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
}
