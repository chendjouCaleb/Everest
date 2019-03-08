package Everest.Mvc.ResponseFormatter;

import Everest.Http.DefaultHttpContext;
import Everest.Http.DefaultHttpRequest;
import org.everest.mvc.service.JsonConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseFormatterTest {
    private ResponseFormatter formatter;
    private ResponseFormatterSelector selector;
    private JsonConverter jsonConverter = new JsonConverter();

    @BeforeEach
    void setUp() {
        JsonResponseFormatter jsonResponseFormatter = new JsonResponseFormatter(jsonConverter);
        selector = new ResponseFormatterSelector();
        selector.addFormatter(jsonResponseFormatter);

        formatter = new ResponseFormatter(selector);
    }

    @Test
    void format() {
        DefaultHttpContext httpContext = new DefaultHttpContext();
        TestObj obj = new TestObj("property1", 10);
        ResponseFormatContext context = new ResponseFormatContext(httpContext, obj);

        formatter.format(context);
        assertEquals(jsonConverter.toJSON(obj), httpContext.getResponse().writer().toString());

    }
}

class TestObj{
    public TestObj(String prop1, int prop2) {
        this.prop1 = prop1;
        this.prop2 = prop2;
    }

    private String prop1;
    private int prop2;

    public String getProp1() {
        return prop1;
    }

    public void setProp1(String prop1) {
        this.prop1 = prop1;
    }

    public int getProp2() {
        return prop2;
    }

    public void setProp2(int prop2) {
        this.prop2 = prop2;
    }
}