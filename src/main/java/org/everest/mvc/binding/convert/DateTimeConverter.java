package org.everest.mvc.binding.convert;

import org.everest.mvc.binding.IConverter;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateTimeConverter implements IConverter<DateTime, String> {
    @Override
    public DateTime convert(String object) {
        DateTimeFormatter longFormatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
        DateTimeFormatter shortFormatter = DateTimeFormat.forPattern("dd/MM/yyyy");
        if( object == null || object.length() < 1) return null;
        DateTime time;
        try {
            time = longFormatter.parseDateTime(object);
        }catch (IllegalArgumentException e){
            time = shortFormatter.parseDateTime(object);
        }
        return  time;
    }
}
