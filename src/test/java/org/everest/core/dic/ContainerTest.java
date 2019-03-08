package org.everest.core.dic;

import org.everest.service.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContainerTest {
    private Container container = new Container();
    private Logger logger = LoggerFactory.getLogger(ContainerTest.class);

    private List<Class<? extends Annotation>> typesAnnotations = new ArrayList<>();
    private List<Class<? extends Annotation>> methodAnnotations = new ArrayList<>();
    private List<String> packageNames = new ArrayList<>();

    @BeforeEach
    public void setUp() throws Exception {
        container.addPackage("org.everest.dic.test.builder",
                "org.everest.dic.test.repository", "org.everest.dic.test.component",
                "org.everest.dic.test.controller");
        container.initialize();
    }

    @Test
    public void initialize() {
       assertEquals(16, container.getInstanceList().size());
    }

    @Test
    public void addObjectWithKey(){
        container.addInstance(new Message(), "message");
        container.getInstance("message");
    }
    @Test
    public void printKey(){
        container.getInstanceList().forEach(instance -> logger.info("Type:[{}]; Key:[{}]", instance.getRegisteredType(), instance.getKey()));
    }
//    @Test(expected = IllegalArgumentException.class)
//    public void addAlready_Existing_Class_To_Container(){
//        container.addClass(GalaxyRepository.class);
//        container.initialize();
//    }

}