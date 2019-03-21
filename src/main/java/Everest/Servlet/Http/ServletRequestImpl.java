package Everest.Servlet.Http;

import Everest.Core.Exception.InputOutputException;
import Everest.Http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * The {@link Everest.Http.HttpRequest} provider by servletAPI
 */
public class ServletRequestImpl extends HttpRequest {
    private Logger logger = LoggerFactory.getLogger(ServletRequestImpl.class);
    private HttpServletRequest servletRequest;
    private QueryCollection queryCollection;
    private FormCollection formCollection;
    private HeaderCollection headerCollection;
    private String path;

    public ServletRequestImpl(HttpServletRequest servletRequest) {
        if(servletRequest == null){
            throw new NullPointerException("The HttpServletRequest is null");
        }
        this.servletRequest = servletRequest;

        String queryString = servletRequest.getQueryString();
        logger.info("Query string = {}", queryString);

        this.queryCollection = new QueryCollectionBuilder(queryString).parse().getCollection();

        setFormCollection(servletRequest);
        setHeaderCollection(servletRequest);

    }

    @Override
    public String method() {
        return servletRequest.getMethod();
    }

    @Override
    public String scheme() {
        return servletRequest.getScheme();
    }

    @Override
    public boolean isHttps() {
        return servletRequest.isSecure();
    }

    @Override
    public String pathBase() {
        return servletRequest.getContextPath();
    }

    @Override
    public String path() {
        return servletRequest.getPathInfo();
    }

    @Override
    public QueryString queryString() {
        return new QueryString(servletRequest.getQueryString());
    }

    @Override
    public QueryCollection query() {
        return queryCollection;
    }

    @Override
    public String protocol() {
        return servletRequest.getProtocol();
    }

    @Override
    public HeaderCollection headers() {
        return headerCollection;
    }

    @Override
    public Long contentLength() {
        return servletRequest.getContentLengthLong();
    }

    @Override
    public String contentType() {
        return servletRequest.getContentType();
    }

    @Override
    public boolean hasFormContentType() {
        String contentType = servletRequest.getContentType();
        return MediaType.MULTIPART_FORM_DATA_VALUE.equals(contentType)
                || MediaType.APPLICATION_FORM_URLENCODED_VALUE.equals(contentType);
    }

    @Override
    public FormCollection forms() {
        return formCollection;
    }

    @Override
    public InputStream inputStream() {
        try {
            return servletRequest.getInputStream();
        } catch (IOException e) {
            throw new InputOutputException(e);
        }
    }

    @Override
    public BufferedReader reader() {
        try {
            return servletRequest.getReader();
        } catch (IOException e) {
            throw new InputOutputException(e);
        }
    }

    private void setHeaderCollection(HttpServletRequest request){
        headerCollection = new HeaderCollection();

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            headerCollection.put(name, request.getHeader(name));
        }
    }

    private void setFormCollection(HttpServletRequest request){
        formCollection = new FormCollection();
        request.getParameterMap().forEach((key, values) -> {
            formCollection.put(key, Arrays.asList(values));
        });
    }
}
