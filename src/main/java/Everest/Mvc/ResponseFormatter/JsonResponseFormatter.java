package Everest.Mvc.ResponseFormatter;

import Everest.Http.MediaType;
import org.everest.mvc.service.JsonConverter;

public class JsonResponseFormatter implements IResponseFormatter {
    private JsonConverter jsonConverter;

    public JsonResponseFormatter(JsonConverter jsonConverter) {
        this.jsonConverter = jsonConverter;
    }

    @Override
    public String[] getMediaTypes() {
        return new String[]{
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_JSON_UTF8_VALUE
        };
    }


    public void writeResponseBody(ResponseFormatContext context) {
        String response = jsonConverter.toJSON(context.getObject());
        context.getHttpContext().getResponse().write(response);
    }
}
