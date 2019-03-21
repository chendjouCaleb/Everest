package Everest.Http;

import Everest.Core.Exception.InputOutputException;

import java.io.*;

public class DefaultHttpResponse extends HttpResponse {
    private int statusCode;
    private Long contentLength;
    private String contentType;
    private HeaderCollection headers = new HeaderCollection();
    private OutputStream outputStream;
    private Writer writer = new StringWriter();
    public void setStatusCode(int code) {
        this.statusCode = code;
    }

    @Override
    public int statusCode() {
        return statusCode;
    }

    @Override
    public String contentType() {
        return contentType;
    }

    @Override
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    @Override
    public String getHeader(String key) {
        return headers.get(key);
    }

    @Override
    public Long contentLength() {
        return contentLength;
    }

    @Override
    public void setContentLength(long contentLength) {
this.contentLength = contentLength;
    }

    @Override
    public boolean hasStarted() {
        return false;
    }

    @Override
    public OutputStream outputStream() {
        return outputStream;
    }

    @Override
    public void setOutputStream(OutputStream stream) {
        this.outputStream = stream;
    }

    @Override
    public HeaderCollection headers() {
        return headers;
    }

    @Override
    public void redirect(String url) {

    }

    @Override
    public Writer writer() {
        return writer;
    }

    @Override
    public void write(String content) {
        try {
            writer.write(content);
        } catch (IOException e) {
            throw new InputOutputException(e);
        }
    }

    @Override
    public void reset() {

    }

    public void setContentLength(Long contentLength) {
        this.contentLength = contentLength;
    }

    public void setHeaders(HeaderCollection headers) {
        this.headers = headers;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }
}
