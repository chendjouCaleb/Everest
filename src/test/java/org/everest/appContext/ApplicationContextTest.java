package org.everest.appContext;

import org.everest.context.ApplicationContext;
import org.everest.context.classHandler.ComponentHandler;
import org.everest.context.classHandler.FactoryHandler;
import org.everest.context.classHandler.InstanceHandler;
import org.everest.service.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ApplicationContextTest {

    private ApplicationContext context;

    @Before
    public void init(){

        context = new ApplicationContext();
    }

    @Test
    public void addInstanceByObject(){
        context.addInstance(new StarCreator(), "starCreator");
        context.addInstance(new Message());
        context.initialize();
        Assert.assertEquals(2, context.countDependencies());
    }

    @Test
    public void addInstanceByClass(){
        context.addInstance(StarCreator.class, "starCreator");
        context.addInstance(Message.class);
        context.initialize();
        Assert.assertEquals(2, context.countDependencies());
        Assert.assertNotNull(context.getInstance(Message.class));
    }

    @Test
    public void addInstanceByFactory(){

    }

    @Test
    public void addInstanceBySimplePackage(){
        context.addByPackage("org.everest.simplePackage");
        context.initialize();
        context.printComponent();
        Assert.assertEquals(context.countDependencies(), 3);
    }

    @Test
    public void addInstanceBySimplePackageWithFactory(){
        context.addByPackage("org.everest.packageWithFactory");
        context.initialize();
        context.printComponent();
        Assert.assertEquals(context.countDependencies(), 8);
    }
}
