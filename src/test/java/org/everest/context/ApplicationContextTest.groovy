package org.everest.context

import org.junit.Before
import org.junit.Test

public class ApplicationContextTest{
    private ApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ApplicationContext();
        applicationContext.initialize();
    }

    @Test
    public void testInitialize() {
        System.out.println("Controller: " + applicationContext.getControllers());
        System.out.println("Filter: " + applicationContext.getRequestFilters().size())
        System.out.println("Type Variable resolver: " + applicationContext.getVariableResolverByTypes())
        System.out.println("Annotation Variable resolver: " + applicationContext.getVariableResolverByAnnotations())
    }
}
