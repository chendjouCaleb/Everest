package Everest.Mvc.ResponseFormatter;

import Everest.Http.DefaultHttpContext;
import Everest.Http.DefaultHttpResponse;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

class PlainTextResponseFormatterTest {
    PlainTextResponseFormatter formatter = new PlainTextResponseFormatter();
    @Test
    void writeResponseBody() {
        DefaultHttpContext httpContext = new DefaultHttpContext();

        String result = "plain text result";
        ResponseFormatContext formatContext = new ResponseFormatContext(httpContext, result);

        formatter.writeResponseBody(formatContext);

        assertEquals(result, httpContext.getResponse().writer().toString());
    }
}