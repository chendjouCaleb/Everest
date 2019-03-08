package Everest.Mvc.ActionResultExecutor;

import Everest.Core.Exception.InvalidOperationException;
import org.everest.decorator.Instance;

import javax.annotation.Nonnull;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

/**
 * The class must store all the {@link IResultExecutor} of the application.
 */
@Instance
public class ActionResultExecutorProvider {
    /**
     * The list of the added {@link IResultExecutor}.
     */
    private Map<Class<?>, IResultExecutor<?>> executors = new HashMap<>();

    /**
     * Add the {@link IResultExecutor} to the provider
     * If the executor don't have a generics argument, an {@link InvalidOperationException} will the throw
     * @param handler to dd
     */
    public void addExecutor(IResultExecutor<?> handler){
        try{
            Class type = (Class) ((ParameterizedType) handler.getClass().getGenericInterfaces()[0])
                    .getActualTypeArguments()[0];
            executors.put(type, handler);
        }catch (ClassCastException e){
            throw new InvalidOperationException(handler.getClass()+ " cannot added as result executor" +
                    "Only the parameterized type cant be added as result executor.");
        }

    }

    /**
     * Provider an {@link IResultExecutor} based on her parameterized type.
     * @param type The parameterized type of the {@link IResultExecutor}.
     * @return {@link IResultExecutor} corresponding to the {@param type}.
     */
    public IResultExecutor getExecutor(@Nonnull Class type){
        if(executors.containsKey(type)){
            return executors.get(type);
        }
        return null;
    }
    public void setExecutors(Map<Class<?>, IResultExecutor<?>> executors) {
        this.executors = executors;
    }

    public Map<Class<?>, IResultExecutor<?>> getExecutors() {
        return executors;
    }
}

