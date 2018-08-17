package org.everest.dic.test.controller;

import dic.AutoInject;
import org.everest.core.dic.decorator.AutoWired;
import org.everest.core.dic.decorator.Bean;
import org.everest.core.dic.decorator.Resolve;
import org.everest.decorator.Component;
import org.everest.decorator.Instance;
import org.everest.dic.test.component.*;
import org.everest.dic.test.service.GalaxyService;
import org.everest.dic.test.service.IGalaxyService;
import org.everest.dic.test.service.PlanetService;


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
}
