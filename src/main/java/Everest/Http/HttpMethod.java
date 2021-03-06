package Everest.Http;

public enum HttpMethod {
    POST("POST"),
    GET("GET"),
    DELETE("DELETE"),
    PUT("PUT"),
    OPTIONS("OPTIONS"),
    PATH("PATH");

    private String description;
    HttpMethod(String desc){
        description = desc;
    }

    @Override
    public String toString() {
        return description;
    }
}
