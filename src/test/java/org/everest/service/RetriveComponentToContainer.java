package org.everest.service;

import org.everest.core.dic.Container;
import org.junit.jupiter.api.BeforeEach;

public class RetriveComponentToContainer {
    Container container;
    @BeforeEach
    public void initComponent(){
        container = new Container();
//        container.addComponentByFactory(FactoryClass.class);
//        container.addComponentByClass(SendMessage.class);
//        container.addComponentByClass(Message.class.getName());
    }

}
