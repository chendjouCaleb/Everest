package org.everest.mvc.binding;


import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.everest.decorator.Instance;
import org.everest.mvc.binding.convert.DateTimeConverter;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Instance
public class ModelBinder implements IModelBinder {


    private List<IConverter> converters = new ArrayList<>();
    public ModelBinder(){
        addConverter(new DateTimeConverter());
    }


    public void addConverter(IConverter converter) {
        Class target = (Class) ((ParameterizedType) converter.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
        Class source = (Class) ((ParameterizedType) converter.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[1];
        ConvertUtils.register(new Converter() {
            @Override
            public <T> T convert(Class<T> aClass, Object o) {
                return (T) converter.convert(o);
            }
        }, target);
    }

    @Override
    public List<IConverter> getConverters() {
        return converters;
    }

    @Override
    public void bind(Object obj, Map<String, ?> values) {
        try {
            BeanUtils.populate(obj, values);
        } catch (Exception e) {
            throw new ConversionException(e);
        }
    }

    @Override
    public <T> T bind(Class<T> type, Map<String, ?> values) {
        Object obj;
        try {
            obj = type.newInstance();
            BeanUtils.populate(obj, values);
            return (T) obj;
        } catch (Exception e) {
            throw new ConversionException(e);
        }

    }

    @Override
    public <T> T convert(Object object, Class<T> target) {
        try {
            T o = (T) ConvertUtils.convert(object.toString(), target);
            return o;
        } catch (Exception ex) {
            throw new ConversionException(ex);
        }

    }
}
