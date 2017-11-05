package filter;

import component.http.Controller;
import component.http.Request;
import component.http.Response;
import main.Utils;
import router.RouterUtils;

import java.util.ArrayList;
import java.util.List;

public class FilterChain {
    private List<Filter> filters = new ArrayList<>();
    private String target;
    private Controller controller;
    private Object[] targetParams;
    private int index = 0;

    public void addFilter(Filter filter){
        filters.add(filter);
    }
    public void execute(Request request, Response response){
        if(filters.size() > 0){
            System.out.println("NOMBRE DE FILTRE: " + filters.size());
            Filter filter = filters.get(index);
            filter.execute(request, response, this);
        }else{
            try {
                Utils.callRemote(controller, target, targetParams);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void doNext(Request request, Response response){
        this.index +=1;
        if(index < filters.size()){
            Filter filter = filters.get(index);
            filter.execute(request, response, this);

        }else {
            try {
                Utils.callRemote(controller, target, targetParams);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setTarget(String target){
        this.target = target;
    }

    public void setTargetParams(Object[] targetParams) {
        this.targetParams = targetParams;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
