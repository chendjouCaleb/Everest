package filter;


import org.everest.main.component.http.Request;
import org.everest.main.component.http.Response;

public interface Filter<T> {
    public void init(T annotation);
    public void execute(Request request, Response Response, FilterChain filterChain);
}
