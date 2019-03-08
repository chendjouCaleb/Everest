package org.everest.mvc.filter;

import Everest.Http.HttpContext;
import org.everest.mvc.result.IFilterResult;

public class RestContextFilter extends Filter<RestContext> {


    public IFilterResult execute(HttpContext context) {
        context.setRestContext(true);
        return Next();
    }


    @Override
    public void init(RestContext annotation) {

    }
}
