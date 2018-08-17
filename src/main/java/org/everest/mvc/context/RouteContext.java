package org.everest.mvc.context;

import org.apache.commons.beanutils.ConvertUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RouteContext {
    private Map<String, String> parameters = new HashMap<>();
    private List<String> parameterNames = new ArrayList<>();

    public String getParameter(String name){
        return parameters.get(name);
    }

    public <T> T getParameter(String name, Class<? extends T> type){
        return (T) ConvertUtils.convert(getParameter(name), type);
    }

    public String getParameter(int index){
        String name = parameterNames.get(index);
        return parameters.get(name);
    }

    public <T> T getParameter(int index, Class<? extends T> type){
        return (T) ConvertUtils.convert(getParameter(index), type);
    }
    public int getIntParameter(int index){
        return Integer.valueOf(getParameter(index));
    }
    public int getIntParameter(String name){
        return Integer.valueOf(parameters.get(name));
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public List<String> getParameterNames() {
        return parameterNames;
    }

    public void setParameterNames(List<String> parameterNames) {
        this.parameterNames = parameterNames;
    }
}
