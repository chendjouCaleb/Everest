package org.everest.mvc.renderer;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import nz.net.ultraq.thymeleaf.decorators.strategies.GroupingStrategy;
import org.everest.context.ApplicationContext;
import org.everest.mvc.httpContext.HttpContext;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ThymeleafViewRenderer extends ViewRenderer {
    private TemplateEngine templateEngine = new TemplateEngine();
    private String prefix = "/WEB-INF/views/";
    private String suffix = ".html";

    public ThymeleafViewRenderer(ApplicationContext applicationContext) {
       // this.applicationContext = applicationContext;
        templateEngine = new TemplateEngine();

    }

    public void render(String viewName, HttpContext httpContext) {
        HttpServletRequest request = httpContext.getRequest().getServletRequest();
        HttpServletResponse response = httpContext.getResponse().getServletResponse();
        ServletContextTemplateResolver templateResolver =
                new ServletContextTemplateResolver(request.getServletContext());
        templateResolver.setSuffix(suffix);
        templateResolver.setPrefix(prefix);

        GroupingStrategy groupingStrategy = new GroupingStrategy();
        //groupingStrategy.setProperty("prefix", prefix);
        LayoutDialect layoutDialect = new LayoutDialect(groupingStrategy);
        //layoutDialect.setProperty("prefix", prefix);
        templateEngine.addDialect(layoutDialect);

        templateEngine.setTemplateResolver(templateResolver);


        WebContext context = new WebContext(request,response, request.getServletContext());
        response.setContentType("text/html;charset=UTF-8");
        try {
            templateEngine.process(viewName, context, response.getWriter());
        } catch (Exception e) {
            throw new RendererException("Erreur lors du rendu de la vue " + viewName, e);
        }
    }

    @Override
    public String getPrefix() {
        return prefix;
    }

    @Override
    public String getSuffix() {
        return suffix;
    }
}
