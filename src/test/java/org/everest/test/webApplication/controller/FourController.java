package org.everest.test.webApplication.controller;

import org.everest.mvc.decorator.HttpMapping;
import org.everest.mvc.httpContext.decorator.HttpController;
import org.everest.mvc.httpContext.decorator.Path;

@HttpController
@HttpMapping
public class FourController {

    @Path(route = "/app/")
    public void init(){
        System.out.println("dfgf");
    }
}
