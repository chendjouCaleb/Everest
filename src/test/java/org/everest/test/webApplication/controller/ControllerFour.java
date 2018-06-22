package org.everest.test.webApplication.controller;

import org.everest.mvc.httpContext.decorator.HttpController;
import org.everest.mvc.httpContext.decorator.Path;

@HttpController
public class ControllerFour {

    @Path(route = "/app/")
    public void init(){
        System.out.println("dfgf");
    }
}
