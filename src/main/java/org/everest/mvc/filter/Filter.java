package org.everest.mvc.filter;


import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;

public interface Filter<T> {
    void init(T annotation);
    void execute(Request request, Response Response, FilterChain filterChain);
}
