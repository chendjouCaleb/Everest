package org.everest.mvc.errorHandler;

public class ErrorResponseModel {
    public ErrorResponseModel(Throwable e){
        classType = e.getClass().getSimpleName();
        errorMessage = e.getMessage();
        message = e.getMessage();
        cause = e.getMessage();
    }
    private String classType;
    private String uriResource;
    private String errorMessage;
    private String message;
    private String cause;
    private int statusCode;

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getUriResource() {
        return uriResource;
    }

    public void setUriResource(String uriResource) {
        this.uriResource = uriResource;
    }
}
