package Everest.Mvc.ActionResultExecutor;

import Everest.Http.HttpContext;
import Everest.Http.HttpResponse;
import Everest.Mvc.ResponseFormatter.ResponseFormatContext;
import Everest.Mvc.ResponseFormatter.ResponseFormatter;
import Everest.Mvc.Result.EntityResult;
import org.everest.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntityResultExecutor implements IResultExecutor<EntityResult> {
    private Logger logger = LoggerFactory.getLogger(EntityResultExecutor.class);
    private ResponseFormatter formatter;

    public EntityResultExecutor(ResponseFormatter formatter) {
        this.formatter = formatter;
    }

    public void execute(HttpContext context, EntityResult result) {
        HttpResponse response = context.getResponse();
        if(StringUtils.isEmpty(result.getContentType())){
            result.setContentType(context.getOptions().getResponseContentType());
        }

        response.setContentType(result.getContentType());

        if(result.getStatusCode() != 0){
            response.setStatusCode(result.getStatusCode());
        }

        logger.info("Response: [content-type={}", response.contentType());
        formatter.format(new ResponseFormatContext(context, result.getBody()));
    }
}
