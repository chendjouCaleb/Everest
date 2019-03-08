package Everest.Mvc.Result;

import Everest.Core.Exception.NullArgumentException;
import Everest.Http.StatusCode;

/**
 * Represents an {@link IActionResult} that when executed will
 *  produce an HTTP response with the given response status code.
 */
public class StatusCodeResult implements IActionResult {
    private Integer statusCode;

    public StatusCodeResult(Integer statusCode) {
        if(statusCode == null){
            throw new NullArgumentException();
        }
        this.statusCode = statusCode;
    }

    public StatusCodeResult(StatusCode statusCode){
        this(statusCode.value());
    }

    /**
     * Gets the HTTP status code.
     * @return The HTTP status code
     */
    public Integer getStatusCode() {
        return statusCode;
    }
}
