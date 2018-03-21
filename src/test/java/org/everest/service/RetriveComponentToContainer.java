package org.everest.service;

import org.everest.core.dic.Container;
import org.junit.Before;

public class RetriveComponentToContainer {
    Container container;
    @Before
    public void initComponent(){
        container = new Container();
//        container.addComponentByFactory(FactoryClass.class);
//        container.addComponentByClass(SendMessage.class);
//        container.addComponentByClass(Message.class.getName());
    }

}
