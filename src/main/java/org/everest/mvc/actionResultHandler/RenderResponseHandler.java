package org.everest.mvc.actionResultHandler;

import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.result.Render;

import javax.servlet.ServletException;
import java.io.IOException;

public class RenderResponseHandler implements IResultHandler<Render> {

    @Override
    public void handleResponse(HttpContext httpContext, Render result) {
        try {
            for (Object obj: result.getModels()){
                String name = obj.getClass().getSimpleName();
                name = name.substring(0, 1).toLowerCase() + name.substring(1, name.length());
                httpContext.getRequest().addAttribute(name, obj);
            }
            httpContext.getResponse().render(httpContext.getRequest(), result.getView());
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Class<?> getType() {
        return Render.class;
    }
}
