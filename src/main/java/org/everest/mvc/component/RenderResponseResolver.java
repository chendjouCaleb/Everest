package org.everest.mvc.component;

import org.everest.main.StaticContext;
import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;
import org.everest.mvc.action.Render;
import org.everest.mvc.router.Router;

import javax.servlet.ServletException;
import java.io.IOException;

public class RenderResponseResolver implements IResponseResolver<Render>{

    @Override
    public void handleResponse(Request request, Response response, Render result) {
        try {
            for (Object obj: result.getModels()){
                String name = obj.getClass().getSimpleName();
                name = name.substring(0, 1).toLowerCase() + name.substring(1, name.length());
                request.setAttr(name, obj);
            }
            response.render(request, result.getView());
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
