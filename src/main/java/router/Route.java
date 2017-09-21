package router;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Route {
    String path;
    Method method;
    String name;
    Class<?> controller;
    String regex;
    String[] parameters;

    public void setPath(String path) {
        path = path.replaceAll("^/", "").replaceAll("/$", "");
        this.path = path;
        String parsedPath = path.replaceAll(":\\w+", "(\\\\w+)");
        parsedPath = parsedPath.replaceAll("[/]", "[/]?");
        setRegex(parsedPath);
    }

    public String url(String... params){
        String url = path;
        System.out.println("REGEX: " + regex);
        for (int i = 0; i < params.length; i++) {
            url = url.replaceFirst(":\\w+|[(].+[)]", params[i]);
        }
        return url;
    }

    public boolean matche(String url){
        url = url.replaceAll("^/", "").replaceAll("/$", "");
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);
        if (matcher.matches()) {
            System.out.println("METHODE: " + method.getName());
            parameters = new String[matcher.groupCount()];
            for (int j = 0; j < matcher.groupCount(); j++){
                System.out.println(j + " : " + matcher.group(j+1));
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

    public Class<?> getController() {
        return controller;
    }

    public void setController(Class<?> controller) {
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
}
