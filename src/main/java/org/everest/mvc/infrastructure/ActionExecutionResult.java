package org.everest.mvc.infrastructure;

public class ActionExecutionResult {
    private Object objectResult;
    private boolean success;
    private Throwable error;

    public Object getObjectResult() {
        return objectResult;
    }

    public void setObjectResult(Object objectResult) {
        this.objectResult = objectResult;
    }

    public boolean isSuccess() {
        return objectResult != null;
    }



    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }
}
