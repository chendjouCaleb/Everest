package Everest.Middleware;

import Everest.Http.HttpContext;
import org.everest.mvc.infrastructure.MvcRunner;

public class MvcMiddleware implements IMiddleware {
    private MvcRunner mvcRunner;

    public MvcMiddleware(MvcRunner mvcRunner) {
        this.mvcRunner = mvcRunner;
    }

    @Override
    public void execute(MiddlewareChain chain, HttpContext httpContext) {
        mvcRunner.run(httpContext);
    }
}
