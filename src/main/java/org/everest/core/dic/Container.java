package org.everest.core.dic;

import org.everest.core.dic.contract.*;
import org.everest.core.dic.decorator.Bean;
import org.everest.core.dic.enumeration.Scope;
import org.everest.core.dic.handler.*;
import org.everest.core.dic.service.*;
import org.everest.decorator.Component;
import org.everest.decorator.Repository;
import org.everest.utils.Assert;
import org.everest.utils.ReflexionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.*;

public class Container {
    private Logger logger = LoggerFactory.getLogger(Container.class);

    private List<TypeInstance> typeInstances = new ArrayList<>();
    private List<FactoryInstance> factoryInstances = new ArrayList<>();
    private List<Instance> instanceList = new ArrayList<>();
    private IContainerService containerService = new ContainerService();
    private IDependencyResolver dependencyResolver;
    private IRetrieverService retrieverService;
    private InstanceBuilder instanceBuilder = new InstanceBuilder();
    private InstanceValidator instanceValidator = new InstanceValidator();
    private ContainerFilter containerFilter = new ContainerFilter();

    private List<String> packageNames = new ArrayList<>();
    private List<Class> candidateClasses = new ArrayList<>();

    private List<Class<? extends Annotation>> factoryAnnotations = new ArrayList<>();
    private List<Class<? extends Annotation>> typesAnnotations = new ArrayList<>();

    private Map<Class<? extends Annotation>,ITypeAnnotationHandler> typeAnnotationHandlers = new HashMap<>();
    private Map<Class<? extends Annotation>,IFactoryAnnotationHandler> factoryAnnotationHandlers = new HashMap<>();
    private List<ITypeFilter> typeFilters = new ArrayList<>();

    public Container(){
        addTypeAnnotationHandler(org.everest.decorator.Instance.class, new InstanceAnnotationHandler());
        addTypeAnnotationHandler(Component.class, new ComponentAnnotationHandler());
        addTypeAnnotationHandler(Repository.class, new RepositoryAnnotationHandler());
        addFactoryAnnotationHandler(Bean.class, new BeanFactoryAnnotationHandler());
        addFactoryAnnotationHandler(org.everest.decorator.Instance.class, new InstanceFactoryAnnotationHandler());
        addTypeFilter(new ControllerFilter());
    }

    public void initialize(){
        logger.info("Base package: {}", packageNames);
        initializeContainerFilter();
        addSingletonObject(this);
        typeInstances.addAll(instanceBuilder.createTypeInstances(containerFilter.getAnnotatedFoundedClass()));
        typeInstances.addAll(instanceBuilder.createTypeInstances(containerFilter.getFilteredFoundedClass()));
        factoryInstances.addAll(instanceBuilder.createFactoryInstances(containerFilter.getFoundedMethod()));
        candidateClasses = containerFilter.getCandidates();
        setInstanceList();
        retrieverService = new RetrieverService(instanceList);
        dependencyResolver = new IDependencyResolverImpl(retrieverService);
        dependencyResolver.setParentToFactoryInstance();

        instanceValidator.checkDependenciesAvailability(getInstanceList());
        instanceValidator.checkCircularDependencies(getInstanceList());

        dependencyResolver.resolveInstance();

        containerService.setInstanceKey(typeInstances);

        logger.info("Class candidates: {}", getInstanceList().size());
    }

    void initializeContainerFilter(){
        List<Class> classForFilter = ReflexionUtils.getClasses(packageNames);

        containerFilter.setMethodAnnotations(factoryAnnotations);
        containerFilter.setTypeAnnotations(typesAnnotations);
        containerFilter.setTypeFilters(typeFilters);

        containerFilter.setClassForFilter(classForFilter);
        containerFilter.doClassFilter();
        containerFilter.summarize();
    }




    public Instance findInstance(Class type){
        Instance instance = instanceList.stream().filter(ins -> ins.getRegisteredType().equals(type)).findFirst().orElse(null);
        if (instance == null){
            throw new NoSuchElementException("L'instance de type '" + type + "' est introuvable");
        }
        return instance;
    }

    void setInstanceList(){
        instanceList.addAll(typeInstances);
        instanceList.addAll(factoryInstances);
    }

    public Instance addTransient(Class registeredType, Class concreteType){
        TypeInstance instance = instanceBuilder.createInstance(concreteType);
        instance.setRegisteredType(registeredType);
        instance.setScope(Scope.TRANSIENT);
        typeInstances.add(instance);
        return instance;
    }
    public Instance addTransient(Class concreteType){
        return addTransient(concreteType, concreteType);
    }
    public Instance addSingleton(Class registeredType, Class concreteType){
        Instance instance = addTransient(registeredType, concreteType);
        instance.setScope(Scope.SINGLETON);
        return instance;
    }
    public Instance addSingleton(Class concreteType){
        return addSingleton(concreteType, concreteType);
    }

    public Instance addSingletonObject(Object object){
        TypeInstance instance = instanceBuilder.createInstanceByObject(object);
        instance.setScope(Scope.SINGLETON);
        typeInstances.add(instance);
        return instance;
    }
    public Instance addInstance(Object object){
        return addSingletonObject(object);
    }

    public Instance addInstance(Object object, String name){
        TypeInstance instance = instanceBuilder.createInstanceByObject(object);
        instance.setScope(Scope.SINGLETON);
        instance.setKey(name);
        typeInstances.add(instance);
        return instance;
    }

    public Instance getInstance(String key){
        return retrieverService.getInstance(key);
    }

    public Instance getInstance(Class clazz){
        return retrieverService.getInstance(clazz);
    }


    public int countInstances(){
        return instanceList.size();
    }
    public void printAllComponent(){
        instanceList.forEach( instance -> System.out.println(instance.toString()));
    }

    public void addPackage(String... packageNames){
        this.packageNames.addAll(Arrays.asList(packageNames));
    }

    public void addClass(Class type){
        Assert.isFalse(candidateClasses.contains(type), "Le tye '" + type + "' à déjà été enregistré au sein du container");
        candidateClasses.add(type);
    }

    public <T extends Annotation> void addTypeAnnotationHandler(Class<T> annotation, ITypeAnnotationHandler<T> handler) {
        typesAnnotations.add(annotation);
        typeAnnotationHandlers.put(annotation, handler);
    }

    public <T extends Annotation> void addFactoryAnnotationHandler(Class<T> annotation, IFactoryAnnotationHandler<T> annotationHandler) {
        factoryAnnotations.add(annotation);
        factoryAnnotationHandlers.put(annotation, annotationHandler);
    }

    public void addTypeFilter(ITypeFilter typeFilter) {
        typeFilters.add(typeFilter);
    }

    public List<Instance> getInstanceList() {
        List<Instance> instanceList = new ArrayList<>();
        instanceList.addAll(typeInstances);
        instanceList.addAll(factoryInstances);
        return instanceList;
    }
}
