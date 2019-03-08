package Everest.Mvc.ResponseFormatter;

import Everest.Http.HttpContext;
import Everest.Http.HttpResponse;
import org.everest.decorator.Instance;
import org.everest.utils.StringUtils;


@Instance
public class ResponseFormatter {
    private ResponseFormatterSelector formatterSelector;

    public ResponseFormatter(ResponseFormatterSelector formatterSelector) {
        this.formatterSelector = formatterSelector;
    }

    public void format(ResponseFormatContext context){
        HttpContext httpContext = context.getHttpContext();
        HttpResponse response = httpContext.getResponse();

        String contentType = response.contentType();
        if(StringUtils.isEmpty(contentType)){
            contentType = httpContext.getOptions().getResponseContentType();
        }

        IResponseFormatter formatter = formatterSelector.getFormatter(contentType);

        formatter.writeResponseBody(context);
    }
}
