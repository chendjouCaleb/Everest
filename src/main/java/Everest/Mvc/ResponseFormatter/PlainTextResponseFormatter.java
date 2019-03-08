package Everest.Mvc.ResponseFormatter;

import Everest.Http.DefaultHttpContext;
import Everest.Http.DefaultHttpResponse;
import Everest.Http.MediaType;
import Everest.Mvc.ActionInvocation.ActionInvocationContext;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class PlainTextResponseFormatter implements IResponseFormatter {
    public String[] getMediaTypes() {
        return new String[]{MediaType.TEXT_PLAIN_VALUE};
    }


    public void writeResponseBody(ResponseFormatContext context) {
        String response = context.getObject().toString();
        context.getHttpContext().getResponse().write(response);
    }
}
