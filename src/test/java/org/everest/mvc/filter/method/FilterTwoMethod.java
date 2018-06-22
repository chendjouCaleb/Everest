package org.everest.mvc.filter.method;

import org.everest.decorator.RequestFilter;
import org.everest.mvc.filter.Filter;
import org.everest.mvc.filter.IFilter;
import org.everest.mvc.filter.annatotion.FilterTwo;
import org.everest.mvc.httpContext.Request;
import org.everest.mvc.httpContext.Response;
import org.everest.mvc.httpContext.decorator.FilterMethod;
import org.everest.mvc.result.Next;
import org.everest.mvc.result.RouteRedirection;

@RequestFilter
public class FilterTwoMethod extends Filter<FilterTwo> {
    public void init(FilterTwo annotation) {

    }

    @FilterMethod
    public Next executeFilter(Request request, Response Response) {
        return Next();
    }
}
