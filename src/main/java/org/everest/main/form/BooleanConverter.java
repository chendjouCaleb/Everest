package org.everest.main.form;

import org.apache.commons.beanutils.Converter;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class BooleanConverter implements Converter{


    @Override
    public Object convert(Class booleanClass, Object o) {
        return o != null;
    }
}
