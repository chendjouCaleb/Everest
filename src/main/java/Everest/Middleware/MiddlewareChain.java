package Everest.Middleware;


import Everest.Core.Exception.InvalidOperationException;
import Everest.Http.HttpContext;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MiddlewareChain {
    /**
     * The index of the current {@link IMiddleware} in the chain.
     */
    private int current;

    /**
     * All the middleware of the chain.
     */
    private List<IMiddleware> middlewares = new ArrayList<>();

    /**
     * Invoke {@link IMiddleware#execute(MiddlewareChain, HttpContext)} of the next middleware
     * in the chain.
     * @param context The context of the http request
     */
    public void executeNext(@Nonnull HttpContext context){
        if(current < middlewares.size()){
            //To avoid circular call
            current++;
            middlewares.get(current-1).execute(this, context);

        }else {
            throw new InvalidOperationException("All middlewares in this request chain is already executed");
        }
    }

    /**
     * Add a new {@link IMiddleware} in the chain.
     * @param middleware The {@link IMiddleware} to add.
     */
    public void addMiddleware(@Nonnull IMiddleware middleware){
        this.middlewares.add(middleware);
    }

    public void addMiddlewares(@Nonnull Collection<IMiddleware> middlewares){
        middlewares.forEach(this::addMiddleware);
    }

    /**
     * Check that all {@link IMiddleware} in the chain have been executed
     * @return True if all {@link IMiddleware} is executed
     */
    public boolean isFinished(){
        return current == middlewares.size();
    }

    public int getCurrent() {
        return current;
    }

    public List<IMiddleware> getMiddlewares() {
        return middlewares;
    }
}
