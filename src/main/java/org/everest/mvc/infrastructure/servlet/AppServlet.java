package org.everest.mvc.infrastructure.servlet;


import org.everest.exception.OperationException;
import org.everest.mvc.http.CORSConfig;
import org.everest.mvc.http.HttpStatus;
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

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CORSConfig config = WebApplication.getApp().getInitializer().corsConfig();
        resp.setHeader("Access-Control-Allow-Credentials", config.getAllowCredentials().toString());
        resp.setHeader("Access-Control-Allow-Methods", config.getAllowedMethods());
        resp.setHeader("Access-Control-Allow-Headers", config.getAllowedHeaders());
        resp.setHeader("Access-Control-Allow-Origin", config.getAllowedOrigin());
        logger.info("CORS Config = {}", config);
        if (req.getMethod().equals("OPTIONS")) {
            resp.setStatus(HttpStatus.OK.value());
            resp.getWriter().print("OK");
        } else {
            WebApplication.getApp().run(req, resp);
        }

    }
}
