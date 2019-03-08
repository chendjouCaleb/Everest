package org.everest.mvc.filter.method;

import Everest.Http.HttpRequest;
import org.everest.decorator.RequestFilter;
import org.everest.mvc.filter.Filter;
import org.everest.mvc.filter.annatotion.FilterTwo;
import Everest.Http.HttpResponse;
import org.everest.mvc.httpContext.decorator.FilterMethod;
import org.everest.mvc.result.Next;

@RequestFilter
public class FilterTwoMethod extends Filter<FilterTwo> {
    public void init(FilterTwo annotation) {

    }

    @FilterMethod
    public Next executeFilter(HttpRequest httpRequest, HttpResponse HttpResponse) {
        return Next();
    }
}
