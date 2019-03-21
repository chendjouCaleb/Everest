package org.everest.core.dic.service;

import org.everest.core.dic.ContainerFilter;
import org.everest.core.dic.FactoryInstance;
import org.everest.core.dic.Instance;
import org.everest.core.dic.TypeInstance;
import org.everest.core.dic.contract.IRetrieverService;
import org.everest.core.dic.decorator.Bean;
import org.everest.core.dic.handler.ControllerFilter;
import org.everest.decorator.Component;
import org.everest.decorator.Factory;
import org.everest.decorator.Repository;
import org.everest.dic.test.builder.LuminaryFactory;
import org.everest.dic.test.component.BlackHoleComponent;
import org.everest.dic.test.component.HeapComponent;
import org.everest.dic.test.component.NebularComponent;
import org.everest.dic.test.component.PulsarComponent;
import org.everest.dic.test.controller.UniverseController;
import org.everest.dic.test.repository.GalaxyRepository;
import org.everest.dic.test.repository.IGalaxyRepository;
import org.everest.dic.test.repository.NebularRepository;
import org.everest.dic.test.service.GalaxyService;
import org.everest.dic.test.service.IGalaxyService;
import org.everest.dic.test.service.INebularService;
import org.everest.utils.ReflexionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class IDependencyValueResolverImplTest {
    IDependencyResolverImpl resolver;
    IRetrieverService retrieverService;
    InstanceBuilder instanceBuilder = new InstanceBuilder();
    private ContainerFilter containerFilter = new ContainerFilter();
    List<Instance> instances = new ArrayList<>();

    private List<Class<? extends Annotation>> typesAnnotations = new ArrayList<>();
    private List<Class<? extends Annotation>> methodAnnotations = new ArrayList<>();
    private List<String> packageNames = new ArrayList<>();

    @BeforeEach
    public void setUp() throws Exception {
        packageNames.add("org.everest.dic.test.builder");
        packageNames.add("org.everest.dic.test.component");
        packageNames.add("org.everest.dic.test.controller");
        packageNames.add("org.everest.dic.test.repository");
        typesAnnotations.add(org.everest.decorator.Instance.class);
        typesAnnotations.add(Factory.class);
        typesAnnotations.add(Repository.class);
        typesAnnotations.add(Component.class);

        methodAnnotations.add(Bean.class);
        methodAnnotations.add(org.everest.decorator.Instance.class);
        List<Class> classList = ReflexionUtils.getClasses(packageNames);

        containerFilter.setTypeAnnotations(typesAnnotations);
        containerFilter.setMethodAnnotations(methodAnnotations);
        containerFilter.addTypeFilters(new ControllerFilter());
        containerFilter.setClassForFilter(classList);
        containerFilter.doClassFilter();

        instances.addAll(instanceBuilder.createTypeInstances(containerFilter.getAnnotatedFoundedClass()));
        instances.addAll(instanceBuilder.createTypeInstances(containerFilter.getFilteredFoundedClass()));
        instances.addAll(instanceBuilder.createFactoryInstances(containerFilter.getFoundedMethod()));

        retrieverService = new RetrieverService(instances);
        resolver = new IDependencyResolverImpl(retrieverService);
        resolver.setParentToFactoryInstance();
    }

    @Test
    public void resolveConstructor() throws Exception {
        TypeInstance instance = instanceBuilder.createInstance(UniverseController.class);
        resolver.resolveConstructor(instance, new Object[]{new HeapComponent(), new NebularComponent()});
        assertNotNull(instance.getInstance());
    }

    @Test
    public void resolveConstructorInstance(){
        retrieverService.getInstance(IGalaxyService.class).setInstance(new GalaxyService());
        retrieverService.getInstance(BlackHoleComponent.class).setInstance(new BlackHoleComponent());
        retrieverService.getInstance(HeapComponent.class).setInstance(new HeapComponent());
        retrieverService.getInstance(NebularComponent.class).setInstance(new NebularComponent());
        retrieverService.getInstance(PulsarComponent.class).setInstance(new PulsarComponent());
        TypeInstance universeInstance  = (TypeInstance) retrieverService.getInstance(UniverseController.class);

        resolver.resolveInstance(universeInstance);
    }

    @Test
    public void resolveFactoryInstance(){
        retrieverService.getInstance(NebularRepository.class).setInstance(new NebularRepository());
        retrieverService.getInstance(IGalaxyRepository.class).setInstance(new GalaxyRepository());
        Instance luminaryInstance = retrieverService.getInstance( LuminaryFactory.class);
        luminaryInstance.setInstance(new LuminaryFactory());
        FactoryInstance instance = (FactoryInstance) retrieverService.getInstance(INebularService.class);
        instance.setParentInstance(luminaryInstance);
        resolver.resolveFactory(instance);
        assertNotNull(instance.getParentInstance().getInstance());
    }

    @Test
    public void  resolveAllDependencies(){
        resolver.resolveInstance();
    }
}