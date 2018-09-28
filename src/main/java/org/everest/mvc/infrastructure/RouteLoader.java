package org.everest.mvc.infrastructure;

import org.everest.decorator.Instance;
import org.everest.mvc.component.MappingDescriptor;
import org.everest.mvc.decorator.HttpMapping;
import org.everest.mvc.infrastructure.mapping.MappingGetter;
import org.everest.utils.Assert;
import org.everest.utils.ReflexionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import static org.everest.utils.StringUtils.trim;

@Instance
public class RouteLoader {
    private MappingGetter mappingGetter = new MappingGetter();
    private List<ControllerModel> controllers = new ArrayList<>();
    private List<RouteModel> routes = new ArrayList<>();
    private Logger logger = LoggerFactory.getLogger(RouteLoader.class);

    public void load(Collection<?> objects){
        validateName(objects);
        controllers = loadControllers(objects);
        controllers.forEach((value) -> {
            routes.addAll(loadRoutes(value));
        });
    }

    public List<ControllerModel> loadControllers(Collection<?> objects){
        List<ControllerModel> models = new ArrayList<>();

        objects.forEach(o -> models.add(loadController(o)));

        return models;
    }

    public ControllerModel loadController(Object object){
        ControllerModel controllerModel = new ControllerModel();
        HttpMapping mapping = object.getClass().getAnnotation(HttpMapping.class);
        Assert.notNull(mapping, "Le controlleur " + object.getClass().getSimpleName() + "' n'est pas décoré pas @HttpMapping");
        controllerModel.setMapping(mapping.value());
        controllerModel.setHttpMapping(mapping);
        controllerModel.setObject(object);
        controllerModel.setName(controllerName(controllerModel));
        return controllerModel;
    }

    public List<RouteModel> loadRoutes(ControllerModel controllerModel){
        List<RouteModel> routeModels = new ArrayList<>();
        List<Method> methods = ReflexionUtils.findMethodsByAnnotatedAnnotation(controllerModel.getObject().getClass(), HttpMapping.class);
        for (Method method:methods){
            RouteModel routeModel = loadRoute(controllerModel, method);
            routeModels.add(routeModel);
        }
        return routeModels;
    }

     RouteModel loadRoute(ControllerModel controllerModel, Method method) {
         Annotation annotation = ReflexionUtils.annotatedAnnotation(method, HttpMapping.class);
         MappingDescriptor descriptor = mappingGetter.mappingDescriptor(annotation);
        RouteModel routeModel = new RouteModel();

        String urlMapping = trim(controllerModel.getHttpMapping().value(), "/") + "/" + trim(descriptor.getValue(), "/");

        //Dans le cas le mapping sur la méthode est une chaine vide
        urlMapping = trim(urlMapping, "/");
        routeModel.setMapping(urlMapping);
        routeModel.setDescriptor(descriptor);
        routeModel.setVerbs(descriptor.getVerb());
        routeModel.setControllerModel(controllerModel);
        routeModel.setMethod(method);
        routeModel.setName(routeName(routeModel));
        logger.info("Route [mapping = {}, verbs = {}, method = {}, controller = {}]", urlMapping, descriptor.getVerb().toString(), method.getName(), controllerModel.getName());
        return routeModel;
    }

    private String routeName(RouteModel routeModel){
        if (!routeModel.getDescriptor().getName().equals("")){
            return routeModel.getDescriptor().getName();
        }
        String name = routeModel.getControllerModel().getName();
        name = name + "."+ routeModel.getMethod().getName();
        name = name + "#" + routeModel.getDescriptor().getVerb().toString();
        return name;
    }

    private String controllerName(ControllerModel model){
        if(!model.getHttpMapping().name().equals("")){
            return model.getHttpMapping().name();
        }
        String name = model.getObject().getClass().getSimpleName();
        name = name.replaceFirst("Controller$", "");
        return name;
    }

    public void validateName(Collection<?> objects){
        for (Object object : objects) {
            if(!object.getClass().getSimpleName().endsWith("Controller")){
                throw new IllegalStateException("Le nom de la classe '" + object.getClass()
                        + "' enregistrée comme controlleur ne se termine pas 'Controller'");
            }
        }
    }

    public List<ControllerModel> getControllers() {
        return controllers;
    }

    public List<RouteModel> getRoutes() {
        return routes;
    }
}
