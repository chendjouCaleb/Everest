package org.everest.mvc.filter;

import org.everest.mvc.result.*;

public abstract class Filter<T> implements IFilter<T>{
    protected Next Next(){
        return new Next();
    }

    final protected Render Render(String viewPath, Object object){
        return new Render(viewPath, object);
    }


    protected RouteRedirection RouteRedirection(String target, Object... params){
        return new RouteRedirection(target, params);
    }

    protected IFilterResult Redirection(String url){
        return new Redirection(url);
    }
    final protected JSON Json(Object models){
        return new JSON(models);
    }
}
