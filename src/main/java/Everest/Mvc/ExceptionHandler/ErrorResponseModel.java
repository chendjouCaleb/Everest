package Everest.Mvc.ExceptionHandler;

public class ErrorResponseModel {
    public ErrorResponseModel(Throwable e){
        errorType = e.getClass().getSimpleName();
        message = e.getMessage();
    }
    private String errorType;
    private String helpLink;
    private String message;
    private int statusCode;

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getHelpLink() {
        return helpLink;
    }

    public void setHelpLink(String helpLink) {
        this.helpLink = helpLink;
    }

    @Override
    public String toString() {
        return "ErrorResponseModel{" +
                "errorType='" + errorType + '\'' +
                ", helpLink='" + helpLink + '\'' +
                ", message='" + message + '\'' +
                ", statusCode=" + statusCode +
                '}';
    }
}
