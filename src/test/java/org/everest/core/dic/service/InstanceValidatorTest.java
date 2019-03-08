package org.everest.core.dic.service;

import org.everest.core.dic.Instance;
import org.everest.core.dic.decorator.AutoWired;
import org.everest.dic.test.component.PulsarComponent;
import org.everest.dic.test.controller.LuminaryController;
import org.everest.dic.test.controller.UniverseController;
import org.everest.dic.test.repository.GalaxyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class InstanceValidatorTest {
    private InstanceBuilder instanceBuilder = new InstanceBuilder();
    private InstanceValidator instanceValidator = new InstanceValidator();
    private List<Instance> instances = new ArrayList<>();
    private List<Class> candidates = new ArrayList<>();

    @Test
    public void checkDependenciesAvailability_With_Independent_Class() {
        Instance galaxyRepositoryInstance = instanceBuilder.createInstance(GalaxyRepository.class);
        instances.add(galaxyRepositoryInstance);
        instanceValidator.checkDependenciesAvailability(instances);
    }
    @Test
     public void checkDependenciesAvailability_With_Dependent_Class_And_Her_Dependencies(){
        Instance luminaryControllerInstance = instanceBuilder.createInstance(LuminaryController.class);
        instances.add(luminaryControllerInstance);
        instances.add(instanceBuilder.createInstance(PulsarComponent.class));
        instanceValidator.checkDependenciesAvailability(instances);
    }

    public void checkDependenciesAvailability_With_Dependent_Class_Without_Her_Dependencies(){
        Instance  universeControllerInstance = instanceBuilder.createInstance(UniverseController.class);
        instances.add(universeControllerInstance);

        assertThrows(NoSuchElementException.class,
                () -> instanceValidator.checkDependenciesAvailability(instances));
    }

    @Test
    public void checkCircularDependencies_With_UnCircular_Dependencies() {
        Instance luminaryControllerInstance = instanceBuilder.createInstance(LuminaryController.class);
        Instance pulsarComponentInstance = instanceBuilder.createInstance(PulsarComponent.class);
        instances.add(luminaryControllerInstance);
        instances.add(pulsarComponentInstance);
        instanceValidator.checkCircularDependencies(instances);
    }

    @Test
    public void checkCircularDependencies_With_Circular_Dependencies() {
        Instance circularInstance1 = instanceBuilder.createInstance(Circular1.class);
        Instance circularInstance2 = instanceBuilder.createInstance(Circular2.class);
        instances.add(circularInstance1);
        instances.add(circularInstance2);

        assertThrows(IllegalArgumentException.class, () -> instanceValidator.checkCircularDependencies(instances));
    }

    @Test
    public void checkCircularDependencies_With_Circular_Same_Class() {
        Instance circularInstance = instanceBuilder.createInstance(Circular.class);
        instances.add(circularInstance);
        instanceValidator.checkCircularDependencies(instances);
        assertThrows(IllegalArgumentException.class, () -> instanceValidator.checkCircularDependencies(instances));
    }
}

class Circular{
    @AutoWired Circular circular;
}
class Circular1{
    @AutoWired Circular2 circular2;
}

class Circular2{
    @AutoWired Circular1 circular1;
}