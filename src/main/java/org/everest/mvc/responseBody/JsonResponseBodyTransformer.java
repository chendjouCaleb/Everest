package org.everest.mvc.responseBody;

import org.everest.mvc.http.MediaType;
import org.everest.mvc.service.JSONService;

import java.util.ArrayList;
import java.util.List;

public class JsonResponseBodyTransformer implements IResponseBodyTransformer {
    private JSONService jsonService;

    public JsonResponseBodyTransformer(JSONService jsonService) {
        this.jsonService = jsonService;
    }

    @Override
    public String getBody(Object object) {
        return jsonService.toJSON(object);
    }

    @Override
    public List<String> getMediaType() {
        List<String> mediaType = new ArrayList<>();
        mediaType.add(MediaType.APPLICATION_JSON_VALUE);
        mediaType.add(MediaType.APPLICATION_JSON_UTF8_VALUE);
        return mediaType;
    }
}
