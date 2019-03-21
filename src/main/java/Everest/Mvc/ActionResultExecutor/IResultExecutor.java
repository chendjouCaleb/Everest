package Everest.Mvc.ActionResultExecutor;


import Everest.Http.HttpContext;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IResultExecutor<T> {
    void execute(HttpContext httpContext, T result) throws IOException;
}
