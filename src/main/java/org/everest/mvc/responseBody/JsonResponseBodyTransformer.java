package org.everest.mvc.responseBody;

import org.everest.mvc.infrastructure.StaticContext;
import org.everest.mvc.service.JSONService;

public class JsonResponseBodyTransformer implements IResponseBodyTransformer {
    @Override
    public String getBody(Object object) {
        JSONService jsonService = StaticContext.context.getInstance(JSONService.class);
        return jsonService.toJSON(object);
    }
}
