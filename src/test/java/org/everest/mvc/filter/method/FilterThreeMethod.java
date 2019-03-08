package org.everest.mvc.filter.method;

import Everest.Http.HttpRequest;
import org.everest.decorator.RequestFilter;
import org.everest.mvc.filter.Filter;
import org.everest.mvc.filter.annatotion.FilterThree;
import Everest.Http.HttpResponse;
import org.everest.mvc.result.IFilterResult;

@RequestFilter
public class FilterThreeMethod extends Filter<FilterThree> {
    @Override
    public void init(FilterThree annotation) {

    }

    public IFilterResult execute(HttpRequest httpRequest, HttpResponse HttpResponse) {
        return RouteRedirection("");
    }
}
