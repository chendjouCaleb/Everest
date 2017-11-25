package filter;

import component.http.Request;
import component.http.Response;

import java.util.ArrayList;
import java.util.List;

public class FilterChain {
    private List<Filter> filters = new ArrayList<>();
    private int index = 0;

    public void addFilter(Filter filter){
        filters.add(filter);
    }
    public void execute(Request request, Response response){
        if(filters.size() > 0){
            System.out.println("NOMBRE DE FILTRE: " + filters.size());
            Filter filter = filters.get(index);
            filter.execute(request, response, this);
        }
    }

    public void doNext(Request request, Response response) {
        this.index += 1;
        if (index < filters.size()) {
            Filter filter = filters.get(index);
            filter.execute(request, response, this);

        }
    }
}
