package org.everest.mvc.httpContext;

import org.everest.mvc.http.ResponseEntity;
import org.everest.mvc.result.*;

import java.util.Map;

public abstract class Controller {
    final protected Render Render(String viewPath, Object... objects){
        return new Render(viewPathPrefix()+viewPath, objects);
    }

    final protected View View(String viewPath){
        return new View(viewPath);
    }

    final protected RouteRedirection RouteRedirection(String target, Object... params) {
        return new RouteRedirection(target, params);
    }
    final protected RouteRedirection RouteRedirection(String target, Map<String, String> params) {
        return new RouteRedirection(target, params);
    }

    final protected Redirection Redirection(String url){
        return new Redirection(url);
    }
    final protected Redirection Redirection(String url, HttpStatus status){
        return (Redirection) new Redirection(url).statusCode(status.value());
    }

    protected  <T> ResponseEntity<T> ResponseEntity(T entity, org.everest.mvc.http.HttpStatus status){
        return new ResponseEntity<>(entity, status);
    }

    protected org.everest.mvc.http.HttpStatus Ok(){
        return org.everest.mvc.http.HttpStatus.OK;
    }

    protected  <T> ResponseEntity<T> ResponseEntity(T entity){
        return new ResponseEntity<>(entity, org.everest.mvc.http.HttpStatus.OK);
    }
    protected  <T> ResponseEntity<T> Ok(T entity){
        return new ResponseEntity<>(entity, org.everest.mvc.http.HttpStatus.OK);
    }

    final protected JSON Json(Object models){
        return new JSON(models);
    }

    protected String viewPathPrefix(){
        return "";
    }
}
