package org.everest.core.dic.service;

import org.everest.dic.test.builder.LuminaryFactory;
import org.everest.dic.test.component.BlackHoleComponent;
import org.everest.dic.test.component.HeapComponent;
import org.everest.dic.test.component.NebularComponent;
import org.everest.dic.test.component.PulsarComponent;
import org.everest.dic.test.controller.UniverseController;
import org.everest.dic.test.repository.GalaxyRepository;
import org.everest.dic.test.repository.NebularRepository;
import org.everest.dic.test.service.GalaxyService;
import org.everest.dic.test.service.IGalaxyService;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.Assert.*;

public class DependencyInspectorTest {
    private DependencyInspector dependencyInspector = new DependencyInspector();
    @Test
    public void getDependentTypesByFields() throws Exception {
        List<Class> types = dependencyInspector.getDependentTypesByFields(UniverseController.class);
        assertEquals(3, types.size());

        assertEquals(IGalaxyService.class, types.get(0));
        assertEquals(BlackHoleComponent.class, types.get(1));
        assertEquals(PulsarComponent.class, types.get(2));
    }
    @Test
    public void getDependentTypesByController() throws NoSuchMethodException {
        Constructor constructor = UniverseController.class.getConstructor(HeapComponent.class, NebularComponent.class);
        List<Class> types = dependencyInspector.getDependentTypesByConstructor(constructor);
        assertEquals(HeapComponent.class, types.get(0));
        assertEquals(NebularComponent.class, types.get(1));
    }

    @Test
    public void getDependentTypesWithMethod() throws Exception {
        Method method = LuminaryFactory.class.getMethod("nebularService", GalaxyRepository.class, NebularRepository.class);
        List<Class> types = dependencyInspector.getDependentTypesByMethod(method);
        assertEquals(2, types.size());
        assertEquals(GalaxyRepository.class, types.get(0));
        assertEquals(NebularRepository.class, types.get(1));
    }

    @Test
    public void getDependenciesByType(){
        List<Class> type = dependencyInspector.getDependencyType(UniverseController.class);
        assertEquals(5, type.size());
        type.forEach(System.out::println);
    }

}