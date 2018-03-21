package org.everest.test.webApplication.controller;

import annotation.HttpController;
import annotation.Path;

@HttpController
public class ControllerFour {

    @Path(route = "/app/")
    public void init(){
        System.out.println("dfgf");
    }
}
