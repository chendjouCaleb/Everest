package org.everest.mvc.filter;

import org.everest.mvc.result.IFilterResult;
import org.everest.mvc.result.Next;
import org.everest.mvc.result.Redirection;
import org.everest.mvc.result.RouteRedirection;

public abstract class Filter<T> implements IFilter<T>{
    protected Next Next(){
        return new Next();
    }

    protected RouteRedirection RouteRedirection(String target, Object... params){
        return new RouteRedirection(target, params);
    }

    protected IFilterResult Redirection(String url){
        return new Redirection(url);
    }
}
