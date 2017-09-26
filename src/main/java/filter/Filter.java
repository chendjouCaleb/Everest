package filter;

import component.http.Request;
import component.http.Response;

public interface Filter<T> {
    public void init(T annotation);
    public void execute(Request request, Response Response, FilterChain filterChain);
}
