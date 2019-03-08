package Everest.Servlet.Http;


import Everest.Http.DefaultHttpContext;
import Everest.Http.HttpContext;
import Everest.Middleware.MiddlewarePipeline;
import org.everest.context.ApplicationContext;
import org.everest.mvc.infrastructure.MvcRunner;
import org.everest.mvc.infrastructure.WebApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AppServlet extends HttpServlet {
    private Logger logger = LoggerFactory.getLogger(AppServlet.class);

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpContext httpContext = new DefaultHttpContext();
        httpContext.setRequest(new ServletRequestImpl(request));
        httpContext.setResponse(new ServletResponseImpl(response));

        ApplicationContext application =
                (ApplicationContext) getServletContext().getAttribute("applicationContext");

        logger.debug("New request: [ url = {}, method = {}]", httpContext.getRequest().path(), httpContext.getRequest().method());
        MiddlewarePipeline pipeline = application.getContainer().getInstance(MiddlewarePipeline.class);
        pipeline.run(httpContext);
    }
}
