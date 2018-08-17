package org.everest.mvc.responseBody;

public class PlainTextResponseBodyTransformer implements IResponseBodyTransformer {
    @Override
    public String getBody(Object object) {
        return object.toString();
    }
}
