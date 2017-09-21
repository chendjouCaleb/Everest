package filter;

import router.Controller;
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
    public void execute(String request, String response){
        Filter filter = filters.get(index);
        filter.execute(request, response, this);
    }

    public void doNext(String request, String response){
        this.index +=1;
        System.out.println("INDEX: " + index);
        if(index < filters.size()){
            Filter filter = filters.get(index);
            filter.execute(request, response, this);

        }else {
            try {
                RouterUtils.callRemote(controller, target, targetParams);
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
