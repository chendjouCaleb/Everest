package org.everest.test.webApplication.controller;

import org.everest.mvc.httpContext.Controller;
import org.everest.mvc.httpContext.decorator.HttpController;
import org.everest.mvc.httpContext.HttpMethod;
import org.everest.mvc.httpContext.decorator.Path;

@HttpController
public class ControllerOne extends Controller {
    @Path(route = "home", name = "home")
    public void home(Integer id){

    }

    @Path(route = "/edit", name = "edit", httpMethod = HttpMethod.POST)
    public void edit(){

    }
    @Path(route = "/edit/:id", name = "editIdGET")
    public void editGET(){

    }

    @Path(route = "/edit/:id", name = "editId", httpMethod = HttpMethod.POST)
    public void editWithId(){

    }
}
