package Everest.Middleware;

import Everest.Mvc.ExceptionHandler.ExceptionHandlerMiddleware;

import javax.annotation.Nonnull;
import java.util.ArrayList;

/**
 * Register the {@link IMiddleware} class and and it in the middleware pipeline
 */
public class MiddlewareRegister {
    private ArrayList<Class<? extends IMiddleware>> middlewares = new ArrayList<>();

    public void use(@Nonnull Class<? extends IMiddleware> middlewareType) {
        middlewares.add(middlewareType);
    }

    public ArrayList<Class<? extends IMiddleware>> getMiddlewares() {
        return middlewares;
    }

    public void setMiddlewares(ArrayList<Class<? extends IMiddleware>> middlewares) {
        this.middlewares = middlewares;
    }

    public void useCors(){
        use(CorsMiddleware.class);
    }

    public void useDefaultExceptionHandler(){
        use(ExceptionHandlerMiddleware.class);
    }

    /**
     * Add the Mvc component the the request pipeline
     */
    public void useMvc(){
        use(MvcMiddleware.class);
    }
}
