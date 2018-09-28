package org.everest.core.dic;

import org.apache.el.util.ReflectionUtil;
import org.everest.core.dic.contract.*;
import org.everest.core.dic.decorator.Bean;
import org.everest.core.dic.enumeration.Scope;
import org.everest.core.dic.handler.*;
import org.everest.core.dic.service.*;
import org.everest.decorator.Component;
import org.everest.decorator.Repository;
import org.everest.utils.Assert;
import org.everest.utils.ReflexionUtils;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
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
    private ContainerEventHandler containerEventHandler;

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
        containerEventHandler = new ContainerEventHandler(this);
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
        containerEventHandler.executeAfterContainerInitialized();

        logger.info("Class candidates: {}", getInstanceList().size());
    }

    void initializeContainerFilter(){
        List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());
        FilterBuilder filterBuilder = new FilterBuilder();
        packageNames.forEach(name -> filterBuilder.include(FilterBuilder.prefix(name)));

        //filterBuilder.include(FilterBuilder.prefix("org"));
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
                .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
                .filterInputsBy(filterBuilder));

        Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);
        List<Class> classForFilter = new ArrayList<>();
        classForFilter.addAll(classes);

        //logger.info();

        logger.info("There are {} class to filter", classes.size());

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
        instanceList.add(instance);
        logger.info("new instance " + instance);
        return instance;

    }

    public Object getInstance(String key){
        return retrieverService.getInstance(key).getInstance();
    }

    public <T> T getInstance(Class<? extends T> clazz){
        return (T) retrieverService.getInstance(clazz).getInstance();
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

    public ITypeFilter getTypeFilter(Class<? extends ITypeFilter> type){
        return typeFilters.stream().filter(f -> f.getClass().equals(type))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Aucun ITypeFilter de type '" + type + "' n'a été trouvé"));
    }

    public List<Instance> getInstanceList() {
        List<Instance> instanceList = new ArrayList<>();
        instanceList.addAll(typeInstances);
        instanceList.addAll(factoryInstances);
        return instanceList;
    }

    public IRetrieverService getRetrieverService() {
        return retrieverService;
    }
}
