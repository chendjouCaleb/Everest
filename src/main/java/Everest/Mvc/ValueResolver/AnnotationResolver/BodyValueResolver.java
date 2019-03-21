package Everest.Mvc.ValueResolver.AnnotationResolver;

import Everest.Core.Exception.InputOutputException;
import Everest.Http.HttpContext;
import Everest.Http.HttpRequest;
import Everest.Mvc.ValueResolver.Annotations.BodyValue;
import Everest.Mvc.ValueResolver.IAnnotationValueResolver;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.everest.mvc.service.JsonConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Resolver to convert the request body to a target class.
 */
public class BodyValueResolver implements IAnnotationValueResolver<BodyValue> {
    private Logger logger = LoggerFactory.getLogger(BodyValueResolver.class);
    private JsonConverter converter;

    public BodyValueResolver(JsonConverter converter) {
        this.converter = converter;
    }

    @Override
    public Object getVariable(HttpContext httpContext, Parameter parameter, BodyValue annotation) {
        String body = getStringBody(httpContext.getRequest());
        logger.info("body string: [{}]", body);

        Object object = converter.toObject(body, parameter.getType());
        logger.info("Body object: {}", object);
        return  object;
    }

    private String getStringBody(HttpRequest httpRequest){
        BufferedReader reader = httpRequest.reader();
        StringBuilder builder = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            reader.close();
        } catch (IOException e) {
            throw new InputOutputException(e);
        }

        return builder.toString();
    }
}
