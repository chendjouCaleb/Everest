package org.everest.mvc.router;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Route {
    private String path;
    private Method method;
    private String name;
    private Object controller;
    private String regex;
    private String[] parameters = new String[]{};
    private String httpMethod;

    public void setPath(String path) {
        path = path.replaceAll("^/", "").replaceAll("/$", "");
        this.path = path;
        String parsedPath = path.replaceAll(":\\w+", "(\\\\w+)");
        parsedPath = parsedPath.replaceAll("[/]", "[/]?");
        setRegex(parsedPath);
    }

    public String url(Object... params){
        String url = path;
        for (int i = 0; i < params.length; i++) {
            url = url.replaceFirst(":\\w+|[(].+[)]", params[i].toString());
        }

        return url;
    }

    public boolean matche(String url){
        url = url.replaceAll("^/", "").replaceAll("/$", "");
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);
        if (matcher.matches()) {
            parameters = new String[matcher.groupCount()];
            for (int j = 0; j < matcher.groupCount(); j++){
                parameters[j] = matcher.group(j+1);
            }
            return true;
        }
        return false;
    }



    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public String getPath() {
        return path;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParameters(){

    }
    public String[] getParameters(){
        return parameters;
    }
    public String[] getParams(){
        return parameters;
    }

    public String data(int index){
        if(index < parameters.length ){
            return parameters[index];
        }
        return null;
    }

    public String[] getData(int[] index){
        String[] datas = new String[index.length];
        for (int i = 0; i < index.length; i++){
            datas[i] = data(index[i]);
        }
        return datas;
    }

    @Override
    public String toString() {
        return "Route{" +
                "path='" + path + '\'' +
                ", method=" + method.getName() +
                ", name='" + name + '\'' +
                ", controller=" + controller.getClass().getSimpleName() +
                ", regex='" + regex + '\'' +
                ", parameters=" + Arrays.toString(parameters) +
                '}';
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }
}
