package Everest.Servlet.Http;


import Everest.Core.Exception.InputOutputException;
import Everest.Core.Exception.InvalidOperationException;
import Everest.Http.HeaderCollection;
import Everest.Http.HttpResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * The {@link Everest.Http.HttpResponse} provider by servletAPI
 */
public class ServletResponseImpl extends HttpResponse {
    private HttpServletResponse servletResponse;

    public ServletResponseImpl(HttpServletResponse servletResponse) {
        if(servletResponse == null){
            throw new NullPointerException("The HttpServletResponse is null");
        }
        this.servletResponse = servletResponse;
    }

    private Long contentLength;


    @Override
    public void setStatusCode(int code) {
       servletResponse.setStatus(code);
    }

    @Override
    public int statusCode() {
        return  servletResponse.getStatus();
    }

    @Override
    public String contentType() {
        return servletResponse.getContentType();
    }

    @Override
    public void setContentType(String contentType) {
        servletResponse.setContentType(contentType);
    }

    @Override
    public void addHeader(String key, String value) {
        servletResponse.setHeader(key, value);
    }

    @Override
    public String getHeader(String key) {
        return servletResponse.getHeader(key);
    }

    @Override
    public Long contentLength() {
        return contentLength;
    }

    @Override
    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
        servletResponse.setContentLength((int) contentLength);
    }

    @Override
    public boolean hasStarted() {
        return servletResponse.isCommitted();
    }

    @Override
    public OutputStream outputStream() {
        try {
            return servletResponse.getOutputStream();
        } catch (IOException e) {
            throw new InputOutputException(e);
        }
    }

    @Override
    public void setOutputStream(OutputStream stream) {
        throw new InvalidOperationException("Servlet provider not implement this method");
    }

    @Override
    public HeaderCollection headers() {
        return null;
    }

    @Override
    public void redirect(String url) {
        try {
            servletResponse.sendRedirect(url);
        } catch (IOException e) {
            throw new InputOutputException(e);
        }
    }

    @Override
    public Writer writer() {
        try {
            return servletResponse.getWriter();
        }catch (IOException e) {
            throw new InputOutputException(e);
        }

    }

    @Override
    public void write(String content) {
        try {
            writer().write(content);
        } catch (IOException e) {
            throw new InputOutputException(e);
        }
    }
}
