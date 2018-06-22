package org.everest.mvc.filter.method;

import org.everest.decorator.RequestFilter;
import org.everest.mvc.filter.Filter;
import org.everest.mvc.filter.annatotion.FilterThree;
import org.everest.mvc.httpContext.Request;
import org.everest.mvc.httpContext.Response;
import org.everest.mvc.result.IFilterResult;
import org.everest.mvc.result.Render;

@RequestFilter
public class FilterThreeMethod extends Filter<FilterThree> {
    @Override
    public void init(FilterThree annotation) {

    }

    public IFilterResult execute(Request request, Response Response) {
        return RouteRedirection("");
    }
}
