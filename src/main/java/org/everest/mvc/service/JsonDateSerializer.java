package org.everest.mvc.service;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.joda.time.DateTime;

import java.io.IOException;

public class JsonDateSerializer extends StdSerializer<DateTime> {
    public JsonDateSerializer() {
        this(null);
    }
    public JsonDateSerializer(Class<DateTime> t) {
        super(t);
    }

    @Override
    public void serialize(DateTime dateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(dateTime.toString());
    }
}
