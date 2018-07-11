package org.everest.mvc.actionResultHandler;

import org.everest.context.ApplicationContext;
import org.everest.core.dic.decorator.AutoWired;
import org.everest.decorator.Instance;
import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.infrastructure.ApplicationInitializer;
import org.everest.mvc.infrastructure.StaticContext;
import org.everest.mvc.renderer.RendererException;
import org.everest.mvc.renderer.ThymeleafViewRenderer;
import org.everest.mvc.renderer.ViewRenderer;
import org.everest.mvc.result.Render;
import org.everest.mvc.result.View;
import org.everest.mvc.router.Router;
import org.jtwig.renderable.RenderException;

import javax.servlet.ServletException;
import java.io.IOException;

public class ViewResultHandler implements IResultHandler<View> {
    private ApplicationContext applicationContext;
//    public ViewResultHandler(ApplicationContext applicationContext){
//        this.applicationContext = applicationContext;
//    }
    @Override
    public void handleResponse(HttpContext httpContext, View result) {
        try {

            result.getData().forEach((key, value) -> httpContext.getModel().addData(key, value));
            ApplicationInitializer initializer = StaticContext.applicationInitializer;
            httpContext.getRequest().addAttribute("html", StaticContext.context.getInstance(Router.class));
            ViewRenderer renderer = initializer.viewRenderer();
            renderer.render(result.getView(), httpContext);
        } catch (Exception e) {
            throw new RendererException("Une erreur est survénue lors du rendu de la vue", e);
        }
    }

    @Override
    public Class<?> getType() {
        return View.class;
    }
}
