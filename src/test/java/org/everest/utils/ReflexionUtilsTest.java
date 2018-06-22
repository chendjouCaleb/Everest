package org.everest.utils;

import org.everest.mvc.httpContext.decorator.FilterMethod;
import org.everest.mvc.httpContext.decorator.Path;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class ReflexionUtilsTest {
    private Logger logger = LoggerFactory.getLogger(ReflexionUtilsTest.class);
    @Test
    public void findMethodByAnnotation() throws Exception {
        Method method = ReflexionUtils.findMethodByAnnotation(SimpleClass.class, FilterMethod.class);
        assertNotNull(method);
        logger.info("Method Name: {}", method.getName());
        assertEquals("methodOne", method.getName());
    }

    @Test
    public void findMethodByAnnotation_With_Wrong_Annotation() throws Exception {
        Method method = ReflexionUtils.findMethodByAnnotation(SimpleClass.class, Path.class);
        assertNull(method);
    }

    @Test
    public void getMethodWithName(){
        Method method = ReflexionUtils.getMethod(SimpleClass.class, "methodOne");
        assertNotNull(method);
    }

}