package org.everest.mvc.filter.method;

import org.everest.decorator.RequestFilter;
import org.everest.mvc.filter.IFilter;
import org.everest.mvc.filter.FilterChain;
import org.everest.mvc.filter.annatotion.FilterOne;
import org.everest.mvc.httpContext.Request;
import org.everest.mvc.httpContext.Response;
import org.everest.mvc.result.IFilterResult;
import org.everest.mvc.result.Next;

@RequestFilter
public class FilterOneMethod implements IFilter<FilterOne> {
    @Override
    public void init(FilterOne annotation) {

    }

    public IFilterResult execute(Request request, Response Response) {
        return new Next();
    }
}
