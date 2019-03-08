package Everest.Mvc.Result;

import Everest.Http.StatusCode;

/**
 * Represents an {@link UnauthorizedResult } that when
 * executed will produce an Unauthorized (401) response.
 */
public class UnauthorizedResult extends StatusCodeResult {
    private static final int defaultStatusCode = StatusCode.UNAUTHORIZED.value();

    public UnauthorizedResult(){
        super(defaultStatusCode);
    }


}
