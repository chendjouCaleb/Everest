package Everest.Http;

import java.io.BufferedReader;
import java.io.InputStream;

public class DefaultHttpRequest extends HttpRequest {
    private String method;
    private String scheme;
    private boolean isHttps;
    private String pathBase;
    private String path;
    private InputStream inputStream;
    private BufferedReader reader;
    private boolean hasFormContentType;
    private String  contentType;
    private String protocol;
    private QueryString queryString;
    private Long contentLength;

    private FormCollection forms = new FormCollection();
    private QueryCollection query = new QueryCollection();
    private HeaderCollection headers = new HeaderCollection();


    public String method() {
        return method;
    }

    @Override
    public String scheme() {
        return scheme;
    }

    @Override
    public boolean isHttps() {
        return isHttps;
    }

    @Override
    public String pathBase() {
        return pathBase;
    }

    @Override
    public String path() {
        return path;
    }

    @Override
    public QueryString queryString() {
        return queryString;
    }

    @Override
    public QueryCollection query() {
        return query;
    }

    @Override
    public String protocol() {
        return protocol;
    }

    @Override
    public HeaderCollection headers() {
        return headers;
    }

    @Override
    public Long contentLength() {
        return contentLength;
    }

    @Override
    public String contentType() {
        return contentType;
    }

    @Override
    public boolean hasFormContentType() {
        return hasFormContentType;
    }

    @Override
    public FormCollection forms() {
        return forms;
    }

    @Override
    public InputStream inputStream() {
        return inputStream;
    }

    @Override
    public BufferedReader reader() {
        return reader;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public void setHttps(boolean https) {
        isHttps = https;
    }

    public void setPathBase(String pathBase) {
        this.pathBase = pathBase;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    public void setHasFormContentType(boolean hasFormContentType) {
        this.hasFormContentType = hasFormContentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setQueryString(QueryString queryString) {
        this.queryString = queryString;
    }

    public void setContentLength(Long contentLength) {
        this.contentLength = contentLength;
    }

    public void setForms(FormCollection forms) {
        this.forms = forms;
    }

    public void setQuery(QueryCollection query) {
        this.query = query;
    }

    public void setHeaders(HeaderCollection headers) {
        this.headers = headers;
    }
}
