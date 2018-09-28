package org.everest.context;


import org.everest.core.dic.Container;
import org.everest.core.dic.decorator.AfterContainerInitilized;
import org.everest.core.event.EventEmitter;
import org.everest.decorator.Listener;
import org.everest.mvc.actionResultHandler.ActionResultHandler;
import org.everest.mvc.actionResultHandler.IResultHandler;
import org.everest.mvc.actionResultHandler.ResponseBodyHandler;
import org.everest.mvc.binding.IConverter;
import org.everest.mvc.binding.IModelBinder;
import org.everest.mvc.binding.IModelValidator;
import org.everest.mvc.binding.ModelValidator;
import org.everest.mvc.classFilter.*;
import org.everest.mvc.errorHandler.ErrorEventManager;
import org.everest.mvc.errorHandler.IErrorHandler;
import org.everest.mvc.filter.Filter;
import org.everest.mvc.httpContext.Controller;
import org.everest.mvc.infrastructure.RouteBuilder;
import org.everest.mvc.infrastructure.RouteLoader;
import org.everest.mvc.responseBody.IResponseBodyTransformer;
import org.everest.mvc.service.IRequestBodyHandler;
import org.everest.mvc.service.RequestBodyHandler;
import org.everest.mvc.variableResolver.IVariableResolverByAnnotation;
import org.everest.mvc.variableResolver.IVariableResolverByType;
import org.everest.mvc.variableResolver.RequestVariableResolver;
import org.hibernate.validator.HibernateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.*;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public class ApplicationContext {
    private Logger logger = LoggerFactory.getLogger(ApplicationContext.class);
    private Container container;
    private List<IResultHandler> actionResultHandlers = new ArrayList<>();
    private List<Filter> requestFilters = new ArrayList<>();
    private List<Object> eventListeners = new ArrayList<>();
    private List<IConverter> converters = new ArrayList<>();
    private List<IErrorHandler> errorHandlers = new ArrayList<>();
    private List<IVariableResolverByType> variableResolverByTypes = new ArrayList<>();
    private List<IVariableResolverByAnnotation> variableResolverByAnnotations = new ArrayList<>();
    private List<IRequestBodyHandler> requestBodyHandlers = new ArrayList<>();
    private List<IResponseBodyTransformer> responseBodyTransformers = new ArrayList<>();
    private List<ConstraintValidator> constraintValidators = new ArrayList<>();
    private List<Controller> controllers = new ArrayList<>();
    public ApplicationContext(){
        container = new Container();
        container.addPackage("org.everest.mvc");
        container.addSingletonObject(this);
        container.addTypeFilter(new RequestFilterClass());
        container.addTypeFilter(new EventListenerClassFilter());
        container.addTypeFilter(new ConverterClassFilter());
        container.addTypeFilter(new ErrorManagerClassFilter());
        container.addTypeFilter(new VariableResolverByAnnotationClassFilter());
        container.addTypeFilter(new VariableResolverByTypeClassFilter());
        container.addTypeFilter(new ActionResultHandlerClassFilter());
        container.addTypeFilter(new RequestBodyHandlerClassFilter());
        container.addTypeFilter(new ResponseBodyTransformerClassFilter());
        container.addTypeFilter(new ValidatorClassFilter());

    }

    public <T> T getInstance(String key, Class<? extends T> clazz){
        return (T) container.getInstance(key);
    }
    public <T> T getInstance(Class<? extends T> clazz){
        return (T) container.getInstance(clazz);
    }
    public void initialize(){

        container.initialize();
    }

    public Container getContainer(){
        return container;
    }

    @AfterContainerInitilized
    public void setActionResultHandlers(Container container) {
        this.actionResultHandlers = container.getRetrieverService().getObjectByInterface(IResultHandler.class);
        ActionResultHandler actionResultHandler = container.getInstance(ActionResultHandler.class);
        actionResultHandlers.forEach(actionResultHandler::addActionResultHandler);
        logger.info("Action Result Handlers = {}", actionResultHandlers.size());
    }

    @AfterContainerInitilized
    public void setRequestFilters(Container container) {
        this.requestFilters = container.getRetrieverService().getObjectBySuperType(Filter.class);
        logger.info("Request Filters = {}", requestFilters.size());
    }

    @AfterContainerInitilized
    public void setEventListeners(Container container) {
        this.eventListeners = container.getRetrieverService().getObjectByAnnotation(Listener.class);
        EventEmitter eventEmitter = container.getInstance(EventEmitter.class);
        eventEmitter.addListeners(this.eventListeners);
        logger.info("Event Listeners = ", eventListeners.size());
    }

    @AfterContainerInitilized
    public void setConverters(Container container) {
        this.converters = container.getRetrieverService().getObjectByInterface(IConverter.class);
        IModelBinder modelBinder = container.getInstance(IModelBinder.class);
        converters.forEach(modelBinder::addConverter);
        logger.info("Converters = {}", converters.size());
    }

    @AfterContainerInitilized
    public void setErrorHandlers(Container container) {
        this.errorHandlers = container.getRetrieverService().getObjectByInterface(IErrorHandler.class);
        ErrorEventManager errorEventManager = container.getInstance(ErrorEventManager.class);
        errorHandlers.forEach(errorEventManager::addErrorHandler);
        logger.info("Error Handlers = {}", errorHandlers.size());
    }

    @AfterContainerInitilized
    public void setVariableResolverByTypes(Container container) {
        this.variableResolverByTypes = container.getRetrieverService().getObjectByInterface(IVariableResolverByType.class);
        RequestVariableResolver variableResolver = container.getInstance(RequestVariableResolver.class);
        variableResolverByTypes.forEach(variableResolver::addVariableResolverByType);
        logger.info("Variable Resolver By Types = {}", variableResolverByTypes.size());
    }

    @AfterContainerInitilized
    public void setVariableResolverByAnnotations(Container container) {
        this.variableResolverByAnnotations = container.getRetrieverService().getObjectByInterface(IVariableResolverByAnnotation.class);
        RequestVariableResolver variableResolver = container.getInstance(RequestVariableResolver.class);
        variableResolverByAnnotations.forEach(variableResolver::addVariableResolverByAnnotation);
        logger.info("Variable Resolver By Annotations = {}", variableResolverByAnnotations.size());
    }

    @AfterContainerInitilized
    public void setRequestBodyHandlers(Container container) {
        this.requestBodyHandlers = container.getRetrieverService().getObjectByInterface(IRequestBodyHandler.class);
        RequestBodyHandler requestBodyHandler = container.getInstance(RequestBodyHandler.class);
        requestBodyHandlers.forEach(requestBodyHandler::addHandler);
        logger.info("Body handlers = {}", requestBodyHandlers.size());

    }

    @AfterContainerInitilized
    public void setResponseBodyTransformers(Container container) {
        this.responseBodyTransformers = container.getRetrieverService().getObjectByInterface(IResponseBodyTransformer.class);
        ResponseBodyHandler responseBodyHandler = container.getInstance(ResponseBodyHandler.class);
        responseBodyTransformers.forEach(responseBodyHandler::addTransformers);
        logger.info("Response Body Transformers = {}", responseBodyTransformers.size());
    }

    @AfterContainerInitilized
    public void setConstraintValidators(Container container) {
        this.constraintValidators = container.getRetrieverService().getObjectByInterface(ConstraintValidator.class);
        ConstraintValidatorFactory constraintValidatorFactory = container.getInstance(ConstraintValidatorFactory.class);
        constraintValidators.forEach(constraintValidatorFactory::releaseInstance);
        logger.info("ConstraintValidators = {}", constraintValidators.size());
    }

    @AfterContainerInitilized
    public void setControllers(Container container) {
        this.controllers = container.getRetrieverService().getObjectBySuperType(Controller.class);
        RouteLoader routeLoader = container.getInstance(RouteLoader.class);
        RouteBuilder routeBuilder = container.getInstance(RouteBuilder.class);
        routeLoader.load(controllers);
        routeBuilder.setRouteModels(routeLoader.getRoutes());
        logger.info("Controllers = {}", controllers.size());
    }

    @Override
    public String toString() {
        return "ApplicationContext{" +
                "container=" + container +
                ", actionResultHandlers=" + actionResultHandlers.size() +
                ", requestFilters=" + requestFilters.size() +
                ", eventListeners=" + eventListeners.size() +
                ", converters=" + converters.size() +
                ", errorHandlers=" + errorHandlers.size() +
                ", variableResolverByTypes=" + variableResolverByTypes.size() +
                ", variableResolverByAnnotations=" + variableResolverByAnnotations.size() +
                ", requestBodyHandlers=" + requestBodyHandlers.size() +
                ", responseBodyTransformers=" + responseBodyTransformers.size() +
                ", constraintValidators=" + constraintValidators.size() +
                ", controllers=" + controllers.size() +
                '}';
    }

    public List<IResultHandler> getActionResultHandlers() {
        return actionResultHandlers;
    }

    public List<Filter> getRequestFilters() {
        return requestFilters;
    }

    public List<Object> getEventListeners() {
        return eventListeners;
    }

    public List<IConverter> getConverters() {
        return converters;
    }

    public List<IErrorHandler> getErrorHandlers() {
        return errorHandlers;
    }

    public List<IVariableResolverByType> getVariableResolverByTypes() {
        return variableResolverByTypes;
    }

    public List<IVariableResolverByAnnotation> getVariableResolverByAnnotations() {
        return variableResolverByAnnotations;
    }

    public List<IRequestBodyHandler> getRequestBodyHandlers() {
        return requestBodyHandlers;
    }

    public List<IResponseBodyTransformer> getResponseBodyTransformers() {
        return responseBodyTransformers;
    }

    public List<Controller> getControllers() {
        return controllers;
    }
}
