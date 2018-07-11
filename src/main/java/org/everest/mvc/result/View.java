package org.everest.mvc.result;

import java.util.HashMap;
import java.util.Map;

public class View extends ActionResult implements IFilterResult{
    private String view;


    private Map<String, Object> datas = new HashMap<>();

    public View(String view, Map<String, Object> datas) {
        this.view = view;
        this.datas = datas;
    }
    public View(String view) {
        this.view = view;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    @Override
    public boolean sendResponse() {
        return false;
    }

    public Map<String, Object> getData() {
        return datas;
    }

    public void setDatas(Map<String, Object> datas) {
        this.datas = datas;
    }
    public View addData(String key, Object obj){
        datas.put(key, obj);
        return this;
    }
}
