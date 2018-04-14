package org.everest.context.classHandler;

import org.apache.commons.beanutils.ConvertUtils;
import org.everest.context.ApplicationContext;
import org.everest.decorator.Converter;
import org.everest.decorator.Repository;

public class ConverterHandler implements ClassHandler<Converter>{
    @Override
    public void handleClass(Class<?> clazz, Converter annotation, ApplicationContext context) {
        if(annotation.value().equals("")){
            context.addInstance(clazz);
        }else {
            context.addInstance(clazz, annotation.value());
        }

        ConvertUtils.register((org.apache.commons.beanutils.Converter) context.getInstance(clazz), annotation.target());
    }
}
