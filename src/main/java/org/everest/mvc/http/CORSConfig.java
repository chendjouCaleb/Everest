package org.everest.mvc.http;

public class CORSConfig {
    Boolean allowCredentials = true;
    String allowedOrigin;
    String allowedMethods;
    String allowedHeaders = "Origin, Content-Type, X-Auth-Token, Authorization";

    public Boolean getAllowCredentials() {
        return allowCredentials;
    }

    public void setAllowCredentials(Boolean allowCredentials) {
        this.allowCredentials = allowCredentials;
    }

    public String getAllowedOrigin() {
        return allowedOrigin;
    }

    public void setAllowedOrigin(String allowedOrigin) {
        this.allowedOrigin = allowedOrigin;
    }

    public String getAllowedMethods() {
        return allowedMethods;
    }

    public void setAllowedMethods(String allowedMethods) {
        this.allowedMethods = allowedMethods;
    }

    public String getAllowedHeaders() {
        return allowedHeaders;
    }

    public void setAllowedHeaders(String allowedHeaders) {
        this.allowedHeaders = allowedHeaders;
    }

    @Override
    public String toString() {
        return "CORSConfig{" +
                "allowCredentials=" + allowCredentials +
                ", allowedOrigin='" + allowedOrigin + '\'' +
                ", allowedMethods='" + allowedMethods + '\'' +
                ", allowedHeaders='" + allowedHeaders + '\'' +
                '}';
    }
}
