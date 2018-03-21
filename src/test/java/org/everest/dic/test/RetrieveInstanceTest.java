package org.everest.dic.test;

import org.everest.core.dic.Container;
import org.everest.core.dic.Instance;
import org.everest.service.*;
import org.everest.exception.InstanceNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RetrieveInstanceTest {
    private Container container;
    private EmailMessage em1 = new EmailMessage("chendjou");
    private EmailMessage em2 = new EmailMessage("Jane Doe");
    private EmailMessage em3 = new EmailMessage("John Doe");
    private Human human = new Women();

    @Before
    public void initializeContainer(){
        container = new Container();

        container.addInstance(em1);
        container.addInstance(em2);
        container.addInstance(em3, "emailMessage#3");
        container.addInstance(new Message());
        container.addInstance(human);
        container.addInstance(new SendSMS());
        container.addInstance(new Message(), "nullMessage");
        //container.addInstance(SendMessage.class, null, Scope.SINGLETION);
    }

    @Test(expected = InstanceNotFoundException.class)
    public void retrieveNotExistingInstanceByKey(){
        Assert.assertNotNull(container.getInstance("xyz"));
    }

     @Test(expected = InstanceNotFoundException.class)
    public void retrieveNotExistingInstanceByClass(){
        Assert.assertNotNull(container.getInstance(UniversManager.class));
    }

    @Test
    public void retrieveInstancesByKey(){
        container.getInstance("emailMessage");
        container.getInstance("emailMessage#1");
        container.getInstance("emailMessage#3");
    }

    @Test
    public void retrieveInstanceAndCheckIdentity(){
        Instance instance1 = container.getInstance("emailMessage");
        Instance instance2 = container.getInstance("emailMessage#1");
        Instance instance3 = container.getInstance("emailMessage#3");

        Assert.assertEquals(instance1.getInstance(), em1);
        Assert.assertEquals(instance2.getInstance(), em2);
        Assert.assertEquals(instance3.getInstance(), em3);
    }

    @Test
    public void retrieveInstanceByClass() {
        Instance instance = container.getInstance(EmailMessage.class);
        EmailMessage emailMessage = (EmailMessage) instance.getInstance();
        Assert.assertNotNull(emailMessage.getDestinator());
    }
    @Test
    public void retrieveInstanceByInterface(){
        Instance instance = container.getInstance(ISendMessage.class);
        Assert.assertNotNull(instance);
    }

    @Test
    public void retrieveInstanceBySuperClass(){
        Instance instance = container.getInstance(SendMessage.class);
        Instance instance1 = container.getInstance(Human.class);
        container.printAllComponent();
        Assert.assertNotNull(instance1);
        Assert.assertNotNull(instance);
    }
}
