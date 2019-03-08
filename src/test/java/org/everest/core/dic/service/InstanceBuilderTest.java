package org.everest.core.dic.service;

import org.everest.core.dic.FactoryInstance;
import org.everest.core.dic.Instance;
import org.everest.core.dic.TypeInstance;
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
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class InstanceBuilderTest {
    private InstanceBuilder instanceBuilder = new InstanceBuilder();
    @Test
    public void createInstance_With_Concrete_Type() throws Exception {
        Class type = UniverseController.class;
        TypeInstance instance = instanceBuilder.createInstance(type);
        assertEquals(UniverseController.class, instance.getConcreteType());
        assertEquals(UniverseController.class, instance.getRegisteredType());
        assertEquals(HeapComponent.class, instance.getConstructorDependencies().get(0));
        assertEquals(NebularComponent.class, instance.getConstructorDependencies().get(1));

        assertEquals(IGalaxyService.class, instance.getFieldDependencies().get(0));
        assertEquals(BlackHoleComponent.class, instance.getFieldDependencies().get(1));
        assertEquals(PulsarComponent.class, instance.getFieldDependencies().get(2));
        assertEquals(5, instance.getDependencies().size());
    }

    @Test
    public void createInstance_With_Interface_Type() throws Exception {
        Class type = GalaxyService.class;
        TypeInstance typeInstance = instanceBuilder.createInstance(type);
        assertEquals(GalaxyService.class, typeInstance.getConcreteType());
        assertEquals(IGalaxyService.class, typeInstance.getRegisteredType());
    }

    @Test
    public void  createObjectInstance(){
        NebularComponent component = new NebularComponent();
        TypeInstance instance = instanceBuilder.createInstanceByObject(component);
        assertEquals(NebularComponent.class, instance.getRegisteredType());
        assertEquals(NebularComponent.class, instance.getConcreteType());
        assertEquals(component, instance.getInstance());
    }

    @Test
    public void createFactoryInstance() throws Exception {
        Instance luminaryInstance = instanceBuilder.createInstance(LuminaryFactory.class);
        Method method = LuminaryFactory.class.getMethod("nebularService", IGalaxyRepository.class, NebularRepository.class);
        FactoryInstance instance = instanceBuilder.createInstance(method);
        instance.setParentInstance(luminaryInstance);
        assertEquals(method, instance.getMethod());
        assertEquals(INebularService.class, instance.getRegisteredType());
        assertEquals(LuminaryFactory.class, instance.getDependencies().get(0));
        assertEquals(IGalaxyRepository.class, instance.getDependencies().get(1));
        assertEquals(NebularRepository.class, instance.getDependencies().get(2));
    }

}