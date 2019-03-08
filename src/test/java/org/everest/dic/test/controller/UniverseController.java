package org.everest.dic.test.controller;

import org.everest.core.dic.Container;
import org.everest.core.dic.decorator.AfterContainerInitilized;
import org.everest.core.dic.decorator.AutoWired;
import org.everest.core.dic.decorator.Resolve;
import org.everest.dic.test.component.BlackHoleComponent;
import org.everest.dic.test.component.HeapComponent;
import org.everest.dic.test.component.NebularComponent;
import org.everest.dic.test.service.IGalaxyService;


public class UniverseController extends LuminaryController {
    @Resolve
    IGalaxyService galaxyService;
    @AutoWired
    BlackHoleComponent blackHoleComponent;

    public UniverseController(HeapComponent heapComponent, NebularComponent nebularComponent){
        System.out.println(heapComponent);
        System.out.println(nebularComponent);
    }

    public void afterInitialization(){
        System.out.println(galaxyService);
        System.out.println(blackHoleComponent);
        System.out.println(pulsarComponent);
        System.out.println("initilisation terminée");
    }

    @AfterContainerInitilized
    public void after(Container container){
        System.out.println("Il y'a " + container.getInstanceList().size() + " instance dans le container ");
    }
}
