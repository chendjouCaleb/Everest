package org.everest.mvc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import jdk.net.SocketFlow;
import org.everest.decorator.Instance;
import org.everest.exception.OperationException;
import org.everest.mvc.httpContext.Response;
import org.joda.time.DateTime;

import java.io.IOException;

@Instance
public class JSONService{
    private ObjectMapper mapper;

    public JSONService() {
        this.mapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(DateTime.class, new JsonDateSerializer());
        mapper.registerModule(simpleModule);
    }

    public String toJSON(Object object) {
        String objString;
        try {
            objString = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erreur survenue lors de la conversion de l'object de type: "
                    + object.getClass().getName() + " en JSON", e);

        }
        return objString;
    }

    public <T> T toObject(String jsonString, Class<T> type){
        try {
            Object obj = mapper.readValue(jsonString, type);
            return (T) obj;
        } catch (IOException e) {
            e.printStackTrace();
            throw new OperationException("Erreur lors de la conversion de la chaine: '" + jsonString + "' en type"
                    + type.getSimpleName());

        }
    }

    public void sendJSON(Response response, Object object) throws IOException {
        String objString = toJSON(object);
        response.getServletResponse().setContentType("application/json");
        response.getServletResponse().getWriter().print(objString);
    }

    public ObjectMapper getMapper() {
        return mapper;
    }
}
