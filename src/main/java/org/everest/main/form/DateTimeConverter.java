package org.everest.main.form;

import org.apache.commons.beanutils.Converter;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateTimeConverter implements Converter{


    @Override
    public Object convert(Class DateTimeClass, Object o) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
        String date = (String)o;
        if( date == null || date.length() < 1) return null;
        return formatter.parseDateTime(date);
    }
}
