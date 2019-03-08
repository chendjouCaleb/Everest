package org.everest.mvc.filter;

import Everest.Http.HttpRequest;

public class ConsumeFilter implements IFilter<Consume> {
    private String mediaType;
    public void init(Consume annotation) {
mediaType = annotation.mediaType();
    }


    public void execute(HttpRequest httpRequest) {

    }


}
