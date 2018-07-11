package org.everest.mvc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.net.SocketFlow;
import org.everest.mvc.httpContext.Response;

import java.io.IOException;

public class JSONService{
    public String toJSON(Object object) {
        ObjectMapper mapper = new ObjectMapper();

        String objString = null;
        try {
            objString = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return objString;
    }

    public void sendJSON(Response response, Object object) throws IOException {
        String objString = toJSON(object);
        response.getServletResponse().setContentType("application/json");
        response.getServletResponse().getWriter().print(objString);

    }
}
