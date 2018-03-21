package org.everest.dic.test;

import org.everest.core.dic.Container;
import org.everest.core.dic.exception.ComponentCreationException;
import org.everest.service.EmailMessage;
import org.everest.service.Message;
import org.everest.service.SendMessage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AddComponentToContainerTest {
    private Container container;
    @Before
    public void CreateContainer(){
        container = new Container();
    }


    @Test
    public void AddMultipleSimpleInstance(){
        container.addInstance(new Message());
        container.addInstance(new SendMessage());
        container.addInstance(new EmailMessage());
        Assert.assertEquals(3, container.countInstances());
    }

    @Test
    public void AddInstancesWithKey(){
        container.addInstance(new Message(), "message");
        container.addInstance(new SendMessage(), "sendMessage");
        container.addInstance(new EmailMessage(), "EmailMessage");
        Assert.assertEquals(3, container.countInstances());
    }

    @Test
    public void TestIfOneInstanceDoesNotEraseAnotherInstance(){
        container.addInstance(new Message());
        container.addInstance(new Message());
        Assert.assertEquals(2, container.countInstances());
    }

    @Test(expected = ComponentCreationException.class)
    public void AddInstancesWithSameNameAndExpectException(){
        container.addInstance(new Message(), "message");
        container.addInstance(new SendMessage(), "message");
    }

    @Test(expected = ComponentCreationException.class)
    public void AddInstancesThatWillHaveSameNamesAndExpectException(){
        container.addInstance(new Message());
        container.addInstance(new SendMessage(), "message");
    }
}
