package Everest.Mvc.Result;

import Everest.Http.StatusCode;

/**
 * An {@link IActionResult } that when executed will produce a response
 * with an object setBody an {@link Everest.Http.StatusCode"}.
 * @param <T> The type of the response setBody
 */
public class EntityResult<T> {

    /**
     * Create an {@link EntityResult} with an setBody
     * @param body The setBody of the response
     */
    public EntityResult(T body){
        this.body = body;
    }
    /**
     * The content representing the setBody of the response.
     */
    private T body;

    /**
     * The Content-Type header for the response.
     */
    private String contentType;


    /**
     * The HTTP status code.
     */
    private int statusCode = 200;

    /**
     * Set the HTTP status code of the response.
     * @param statusCode the HTTP status code
     */
    public EntityResult setStatusCode(int statusCode){
        this.statusCode = statusCode;
        return this;
    }

    /**
     * Set the HTTP status code of the response.
     * @param statusCode the {@link StatusCode } HTTP status code
     */
    public EntityResult setStatusCode(StatusCode statusCode){
        this.statusCode = statusCode.value();
        return this;
    }

    /**
     * Set the setBody representing the setBody of the response.
     * @param body The setBody of the response.
     */
    public EntityResult setBody(T body) {
        this.body = body;
        return this;
    }

    /**
     * Set the Content-Type header for the response.
     * @param contentType The Content-Type header for the response.
     */
    public EntityResult setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public T getBody() {
        return body;
    }

    public String getContentType() {
        return contentType;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
