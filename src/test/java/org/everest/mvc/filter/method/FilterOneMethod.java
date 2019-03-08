package org.everest.mvc.filter.method;

import Everest.Http.HttpRequest;
import Everest.Http.HttpResponse;
import org.everest.decorator.RequestFilter;
import org.everest.mvc.filter.Filter;
import org.everest.mvc.filter.annatotion.FilterOne;
import org.everest.mvc.result.IFilterResult;
import org.everest.mvc.result.Next;

@RequestFilter
public class FilterOneMethod extends Filter<FilterOne> {
    @Override
    public void init(FilterOne annotation) {

    }

    public IFilterResult execute(HttpRequest httpRequest, HttpResponse HttpResponse) {
        return new Next();
    }
}
