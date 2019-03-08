package Everest.Mvc.Result;

public class ContentResult {

    /**
     * The content representing the setBody of the response.
     */
    private String content;

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
    public ContentResult statusCode(int statusCode){
        this.statusCode = statusCode;
        return this;
    }

    /**
     * Set the content representing the setBody of the response.
     * @param content The setBody of the response.
     */
    public ContentResult content(String content) {
        this.content = content;
        return this;
    }

    /**
     * Set the Content-Type header for the response.
     * @param contentType The Content-Type header for the response.
     */
    public ContentResult contentType(String contentType) {
        this.contentType = contentType;
        return this;
    }


}
