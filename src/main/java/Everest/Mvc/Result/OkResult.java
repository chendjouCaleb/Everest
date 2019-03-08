package Everest.Mvc.Result;

import Everest.Http.StatusCode;

/**
 * An {@link StatusCodeResult } that when executed will produce an empty
 * {@link Everest.Http.StatusCode#OK"} response.
 */
public class OkResult extends StatusCodeResult{
    private static final int defaultStatusCode = StatusCode.OK.value();

    public OkResult(){
        super(defaultStatusCode);
    }
}
