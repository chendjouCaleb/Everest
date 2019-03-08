package Everest.Http;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.Writer;

/***
 * @author chendjou deGrace
 * @version 0.0.2
 * Represent the http response
 */
public abstract class HttpResponse {

    /**
     * Sets the HTTP response code.
     * @param code The response status code
     */
    public abstract void setStatusCode(int code);

    /**
     * Gets the HTTP response code.
     * @return the HTTP response code
     */
    public abstract int statusCode();


    /**
     * Gets the value for the <c>Content-Type</c> response header.
     * @return The response ContentType
     */
    public abstract String contentType();

    /**
     * Sets the value for the <c>Content-Type</c> response header.
     * @param contentType The response Content-Type
     */
    public abstract void setContentType(String contentType);

    /**
     * Add header to the response
     * @param key The key of the header
     * @param value The value of the header
     */
    public abstract void addHeader(String key, String value);


    /**
     * Get a header of the response
     * @param key The key of the header
     * @return The value of the header
     */
    public abstract String getHeader(String key);

    /**
     * Gets the value for the <c>Content-Length</c> response header.
     * @return The response Content-Length
     */
    public abstract Long contentLength();

    /**
     * Sets the value for the <c>Content-Length</c> response header.
     * @param contentLength The response Content-Length
     */
    public abstract void setContentLength(long contentLength);

    /**
     * Gets a value indicating whether response headers have been sent to the client.
     * @return Whether response headers have been sent to the client.
     */
    public abstract boolean hasStarted();
    private HttpServletResponse servletResponse;

    /**
     * Gets the response setBody
     * @return The response setBody
     */
    public abstract OutputStream outputStream();

    /**
     * Sets the response setBody
     * @param stream The response setBody
     */
    public abstract void setOutputStream(OutputStream stream);

    /**
     * Gets the response headers.
     * @return The response headers
     */
    public abstract HeaderCollection headers();

    /**
     * Returns a temporary redirect response (HTTP 302) to the client.
     * @param url The URL to redirect the client to. This must be properly encoded for use in http headers
     * where only ASCII characters are allowed.
     */
    public abstract void redirect(String url);

    /**
     * The Writer to write the string or char in response setBody
     * @return The response writer
     */
    public abstract Writer writer();

    /**
     * Write charSequence in response setBody
     * @param content Content to write in response
     */
    public abstract void write(String content);
}
