package org.everest.mvc.filter;

import org.everest.mvc.httpContext.Request;
import org.everest.mvc.httpContext.Response;

public class ConsumeFilter implements IFilter<Consume> {
    private String mediaType;
    public void init(Consume annotation) {
mediaType = annotation.mediaType();
    }


    public void execute(Request request) {

    }


}
