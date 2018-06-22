package org.everest.mvc.filter.method;

import org.everest.decorator.RequestFilter;
import org.everest.mvc.filter.Filter;
import org.everest.mvc.filter.annatotion.FilterFour;
import org.everest.mvc.httpContext.Request;
import org.everest.mvc.httpContext.Response;
import org.everest.mvc.httpContext.decorator.FilterMethod;
import org.everest.mvc.result.Next;

@RequestFilter
public class FilterFourMethod extends Filter<FilterFour> {
    public void init(FilterFour annotation) {

    }

    @FilterMethod
    public Next executeFilter(Request request, Response Response) {
        return Next();
    }
}
