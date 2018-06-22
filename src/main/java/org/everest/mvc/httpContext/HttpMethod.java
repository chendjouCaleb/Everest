package org.everest.mvc.httpContext;

public enum HttpMethod {
    POST("POST"),
    GET("GET"),
    DELETE("DELETE"),
    PUT("PUT"),
    OPTIONS("OPTIONS");

    private String description;
    HttpMethod(String desc){
        description = desc;
    }

    @Override
    public String toString() {
        return description;
    }
}
