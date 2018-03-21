package org.everest.main.form.converter;

import org.apache.commons.beanutils.Converter;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class BooleanConverter implements Converter{


    @Override
    public Object convert(Class booleanClass, Object o) {
        return o != null;
    }

    public static class DateConverter implements Converter{


        @Override
        public Object convert(Class DateTimeClass, Object o) {
            DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
            String date = (String)o;
            if( date == null || date.length() < 1) return null;
            return formatter.parseDateTime(date);


        }
    }

    public static class DateTimeConverter implements Converter{


        @Override
        public Object convert(Class DateTimeClass, Object o) {
            DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
            String date = (String)o;
            if( date == null || date.length() < 1) return null;
            return formatter.parseDateTime(date);
        }
    }

    public static class IntegerConverter implements Converter{


        @Override
        public Object convert(Class clazz, Object o) {
            if(o == null){
                return null;
            }
            String obj = o.toString();
            if(obj.length() == 0){
                return null;
            }
            return Integer.valueOf(obj);

        }
    }
}
