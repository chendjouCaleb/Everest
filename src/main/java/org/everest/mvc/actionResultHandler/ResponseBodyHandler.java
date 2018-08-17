package org.everest.mvc.actionResultHandler;

import org.everest.exception.OperationException;
import org.everest.mvc.http.MediaType;
import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.infrastructure.StaticContext;
import org.everest.mvc.responseBody.IResponseBodyTransformer;
import org.everest.mvc.responseBody.JsonResponseBodyTransformer;
import org.everest.mvc.responseBody.PlainTextResponseBodyTransformer;
import org.everest.mvc.result.JSON;
import org.everest.mvc.service.JSONService;
import org.everest.utils.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ResponseBodyHandler {
    Map<String, IResponseBodyTransformer> transformers = new HashMap<>();
    public ResponseBodyHandler(){
        JsonResponseBodyTransformer jsonTransformer = new JsonResponseBodyTransformer();
        transformers.put(MediaType.TEXT_PLAIN_VALUE, new PlainTextResponseBodyTransformer());
        transformers.put(MediaType.APPLICATION_JSON_VALUE, jsonTransformer );
        transformers.put(MediaType.APPLICATION_JSON_UTF8_VALUE, jsonTransformer);
    }

    public String handleBody(HttpContext httpContext, Object object) {
        String contentType = httpContext.getResponse().getContentType();

        Assert.notNull(transformers.get(contentType), "Aucun transformateur trouvé pour le type de média: '" + contentType + "'");
        IResponseBodyTransformer transformer = transformers.get(contentType);
        return transformer.getBody(object);
    }

    public void sendResponse(HttpContext context, String body, int status){
        context.getResponse().getServletResponse().setContentType(context.getResponse().getContentType());
        context.getResponse().getServletResponse().setStatus(status);
        try {
            context.getResponse().getServletResponse().getWriter().print(body);
        } catch (IOException e) {
            throw new OperationException("Erreur lors de la réponse", e);
        }
    }
}
