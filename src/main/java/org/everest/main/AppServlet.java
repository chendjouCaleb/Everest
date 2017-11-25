package org.everest.main;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AppServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Servlet request path: " + req.getPathInfo());
        String appClass = this.getServletConfig().getInitParameter("app-class");
        App app = App.getApp(appClass);
        app.run(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String appClass = this.getServletConfig().getInitParameter("app-class");
        App app = App.getApp(appClass);
        app.run(req, resp);
    }
}
