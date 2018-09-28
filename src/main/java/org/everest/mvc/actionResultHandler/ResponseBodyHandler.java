package org.everest.mvc.actionResultHandler;

import org.everest.decorator.Instance;
import org.everest.exception.OperationException;
import org.everest.mvc.httpContext.HttpContext;
import org.everest.mvc.responseBody.IResponseBodyTransformer;
import org.everest.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Instance
public class ResponseBodyHandler {
    private Map<String, IResponseBodyTransformer> transformers = new HashMap<>();
    private Logger logger = LoggerFactory.getLogger(ResponseBodyHandler.class);

    public void addTransformers(IResponseBodyTransformer responseBodyTransformer){
        responseBodyTransformer.getMediaType().forEach(mediaType -> {
            logger.info("New transformer:[type= {}, class= {}]", mediaType, responseBodyTransformer.getClass().getSimpleName());
            transformers.put(mediaType, responseBodyTransformer);
        });
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
