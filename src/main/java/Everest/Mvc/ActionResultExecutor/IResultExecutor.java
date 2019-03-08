package Everest.Mvc.ActionResultExecutor;


import Everest.Http.HttpContext;

public interface IResultExecutor<T> {
    void execute(HttpContext httpContext, T result);
}
