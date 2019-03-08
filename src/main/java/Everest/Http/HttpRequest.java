package Everest.Http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Represent the incoming Http Request
 * @author Chendjou deGrace
 */
public abstract class HttpRequest {

    /**
     * the HTTP method.
     * @return The HTTP method.
     */
    public abstract String method();

    /**
     * Gets the HTTP request scheme.
     * @return The HTTP request scheme.
     */
    public abstract String scheme();

    /**
     * Returns true if the RequestScheme is https.
     * @return true if this request is using https; otherwise, false.
     */
    public abstract boolean isHttps();

    /**
     * Gets the RequestPathBase.
     * @return The RequestPathBase.
     */
    public abstract String pathBase();


    /**
     * Gets the request path from RequestPath.
     * @return The request path from RequestPath.
     */
    public abstract String path();

    /**
     * Gets the raw query string used to create the query collection in Request.Query.
     * @return The raw query string.
     */
    public abstract QueryString queryString();


    /**
     * Gets the query value collection parsed from Request.QueryString.
     * @return The query value collection parsed from Request.QueryString.
     */
    public abstract QueryCollection query();

    /**
     * Gets or sets the request protocol (e.g. HTTP/1.1).
     * @return The request protocol.
     */
    public abstract String protocol();


    /**
     * Gets the request headers.
     * @return The request headers.
     */
    public abstract HeaderCollection headers();


    /**
     * Gets the Content-Length header.
     * @return The value of the Content-Length header, if any.
     */
    public abstract Long contentLength();


    /**
     * Gets the Content-Type header.
     * @return The Content-Type header.
     */
    public abstract String contentType();

    /**
     * Checks the Content-Type header for form types.
     * @return true if the Content-Type header represents a form content type; otherwise, false.
     */
    public abstract boolean hasFormContentType();

    /**
     * Gets the request setBody as a form.
     * @return
     */
    public abstract FormCollection forms();


    /**
     * Gets the RequestBody Stream.
     * @return The RequestBody Stream.
     */
    public abstract InputStream inputStream();

    /**
     * Gets the RequestBody reader Stream.
     * @return The RequestBody reader
     */
    public abstract BufferedReader reader();

}
