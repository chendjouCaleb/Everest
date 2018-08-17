package org.everest.core.dic;

import org.everest.core.dic.decorator.Bean;
import org.everest.core.dic.handler.ControllerFilter;
import org.everest.decorator.Component;
import org.everest.decorator.Factory;
import org.everest.decorator.Instance;
import org.everest.decorator.Repository;
import org.everest.utils.ReflexionUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class ContainerFilterTest {

    private Logger logger = LoggerFactory.getLogger(ContainerFilterTest.class);
    private List<Class> rawClasses = new ArrayList<>();
    private ContainerFilter containerFilter;

    @Before
    public void setUp() throws Exception {
        containerFilter = new ContainerFilter();
        rawClasses = ReflexionUtils.getClasses("org.everest.dic.test.builder","org.everest.dic.test.component",
                "org.everest.dic.test.controller","org.everest.dic.test.repository");
        containerFilter.addTypeAnnotation(org.everest.decorator.Instance.class);
        containerFilter.addTypeAnnotation(Factory.class);
        containerFilter.addTypeAnnotation(Repository.class);
        containerFilter.addTypeAnnotation(Component.class);

        containerFilter.addMethodAnnotation(Bean.class);
        containerFilter.addMethodAnnotation(Instance.class);

        containerFilter.addTypeFilters(new ControllerFilter());
        containerFilter.setClassForFilter(rawClasses);
        logger.info("class for filter: " + containerFilter.getClassForFilter().size());
    }
    @Test
    public void listClasses() {
        containerFilter.doClassFilter();
        assertEquals(15, containerFilter.getCandidates().size());
    }

    @Test
    public void listMethod() {
        containerFilter.doMethodFilter();
        assertEquals(3, containerFilter.getFoundedMethod().size());
        assertEquals(3, containerFilter.getMethodFoundedClass().size());
    }

    @Test
    public void listByAnnotations() {
        containerFilter.doAnnotationFilter();
        assertEquals(10, containerFilter.getAnnotatedFoundedClass().size());
    }

    @Test
    public void listByClassFilter() {
        containerFilter.doTypeFilterFilter();
        assertEquals(2, containerFilter.getFilteredFoundedClass().size());
    }

}