package org.everest.core.dic.service;

import dic.AutoInject;
import org.everest.core.dic.decorator.AutoWired;
import org.everest.core.dic.decorator.Bean;
import org.everest.core.dic.decorator.Resolve;
import org.everest.utils.Assert;
import org.everest.utils.ReflexionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DependencyInspector {
    private Logger logger = LoggerFactory.getLogger(DependencyInspector.class);
    public List<Class> getDependentTypesByFields(Class type){
        StringBuilder message = new StringBuilder();
        message.append("Field dependencies: {} => [ ");
        List<Field> fields = ReflexionUtils.findFieldsByAnnotation(type, AutoWired.class, AutoInject.class, Inject.class, Resolve.class);
        List<Class> types = new ArrayList<>();
        fields.forEach(field -> {
            types.add(field.getType());
            message.append(field.getType().getSimpleName()).append("; ");
        });
        message.append(" ]");
        logger.info(message.toString(), type.getSimpleName());
        return types;
    }
    public List<Class> getDependencyType(Class type){
        List<Class> types = new ArrayList<>();
        Assert.state(type.getDeclaredConstructors().length == 1,
                "Le classe'" + type.getSimpleName() + "' doit posséder une seule constructeur");
        Constructor constructor = type.getDeclaredConstructors()[0];
        types.addAll(getDependentTypesByConstructor(constructor));

        types.addAll(getDependentTypesByFields(type));

        return types;
    }

    public List<Class> getDependentTypesByMethod(Method method){
        StringBuilder message = new StringBuilder();
        message.append("Method: {} => [ ");
        List<Parameter> parameters = Arrays.asList(method.getParameters());
        List<Class> types = new ArrayList<>();
        parameters.forEach(parameter -> {
            types.add(parameter.getType());
            message.append(parameter.getType().getSimpleName()).append("; ");
        });
        message.append(" ]");
        logger.info(message.toString(), method.getName());
        return types;
    }

    public List<Class> getDependentTypesByConstructor(Constructor constructor){
        StringBuilder message = new StringBuilder();
        message.append("Constructor dependencies: {} => [ ");
        List<Parameter> parameters = Arrays.asList(constructor.getParameters());
        List<Class> types = new ArrayList<>();
        parameters.forEach(parameter -> {
            types.add(parameter.getType());
            message.append(parameter.getType().getSimpleName()).append("; ");
        });
        message.append(" ]");
        logger.info(message.toString(), constructor.getName());
        return types;
    }
}
