package org.everest.core.dic.service;

import org.everest.utils.ReflexionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class ContainerServiceTest {
    ContainerService containerService = new ContainerService();
    private Logger logger = LoggerFactory.getLogger(ContainerServiceTest.class);
    private List<Class<? extends Annotation>> typesAnnotations = new ArrayList<>();
    private List<Class<? extends Annotation>> methodAnnotations = new ArrayList<>();
    private List<Class> classList = new ArrayList<>();
    private List<String> packageNames = new ArrayList<>();

    @BeforeEach
    public void setUp() throws Exception {
        packageNames.add("org.everest.dic.test.builder");
        packageNames.add("org.everest.dic.test.component");
        packageNames.add("org.everest.dic.test.controller");
        packageNames.add("org.everest.dic.test.repository");
        classList = ReflexionUtils.getClasses(packageNames);
    }
}