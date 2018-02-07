package org.everest.main;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AppFilter implements Filter {
    private FilterConfig filterConfig = null;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String path = request.getPathInfo();
        System.out.println("Request PAth: " + filterConfig.getServletContext().getContextPath());

       // String appClass = filterConfig.getInitParameter("app-class");
        //App app = App.getApp(appClass);
       // app.run(request, resp);
        //filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }
}
