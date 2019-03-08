package Everest.Mvc.ResponseFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseFormatterSelectorTest {
private ResponseFormatterSelector selector;

    @BeforeEach
    void setUp() {
        selector = new ResponseFormatterSelector();
    }

    @Test
    void addTransformers() {
        IResponseFormatter fake1 = new Fake1Formatter();
        IResponseFormatter fake2 = new Fake2Formatter();
        selector.addFormatter(fake1);
        selector.addFormatter(fake2);

        assertEquals(3, selector.getFormatters().size());
        assertTrue(selector.getFormatters().contains(fake1));
        assertTrue(selector.getFormatters().contains(fake2));
    }

    @Test
    void getFormatter() {
        IResponseFormatter fake1 = new Fake1Formatter();
        IResponseFormatter fake2 = new Fake2Formatter();
        selector.addFormatter(fake1);
        selector.addFormatter(fake2);

        assertEquals(fake1, selector.getFormatter("fake1/content"));
        assertEquals(fake2, selector.getFormatter("fake2/content"));
        assertEquals(fake2, selector.getFormatter("fake3/content"));
    }
}

class Fake1Formatter implements IResponseFormatter{

    public String[] getMediaTypes() {
        return new String[]{"fake1/content"};
    }

    @Override
    public void writeResponseBody(ResponseFormatContext context) {  }
}

class Fake2Formatter implements IResponseFormatter{

    public String[] getMediaTypes() {
        return new String[]{"fake2/content", "fake3/content"};
    }

    @Override
    public void writeResponseBody(ResponseFormatContext context) {  }
}