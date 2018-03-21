package org.everest.appContext;

import org.everest.context.ApplicationContext;
import org.everest.decorator.Factory;
import org.everest.packageWithFactory.ClassOne;
import org.everest.packageWithFactory.FactoryClass;
import org.everest.packageWithFactory.SimpleAnnotation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RetrieverTest {

    ApplicationContext context = new ApplicationContext();

    @Before
    public void init(){

        context.addByPackage("org.everest.packageWithFactory");
        context.initialize();
    }

    @Test
    public void findInstanceByAnnotationTest(){
        Assert.assertEquals(2, context.findInstanceByAnnotation(SimpleAnnotation.class).size());
        Assert.assertEquals(FactoryClass.class, context.findInstanceByAnnotation(Factory.class).get(0).getClass());
    }

    @Test
    public void retrieveAndCheckFieldDependencies(){
        ClassOne classOne = context.getInstance(ClassOne.class);
        Assert.assertNotNull(classOne);
        Assert.assertNotNull(classOne.getClassSix());
    }

    @Test
    public void retrieveAndCheckFieldDependenciesByInterface(){
        ClassOne classOne = context.getInstance(ClassOne.class);
        Assert.assertNotNull(classOne);
        Assert.assertNotNull(classOne.getClassSeven());
        classOne.getClassSeven().print();
    }
}
