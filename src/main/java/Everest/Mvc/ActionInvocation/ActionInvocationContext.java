package Everest.Mvc.ActionInvocation;

import javax.annotation.Nonnull;
import java.lang.reflect.Method;

/**
 * Contains all the elements for execution and handling the result of the invocation of action method
 */
public class ActionInvocationContext {

    public ActionInvocationContext(@Nonnull Object controller, @Nonnull Method method, @Nonnull Object[] params) {
        this.controller = controller;
        this.method = method;
        this.params = params;
    }

    public ActionInvocationContext() {
    }

    /**
     * The object controller of the action method
     */
    private Object controller;

    /**
     * The method of the action
     */
    private Method method;

    /**
     * The Resolved action method parameter
     */
    private Object[] params;


    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
