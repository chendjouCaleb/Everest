package org.everest.mvc.component;

import org.everest.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class UrlGenerator {
    private String urlModel;
    private String url;
    private String queryString;
    private String matrixString;
    private String host;
    private int port;
    private String protocol = "http";


    private Map<String, String> pathParams = new HashMap<>();
    private Map<String, String> queryParams = new HashMap<>();
    private Map<String, String> matrixParams = new HashMap<>();
    private Map<String, String> params = new HashMap<>();

    public String generate(){
        url = injectPathParams();
        queryString = createQueryString();
        matrixString = createMatrixString();
        if(queryString.length() > 0){
            url += "?" + queryString;
        }
        url += matrixString;

        if(port != 0){
            url = ":" + port + "/" + StringUtils.trim(url, "/");
        }
        if(host != null){
            url = host + "/" + StringUtils.trim(url, "/");
            // To replace ':/' placed before PORT

            url = url.replaceFirst("/:", ":");
        }


        return url;
    }

    private String injectPathParams(){
        String pathUrl = urlModel;
        for (Map.Entry<String, String> entry: pathParams.entrySet()){
            pathUrl = pathUrl.replaceAll("\\{" + entry.getKey() + "\\}",entry.getValue());
        }
        for (Map.Entry<String, String> entry: params.entrySet()){
            pathUrl = pathUrl.replaceAll("\\{" + entry.getKey() + "\\}",entry.getValue());
        }
        return pathUrl;
    }
    private String createQueryString(){
        StringBuilder queryString = new StringBuilder();
        for (Map.Entry<String, String> entry: queryParams.entrySet()){
            queryString.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }

        for (Map.Entry<String, String> entry: params.entrySet()){
            if(!isPathParam(entry.getKey()))
                queryString.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }

        return queryString.toString().replaceFirst("&$", "");
    }

    private String createMatrixString(){
        StringBuilder matrixString = new StringBuilder();
        for (Map.Entry<String, String> entry: matrixParams.entrySet()){
            matrixString
                    .append(";")
                    .append(entry.getKey())
                    .append("=")
                    .append(entry.getValue());
        }
        return matrixString.toString();
    }

    boolean isPathParam(String name){
        return urlModel.contains("{" + name + "}");
    }
    public UrlGenerator(String urlModel) {
        this.urlModel = urlModel;
    }

    public UrlGenerator(String urlModel, Map<String, String> params) {
        this.urlModel = urlModel;
        this.params = params;
    }

    public UrlGenerator host(String host) {
        this.host = host;
        return this;
    }

    public UrlGenerator port(int port) {
        this.port = port;
        return this;
    }

    public UrlGenerator protocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    public UrlGenerator addParam(String key, String name){
        params.put(key, name);
        return this;
    }

    public UrlGenerator addPathParam(String key, String name){
        pathParams.put(key, name);
        return this;
    }

    public UrlGenerator addQueryParam(String key, String name){
        queryParams.put(key, name);
        return this;
    }

    public UrlGenerator addMatrixParam(String key, String name){
        matrixParams.put(key, name);
        return this;
    }
}
