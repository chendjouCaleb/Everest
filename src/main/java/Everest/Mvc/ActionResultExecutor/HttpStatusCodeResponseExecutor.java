package Everest.Mvc.ActionResultExecutor;

import Everest.Http.StatusCode;
import Everest.Http.HttpContext;
import Everest.Mvc.ActionInvocation.ActionInvocationContext;

public class HttpStatusCodeResponseExecutor implements IResultExecutor<StatusCode> {

    public void execute(HttpContext httpContext, StatusCode code) {

        httpContext.getResponse().setStatusCode(code.value());
        httpContext.getResponse().write("");
    }
}
