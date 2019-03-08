package org.everest.mvc.service;

import Everest.Http.HttpResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.everest.decorator.Instance;
import org.everest.exception.OperationException;
import org.joda.time.DateTime;

import java.io.IOException;

@Instance
public class JsonConverter {
    private ObjectMapper mapper;

    public JsonConverter() {
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

    public ObjectMapper getMapper() {
        return mapper;
    }
}
