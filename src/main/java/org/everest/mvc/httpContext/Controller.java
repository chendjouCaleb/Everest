package org.everest.mvc.httpContext;

import Everest.Http.StatusCode;
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

    protected  <T> ResponseEntity<T> ResponseEntity(T entity, StatusCode status){
        return new ResponseEntity<>(entity, status);
    }

    protected StatusCode Ok(){
        return StatusCode.OK;
    }

    protected  <T> ResponseEntity<T> ResponseEntity(T entity){
        return new ResponseEntity<>(entity, StatusCode.OK);
    }
    protected  <T> ResponseEntity<T> Ok(T entity){
        return new ResponseEntity<>(entity, StatusCode.OK);
    }

    protected  <T> ResponseEntity<T> Created(T entity){
        return new ResponseEntity<>(entity, StatusCode.CREATED);
    }

    protected StatusCode NoContent(){
        return StatusCode.NO_CONTENT;
    }

    protected  <T> ResponseEntity<T> NoFound(T entity){
        return new ResponseEntity<>(entity, StatusCode.NOT_FOUND);
    }

    final protected JSON Json(Object models){
        return new JSON(models);
    }

    protected String viewPathPrefix(){
        return "";
    }
}
