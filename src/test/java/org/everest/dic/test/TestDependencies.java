package org.everest.dic.test;

import org.everest.core.dic.Container;
import org.everest.core.dic.Instance;
import org.everest.core.dic.enumeration.Scope;
import org.everest.service.EmailMessage;
import org.everest.service.Message;
import org.everest.service.SendMessage;
import org.everest.service.UniversManager;
import org.everest.exception.InstanceNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDependencies {
    Container container;
    @Before
    public void CreateContainer(){
        container = new Container();
        container.addInstance(EmailMessage.class, null, Scope.SINGLETION);
        container.addInstance(new Message(), null);
        container.addInstance(new Message(), "nullMessage");
        container.addInstance(SendMessage.class, null, Scope.SINGLETION);
        container.resolveAllDependencies();
    }

    @Test
    public void resolveDependenciesWithExistingInstances(){
        container.resolveAllDependencies();
    }

    @Test(expected = InstanceNotFoundException.class)
    public void resolveDependenciesWithNonExistingInstances(){
        container.addInstance(new UniversManager());
        container.resolveAllDependencies();
    }
    @Test()
    public void checkFieldWithExistingDependencies(){
        Instance instance = container.getInstance("sendMessage");
        SendMessage sendMessage = (SendMessage) instance.getInstance();

        Assert.assertNotNull(sendMessage);
        Assert.assertNotNull(sendMessage.getMessage());
        Assert.assertNotNull(sendMessage.getEmailMessage());
    }


}
