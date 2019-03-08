package Everest.Mvc.ActionResultExecutor;

import Everest.Http.HttpContext;
import Everest.Mvc.ResponseFormatter.ResponseFormatContext;
import Everest.Mvc.ResponseFormatter.ResponseFormatter;
import org.everest.mvc.http.ResponseEntity;

@Deprecated
public class ResponseEntityResultExecutor implements IResultExecutor<ResponseEntity> {

    private ResponseFormatter formatter;

    public ResponseEntityResultExecutor(ResponseFormatter formatter) {
        this.formatter = formatter;
    }

    public void execute(HttpContext context, ResponseEntity result) {
        context.getResponse().setStatusCode(result.getStatusCode());

        formatter.format(new ResponseFormatContext(context, result.getBody()));
    }
}
