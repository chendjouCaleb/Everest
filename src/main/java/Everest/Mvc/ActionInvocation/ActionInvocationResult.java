package Everest.Mvc.ActionInvocation;

import Everest.Core.Exception.InvalidOperationException;

/**
 * Represent the result of an execution of action method
 */
public class ActionInvocationResult {

    /**
     * The object returned by the action method execution
     */
    private Object objectResult;

    /**
     * Must be true if the return type of the action method is void
     */
    private boolean isVoid = false;

    /**
     * Must be true if the returned object by the execution is null
     */
    private boolean isNull = false;

    /**
     * Get the type of the object return by the method invocation
     * @return The type of the object return by the method invocation
     */
    public Class getResultType(){
        if(objectResult != null){
            return objectResult.getClass();
        }
        throw new InvalidOperationException("The object is null. The methid is not invoked or the return type is void");
    }

    public Object getObjectResult() {
        return objectResult;
    }

    public void setObjectResult(Object objectResult) {
        this.objectResult = objectResult;
    }

    public boolean isVoid() {
        return isVoid;
    }

    public void setVoid(boolean aVoid) {
        isVoid = aVoid;
    }

    public boolean isNull() {
        return isNull;
    }

    public void setNull(boolean aNull) {
        isNull = aNull;
    }
}
