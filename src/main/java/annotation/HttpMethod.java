package annotation;

public enum HttpMethod {
    POST("POST"),
    GET("GET"),
    DELETE("DELETE"),
    PUT("PUT");

    private String description;
    HttpMethod(String desc){
        description = desc;
    }

    @Override
    public String toString() {
        return description;
    }
}
