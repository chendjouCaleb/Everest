package org.everest.dic.test;

import dic.Container;
import org.junit.Assert;
import org.junit.Test;

public class MessageTest {

    @Test
    public void testSimpleMessage(){
        Message message = (Message) Container.getService(Message.class);
        Message message1 = (Message) Container.getService(Message.class);

        Assert.assertEquals(message, message1);
    }

    @Test
    public void testSendMessage(){
        String name = "bonjou.cvb";
        System.out.println("LENGTH: "  + name.split("\\.")[1]);

    }
}
