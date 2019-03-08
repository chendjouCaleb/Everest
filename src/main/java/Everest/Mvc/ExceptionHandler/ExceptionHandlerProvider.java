package Everest.Mvc.ExceptionHandler;

import Everest.Core.Exception.InvalidOperationException;
import org.everest.decorator.Instance;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * Store and provide a {@link IExceptionHandler}
 */
@Instance
public class ExceptionHandlerProvider {
    private HashMap<Class<? extends Throwable>, IExceptionHandler> handlers
            = new HashMap<>();

    /**
     * Add an {@link IExceptionHandler} to the provider
     * @param exceptionHandler The {@link IExceptionHandler} to add.
     * @throws InvalidOperationException If the are already handler for one
     * exception Type provider by the {@param exceptionHandler}.
     */
    public void addExceptionHandler(@Nonnull IExceptionHandler exceptionHandler){
        if(exceptionHandler.getExceptionTypes() == null){
            throw new NullPointerException("The handler " + exceptionHandler.getClass() + " dont have a handled exception class");
        }
        exceptionHandler.getExceptionTypes().forEach(exception -> {
            if(handlers.containsKey(exception)){
                throw new InvalidOperationException("Un gestionnaire d'exception a déjà été ajouté pour le type d'exception " + exception + ".");
            }
            handlers.put(exception, exceptionHandler);
        });
    }

    /**
     * Get an {@link IExceptionHandler} corresponding to {@param exceptionType}.
     * @param exceptionType The exceptionType to return the handler.
     * @return {@link IExceptionHandler} corresponding to {@param exceptionType}.
     * @throws NoSuchElementException If there are no corresponding {@link IExceptionHandler}.
     */
    public IExceptionHandler getExceptionHandler(@Nonnull Class<? extends Throwable> exceptionType){
        if(handlers.containsKey(exceptionType)){
            return handlers.get(exceptionType);
        }

        throw new NoSuchElementException("There are no Exception handler for the exception " + exceptionType + ".");
    }
    public HashMap<Class<? extends Throwable>, IExceptionHandler> getHandlers() {
        return handlers;
    }


}
