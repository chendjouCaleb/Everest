package org.everest.service;

import org.everest.core.dic.decorator.AutoWired;

public class UniversManager {
    public void createPlanete(){
        System.out.println("Create planete");
    }

    @AutoWired
    private StarCreator starCreator;
}
