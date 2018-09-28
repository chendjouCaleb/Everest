package org.everest.mvc.responseBody;

import org.everest.mvc.http.MediaType;

import java.util.ArrayList;
import java.util.List;

public class PlainTextResponseBodyTransformer implements IResponseBodyTransformer {
    @Override
    public String getBody(Object object) {
        return object.toString();
    }

    @Override
    public List<String> getMediaType() {
        List<String> media = new  ArrayList<>();
        media.add(MediaType.TEXT_PLAIN_VALUE);
        return media;
    }
}
